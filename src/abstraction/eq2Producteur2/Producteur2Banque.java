package abstraction.eq2Producteur2;

import abstraction.fourni.Filiere;

public class Producteur2Banque extends Producteur2VeudeurFeveCC {

	public Producteur2Banque() {
		super();
	}

	public double coutTotDuStep() { // cout a payer a ce step
		double cout = 0;
		// cout prod (salaire, replanter, ...)
		// cout stockage
		return cout;
	}
	
	public void perdreArgent(double montant) {
		System.out.println("perte"+ montant);
		Filiere.LA_FILIERE.getBanque().virer( Filiere.LA_FILIERE.getActeur("Baratao") , this.cryptogramme, Filiere.LA_FILIERE.getBanque(), montant);
	}
	
	/*
	 * protected void perteargent(double quantite) {
		if (quantite>0) {
			Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("EQ1"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(),quantite );
		}
	}
	 */

	
}
