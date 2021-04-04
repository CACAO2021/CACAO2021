package abstraction.eq2Producteur2;

import java.util.LinkedList;

public class Producteur2Stockage extends Producteur2Transfo {
	private LinkedList<Stock> stockFeveHBE; 
	// fr les autres types
	private LinkedList<Stock> stockPoudre;
	
	//Dim
	/**
	 * @param stockFeve
	 * @param stockPoudre
	 */
	public Producteur2Stockage() {
		// a modifier en prenant en compte le fait que le stocke de depart n'est pas entierement creer au step 0 mais aussi a des steps anterieurs
		this.stockFeveHBE = new LinkedList<Stock>();
		this.stockFeveHBE.add(new Stock(QTT_FEVE_HBE_DEPART, 0));
		// fr les autres feves
		this.stockPoudre = new LinkedList<Stock>();
		this.stockPoudre.add(new Stock(QTT_POUDRE_DEPART, 0));
	}


	public LinkedList<Stock> getStockFeveHBE() {
		return stockFeveHBE;
	}
	// fr les autres types
	public LinkedList<Stock> getStockPoudre() {
		return stockPoudre;
	}
	
	//Dim
	/*
	 * retourne la quantité de stock disponible totale d'un produit 
	 */
	public double qttTotale(Object produit) {
		if (estFeveHBE(produit)) {
			double stock = 0;
			for (Stock s : this.getStockFeveHBE()) {
				stock += s.getQtt();
			}
			return stock;
			// finir les autres types de feve
		}else if (estPoudre(produit)) {
			double stock = 0;
			for (Stock s : this.getStockPoudre()) {
				stock += s.getQtt();
			}
			return stock;
		}
		else {// si on ne vend pas le produit on retourne 0
		return 0;
		}
	}
	
	//Dim
	public void addStock(double qtt, Object produit) {
		int step = 0; // trouver comment obtenir le step actuel
		Stock s = new Stock(qtt, step);
		if (estFeveHBE(produit)) {
			this.getStockFeveHBE().add(s);
			// finir tous les types de feve
		}else if (estPoudre(produit)) {
			this.getStockPoudre().add(s);
		}else {
			System.out.println("erreur");
		}
		
	}

}
