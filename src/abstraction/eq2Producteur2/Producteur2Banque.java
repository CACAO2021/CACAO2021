package abstraction.eq2Producteur2;

import abstraction.fourni.Filiere;

//  DIM

public abstract class Producteur2Banque extends Producteur2VeudeurFeveCC {

	public Producteur2Banque() {
		super();
	}

	// DIM
	public void perdreArgent(double montant) {
		if (montant>0) {
			Filiere.LA_FILIERE.getBanque().virer( Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme, Filiere.LA_FILIERE.getBanque(), montant);
		}		
	}
	
	//DIM
	public void coutStockage() {
		// calcul cout variable
		double sum = 0; // cout variable depend de la qtt de stock total
		for (Object p : listeProd) {
			majStock(p);
			sum += qttTotale(p).getValeur();
		}
		double var = sum * COUT_STOCKAGE_FEVE; // cout variable
		double fixe = COUT_STOCKAGE_FIXE; // cout fixe a determiner dans les cte
		perdreArgent(var + fixe);  
	}
	
	//DIM
	//révolution si pas assez d'argent pour payer les prods
	public void revolte() {
		//if (plus assez dargent) {
			//révolte
		//}
	}

	
}
