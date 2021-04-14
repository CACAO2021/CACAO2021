package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeurAcheteur;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.fourni.Filiere;

public class Producteur2TestDesVentesCC extends Filiere {
	
	private SuperviseurVentesContratCadre superviseurCC;
	
	public Producteur2TestDesVentesCC() {
		super();
		this.ajouterActeur(new Producteur2());
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		
	}

}
