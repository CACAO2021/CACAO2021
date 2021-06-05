/**
 * 
 */
package abstraction.eq1Producteur1;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.IActeur;

/**
 * @author
 *
 */
public abstract class Stocks extends VendeurContratCadre1{
	private Stock stock_F_M_E;
	private Stock stock_F_M;
	private Stock stock_F_B;
	private Stock stock_P_M_E;
	private Stock stock_P_M;
	protected HashMap<Object, Stock> stocks; //dictionnaire qui contient tous nos stocks.

	/**
	 * @author Alb1x
	 * On crée un stock pour chaque chose que l'on produit.
	 * On range ensuite les stock dans un dictionnaire stocks.
	 */
		private void init_stocks(IActeur a) {
		this.stock_F_M_E = new Stock("Stock F_M_E",0, a); //stock que l'on possède au départ
		this.stock_F_M = new Stock("Stock F_M",0, a);
		this.stock_F_B = new Stock("Stock F_B",0, a);
		this.stock_P_M_E = new Stock("Stock P_M_E",0, a);
		this.stock_P_M = new Stock("Stock P_M",0, a);
		this.stocks = new HashMap<Object, Stock>();
		this.stocks.put(Feve.FEVE_MOYENNE_EQUITABLE, stock_F_M_E);
		this.stocks.put(Feve.FEVE_MOYENNE, stock_F_M);
		this.stocks.put(Feve.FEVE_BASSE, stock_F_B);
		this.stocks.put(Chocolat.POUDRE_MOYENNE_EQUITABLE, stock_P_M_E);
		this.stocks.put(Chocolat.POUDRE_MOYENNE, stock_P_M);
	}
	public Stocks() {
		this.init_stocks(this);
	}
	
	public Stocks getStocks2() {
		return this;
	}
	
	public Stock getStock(Object o) {
		return stocks.get(o);		}
	
	public HashMap<Object, Stock> getStocks() {
		return stocks;
	}

}
