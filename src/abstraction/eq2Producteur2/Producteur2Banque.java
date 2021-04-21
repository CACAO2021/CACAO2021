package abstraction.eq2Producteur2;

import abstraction.fourni.Filiere;

public class Producteur2Banque extends Producteur2VeudeurFeveCC {

	public Producteur2Banque() {
		super();
	}


	public void perdreArgent(double montant) {
		Filiere.LA_FILIERE.getBanque().virer( Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme, Filiere.LA_FILIERE.getBanque(), montant);
	}
	
	//DIM
	public void coutStockage() {
		// a faire
		double fixe = 0; // cout fixe a determiner
		double sum = 0; // cout variable depend de la qtt de stock total
		for (Object p : getListeProd()) {
			majStock(p);
			sum += qttTotale(p).getValeur();
		}
		double var = sum * 0; // a determiner
		perdreArgent(var + fixe + 0.1); //+0.1 a enlever qd la valeur sera positive 
	}
	
	//faire focntion revolte des prod si plus dargent pour les payer

	
}
