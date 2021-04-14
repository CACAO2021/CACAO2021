package abstraction.eq4Transformateur2;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;


//Antoine R

public class Transformateur2Stock extends Transformateur2Production {
	
	public Transformateur2Stock() {
		super();
		stock_feve = new HashMap<Feve, Double>();
		stock_chocolat = new HashMap<Chocolat, Double>();
		stock_feve.put(Feve.FEVE_BASSE, 0.0);
		stock_feve.put(Feve.FEVE_MOYENNE, 0.0);
		stock_chocolat.put(Chocolat.CONFISERIE_BASSE, 0.0);
		stock_chocolat.put(Chocolat.CONFISERIE_MOYENNE, 0.0);
		stock_chocolat.put(Chocolat.TABLETTE_BASSE, 0.0);
		stock_chocolat.put(Chocolat.TABLETTE_MOYENNE, 0.0);
	}
	
	public void add_stock(Object o, double quantite) {
		if (o instanceof Feve) {
			stock_feve.replace((Feve) o, stock_feve.get(o) + quantite);
		}
		if (o instanceof Chocolat) {
			stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
		}
	}
	
	public void delete_stock(Object o, double quantite) {
		if (o instanceof Feve) {
			if (stock_feve.get(o) - quantite<0) {
				this.journal.ajouter("il manque"+( -stock_feve.get(o) + quantite)+"de"+ o.toString());
				stock_feve.replace((Feve) o, 0.0);
			}
			else {
				stock_feve.replace((Feve) o, stock_feve.get(o) - quantite);
			}
		}
		if (o instanceof Chocolat) {
			if (stock_chocolat.get(o) - quantite<0) {
				this.journal.ajouter("il manque"+( -stock_chocolat.get(o) + quantite)+"de"+ o.toString());
				stock_chocolat.replace((Chocolat) o, 0.0);
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) - quantite);
			}
		}
	}
	
	public double get_stock(Object o) {
		if (o instanceof Feve) {
			return stock_feve.get(o);
		}
		if (o instanceof Chocolat) {
			return stock_chocolat.get(o);
		}
		return 0.0;
	}
	
	public double Stock_feve_total() {
		return get_stock(Feve.FEVE_BASSE) + get_stock(Feve.FEVE_MOYENNE);	
	}
	public double Stock_tablette_total() {
		return get_stock(Chocolat.TABLETTE_BASSE) + get_stock(Chocolat.TABLETTE_MOYENNE);
	}
	public double Stock_confiserie_total() {
		return get_stock(Chocolat.CONFISERIE_BASSE) + get_stock(Chocolat.CONFISERIE_MOYENNE);
	}
	public double Stock_choco_total() {
		return Stock_tablette_total()+Stock_confiserie_total();
	}
	
	public double cout_stock_feves() {
		return this.Stock_feve_total()*cout_stockage_unite_feve;
	}
	public double cout_stock_chocolat() {
		return this.Stock_choco_total()*cout_stockage_unite_choco;
	}
}
