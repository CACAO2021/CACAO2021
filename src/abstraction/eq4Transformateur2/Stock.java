package abstraction.eq4Transformateur2;

//Antoine R

public class Stock extends Transformateur2Acteur {
	
	public Stock() {
		super();
	}
	
	public double Stock_feve_total() {
		return super.stock_feve_basse + this.stock_feve_moyenne;	
	}
	public double Stock_tablette_total() {
		return this.stock_tablette_basse + this.stock_tablette_moyenne;
	}
	public double Stock_confiserie_total() {
		return this.stock_confiserie_basse + this.stock_confiserie_moyenne;
	}
	public double Stock_choco_total() {
		return this.Stock_tablette_total()+this.Stock_confiserie_total();
	}
	
	
	public double getStock_feve_basse() {
		return stock_feve_basse;
	}
	public void setStock_feve_basse(double stock_feve_basse) {
		this.stock_feve_basse = stock_feve_basse;
	}
	public void addStock_feve_basse(double x) {
		this.stock_feve_basse = this.stock_feve_basse + x;
	}
	public void suppStock_feve_basse(double x) {
		this.stock_feve_basse = this.stock_feve_basse - x;
	}
	public double getStock_feve_moyenne() {
		return stock_feve_moyenne;
	}
	public void setStock_feve_moyenne(double stock_feve_moyenne) {
		this.stock_feve_moyenne = stock_feve_moyenne;
	}
	public void addStock_feve_moyenne(double x) {
		this.stock_feve_moyenne = this.stock_feve_moyenne + x;
	}
	public void suppStock_feve_moyenne(double x) {
		this.stock_feve_moyenne = this.stock_feve_moyenne - x;
	}
	public double getStock_tablette_basse() {
		return stock_tablette_basse;
	}
	public void setStock_tablette_basse(double stock_tablette_basse) {
		this.stock_tablette_basse = stock_tablette_basse;
	}
	public void addStock_tablette_basse(double x) {
		this.stock_tablette_basse = this.stock_tablette_basse + x;
	}
	public void suppStock_tablette_basse(double x) {
		this.stock_tablette_basse = this.stock_tablette_basse - x;
	}
	public double getStock_tablette_moyenne() {
		return stock_tablette_moyenne;
	}
	public void setStock_tablette_moyenne(double stock_tablette_moyenne) {
		this.stock_tablette_moyenne = stock_tablette_moyenne;
	}
	public void addStock_tablette_moyenne(double x) {
		this.stock_tablette_moyenne = this.stock_tablette_moyenne + x;
	}
	public void suppStock_tablette_moyenne(double x) {
		this.stock_tablette_moyenne = this.stock_tablette_moyenne - x;
	}
	public double getStock_confiserie_basse() {
		return stock_confiserie_basse;
	}
	public void setStock_confiserie_basse(double stock_confiserie_basse) {
		this.stock_confiserie_basse = stock_confiserie_basse;
	}
	public void addStock_confiserie_basse(double x) {
		this.stock_confiserie_basse = this.stock_confiserie_basse + x;
	}
	public void suppStock_confiserie_basse(double x) {
		this.stock_confiserie_basse = this.stock_confiserie_basse - x;
	}
	public double getStock_confiserie_moyenne() {
		return stock_confiserie_moyenne;
	}
	public void setStock_confiserie_moyenne(double stock_confiserie_moyenne) {
		this.stock_confiserie_moyenne = stock_confiserie_moyenne;
	}
	public void addStock_confiserie_moyenne(double x) {
		this.stock_confiserie_moyenne = this.stock_confiserie_moyenne + x;
	}
	public void suppStock_confiserie_moyenne(double x) {
		this.stock_confiserie_moyenne = this.stock_confiserie_moyenne - x;
	}

}
