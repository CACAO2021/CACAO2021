package abstraction.eq4Transformateur2;
import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;


//Antoine R

public class Transformateur2Stock extends Transformateur2Acteur {
	
	public Transformateur2Stock() {
		super();
		stock_feve = new HashMap<Feve, Double>();
		stock_chocolat = new HashMap<Chocolat, Double>();
		stock_feve.put(Feve.FEVE_BASSE, quantite_init_feve_basse );
		stock_feve.put(Feve.FEVE_MOYENNE, quantite_init_feve_moyenne );
		stock_chocolat.put(Chocolat.CONFISERIE_BASSE, quantite_init_confiserie_basse );
		stock_chocolat.put(Chocolat.CONFISERIE_MOYENNE, quantite_init_confiserie_moyenne );
		stock_chocolat.put(Chocolat.TABLETTE_BASSE, quantite_init_tablette_basse);
		stock_chocolat.put(Chocolat.TABLETTE_MOYENNE, quantite_init_tablette_moyenne);
	}
	
	public void add_stock(Object o, double quantite) {
		if (o instanceof Feve) {
			stock_feve.replace((Feve) o, stock_feve.get(o) + quantite);
			if (o == Feve.FEVE_BASSE) {
				var_stock_feve_basse.setValeur(this, stock_feve.get(Feve.FEVE_BASSE));
			}
			else {
				var_stock_feve_moyenne.setValeur(this, stock_feve.get(Feve.FEVE_MOYENNE));
			}
		}
		if (o instanceof Chocolat) {
			stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
			if (o == Chocolat.TABLETTE_BASSE) {
				var_stock_tablette_basse.setValeur(this, var_stock_tablette_basse.getValeur() + quantite);
			}
			if (o == Chocolat.TABLETTE_MOYENNE) {
				var_stock_tablette_moyenne.setValeur(this, var_stock_tablette_moyenne.getValeur() + quantite);
			}
			if (o == Chocolat.CONFISERIE_BASSE) {
				var_stock_confiserie_basse.setValeur(this, var_stock_confiserie_basse.getValeur() + quantite);
			}
			if (o == Chocolat.CONFISERIE_MOYENNE) {
				var_stock_confiserie_moyenne.setValeur(this, var_stock_confiserie_moyenne.getValeur() + quantite);
			}
		}
	}

	
	public void delete_stock(Object o, double quantite) {
		if (o instanceof Feve) {
			if (stock_feve.get(o) - quantite<0) {
				this.journal.ajouter("il manque"+( -stock_feve.get(o) + quantite)+"de"+ o.toString());
				stock_feve.replace((Feve) o, 0.0);
				if (o == Feve.FEVE_BASSE) {
					var_stock_feve_basse.setValeur(this, 0);
				}
				else {
					var_stock_feve_moyenne.setValeur(this, 0);
				}
			}
			else {
				stock_feve.replace((Feve) o, stock_feve.get(o) - quantite);
				if (o == Feve.FEVE_BASSE) {
					var_stock_feve_basse.setValeur(this, var_stock_feve_basse.getValeur() - quantite);
				}
				else {
					var_stock_feve_moyenne.setValeur(this, var_stock_feve_moyenne.getValeur() - quantite);
				}
			}
		}
		if (o instanceof Chocolat) {
			if (stock_chocolat.get(o) - quantite<0) {
				this.journal.ajouter("il manque"+( -stock_chocolat.get(o) + quantite)+"de"+ o.toString());
				stock_chocolat.replace((Chocolat) o, 0.0);
				if (o == Chocolat.TABLETTE_BASSE) {
					var_stock_tablette_basse.setValeur(this, 0);
				}
				if (o == Chocolat.TABLETTE_MOYENNE) {
					var_stock_tablette_moyenne.setValeur(this, 0);
				}
				if (o == Chocolat.CONFISERIE_BASSE) {
					var_stock_confiserie_basse.setValeur(this, 0);
				}
				if (o == Chocolat.CONFISERIE_MOYENNE) {
					var_stock_confiserie_moyenne.setValeur(this, 0);
				}
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) - quantite);
				if (o == Chocolat.TABLETTE_BASSE) {
					var_stock_tablette_basse.setValeur(this, var_stock_tablette_basse.getValeur() - quantite);
				}
				if (o == Chocolat.TABLETTE_MOYENNE) {
					var_stock_tablette_moyenne.setValeur(this, var_stock_tablette_moyenne.getValeur() - quantite);
				}
				if (o == Chocolat.CONFISERIE_BASSE) {
					var_stock_confiserie_basse.setValeur(this, var_stock_confiserie_basse.getValeur() - quantite);
				}
				if (o == Chocolat.CONFISERIE_MOYENNE) {
					var_stock_confiserie_moyenne.setValeur(this, var_stock_confiserie_moyenne.getValeur() - quantite);
				}
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
	
	// Test
	
}
