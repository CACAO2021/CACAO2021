package abstraction.eq2Producteur2;

import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;

//Emeline

public class Producteur2Prod extends Producteur2Stockage {
	private LinkedList<Stock> arbrePlantesHBE;
	private LinkedList<Stock> arbrePlantesHE;
	// a finir
	private LinkedList<Object> listeProd;
	
	public Producteur2Prod() {
		super();
		// il faudra tenir compte du fait que les arbres nont pas tous le meme age au début
		this.arbrePlantesHBE = new LinkedList<Stock>();
		this.arbrePlantesHBE.add(new Stock(ARBRE_DEBUT_HBE, 0)); 
		this.arbrePlantesHE = new LinkedList<Stock>();
		this.arbrePlantesHE.add(new Stock(ARBRE_DEBUT_HE, 0));
		// a finir
		this.listeProd = new LinkedList<Object>();
		this.listeProd.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
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
			addStock(qtt, p);
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
