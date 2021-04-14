/**
 * 
 */
package abstraction.eq1Producteur1;

/**
 * @author Alb1x
 *
 */
public class VenteAO {
	private boolean vente_effectue;
	private double prix;

	public VenteAO(boolean v,double p) {
		vente_effectue = v;
		prix = p;
	}
	public VenteAO() {
		this(false, 0.0);
	}

	public boolean etatVente () {
		return vente_effectue;
	}
	
	public double prixVente () {
		return prix;
	}
	
	public void set_etatVente (boolean b) {
		vente_effectue = b;
	}
	
	public void set_prixVente (double p) {
		prix = p;
	}
}
