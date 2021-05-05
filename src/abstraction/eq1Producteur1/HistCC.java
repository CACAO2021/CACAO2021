package abstraction.eq1Producteur1;


/**
 * @author arthurlemerle
 * Classe permettant de suivre le nombre de contrats cadres signés avec chacun des acteurs
 */

public class HistCC {
	private int nbcontrats;
	private String acteur;
	
	public HistCC(String acteur) {
		this.nbcontrats=0;
		this.acteur=acteur;
	}

	public void signercontrat() {
		this.nbcontrats +=1;
	}
	public int getNbcontrats() {
		return this.nbcontrats;
	}
	
	public String getActeur() {
		return acteur;
	}
}
