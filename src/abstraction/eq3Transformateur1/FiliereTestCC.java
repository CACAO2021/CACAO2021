package abstraction.eq3Transformateur1;

import abstraction.eq2Producteur2.Producteur2;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.fourni.Filiere;

public class FiliereTestCC  extends Filiere{

	private SuperviseurVentesContratCadre superviseurCC;
	
	public FiliereTestCC() {
		super();
		this.ajouterActeur(new Producteur2());
		this.ajouterActeur(new Transformateur1());
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		
	}

}
