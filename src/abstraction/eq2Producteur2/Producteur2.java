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
		prod();
		transfo();
		renouvellement();
		coutTotDuStep();	
		verifPeremption();
	}
}