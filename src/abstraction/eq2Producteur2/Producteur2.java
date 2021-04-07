package abstraction.eq2Producteur2;

<<<<<<< HEAD
public class Producteur2 extends Producteur2Acteur {
=======

public class Producteur2 extends Producteur2Param  {
>>>>>>> branch 'master' of https://github.com/CACAO2021/CACAO2021

	public Producteur2() {
		super();		
	}
	
	public void initialiser() {
		initJournaux();
	}
<<<<<<< HEAD
=======
	
	public void next() {
		majJournaux();
		prod();
		transfo();
		renouvellement();
		coutTotDuStep();	
		verifPeremption();
	}
>>>>>>> branch 'master' of https://github.com/CACAO2021/CACAO2021
}