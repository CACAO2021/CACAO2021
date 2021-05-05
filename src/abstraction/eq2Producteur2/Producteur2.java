package abstraction.eq2Producteur2;

//DIM

public class Producteur2 extends Producteur2Aleas  {

	public Producteur2() {
		super();		
	}
	
	public void initialiser() {
		initJournaux();
	}
	
	//DIM
	public void next() {
		majJournaux();
		lesProblemes();
		prod();
		transfo(); // vide pour le moment
		renouvellement();
		verifPeremption();
		coutStockage();
	}


	@Override
	public String toString() {
		return "Producteur2, " + getNom() ;
	}
	

}