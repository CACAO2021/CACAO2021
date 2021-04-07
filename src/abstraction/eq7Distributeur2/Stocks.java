package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;


import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur2Acteur implements IStocks{
	
	//private HashMap<Chocolat, Variable> stocksParChocolat; //Stocks de chocolat par type
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	//protected HashMap<ChocolatDeMarque, Variable> chocolatRecuEtape; // Chocolat recu à chaque étape
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color metaColor = Color.CYAN;
		public Color alertColor = Color.RED;
		public Color addStockColor = Color.GREEN;
		public Color removeStockColor = Color.ORANGE;
		public Color descriptionColor = Color.YELLOW;
		public Color peremptionColor = Color.MAGENTA;
	

	public Stocks() {
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		//chocolatRecuEtape = new HashMap<ChocolatDeMarque, Variable>();		
		for (ChocolatDeMarque chocoDeMarq : super.getCatalogue()) {
			stocksParMarque.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name()+ " en Stock", this,0));
			super.journalStocks.ajouter(Journal.texteColore(metaColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
			//chocolatRecuEtape.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name() + "Recu",acteur,0));
			HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
			Init.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name()+ " en Stock", this,0));
			nouveauChocoParEtape.put(0, Init);
		}
		}		
	
	public void initialiserChqEtape() {
		for (ChocolatDeMarque chocoDeMarq : super.getCatalogue()) {
			int etape = Filiere.LA_FILIERE.getEtape();
			HashMap<ChocolatDeMarque, Variable> Init = new HashMap<ChocolatDeMarque, Variable>();
			Init.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name()+ " en Stock", this,0));
			nouveauChocoParEtape.put(etape, Init);
			
		}
	}


	@Override
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		return this.stocksParMarque.get(chocolatDeMarque).getValeur();
	}


	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantite) {
		this.stocksParMarque.get(chocolatDeMarque).ajouter(this, quantite);
		super.journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, "[AJOUT] : " + Journal.doubleSur(quantite,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
		int etape = Filiere.LA_FILIERE.getEtape();
		nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(this, quantite);
		
		
	}


	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantite) {
		this.stocksParMarque.get(chocolatDeMarque).ajouter(this, - quantite);
		super.journalStocks.ajouter(Journal.texteColore(removeStockColor, Color.BLACK, "[SUPPRESSION] : " + Journal.doubleSur(quantite,2) + " de " + chocolatDeMarque.name() + ", [TOTAL] : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
		int etape = Filiere.LA_FILIERE.getEtape();
		nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(this, -quantite);

	}


	@Override
	public void jeterChocolatPerime() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getCoutStockage() {
		// TODO Auto-generated method stub
		
	}
}
