package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;

public abstract class contratAO extends Producteur2VendeurFeveAO {
	private double prixKg;
	private boolean etat;
	private Feve typefeve;
	public void contratA0(double prixKg, boolean etat, Feve typefeve) {
		this.prixKg=prixKg;
		this.etat=etat;
		this.typefeve=typefeve;
	}
	public void setPrix(double prixKg) {
		this.prixKg=prixKg;
	}
	public void setEtat(boolean etat) {
		this.etat=etat;
	}
	public void setType(Feve typefeve) {
		this.typefeve=typefeve;
	}
	public double getPrix() {
		return prixKg;
	}
	public boolean getEtat() {
		return etat;
	}
	public Feve getType() {
		return typefeve;
	}
	
}
