package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;


import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur2Acteur implements IStocks{
	
	
	protected static int dureeDePeremption = 6;
	protected static double limiteStocks = 1000000000;
	protected static double prixStockage = 0.0000001;
	protected static double limiteEnTG = 9.5; // en pourcent
	
	
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
	

	public Stocks(Distributeur2Acteur acteur) {
		this.acteur = acteur;
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		stocksEnTG = new HashMap<ChocolatDeMarque, Variable>();		
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			stocksParMarque.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +" [W&S]", acteur,0));
			acteur.journalStocks.ajouter(Journal.texteColore(infoColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
			HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
			Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
			nouveauChocoParEtape.put(0, Init);
		}
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			stocksEnTG.put(choco, new Variable("Stocks en TG de " + choco.name() +" [W&S]", acteur,0));
		}
	}		
	
	public void next() {
		HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
		}
		nouveauChocoParEtape.put(etape, Init);
		this.jeterChocolatPerime();
		this.CoutStockage();
		this.majTGSuiteASuppression();
	}


	@Override
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		return this.stocksParMarque.get(chocolatDeMarque).getValeur();
	}
	
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, int etape) {
		return this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).getValeur();
	}


	// AJOUTE LA QUANTITE DE CHOCOLAT A LETAPE ACTUELLE ET DANS LE STOCK DE MARQUES
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		double limiteDeStocks = limiteStocks;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals("limiteStocks")) {
				limiteDeStocks = var.getValeur();
			}
		}
		if (getQuantiteTotaleStocks()+qte<=limiteDeStocks) {
			this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, "[AJOUT] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
			int etape = Filiere.LA_FILIERE.getEtape();
			this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(acteur, qte);
			}else {acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[LIMITE STOCK ATTEINTE] Impossible d'ajouter : " + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()));
		}
	}
	
	
	public void ajouterChocolatEnTG(ChocolatDeMarque chocolatDeMarque, double qte) { // Caractérise juste une partie du stock de TG
		if(this.verifNouveauChocoEnTG(chocolatDeMarque, qte)) {
		this.stocksEnTG.get(chocolatDeMarque).ajouter(acteur, qte);
		acteur.journalStocks.ajouter(Journal.texteColore(TGColor, Color.BLACK, "[TG] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + "Est passé en TG, [TOTAL] : " + Journal.doubleSur(this.getQuantiteTotaleEnTG()/this.getQuantiteTotaleStocks(),5) + " De Choco en TG"));
		}
	}
	
	public double getQuantiteTotaleStocks() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksParMarque.get(chocoDM).getValeur();
		}return res;
	}
	
	
	public double getQuantiteTotaleStockEtape(int etape) {
		double res = 0.0;
		for(Variable qte : this.nouveauChocoParEtape.get(etape).values()) {
			res+= qte.getValeur();
		}return res;
		
	}
	
	// returns yes si on peut mettre une telle quantité d'un tel chocolat en TG
	public boolean verifNouveauChocoEnTG(ChocolatDeMarque choco, double qte) {
		double limiteTG = limiteEnTG;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals("limiteEnTG")) {
				limiteTG = var.getValeur();
			}
		}
		return (acteur.quantiteEnVenteTG(choco)+qte)/(acteur.quantiteEnVente(choco)+qte)<limiteTG;
	}
	
	
	public double getQuantiteTotaleEnTG() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksEnTG.get(chocoDM).getValeur();
		}return res;
	}
	
	public double getQuantiteChocoEnTG(ChocolatDeMarque choco) {
		return this.stocksEnTG.get(choco).getValeur();
	}
	
	public void setQuantiteChocoEnTG(ChocolatDeMarque choco,double qte) {
		this.stocksEnTG.get(choco).setValeur(acteur, qte);
	}
	public void supprQuantiteChocoEnTG(ChocolatDeMarque choco,double qte) {
		this.stocksEnTG.get(choco).retirer(acteur, qte);
	}
	
	public double qtePossibleTG(ChocolatDeMarque choco){
		return 0.0;
	}
	
	public void majTGSuiteASuppression() {
		double limiteTG = this.getParametre("limiteEnTG");
		for(ChocolatDeMarque choco : acteur.getCatalogue()) {
			if(this.stocksEnTG.containsKey(choco)) {

				double qteTG = acteur.quantiteEnVenteTG(choco);
				double qteStocksGeneral = acteur.quantiteEnVente(choco);
				if(qteTG/qteStocksGeneral>=limiteTG) {
					double qteAEnlever = qteTG-(limiteTG*qteStocksGeneral);
					this.stocksEnTG.get(choco).retirer(acteur, qteAEnlever);
					acteur.journalStocks.ajouter(Journal.texteColore(Color.RED, Color.BLACK, "[REAJUSTEMENT TG] " + Journal.doubleSur(qteAEnlever,2) + " de " + choco.name() + "ont été enlevés de TG, [TOTAL] : " + Journal.doubleSur(acteur.quantiteEnVenteTG(choco),2) + " De Choco en TG"));
				}
				
			}
			
		}
	
	}
		
