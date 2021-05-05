package abstraction.eq2Producteur2;

public class Producteur2Aleas extends Producteur2Param  {

	public Producteur2Aleas() {
		super();
	}

	public void lesProblemes() {
		// intempéries -> destruction stock
		if (Math.random() < PROBA_INTEMPERIE) { // l'intemperie à lieu dans ce cas
			double pourcentageStockADetruire = Math.random() / 2. ;
			// pourcentage du stock qui sera détruit lors de l'intempérie 
			// il n'est pas possible que plus de la moitie du stock soit détruite
			
			
		}
		
	}
}
