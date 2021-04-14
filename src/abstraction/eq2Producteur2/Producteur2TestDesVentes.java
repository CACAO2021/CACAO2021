package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;

public class Producteur2TestDesVentes  extends Filiere {

		public Producteur2TestDesVentes() {
			super();
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_BASSE, 0.85, 500, 4000));
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_BASSE, 0.8, 1000, 2000));
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_MOYENNE, 1.1, 250, 1500));
			SuperviseurVentesFevesAO superviseur = new SuperviseurVentesFevesAO();
			this.ajouterActeur(superviseur);
		}
		/**
		 * Redefinition afin d'interdire l'acces direct au superviseur.
		 * Sans cela, il serait possible de contourner l'autentification
		 */
		public IActeur getActeur(String nom) {
			if (!nom.equals("Sup.Feves.A.O.")) {
				return super.getActeur(nom); 
			} else {
				return null;
			}
		}


	}
}
