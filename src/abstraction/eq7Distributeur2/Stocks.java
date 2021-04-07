package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;


import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Stocks implements IStocks{
	
	//private HashMap<Chocolat, Variable> stocksParChocolat; //Stocks de chocolat par type
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	protected HashMap<ChocolatDeMarque, Variable> chocolatRecuEtape; // Chocolat recu à chaque étape
	private Distributeur2 acteur;
	protected Journal journalStocks;
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color metaColor = Color.CYAN;
		public Color alertColor = Color.RED;
		public Color addStockColor = Color.GREEN;
		public Color removeStockColor = Color.ORANGE;
		public Color descriptionColor = Color.YELLOW;
		public Color peremptionColor = Color.MAGENTA;
	

	public Stocks(Distributeur2Acteur acteur) {
		this.acteur = (Distributeur2) acteur;
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		chocolatRecuEtape = new HashMap<ChocolatDeMarque, Variable>();
		initialisationJournalStocks();
		if (acteur.getCatalogue()!= null) {
		for (ChocolatDeMarque chocoDeMarq : acteur.getCatalogue()) {
			stocksParMarque.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name()+ " en Stock", acteur,0));
			journalStocks.ajouter(Journal.texteColore(metaColor, Color.BLACK,"[CRÉATION] Création d'un stock pour le " + chocoDeMarq + "."));
			chocolatRecuEtape.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name() + "Recu",acteur,0));
		}
		}		
	}
	
	public void initialisationJournalStocks(){
		journalStocks= new Journal("Stocks", (IActeur)this.acteur);
		journalStocks.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Gestion des Stocks"));
		journalStocks.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal regroupe toutes les variations du Stock"));
	}
	


	@Override
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		return this.stocksParMarque.get(chocolatDeMarque).getValeur();
	}


	@Override
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantite) {
		this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, quantite);
		stocksParMarque.put(chocolatDeMarque, this.stocksParMarque.get(chocolatDeMarque));
		journalStocks.ajouter(Journal.texteColore(addStockColor, Color.BLACK, Journal.doubleSur(quantite,2) + " de " + chocolatDeMarque.name() + " (nouveau stock : " + Journal.doubleSur(stocksParMarque.get(chocolatDeMarque).getValeur(),2) + " "));
		int etape = Filiere.LA_FILIERE.getEtape();
		this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque).ajouter(acteur, quantite);
		nouveauChocoParEtape.get(etape).put(chocolatDeMarque,this.nouveauChocoParEtape.get(etape).get(chocolatDeMarque));
		
		
	}


	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantité) {
		this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, - quantité);
		stocksParMarque.put(chocolatDeMarque, this.stocksParMarque.get(chocolatDeMarque));
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
