package abstraction.eq1Producteur1;

import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;

public abstract class Plantations extends Stocks{
	
	private LinkedList<Arbre> arbresmq; //Création des arbres produisant des fèves moyennes
	private LinkedList<Arbre> arbresmqe; //Création des arbres produisant des fèves moyenne équitables
	private LinkedList<Arbre> arbresbq; //Création des arbres produisant des fèves basse qualité
	
	/**
	 * On crée au départ une plantation qui correspond à notre production
	 *  @author louis
	 *  @author Alb1x
	 */
	public Plantations() {
		this.arbresmq = new LinkedList<Arbre>();
		for (int i=40*24; i>=0; i--) {
			arbresmq.add(new Arbre(i,2404000));
		}
		this.arbresmqe = new LinkedList<Arbre>();
		for (int i=40*24; i>=0; i--) {
			arbresmqe.add(new Arbre(i,801333));
		}
		this.arbresbq = new LinkedList<Arbre>();
		for (int i=40*24; i>=0; i--) {
			arbresbq.add(new Arbre(i,2137333));
		}
			}
	
	public void maj_plantation(double new_mq, double new_mqe, double new_bq, Producteur1Acteur a) {
		if (new_mq!=0) {
			this.arbresmq.add(new Arbre(0,new_mq));
			a.getJournal("Ghanao Plantation").ajouter("On a planté "+new_mq+" arbres de moyenne qualité");
			
		}
		if (new_mqe!=0) {
			this.arbresmqe.add(new Arbre(0,new_mqe));
			a.getJournal("Ghanao Plantation").ajouter("On a planté "+new_mqe+" arbres de moyenne qualité équitables");
		}
		if (new_bq!=0) {
			this.arbresbq.add(new Arbre(0,new_bq));
			a.getJournal("Ghanao Plantation").ajouter("On a planté "+new_bq+" arbres de basse qualité");
		}
		
		a.getJournal("Ghanao Plantation").ajouter(this.arbresmq.remove().getNombre_arbre()+" arbres de moyenne qualité sont morts");
		for (Arbre arbre : this.arbresmq) {
				arbre.augmenter_age();
		}
		a.getJournal("Ghanao Plantation").ajouter(this.arbresmqe.remove().getNombre_arbre()+" arbres de moyenne qualité équitables sont morts");
		for (Arbre arbre : this.arbresmqe) {
				arbre.augmenter_age();
		}
		a.getJournal("Ghanao Plantation").ajouter(this.arbresbq.remove().getNombre_arbre()+" arbres de basse qualité sont morts");
		for (Arbre arbre : this.arbresbq) {
				arbre.augmenter_age();
		}
	}
	
	public double production_mq () {
		double prod = 0;
		for (Arbre arbre : this.arbresmq) {
			prod += arbre.getNombre_arbre() * arbre.getRendement();
		}
		return prod;
	}
	
	public double production_mqe () {
		double prod = 0;
		for (Arbre arbre : this.arbresmqe) {
			prod += arbre.getNombre_arbre() * arbre.getRendement();
		}
		return prod;
	}
	
	public double production_bq () {
		double prod = 0;
		for (Arbre arbre : this.arbresbq) {
			prod += arbre.getNombre_arbre() * arbre.getRendement();
		}
		return prod;
	}
	
	/**
	 * on définit un rapport entre notre stock et la quantité initiale (plus tard en rapport avec le stock limite
	 * quand arthur l'aura implementé) pour planter un nombre d'arbres par step qui permettra de plus
	 * se rapprocher d'un nombre d'arbre qui produirait une quantité plus proche de ce que l'on veut
	 */
	
	public double planter_bq(Stocks s)  {
		double rapport = s.getStock(Feve.FEVE_BASSE).getQuantite()/66666666;
		return 2137333/rapport;
	}
	
	public double planter_mq(Stocks s)  {
		double rapport = s.getStock(Feve.FEVE_MOYENNE).getQuantite()/75000000;
		return 2404000/rapport;
	}
	
	public double planter_mqe(Stocks s)  {
		double rapport = s.getStock(Feve.FEVE_MOYENNE_EQUITABLE).getQuantite()/25000000;
		return 801333/rapport;
	}

}
