package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;

public class Arbre {
	
	private double age;
	private double rendement;
	private double nombre_arbre;
	private Feve feve;
	
	/**
	 * @return the nombre_arbre
	 */
	public double getNombre_arbre() {
		return nombre_arbre;
	}
	/**
	 * @param nombre_arbre the nombre_arbre to set
	 */
	public void setNombre_arbre(double nombre_arbre) {
		this.nombre_arbre = nombre_arbre;
	}
	/**
	 * @return the age
	 */
	public double getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(double age) {
		this.age = age;
	}
	/**
	 * @return the rendement
	 */
	public double getRendement() {
		return rendement;
	}
	/**
	 * @param rendement the rendement to set
	 */
	public void setRendement(double rendement) {
		this.rendement = rendement;
	}
	
	public double rendement() {
		return this.rendement;
	}
	
	public Arbre(double age, double nombre_arbre, Feve feve) {
		this.age = age;
		this.nombre_arbre = nombre_arbre;
		this.feve = feve;
		this.rendement = this.rendement();
	}

}
