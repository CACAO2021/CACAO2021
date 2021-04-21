package abstraction.eq2Producteur2;


public class Producteur2 extends Producteur2Param  {

	public Producteur2() {
		super();		
	}
	
	public void initialiser() {
		initJournaux();
	}
	
	public void next() {
		majJournaux();
		// faire fonction probleme : intemperie,...
		prod();
		transfo(); // vide pour le moment
		renouvellement();
		verifPeremption();
		coutStockage();
	}
}