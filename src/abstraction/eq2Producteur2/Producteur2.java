package abstraction.eq2Producteur2;



public class Producteur2 extends Producteur2VeudeurFeveCC {

	public Producteur2() {
		super();
	}
	
	public void next() {
		System.out.println("next");
		verifPeremption();
		prod();
		transfo();
		renouvellement();
		coutTotDuStep(); 
	}
}