//"Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]" --> Nom d'une variable de chocolat à une étape donnée (si besoin d'y accéder)
	
	
	//ON SUPPRIME DU CHOCOLAT EN COMMENCANT PAR LE PLUS VIEUX AYANT ETE RECU.

	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		int etape = Filiere.LA_FILIERE.getEtape();
		if (this.getStockChocolatDeMarque(chocolatDeMarque)>qte) { //on vérifie déjà que l'action soit possible
			if(this.stocksEnTG.get(chocolatDeMarque).getValeur()!=0) {
			this.stocksEnTG.get(chocolatDeMarque).setValeur(acteur, acteur.quantiteEnVente(chocolatDeMarque)*(this.getParametre("limiteEnTG")-0.01)/100);
			}
			if(this.getQuantiteChocoEnTG(chocolatDeMarque)<0) { // on dit que lorsqu'un chocolat est vendu, c'est qu'il était d'abord en TG
				this.setQuantiteChocoEnTG(chocolatDeMarque, 0.0);
			}
			enleverParEtape(chocolatDeMarque,qte); // voir juste en dessous (boucle sur les étapes pour supprimer petit à petit)
			this.stocksParMarque.get(chocolatDeMarque).retirer(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(removeStockColor, Color.BLACK, "[SUPPRESSION] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
			this.majTGSuiteASuppression();
		}else {
			acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[PAS ASSEZ DE STOCKS] Impossible de supprimer" + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()+ " à l'étape " + Journal.entierSur6(etape)));
		}
	}
	
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


	@Override
	public void jeterChocolatPerime() {
		int etape = Filiere.LA_FILIERE.getEtape();
		int dureePeremption = (int)this.getParametre("dureeDePeremption");
		int etapeImpactee = etape-dureePeremption;
		if (etape>=dureePeremption) {
			for (ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
				if(this.nouveauChocoParEtape.get(etapeImpactee).containsKey(chocoDM)) {
				double valeurRecue = this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).getValeur();
				acteur.journalStocks.ajouter(Journal.texteColore(peremptionColor,Color.BLACK,"[PEREMPTION] " + Journal.doubleSur(valeurRecue,2) + " de " + chocoDM.name() +" datants de l'étape " + Journal.entierSur6(etapeImpactee) + " ont été jetés"));
				this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).retirer(acteur, valeurRecue);
				this.stocksParMarque.get(chocoDM).retirer(acteur, valeurRecue);
				if(this.getQuantiteChocoEnTG(chocoDM)>this.getStockChocolatDeMarque(chocoDM)) {
					this.setQuantiteChocoEnTG(chocoDM, this.getStockChocolatDeMarque(chocoDM));
				}
				}
			}this.majTGSuiteASuppression();
		}
		//this.nouveauChocoParEtape.remove(etapeImpactee);
	}

	//A LIER AVEC LE COMPTE BANCAIRE
	@Override
	public void CoutStockage() {
		int etape = Filiere.LA_FILIERE.getEtape();
		double prixDeStockage = this.getParametre("limiteStocks");
		if(etape!=0 && etape!=1) {
			double cout = this.getQuantiteTotaleStockEtape(etape-1) * prixDeStockage;
			// PARTIE OU ON ENLEVE DE L'ARGENT DE NOTRE COMPTE BANCAIRE, A CODER Filiere.LA_FILIERE.getBanque();
			if(cout>0) {
		acteur.deduireUneSomme(cout);
			}
		}
	}
	
	public double getParametre(String indicateur){
		double indicateurV = 0.0;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals(indicateur)) {
				indicateurV = var.getValeur();
			}
		}return indicateurV;
	}
}
