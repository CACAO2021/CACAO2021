package abstraction.eq1Producteur1;

import java.util.LinkedList;

public abstract class Plantations extends Stocks{
	
	private LinkedList<Arbre> arbresmq;
	private LinkedList<Arbre> arbresmqe;
	private LinkedList<Arbre> arbresbq;
	
	public Plantations() {
		this.arbresmq = new LinkedList<Arbre>();
			}

}
