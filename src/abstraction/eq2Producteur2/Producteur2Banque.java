package abstraction.eq2Producteur2;

import java.awt.Color;

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
		//dim


		// si on devient trop riche 
		// ce qui n'est pas forcement le but de notre acteur qui se veut equitable
		// on fait des dons dargent et on augmente le salaire de nos producteurs
		double montantMax = 1e10 * 10;
		// 1e11 : 10 fois plus d'argent qu'au départ -> pas le but de gagner autant
		boolean bol = Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme) > montantMax ;
		if (bol) {
			System.out.println("trop riche " + this.getNom());
			JournalPB.ajouter(Color.GREEN, Color.BLACK, "trop riche " + Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme));
			// on augmente les salaires
			// on augemente les salirs pour nous mais pas pour lautre equipe
			COUT_PRODUCTION_FEVE_B_ *=  1.2; // cout de prod multiplier par 1.2
			COUT_PRODUCTION_FEVE_M_ *= 1.2;
			COUT_PRODUCTION_FEVE_ME_ *= 1.2;
			COUT_PRODUCTION_FEVE_HE_ *= 1.3;
			COUT_PRODUCTION_FEVE_HBE_ *= 1.4;
			// on fait un don 
			perdreArgent(montantMax/4);
		} else {
			// si on devient plus modéremment trop riche
			// on se contente d'augmenter les salaires
			double montantAugmentationSalaire = 1e10 * 3; 
			// 3 fois plus qu'au départ
			boolean bol2 = Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme) > montantAugmentationSalaire ;
			if (bol2) {
				System.out.println("trop riche " + this.getNom());
				JournalPB.ajouter(Color.GREEN, Color.BLACK, "trop riche " + Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur("Baratao") , cryptogramme));
				// on augmente les salaires
				// on augemente les salirs pour nous mais pas pour lautre equipe
				COUT_PRODUCTION_FEVE_B_ *=  1.2; // cout de prod multiplier par 1.2
				COUT_PRODUCTION_FEVE_M_ *= 1.2;
				COUT_PRODUCTION_FEVE_ME_ *= 1.2;
				COUT_PRODUCTION_FEVE_HE_ *= 1.3;
				COUT_PRODUCTION_FEVE_HBE_ *= 1.4;
				// on ne fait pas de don 
			}
		}

	}




}
