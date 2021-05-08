package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;


// Paul
public class Transformateur1 extends AcheteurFevesContratCadre {
	
	public Transformateur1() { 
		super();
	}
	
	public void next() {
		
		this.getStock().gestionDesPeremptions();
		this.getStock().getFinancier().setIndicateurs();
		this.getStock().getFinancier().miseAJourContratVendeur();
		this.nosDemandesCC();
		this.getStock().getFinancier().miseAJourContratAcheteur();
		//this.getStock().transformationFeveChocolat(Filiere.LA_FILIERE.getEtape());
		this.getStock().transformationFeveChocolat();
		this.getStock().coutStock();

		
		
	}
	
	public void initialiser() {
		this.setSupCCadre();
		this.getStock().initialiserLeStock();
	}
	
	public String toString() {
		return this.getNom();
	}
}
