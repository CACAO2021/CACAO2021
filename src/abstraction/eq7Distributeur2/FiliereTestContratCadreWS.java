package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeur;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;

public class FiliereTestContratCadreWS extends Filiere{
	
	private SuperviseurVentesContratCadre superviseurCC;

	public FiliereTestContratCadreWS() {

			super();
			this.ajouterActeur(new Distributeur2Acteur());
			this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,"test1")));
			this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE,"test2")));
			this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE,"test3")));
			this.superviseurCC=new SuperviseurVentesContratCadre();
			this.ajouterActeur(this.superviseurCC);
			
		}
}
