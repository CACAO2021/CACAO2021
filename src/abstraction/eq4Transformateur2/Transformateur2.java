package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Antoine C
public class Transformateur2 extends Transformateur2AchatAO {

	
	public Transformateur2 () {
		super();
		
		for (int i=0; i<24; i++ ) {
			echeancier_basse.add(0.0);
			echeancier_moyenne.add(0.0);
			echeancier_total.add(0.0);
		}
	}

	public String toString() {
		return this.getNom();
	}
	public void next() {
		transformation_feve(get_stock(Feve.FEVE_BASSE)*0.75, Chocolat.TABLETTE_BASSE);
		transformation_feve(get_stock(Feve.FEVE_BASSE)*0.25, Chocolat.CONFISERIE_BASSE);
		transformation_feve(get_stock(Feve.FEVE_MOYENNE)*0.75, Chocolat.TABLETTE_MOYENNE);
		transformation_feve(get_stock(Feve.FEVE_MOYENNE)*0.25, Chocolat.CONFISERIE_MOYENNE);
		Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("Boni Suci"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(), (cout_fixe_entrepot_feve + (stock_feve.get(Feve.FEVE_BASSE)+stock_feve.get(Feve.FEVE_MOYENNE))*cout_stockage_unite_feve));
		Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("Boni Suci"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(), (cout_fixe_entrepot_choco + (stock_chocolat.get(Chocolat.CONFISERIE_BASSE)+stock_chocolat.get(Chocolat.CONFISERIE_MOYENNE)+stock_chocolat.get(Chocolat.TABLETTE_BASSE)+stock_chocolat.get(Chocolat.TABLETTE_MOYENNE))*cout_stockage_unite_choco));

		// à mettre à la toute fin
		this.update_echeanciers();
	}
}
