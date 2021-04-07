package abstraction.eq7Distributeur2;

import java.util.HashMap;


import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

public class Stocks implements IStocks{
	
	//private HashMap<Chocolat, Variable> stocksParChocolat; //Stocks de chocolat par type
	protected HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	protected HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	protected HashMap<ChocolatDeMarque, Variable> chocolatRecuEtape; // Chocolat recu à chaque étape
	private Distributeur2 acteur;
	

	public Stocks(Distributeur2Acteur acteur) {
		this.acteur = (Distributeur2) acteur;
		//stocksParChocolat = new HashMap<Chocolat,Variable>();
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		chocolatRecuEtape = new HashMap<ChocolatDeMarque, Variable>();
		//for(Chocolat chocolat : acteur.chocolatPropose) {
			//stocksParChocolat.put(chocolat, new Variable("quantite de " + chocolat.name(),acteur,0 ));
	//}
		for (ChocolatDeMarque chocoDeMarq : Filiere.LA_FILIERE.getChocolatsProduits()) {
			stocksParMarque.put(chocoDeMarq, new Variable("quantite de " + chocoDeMarq.name(),acteur,0));
		}		
	}


	@Override
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		return this.stocksParMarque.get(chocolatDeMarque).getValeur();
	}


	@Override
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantité) {
		this.stocksParMarque.get(chocolatDeMarque).ajouter(acteur, quantité);
		stocksParMarque.put(chocolatDeMarque, this.stocksParMarque.get(chocolatDeMarque));
		
		
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


	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		
	}

}
