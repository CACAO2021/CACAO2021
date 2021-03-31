package abstraction.eq2Producteur2;

//Emeline

public class Producteur2Prod extends Producteur2Acteur {
	private int arbre_plante;
	private int duree_de_vie;
	
	
	public Producteur2Prod(int arbre_plante, int duree_de_vie) {
		this.arbre_plante=arbre_plante;
		this.duree_de_vie=duree_de_vie;
	}

	/**
	 * @return the arbre_plante
	 */
	public int getArbre_plante() {
		return arbre_plante;
	}

	/**
	 * @param arbre_plante the arbre_plante to set
	 */
	public void setArbre_plante(int arbre_plante) {
		this.arbre_plante = arbre_plante;
	}

	/**
	 * @return the duree_de_vie
	 */
	public int getDuree_de_vie() {
		return duree_de_vie;
	}

	/**
	 * @param duree_de_vie the duree_de_vie to set
	 */
	public void setDuree_de_vie(int duree_de_vie) {
		this.duree_de_vie = duree_de_vie;
	}
	
	
	

}
