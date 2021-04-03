package abstraction.eq1Producteur1;

public class Stock {
	private double quantite;
	public Stock(double quantite) {
		this.quantite=quantite;
	}
	private double getQuantite() {
		return quantite;
	}
	
	private void setQuantite(double d) {
		this.quantite=d;
	}
	
	
}
