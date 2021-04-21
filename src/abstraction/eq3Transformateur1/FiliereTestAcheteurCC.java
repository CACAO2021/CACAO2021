package abstraction.eq3Transformateur1;

import abstraction.eq2Producteur2.Producteur2;
import abstraction.eq7Distributeur2.Distributeur2;
import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeurAcheteur;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.fourni.Filiere;

public class FiliereTestAcheteurCC extends Filiere {
	
	
	private SuperviseurVentesContratCadre superviseurCC;
	
	public FiliereTestAcheteurCC() {
		super();
		this.ajouterActeur(new Producteur2());
		this.ajouterActeur(new Transformateur1());
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		
	}

}
