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
<<<<<<< HEAD
		// faire fonction probleme : intemperie,...
=======
		lesProblemes();
>>>>>>> branch 'master' of https://github.com/CACAO2021/CACAO2021
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