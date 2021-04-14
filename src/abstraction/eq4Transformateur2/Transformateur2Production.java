package abstraction.eq4Transformateur2;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

// Antoine C & Antoine R

public class Transformateur2Production extends Transformateur2Stock {

	public Transformateur2Production() {
		super();
	}
	
	public void transformation_feve(double quantite, Object o) {
		if (o instanceof Chocolat) {
			if (o == Chocolat.TABLETTE_BASSE && stock_feve.get(Feve.FEVE_BASSE) - quantite >= 0) {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
				stock_feve.replace(Feve.FEVE_BASSE, stock_feve.get(Feve.FEVE_BASSE) - quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(quantite)*Transformateur2Valeurs.cout_transformation);
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + stock_feve.get(Feve.FEVE_BASSE));
				stock_feve.replace(Feve.FEVE_BASSE, 0.0);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(stock_feve.get(Feve.FEVE_BASSE))*Transformateur2Valeurs.cout_transformation);
			}
			if (o == Chocolat.TABLETTE_MOYENNE && stock_feve.get(Feve.FEVE_MOYENNE) - quantite >= 0) {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
				stock_feve.replace(Feve.FEVE_MOYENNE, stock_feve.get(Feve.FEVE_MOYENNE) - quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(quantite)*Transformateur2Valeurs.cout_transformation);
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + stock_feve.get(Feve.FEVE_MOYENNE));
				stock_feve.replace(Feve.FEVE_MOYENNE, 0.0);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(stock_feve.get(Feve.FEVE_MOYENNE))*Transformateur2Valeurs.cout_transformation);
			}
			if (o == Chocolat.CONFISERIE_BASSE && stock_feve.get(Feve.FEVE_BASSE) - quantite >= 0) {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
				stock_feve.replace(Feve.FEVE_BASSE, stock_feve.get(Feve.FEVE_BASSE) - quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(quantite)*Transformateur2Valeurs.cout_transformation);
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + stock_feve.get(Feve.FEVE_BASSE));
				stock_feve.replace(Feve.FEVE_BASSE, 0.0);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(stock_feve.get(Feve.FEVE_BASSE))*Transformateur2Valeurs.cout_transformation);
			}
			if (o == Chocolat.CONFISERIE_MOYENNE && stock_feve.get(Feve.FEVE_MOYENNE) - quantite >= 0) {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + quantite);
				stock_feve.replace(Feve.FEVE_MOYENNE, stock_feve.get(Feve.FEVE_MOYENNE) - quantite);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(quantite)*Transformateur2Valeurs.cout_transformation);
			}
			else {
				stock_chocolat.replace((Chocolat) o, stock_chocolat.get(o) + stock_feve.get(Feve.FEVE_MOYENNE));
				stock_feve.replace(Feve.FEVE_MOYENNE, 0.0);
				Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), -(stock_feve.get(Feve.FEVE_MOYENNE))*Transformateur2Valeurs.cout_transformation);
			}
		}
	}
	
		
}