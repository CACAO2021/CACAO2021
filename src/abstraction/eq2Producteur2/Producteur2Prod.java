package abstraction.eq2Producteur2;

import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;

//Emeline

public class Producteur2Prod extends Producteur2Stockage {
	private LinkedList<Stock> arbrePlantesHBE;
	private LinkedList<Stock> arbrePlantesHE;
	private LinkedList<Stock> arbrePlantesME;
	private LinkedList<Stock> arbrePlantesM;
	private LinkedList<Stock> arbrePlantesB;
	private LinkedList<Object> listeProd;
	
	public Producteur2Prod() {
		super();
		// il faudra tenir compte du fait que les arbres nont pas tous le meme age au début
		this.arbrePlantesHBE = new LinkedList<Stock>();
		this.arbrePlantesHBE.add(new Stock(ARBRE_DEBUT_HBE, 0)); 
		this.arbrePlantesHE = new LinkedList<Stock>();
		this.arbrePlantesHE.add(new Stock(ARBRE_DEBUT_HE, 0));
		this.arbrePlantesME = new LinkedList<Stock>();
		this.arbrePlantesME.add(new Stock(ARBRE_DEBUT_ME, 0));
		this.arbrePlantesM = new LinkedList<Stock>();
		this.arbrePlantesM.add(new Stock(ARBRE_DEBUT_M, 0));
		this.arbrePlantesB = new LinkedList<Stock>();
		this.arbrePlantesB.add(new Stock(ARBRE_DEBUT_B, 0));
		this.listeProd = new LinkedList<Object>();
		this.listeProd.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		}
	
	public double qttArbre(Object produit) {
		double nb = 0;
		if (estFeveHBE(produit)) {			
			for (Stock s : this.arbrePlantesHBE) {
				nb += s.getQtt();
			}
		}else if (estFeveHE(produit)) {
			for (Stock s : this.arbrePlantesHE) {
				nb += s.getQtt();
			}
		}else if (estFeveHE(produit)) {
			for (Stock s : this.arbrePlantesHE) {
				nb += s.getQtt();
			}
		}else if (estFeveME(produit)) {
			for (Stock s : this.arbrePlantesME) {
				nb += s.getQtt();
			}
		}else if (estFeveM(produit)) {
			for (Stock s : this.arbrePlantesM) {
				nb += s.getQtt();
			}
		}else if (estFeveB(produit)) {
			for (Stock s : this.arbrePlantesB) {
				nb += s.getQtt();
			}
		}		
		return nb;
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
