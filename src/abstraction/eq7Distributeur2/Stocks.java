package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;


import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur2Acteur implements IStocks{
	
	
	protected static int dureeDePeremption = 10;
	protected static double limiteStocks = 1000000000;
	protected static double prixStockage = 0.0000001;
	protected static double limiteEnTG = 9; // en pourcent
	
	
	protected HashMap<ChocolatDeMarque,Variable> stocksEnTG; // Stocks de chocolat en Tête de Gondole STOCK PAS COMPTE DANS LE STOCK GENERAL
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	private Distributeur2Acteur acteur;
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color TGColor = Color.CYAN;
		public Color alertColor = Color.RED;
		public Color addStockColor = Color.GREEN;
		public Color removeStockColor = Color.ORANGE;
		public Color infoColor = Color.YELLOW;
		public Color peremptionColor = Color.MAGENTA;
	
		
		
// fait par Elio Granger
// Initialise toutes les variables correctement. dont les doubles hashmap
		
	public Stocks(Distributeur2Acteur acteur) {
		this.acteur = acteur;
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		stocksEnTG = new HashMap<ChocolatDeMarque, Variable>();
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
//			System.out.println(chocoDeMarq.name());
				stocksParMarque.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +" [W&S]", acteur,0));
				acteur.journalStocks.ajouter(Journal.texteColore(infoColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
				if(nouveauChocoParEtape.get(0)==null) {
					HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
					Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
					nouveauChocoParEtape.put(0, Init);
				}
				else {
					HashMap<ChocolatDeMarque, Variable> Init = nouveauChocoParEtape.get(0);
					Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
					nouveauChocoParEtape.put(0, Init);
				}
		}
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			stocksEnTG.put(choco, new Variable("Stocks en TG de " + choco.name() +" [W&S]", acteur,0));
		}
	}
	
	
	
	// fait par Elio Granger
	// A chaque étape :
	// Initialise la Hashmap nouveauChocoParEtape pour l'étape actuelle avec une quantité égale à 0 pour chaque produit.
	// appelle les méthodes utiles : Peremption, cout de stockage, vérification des produits en TG
	
	public void next() {
		
		if(Filiere.LA_FILIERE.getEtape()==0) {
			for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
				if(!chocoDeMarq.name().equals(acteur.getChocoProduit().name())){
					this.ajouterChocolatDeMarque(chocoDeMarq, Filiere.LA_FILIERE.getVentes(chocoDeMarq,-24)*3);
					
					acteur.journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, "[AJOUT] " + Journal.doubleSur(Filiere.LA_FILIERE.getVentes(chocoDeMarq,-24)*3,2) + " de " + chocoDeMarq.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocoDeMarq).getValeur(),2) + " "));
					/*
					 * this.stocksParMarque.get(chocoDeMarq).ajouter(acteur,Filiere.LA_FILIERE.getVentes(chocoDeMarq,-24)*10);
					 * this.nouveauChocoParEtape.get(0).get(chocoDeMarq).ajouter(acteur,Filiere.LA_FILIERE.getVentes(chocoDeMarq,-24)*10);
					 */
				}
			}
			
		}
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			if(nouveauChocoParEtape.get(etape)==null) {
				HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
				Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
				nouveauChocoParEtape.put(etape, Init);
			}
			else {
				HashMap<ChocolatDeMarque, Variable> Init = nouveauChocoParEtape.get(etape);
				Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
				nouveauChocoParEtape.put(etape, Init);
			}
		}
		this.jeterChocolatPerime();
		this.CoutStockage();
		this.majTGSuiteASuppression();
	}


	
	// fait par Elio Granger
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		return this.stocksParMarque.get(chocolatDeMarque).getValeur();
	}
	
	
	// fait par Elio Granger
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, int etape) {
		return this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).getValeur();
	}

	
	
	// fait par Elio Granger
	// AJOUTE LA QUANTITE DE CHOCOLAT A LETAPE ACTUELLE ET DANS LE STOCK DE MARQUES
	
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		double limiteDeStocks = getParametre("limiteStocks");
		if (getQuantiteTotaleStocks()+qte<=limiteDeStocks*100) {
			this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, "[AJOUT] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
			int etape = Filiere.LA_FILIERE.getEtape();
			this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(acteur, qte);
			}else {acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[LIMITE STOCK ATTEINTE] Impossible d'ajouter : " + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()));
		}
	}
	
	
	
	// fait par Elio Granger
	// Ajoute le chocolat dans la quantité donnée en TG
	// vérifie notamment que l'opération est possible avec la méthode verifNouveauChocoEnTG(chocolatDeMarque, qte).
	
	public void ajouterChocolatEnTG(ChocolatDeMarque chocolatDeMarque, double qte) { // Caractérise juste une partie du stock de TG
		double quantitePossibleDAjouter = qtePossibleTG(chocolatDeMarque);
		if(this.verifNouveauChocoEnTG(chocolatDeMarque, qte)) {
			//System.out.println("OUI c'est possible, qte possible a ajouter "+ quantitePossibleDAjouter +" qte voulue : " + qte);
			this.stocksEnTG.get(chocolatDeMarque).ajouter(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(TGColor, Color.BLACK, "[TG - AJOUT] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + " est passé en TG, [TOTAL] : " + Journal.doubleSur(this.getQuantiteTotaleEnTG()/this.getQuantiteTotaleStocks(),5) + " De Choco en TG"));
		}else {
			//System.out.println("NON IMPOSSIBLE, qte possible à ajouter : " + quantitePossibleDAjouter +" voulue : "+qte);
			this.stocksEnTG.get(chocolatDeMarque).ajouter(acteur,quantitePossibleDAjouter);
			acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[TG - PROBLEME] l'ajout de "+ Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + " dépasserait la limite de TG,"
					+ "ajout de "+ Journal.doubleSur(quantitePossibleDAjouter,2) + " en TG et " + Journal.doubleSur(qte-quantitePossibleDAjouter,2) + " dans les stocks classiques"));
			this.ajouterChocolatDeMarque(chocolatDeMarque, qte-quantitePossibleDAjouter);
		}
		
	}
	
	
	// fait par Elio Granger
	// donne la quantité totale de chocolat en stocks
	
	public double getQuantiteTotaleStocks() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksParMarque.get(chocoDM).getValeur();
		}return res;
	}
	
	
	
	// fait par Elio Granger
	// donne la quantité totale en stocks pour une étape donnée
	
	public double getQuantiteTotaleStockEtape(int etape) {
		double res = 0.0;
		for(Variable qte : this.nouveauChocoParEtape.get(etape).values()) {
			res+= qte.getValeur();
		}return res;
		
	}
	
	
	
	// fait par Elio Granger
	// returns yes si on peut mettre une telle quantité d'un tel chocolat en TG
	
	public boolean verifNouveauChocoEnTG(ChocolatDeMarque choco, double qte) {
		return qtePossibleTG(choco)>qte;
	}
	
	
	
	// fait par Elio Granger
	// donne la quantité totale en TG
	
	public double getQuantiteTotaleEnTG() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksEnTG.get(chocoDM).getValeur();
		}return res;
	}
	
	
	// fait par Elio Granger
	// donne la quantité en TG pour un chocolat
	public double getQuantiteChocoEnTG(ChocolatDeMarque choco) {
		return this.stocksEnTG.get(choco).getValeur();
	}
	
	
	// fait par Elio Granger
	public void setQuantiteChocoEnTG(ChocolatDeMarque choco,double qte) {
		this.stocksEnTG.get(choco).setValeur(acteur, qte);
	}
	public void supprQuantiteChocoEnTG(ChocolatDeMarque choco,double qte) {
		this.stocksEnTG.get(choco).retirer(acteur, qte);
	}
	
	
	// fait par Elio Granger
	// Renvoie la quantité possible à rajouter en TG, utile lors de la création de contrats d'achat
	
	public double qtePossibleTG(ChocolatDeMarque choco){
		double qteDejaEnTG = getQuantiteChocoEnTG(choco);
		double limiteTG = getParametre("limiteEnTG");
		double qteDeChocoAvant = getStockChocolatDeMarque(choco);
		return (qteDeChocoAvant*limiteTG/100)-qteDejaEnTG;
	}
	
	
	// fait par Elio Granger
	// Vérifie que la quantité en TG de chaque produit respecte bien le pourcentage imposé
	// si ce n'est pas le cas, des réajustements sont faits
	
	public void majTGSuiteASuppression() {
		double limiteTG = this.getParametre("limiteEnTG");
		for(ChocolatDeMarque choco : acteur.getCatalogue()) {
			if(this.stocksEnTG.keySet().contains(choco)) {
				double qteTG = getQuantiteChocoEnTG(choco);
				double qteStocksGeneral = getStockChocolatDeMarque(choco);
				if(qteTG/qteStocksGeneral>=limiteTG/100) {
					double qteAEnlever = qteTG-(limiteTG*qteStocksGeneral/100);
					this.stocksEnTG.get(choco).retirer(acteur, qteAEnlever);
					ajouterChocolatDeMarque(choco, qteAEnlever);
					acteur.journalStocks.ajouter(Journal.texteColore(Color.RED, Color.BLACK, "[REAJUSTEMENT TG] " + Journal.doubleSur(qteAEnlever,2) + " de " + choco.name() + "ont été enlevés de TG, [TOTAL] : " + Journal.doubleSur(acteur.quantiteEnVenteTG(choco),2) + " De Choco en TG"));
				}
			}
			
		}
	
	}
		
	
	
	// fait par Elio Granger
	//ON SUPPRIME DU CHOCOLAT EN COMMENCANT PAR LE PLUS VIEUX AYANT ETE RECU.
	// Si le chocolat est en TG, on considère que c'est le produit en TG qui a été acheté
	
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		int etape = Filiere.LA_FILIERE.getEtape();
		if (this.getStockChocolatDeMarque(chocolatDeMarque)>=qte) { //on vérifie déjà que l'action soit possible
			double qteEnTGAvantSuppression = getQuantiteChocoEnTG(chocolatDeMarque);
			if(qteEnTGAvantSuppression!=0) { // on regarde la quantité de ce produit présente avant l'action de suppresion
				acteur.journalStocks.ajouter(Journal.texteColore(removeStockColor, Color.BLACK, "[TG - SUPPRESSION] " + Journal.doubleSur(qteEnTGAvantSuppression,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(getQuantiteChocoEnTG(chocolatDeMarque)/getStockChocolatDeMarque(chocolatDeMarque),4) + " "));
				this.stocksEnTG.get(chocolatDeMarque).retirer(acteur, qte);
			}
			if(this.getQuantiteChocoEnTG(chocolatDeMarque)<0) { // on dit que lorsqu'un chocolat est vendu, c'est qu'il était d'abord en TG
				this.setQuantiteChocoEnTG(chocolatDeMarque, 0.0);
			}
			enleverParEtape(chocolatDeMarque,qte-qteEnTGAvantSuppression); // voir juste en dessous (boucle sur les étapes pour supprimer petit à petit)
			this.stocksParMarque.get(chocolatDeMarque).retirer(acteur, qte-qteEnTGAvantSuppression);
			acteur.journalStocks.ajouter(Journal.texteColore(removeStockColor, Color.BLACK, "[SUPPRESSION] " + Journal.doubleSur(qte-qteEnTGAvantSuppression,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
			this.majTGSuiteASuppression();
		}else {
			acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[PAS ASSEZ DE STOCKS] Impossible de supprimer" + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()+ " à l'étape " + Journal.entierSur6(etape)));
		}
	}
	
	
	
	// fait par Elio Granger et Arnaud Berger
	// Parcourt toutes les étapes dans l'ordre croissant pour supprimer une quantité de chocolat donnée
	
	public void enleverParEtape(ChocolatDeMarque chocolatDeMarque,double qte ) {
		int etape = Filiere.LA_FILIERE.getEtape();
		for(int i=0; i<=etape; i++){// on prend les étapes dans l'odre.
			double quantiteAvantSuppression = this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).getValeur();
			if(quantiteAvantSuppression!=0.0) {
				if(quantiteAvantSuppression>=qte) { //c'est bon, on peut enlever la quantité souhaitée.
					this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).retirer(acteur, qte);
				}else { //sinon, c'est que la quanité dispo est inférieure à celle qu'on veut enlever.
				this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).ajouter(acteur, -quantiteAvantSuppression); // on enleve ce qu'on peut
				enleverParEtape(chocolatDeMarque, qte-quantiteAvantSuppression); //on recommence avec ce qu'il reste à enlever
				}
			}
		}
	}


	
	// fait par Elio Granger et Arnaud Berger
	// méthode qui supprime le chocolat périmé s
	
	public void jeterChocolatPerime() {
		int etape = Filiere.LA_FILIERE.getEtape();
		int dureePeremption = (int)this.getParametre("dureeDePeremption");
		int etapeImpactee = etape-dureePeremption;
		if (etape>=dureePeremption) {
			for (ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
				if(this.nouveauChocoParEtape.get(etapeImpactee).containsKey(chocoDM)) {
				double valeurRecue = this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).getValeur();
					if(valeurRecue!=0.0) {
						acteur.journalStocks.ajouter(Journal.texteColore(peremptionColor,Color.BLACK,"[PEREMPTION] " + Journal.doubleSur(valeurRecue,2) + " de " + chocoDM.name() +" datants de l'étape " + Journal.entierSur6(etapeImpactee) + " ont été jetés"));
						this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).retirer(acteur, valeurRecue);
						if(this.stocksParMarque.get(chocoDM).getValeur()>valeurRecue) {
						this.stocksParMarque.get(chocoDM).retirer(acteur, valeurRecue);
						}else {
							this.stocksParMarque.get(chocoDM).setValeur(acteur, 0.0);
						}
						if(this.getQuantiteChocoEnTG(chocoDM)>this.getStockChocolatDeMarque(chocoDM)) {
							this.setQuantiteChocoEnTG(chocoDM, this.getStockChocolatDeMarque(chocoDM));
						}
					}
				}
			}this.majTGSuiteASuppression();
		}
		//this.nouveauChocoParEtape.remove(etapeImpactee);
	}

	
	
	
	// fait par Elio Granger et Arnaud Berger
	// méthode qui calcule et demande une déduction du coût de stockage du compte bancaire
	
	public void CoutStockage() {
		int etape = Filiere.LA_FILIERE.getEtape();
		double prixDeStockage = this.getParametre("prixStockage");
		if(etape!=0 && etape!=1) {
			double cout = this.getQuantiteTotaleStockEtape(etape-1) * prixDeStockage;
			// PARTIE OU ON ENLEVE DE L'ARGENT DE NOTRE COMPTE BANCAIRE, A CODER Filiere.LA_FILIERE.getBanque();
			if(cout>0) {
		acteur.deduireUneSomme(cout);
			}
		}
	}
	
	
	// fait par Elio Granger
	// méthode qui renvoie la valeur du parametre recherché
	
	public double getParametre(String indicateur){
		double indicateurV = 0.0;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals(indicateur)) {
				indicateurV = var.getValeur();
			}
		}return indicateurV;
	}
}
