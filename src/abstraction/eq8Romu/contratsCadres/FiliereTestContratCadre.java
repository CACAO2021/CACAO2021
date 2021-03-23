package abstraction.eq8Romu.contratsCadres;

import abstraction.eq8Romu.produits.Beurre;
import abstraction.fourni.Filiere;

public class FiliereTestContratCadre extends Filiere {

	private SuperviseurVentesContratCadre superviseurCC;

	public FiliereTestContratCadre() {
		super();
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeurAcheteur(Beurre.BEURRE_MOYENNE));
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
	}
}
