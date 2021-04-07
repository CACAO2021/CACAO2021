package abstraction.eq2Producteur2;


public class Producteur2 extends Producteur2VeudeurFeveCC  {

	public Producteur2() {
		super();		
	}
	
	public void initialiser() {
		//initJournaux();
	}
	
	public void next() {
		//majJournaux();
		verifPeremption();
		prod();
		transfo();
		renouvellement();
		coutTotDuStep();		
	}
}