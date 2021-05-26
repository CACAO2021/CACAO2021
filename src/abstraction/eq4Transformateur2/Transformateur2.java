package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Antoine C
public class Transformateur2 extends Transformateur2AchatAO {

	private SuperviseurVentesContratCadre supCCadre;
	
	public Transformateur2 () {
		super();
	}

	public String toString() {
		return this.getNom();
	}
	public void next() {
		/*this.supCCadre = (SuperviseurVentesContratCadre )(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		CCimportant();*/
		
		/*
		for (int i=0; i<duree_stockage_max-1;i++) {
			if (i == 0) {
				stock_feve.get(Feve.FEVE_BASSE)[duree_stockage_max-1] = 0;
				stock_feve.get(Feve.FEVE_MOYENNE)[duree_stockage_max-1] = 0;
				stock_chocolat.get(Chocolat.TABLETTE_BASSE)[duree_stockage_max-1] = 0;
				stock_chocolat.get(Chocolat.CONFISERIE_BASSE)[duree_stockage_max-1] = 0;
				stock_chocolat.get(Chocolat.TABLETTE_MOYENNE)[duree_stockage_max-1] = 0;
				stock_chocolat.get(Chocolat.CONFISERIE_MOYENNE)[duree_stockage_max-1] = 0;
			}
			else {
				stock_feve.get(Feve.FEVE_BASSE)[duree_stockage_max-i] = stock_feve.get(Feve.FEVE_BASSE)[duree_stockage_max-1-i];
				stock_feve.get(Feve.FEVE_MOYENNE)[duree_stockage_max-i] = stock_feve.get(Feve.FEVE_MOYENNE)[duree_stockage_max-1-i];
				stock_chocolat.get(Chocolat.TABLETTE_BASSE)[duree_stockage_max-i] = stock_chocolat.get(Chocolat.TABLETTE_BASSE)[duree_stockage_max-1-i];
				stock_chocolat.get(Chocolat.CONFISERIE_BASSE)[duree_stockage_max-i] = stock_chocolat.get(Chocolat.CONFISERIE_BASSE)[duree_stockage_max-1-i];
				stock_chocolat.get(Chocolat.TABLETTE_MOYENNE)[duree_stockage_max-i] = stock_chocolat.get(Chocolat.TABLETTE_MOYENNE)[duree_stockage_max-1-i];
				stock_chocolat.get(Chocolat.CONFISERIE_MOYENNE)[duree_stockage_max-i] = stock_chocolat.get(Chocolat.CONFISERIE_MOYENNE)[duree_stockage_max-1-i];
			}
		}
		*/
		
		if (get_stock(Chocolat.TABLETTE_BASSE) < mini_stock_tablette_basse ) {
			transformation_feve(mini_stock_tablette_basse-get_stock(Chocolat.TABLETTE_BASSE), Chocolat.TABLETTE_BASSE);
		}
		if (get_stock(Chocolat.CONFISERIE_BASSE) < mini_stock_confiserie_basse ) {
			transformation_feve(mini_stock_confiserie_basse-get_stock(Chocolat.CONFISERIE_BASSE), Chocolat.CONFISERIE_BASSE);
		}
		if (get_stock(Chocolat.TABLETTE_MOYENNE) < mini_stock_tablette_moyenne ) {
			transformation_feve(mini_stock_tablette_moyenne-get_stock(Chocolat.TABLETTE_MOYENNE), Chocolat.TABLETTE_MOYENNE);
		}
		if (get_stock(Chocolat.CONFISERIE_MOYENNE) < mini_stock_confiserie_moyenne ) {
			transformation_feve(mini_stock_confiserie_moyenne-get_stock(Chocolat.CONFISERIE_MOYENNE), Chocolat.CONFISERIE_MOYENNE);
		}
		if (cout_fixe_entrepot_feve + (get_stock(Feve.FEVE_BASSE)+get_stock(Feve.FEVE_MOYENNE))*cout_stockage_unite_feve > 0.0) {
			Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("Boni Suci"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(), (cout_fixe_entrepot_feve + (get_stock(Feve.FEVE_BASSE)+get_stock(Feve.FEVE_MOYENNE))*cout_stockage_unite_feve));
		}
		if (cout_fixe_entrepot_choco + (get_stock(Chocolat.CONFISERIE_BASSE)+get_stock(Chocolat.CONFISERIE_MOYENNE)+get_stock(Chocolat.TABLETTE_BASSE)+get_stock(Chocolat.TABLETTE_MOYENNE))*cout_stockage_unite_choco > 0) {
			Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("Boni Suci"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(), (cout_fixe_entrepot_choco + (get_stock(Chocolat.CONFISERIE_BASSE)+get_stock(Chocolat.CONFISERIE_MOYENNE)+get_stock(Chocolat.TABLETTE_BASSE)+get_stock(Chocolat.TABLETTE_MOYENNE))*cout_stockage_unite_choco));
		}
		
		
		
		// à mettre à la toute fin
		this.update_echeanciers();
	}
}
