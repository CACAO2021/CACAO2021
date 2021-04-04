package abstraction.eq2Producteur2;

import java.util.LinkedList;

public class Producteur2Stockage extends Producteur2Transfo {
	private LinkedList<Stock> stockFeve; // detail a faire selon les parametres de la feve
	private LinkedList<Stock> stockPoudre;
	
	//Dim
	/**
	 * @param stockFeve
	 * @param stockPoudre
	 */
	public Producteur2Stockage() {
		// a modifier en prenant en compte le fait que le stocke de depart n'est pas entierement creer au step 0 mais aussi a des steps anterieurs
		this.stockFeve = new LinkedList<Stock>();
		this.stockFeve.add(new Stock(QTT_FEVE_DEPART, 0));
		this.stockPoudre = new LinkedList<Stock>();
		this.stockPoudre.add(new Stock(QTT_POUDRE_DEPART, 0));
	}

	/**
	 * @return the stockFeve
	 */
	public LinkedList<Stock> getStockFeve() {
		return stockFeve;
	}

	/**
	 * @return the stockPoudre
	 */
	public LinkedList<Stock> getStockPoudre() {
		return stockPoudre;
	}
	
	//Dim
	/*
	 * retourne la quantité de stock disponible totale d'un produit 
	 */
	public double qttTotale(Object produit) {
		if (estFeve(produit)) {
			double stock = 0;
			for (Stock s : this.getStockFeve()) {
				stock += s.getQtt();
			}
			return stock;
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
		if (estFeve(produit)) {
			this.getStockFeve().add(s);
		}else if (estPoudre(produit)) {
			this.getStockPoudre().add(s);
		}else {
			System.out.println("erreur");
		}
		
	}

}
