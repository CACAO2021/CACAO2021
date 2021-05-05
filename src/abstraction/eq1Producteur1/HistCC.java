package abstraction.eq1Producteur1;


/**
 * @author arthurlemerle
 * Classe permettant de suivre le nombre de contrats cadres signés avec chacun des acteurs
 */

public class HistCC {
	private int nbcontrats;
	private String acteur;
	protected HistCC cceq2;
	protected HistCC cceq3;
	protected HistCC cceq4;
	protected HistCC cceq5;
	protected HistCC cceq6;
	protected HistCC cceq7;	
	/**
	 * @author arthurlemerle
	 * On initialise la fidélité des clients
	 */
	private void initHistCC() {
		this.cceq2 = new HistCC("EQ2");
		this.cceq3=new HistCC("EQ3");
		this.cceq4=new HistCC("EQ4");
		this.cceq5=new HistCC("EQ5");
		this.cceq6=new HistCC("EQ6");
		this.cceq7=new HistCC("EQ7");
	}
	
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
