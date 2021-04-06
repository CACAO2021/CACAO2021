package abstraction.eq7Distributeur2;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Variable;

public class Stocks implements IStocks{
	
	private HashMap<Chocolat, Variable> stocksParChocolat; //Stocks de chocolat par type
	private HashMap<ChocolatDeMarque, Variable> stocksParMarque; //Stocks de chocolat par marque
	private HashMap<Integer, HashMap<ChocolatDeMarque, Variable>> nouveauChocoParEtape; //Historique des chocolats reçus à chaque étape par marque
	private HashMap<ChocolatDeMarque, Variable> chocolatRecuEtape; // Chocolat recu à chaque étape
	private Distributeur2 acteur;
	

	public Stocks(Distributeur2 acteur) {
		this.acteur = acteur;
		stocksParChocolat = new HashMap<Chocolat,Variable>();
		stocksParMarque = new HashMap<ChocolatDeMarque, Variable>();
		nouveauChocoParEtape = new HashMap<Integer, HashMap<ChocolatDeMarque, Variable>>();
		chocolatRecuEtape = new HashMap<ChocolatDeMarque, Variable>();
		for(Chocolat chocolat : acteur.chocolatPropose) {
			stocksParChocolat.put(chocolat, new Variable("quantite de " + chocolat.name(),acteur,0 ));
		
		//initialiser les chocolats recus et par marque.

			
		}
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public double getStockChocolat(Chocolat chocolat) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantité) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque, double quantité) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void jeterChocolatPerime() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getFraisStockage() {
		// TODO Auto-generated method stub
		
	}

}
