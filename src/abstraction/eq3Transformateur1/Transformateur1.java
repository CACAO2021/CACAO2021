package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

public class Transformateur1 extends AcheteurFevesContratCadre {
	


	public Transformateur1() { 
		super();
	}
	
	public void next() {
		
		this.getStock().getFinancier().setIndicateurs();
		this.getStock().getFinancier().miseAJourContratVendeur();
		this.nosDemandesCC();
		this.getStock().getFinancier().miseAJourContratAcheteur();
		this.getStock().transformationFeveChocolat();
		this.getStock().coutStock();

		
		
	}
	
	public void initialiser() {
		this.supCCadre = (SuperviseurVentesContratCadre) (Filiere.LA_FILIERE.getActeur("Sup.CCadre")) ;
		this.getStock().setStockFeve(Feve.FEVE_MOYENNE_EQUITABLE, new Variable(this.getNom(), this, 10000), new Variable(this.getNom(), this,500));
	}
}
