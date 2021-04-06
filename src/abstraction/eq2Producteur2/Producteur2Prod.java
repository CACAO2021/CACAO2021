package abstraction.eq2Producteur2;

import java.util.LinkedList;

//Emeline

public class Producteur2Prod extends Producteur2Stockage {
	private LinkedList<Stock> arbrePlantesHBE;
	private LinkedList<Stock> arbrePlantesHE;
	// a finir
	private static Object[] listeProd;
	
	public Producteur2Prod() {
		super();
		this.arbrePlantesHBE = new LinkedList<Stock>();
		this.arbrePlantesHBE.add(new Stock(ARBRE_DEBUT_HBE, 0)); 
		this.arbrePlantesHE = new LinkedList<Stock>();
		this.arbrePlantesHE.add(new Stock(ARBRE_DEBUT_HE, 0));
		// a finir
		}
	
	public double qttArbre(Object produit) {
		if (estFeveHBE(produit)) {
			double nb = 0;
			for (Stock s : this.arbrePlantesHBE) {
				nb += s.getQtt();
			}
			return nb;
			// finir les autres types d arbre
			}
		return 0;
		}


	public void prod() {
		for (Object p : listeProd) {
			double qtt = prodParStep(p);
			Object produit = 0;
			addStock(qtt, produit);
		}		
		}
	
	public void renouvellement() {
		//  a faire plus tard 
	}
	
	private double prodParStep(Object p) {
		// prod en fonction de la qtt darbre plante
		return qttArbre(p) * prodParArbre(p);
	}

	private double prodParArbre(Object p) {
		if(estFeveHBE(p)) {
			return PROD_HBE;
		} else if(estFeveHE(p)) {
			return PROD_HE;
		} // a finir
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
		
	
	

}
