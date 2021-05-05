package abstraction.eq1Producteur1;

import java.util.LinkedList;

public abstract class Plantations extends Stocks{
	
	private LinkedList<Arbre> arbresmq; //Création des arbres produisant des fèves moyennes
	private LinkedList<Arbre> arbresmqe; //Création des arbres produisant des fèves moyenne équitables
	private LinkedList<Arbre> arbresbq; //Création des arbres produisant des fèves basse qualité
	
	public Plantations() {
		this.arbresmq = new LinkedList<Arbre>();
			}

}
