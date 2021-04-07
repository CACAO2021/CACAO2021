package abstraction.eq1Producteur1;

/**
 * @author arthurlemgit
 * @author Alb1x
 * Implémentation basique du stock pour le moment (juste un nombre de fèves), quantité en kg.
 */
public class Stock {
	private double quantite;
	public Stock(double quantite) {
		this.quantite=quantite;
	}
	public double getQuantite() {
		return quantite;
	}
	
	private void setQuantite(double d) {
		this.quantite=d;
	}
	
	public void addQuantite(double d) {
		this.quantite+=d;
	}
	
	public void removeQuantite(double d) {
		this.quantite-=d;
	}
	
	
	
}
