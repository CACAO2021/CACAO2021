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
		//transfo(); 		// on ne fait finalement pas de transformation
		renouvellement();
		verifPeremption();
		verifUtiliteStock();
		coutStockage();
		tropDArgent();
	}


	@Override
	public String toString() {
		return "Producteur2, " + getNom() ;
	}
	

}