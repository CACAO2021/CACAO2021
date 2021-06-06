package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeurAcheteur;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

public class Producteur2TestDesVentesCC extends Filiere {
	// ensemble fait par DIM
	
	// a servi au debut pr comprendre le fonctionnement
	
	private SuperviseurVentesContratCadre superviseurCC;
	
	public Producteur2TestDesVentesCC() {
		super();
		//this.ajouterActeur(new Producteur2());
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Feve.FEVE_HAUTE_EQUITABLE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Feve.FEVE_MOYENNE_EQUITABLE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Feve.FEVE_MOYENNE));
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		
	}

}