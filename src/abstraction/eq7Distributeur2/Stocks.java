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
	protected static double prixStockage = 10;
	protected static double limiteEnTG = 10; // en pourcent
	
	
	protected HashMap<ChocolatDeMarque,Variable> stocksEnTG; // Stocks de chocolat en Tête de Gondole STOCK PAS COMPTE DANS LE STOCK GENERAL
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	private Distributeur2 acteur;
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color TGColor = Color.CYAN;
		public Color alertColor = Color.RED;
		public Color addStockColor = Color.GREEN;
		public Color removeStockColor = Color.ORANGE;
		public Color infoColor = Color.YELLOW;
		public Color peremptionColor = Color.MAGENTA;
	

	public Stocks(Distributeur2 acteur) {
		this.acteur = acteur;
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		stocksEnTG = new HashMap<ChocolatDeMarque, Variable>();		
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			stocksParMarque.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +" [W&S]", acteur,0));
			stocksEnTG.put(chocoDeMarq, new Variable("Stocks en TG de " + chocoDeMarq.name() +" [W&S]", acteur,0));
			acteur.journalStocks.ajouter(Journal.texteColore(infoColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
			//chocolatRecuEtape.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name() + "Recu",acteur,0));
			HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
			Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
			nouveauChocoParEtape.put(0, Init);
		}
	}		
	
	public void next() {
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			int etape = Filiere.LA_FILIERE.getEtape();
			HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
			Init.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]", acteur,0));
			nouveauChocoParEtape.put(etape, Init);
			jeterChocolatPerime();
			
		}
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
			System.out.println(this.nouveauChocoParEtape.get(etape)+" "+this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque));
			this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(acteur, qte);
			}else {acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[LIMITE STOCK ATTEINTE] Impossible d'ajouter : " + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()));
		}
	}
	
	
	public void ajouterChocolatEnTG(ChocolatDeMarque chocolatDeMarque, double qte) { // Caractérise juste une partie du stock de TG
		this.stocksEnTG.get(chocolatDeMarque).ajouter(acteur, qte);
		acteur.journalStocks.ajouter(Journal.texteColore(TGColor, Color.BLACK, "[TG] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + "Est passé en TG, [TOTAL] : " + Journal.doubleSur(this.getQuantiteTotaleEnTG()/this.getQuantiteTotaleStocks(),2) + " De Choco en TG"));
	}
	
	public double getQuantiteTotaleStocks() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksParMarque.get(chocoDM).getValeur();
		}return res;
	}
	
	
	public double getQuantiteTotaleStockEtape(int etape) {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.nouveauChocoParEtape.get(etape).get(chocoDM).getValeur();
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
		return (this.getQuantiteTotaleEnTG()+qte)/(this.getQuantiteTotaleStocks()+qte)<=limiteTG;
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
		
//"Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]" --> Nom d'une variable de chocolat à une étape donnée (si besoin d'y accéder)
	
	
	//ON SUPPRIME DU CHOCOLAT EN COMMENCANT PAR LE PLUS VIEUX AYANT ETE RECU.

	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		int etape = Filiere.LA_FILIERE.getEtape();
		if (getStockChocolatDeMarque(chocolatDeMarque)>qte) { //on vérifie déjà que l'action soit possible
			this.stocksEnTG.get(chocolatDeMarque).retirer(acteur, qte);
			if(this.getQuantiteChocoEnTG(chocolatDeMarque)<0) { // on dit que lorsqu'un chocolat est vendu, c'est qu'il était d'abord en TG
				this.setQuantiteChocoEnTG(chocolatDeMarque, 0.0);
			}
			for(int i=0; i<=etape; i++){// on prend les étapes dans l'odre.
				double quantiteAvantSuppression = this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).getValeur();
				if(quantiteAvantSuppression!=0.0) {
					if(quantiteAvantSuppression>=qte) { //c'est bon, on peut enlever la quantité souhaitée.
						this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).retirer(acteur, qte);
					}else { //sinon, c'est que la quanité dispo est inférieure à celle qu'on veut enlever.
					this.nouveauChocoParEtape.get(i).get(chocolatDeMarque).ajouter(acteur, -quantiteAvantSuppression); // on enleve ce qu'on peut
					supprimerChocolatDeMarque(chocolatDeMarque, qte-quantiteAvantSuppression); //on recommence avec ce qu'il reste à enlever
					}
				}
			}
			this.stocksParMarque.get(chocolatDeMarque).retirer(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(removeStockColor, Color.BLACK, "[SUPPRESSION] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
		}else {
			acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[PAS ASSEZ DE STOCKS] Impossible de supprimer" + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()+ " à l'étape " + Journal.entierSur6(etape)));
		}
	}


	@Override
	public void jeterChocolatPerime() {
		int etape = Filiere.LA_FILIERE.getEtape();
		int dureePeremption = dureeDePeremption;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals("dureeDePeremption")) {
				dureePeremption = (int)var.getValeur();
			}
		}
		int etapeImpactee = etape-dureePeremption;
		if (etape>=dureePeremption) {
			for (ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
				double valeurRecue = this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).getValeur();
				acteur.journalStocks.ajouter(Journal.texteColore(peremptionColor,Color.BLACK,"[PEREMPTION] " + Journal.doubleSur(valeurRecue,2) + " de " + chocoDM.name() +" datants de l'étape " + Journal.entierSur6(etapeImpactee) + " ont été jetés"));
				this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).retirer(acteur, valeurRecue);
				this.stocksParMarque.get(chocoDM).retirer(acteur, valeurRecue);
				if(this.getQuantiteChocoEnTG(chocoDM)>this.getStockChocolatDeMarque(chocoDM)) {
					this.setQuantiteChocoEnTG(chocoDM, this.getStockChocolatDeMarque(chocoDM));
				}
			}
		}
	}

	//A LIER AVEC LE COMPTE BANCAIRE
	@Override
	public void CoutStockage() {
		int etape = Filiere.LA_FILIERE.getEtape();
		double prixDeStockage = prixStockage;
		for(Variable var : acteur.getParametres()) {
			if (var.getNom().equals("prixStockage")) {
				prixDeStockage = var.getValeur();
			}
		}
		double cout = this.getQuantiteTotaleStockEtape(etape) * prixDeStockage;
		// PARTIE OU ON ENLEVE DE L'ARGENT DE NOTRE COMPTE BANCAIRE, A CODER Filiere.LA_FILIERE.getBanque();
		acteur.notificationOperationBancaire(cout);
	}
}
