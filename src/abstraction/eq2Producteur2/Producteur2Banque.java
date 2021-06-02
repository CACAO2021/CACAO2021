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
		double malus = 0; // cout si on depasse le stockage max
		for (Object p : listeProd) {
			majStock(p);
			sum += qttTotale(p).getValeur();
		}
		JournalStock.ajouter("stock total " + sum );
		if (sum>QTT_STOCKAGE_MAX) {
			JournalStock.ajouter("on stocke trop");
			// si on depasse le stockage max, on paye bien plus cher
			malus = (sum - QTT_STOCKAGE_MAX) * COUT_STOCKAGE_FEVE_DEPASSEMENT;
			sum = QTT_STOCKAGE_MAX; // on stocke le maximum avec les moyens normaux
		}
		double var = sum * COUT_STOCKAGE_FEVE; // cout variable
		double fixe = COUT_STOCKAGE_FIXE; // cout fixe a determiner dans les cte
		perdreArgent(var + fixe + malus);  
		JournalStock.ajouter("prix " + (var + fixe + malus) );

	}




}
