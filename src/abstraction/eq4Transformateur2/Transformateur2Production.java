package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Antoine C & Antoine R

public class Transformateur2Production extends Transformateur2Stock {

	public Transformateur2Production() {
		super();
	}
	
	public void transformation_feve(double quantite, Object o) {
		if (o instanceof Chocolat) {
			if ((o == Chocolat.TABLETTE_BASSE || o == Chocolat.CONFISERIE_BASSE) && get_stock(Feve.FEVE_BASSE) - quantite > 0) {
				add_stock((Chocolat) o, quantite);
				delete_stock(Feve.FEVE_BASSE, quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), quantite*cout_transformation);
			}
			if ((o == Chocolat.TABLETTE_BASSE || o == Chocolat.CONFISERIE_BASSE) && get_stock(Feve.FEVE_BASSE) - quantite < 0) {
				add_stock((Chocolat) o, get_stock(Feve.FEVE_BASSE));
				delete_stock(Feve.FEVE_BASSE, get_stock(Feve.FEVE_BASSE));
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), get_stock(Feve.FEVE_BASSE)*cout_transformation);
			}
			if ((o == Chocolat.TABLETTE_MOYENNE || o == Chocolat.CONFISERIE_MOYENNE) && get_stock(Feve.FEVE_MOYENNE) - quantite > 0) {
				add_stock((Chocolat) o, quantite);
				delete_stock(Feve.FEVE_MOYENNE, quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), quantite*cout_transformation);
			}
			if ((o == Chocolat.TABLETTE_MOYENNE || o == Chocolat.CONFISERIE_MOYENNE) && get_stock(Feve.FEVE_MOYENNE) - quantite < 0) {
				add_stock((Chocolat) o, get_stock(Feve.FEVE_MOYENNE));
				delete_stock(Feve.FEVE_MOYENNE, get_stock(Feve.FEVE_MOYENNE));
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), stock_feve.get(Feve.FEVE_MOYENNE)*cout_transformation);
			}
		}
	}
	
		
}