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
	
	//private HashMap<Chocolat, Variable> stocksParChocolat; //Stocks de chocolat par type
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	//protected HashMap<ChocolatDeMarque, Variable> chocolatRecuEtape; // Chocolat recu à chaque étape
	private Distributeur2 acteur;
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color metaColor = Color.CYAN;
		public Color alertColor = Color.RED;
		public Color addStockColor = Color.GREEN;
		public Color removeStockColor = Color.ORANGE;
		public Color descriptionColor = Color.YELLOW;
		public Color peremptionColor = Color.MAGENTA;
	

	public Stocks(Distributeur2 acteur) {
		this.acteur = acteur;
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		//chocolatRecuEtape = new HashMap<ChocolatDeMarque, Variable>();		
		int etape = Filiere.LA_FILIERE.getEtape();
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			stocksParMarque.put(chocoDeMarq, new Variable("Stocks de " + chocoDeMarq.name() +" [W&S]", acteur,0));
			acteur.journalStocks.ajouter(Journal.texteColore(metaColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
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
		if (getQuantiteTotaleStocks()+qte<=limiteStocks) {
			this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, qte);
			acteur.journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, "[AJOUT] " + Journal.doubleSur(qte,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
			int etape = Filiere.LA_FILIERE.getEtape();
			this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(acteur, qte);
			}else {acteur.journalStocks.ajouter(Journal.texteColore(alertColor, Color.BLACK, "[LIMITE STOCK ATTEINTE] Impossible d'ajouter : " + Journal.doubleSur(qte,2) +" de "+ chocolatDeMarque.name()));
		}
	}
	
	public double getQuantiteTotaleStocks() {
		double res = 0.0;
		for(ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
			res+= this.stocksParMarque.get(chocoDM).getValeur();
		}return res;
	}
		
//"Stocks de " + chocoDeMarq.name() +"/ Etape d'ajout: "+ etape+ " [W&S]" --> Nom d'une variable de chocolat à une étape donnée (si besoin d'y accéder)
	
	
	//ON SUPPRIME DU CHOCOLAT EN COMMENCANT PAR LE PLUS VIEUX AYANT ETE RECU.

	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double qte) {
		int etape = Filiere.LA_FILIERE.getEtape();
		if (getStockChocolatDeMarque(chocolatDeMarque)>qte) { //on vérifie déjà que l'action soit possible
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
		int etapeImpactee = etape-dureeDePeremption;
		if (etape>=dureeDePeremption) {
			for (ChocolatDeMarque chocoDM : acteur.getCatalogue()) {
				double valeurRecue = this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).getValeur();
				acteur.journalStocks.ajouter(Journal.texteColore(peremptionColor,Color.BLACK,"[PEREMPTION] " + Journal.doubleSur(valeurRecue,2) + " de " + chocoDM.name() +" datants de l'étape " + Journal.entierSur6(etapeImpactee) + " ont été jetés"));
				this.nouveauChocoParEtape.get(etapeImpactee).get(chocoDM).retirer(acteur, valeurRecue);
				this.stocksParMarque.get(chocoDM).retirer(acteur, valeurRecue);
			}
		}
	}


	@Override
	public void getCoutStockage() {
		// TODO Auto-generated method stub
		
	}
}
