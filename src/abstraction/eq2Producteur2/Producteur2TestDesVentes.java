package abstraction.eq2Producteur2;

public class Producteur2TestDesVentes {
	package abstraction.eq8Romu.fevesAO;



	import abstraction.eq8Romu.produits.Feve;
	import abstraction.fourni.Filiere;
	import abstraction.fourni.IActeur;

	public class FiliereTestAOFeves extends Filiere {

		public FiliereTestAOFeves() {
			super();
			this.ajouterActeur(new ExempleVendeurFevesAO(Feve.FEVE_BASSE, 1.0, 20.0, 0.65, 1.65));
			this.ajouterActeur(new ExempleVendeurFevesAO(Feve.FEVE_BASSE, 1.0, 20.0, 0.75, 1.35));
			this.ajouterActeur(new ExempleVendeurFevesAO(Feve.FEVE_MOYENNE, 0.5, 10.0, 0.95, 1.95));
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
