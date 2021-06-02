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
			// si on depasse le stockage max, on paye plus cher
			malus = (sum - QTT_STOCKAGE_MAX) * COUT_STOCKAGE_FEVE_DEPASSEMENT;
			sum = QTT_STOCKAGE_MAX; // on stocke le maximum avec les moyens normaux
		}
		double var = sum * COUT_STOCKAGE_FEVE; // cout variable
		double fixe = COUT_STOCKAGE_FIXE; // cout fixe a determiner dans les cte
		perdreArgent(var + fixe + malus);  
		JournalStock.ajouter("prix " + (var + fixe + malus) );

	}
	
	public void tropDArgent() {
		/*
		// si on devient trop riche 
		// ce qui n'est pas forcement le but de notre acteur qui se veut equitable
		// on fait des dons dargent et on augmente le salaire de nos producteurs
		boolean bol = Filiere.LA_FILIERE.getBanque()
		if () {
			// on augmente les salaires
			COUT_PRODUCTION_FEVE_B *=  1.2; // cout de prod multiplier par 1.2
			COUT_PRODUCTION_FEVE_M *= 1.2;
			COUT_PRODUCTION_FEVE_ME *= 1.3;
			COUT_PRODUCTION_FEVE_HE *= 1.4;
			COUT_PRODUCTION_FEVE_HBE *= 1.5;
		}
		*/
	}
 



}
