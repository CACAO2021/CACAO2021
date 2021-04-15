package abstraction.eq3Transformateur1;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

public class Transformateur1 extends AcheteurFevesContratCadre {


	public Transformateur1() { 
		super();
	}
	
	public void next() {

		this.getStock().transformationFeveChocolat();
		this.getStock().getFinancier().setIndicateurs();
		this.getStock().getFinancier().miseAJourContratVendeur();
		this.nosDemandesCC();
		this.getStock().coutStock();
		this.getStock().setStockFeve(Feve.FEVE_HAUTE_BIO_EQUITABLE, new Variable(this.getNom(), this, 3000),  new Variable(this.getNom(), this, 5000));
		
		
	}
	
	public void initialiser() {
		this.setSupCCadre();
	}
}
