package abstraction.eq2Producteur2;

//Emeline

public class Producteur2Prod extends Producteur2Acteur {
	private int arbre_plante;
	private int duree_de_vie;
	private double pourcent_haute_bio_equitable;
	private double pourcent_haute_equitable;
	private double pourcent_moyenne_equitable;
	private double pourcent_moyenne;
	private double pourcent_basse;
	private int salaire_min;
	
// prendre en compte le salaire des employés
// mettre a jour les arbres plantés en fonction de leur durée de vie 
	
	
	public Producteur2Prod(int arbre_plante, int duree_de_vie,double pourcent_haute_bio_equitable,double pourcent_haute_equitable,double pourcent_moyenne_equitable,double pourcent_moyenne,double pourcent_basse, int salaire_min) {
		this.arbre_plante=arbre_plante;
		this.duree_de_vie=duree_de_vie;
		this.pourcent_haute_bio_equitable=pourcent_haute_bio_equitable;
		this.pourcent_haute_equitable= pourcent_haute_equitable;
		this.pourcent_moyenne_equitable=pourcent_moyenne_equitable;
		this.pourcent_moyenne=pourcent_moyenne;
		this.pourcent_basse=pourcent_basse;
		this.salaire_min=salaire_min;
	}

	public int getArbre_plante() {
		return this.arbre_plante;
	}


	public void setArbre_plante(int arbre_plante) {
		this.arbre_plante = arbre_plante;
	}


	public int getDuree_de_vie() {
		return this.duree_de_vie;
	}


	public void setDuree_de_vie(int duree_de_vie) {
		this.duree_de_vie = duree_de_vie;
	}
	
	// On plante n arbre 
	public void n_arbre_supplémentaire(int n) {
		this.setArbre_plante(this.getArbre_plante()+n);
	}
	
	/* A revoir mais si on a s step de passé, des arbres vont mourir, on réactualise le nombre d'arbre 
	 *  Il faut enregistrer quand on plante des arbres. Faire une liste d'arbre avec le temps auquel il a été planté 
	 */
	public void step_passe(int s) {
		
	}
		
	
	

}
