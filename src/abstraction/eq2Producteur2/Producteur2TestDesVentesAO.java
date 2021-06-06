package abstraction.eq2Producteur2;

import abstraction.eq1Producteur1.Producteur1;
import abstraction.eq8Romu.fevesAO.ExempleAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;

public class Producteur2TestDesVentesAO  extends Filiere {
	
	// ensemble fait par DIM
	
	// a servi au debut pr comprendre le fonctionnement

		public Producteur2TestDesVentesAO() { 
			super();
			//this.ajouterActeur(new Producteur2());
			this.ajouterActeur(new Producteur1());
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_BASSE, 5, 500, 4000));
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_BASSE, 8, 1000, 2000));
			this.ajouterActeur(new ExempleAcheteurFevesAO(Feve.FEVE_MOYENNE, 11, 250, 1500));
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