package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Feve;

/**
 * 
 * @author lebra
 *
 */

public class Arbre {
	
	private double age;
	private double rendement;
	private double nombre_arbre;
	
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
	
	/**
	 * 
	 * l'arbre ne produit rien avant 3 ans
	 * commence à produire à 3 ans et produit linéarement jusqu'à 6ans
	 * puis production constante jusqu'à la fin de sa vie
	 * rendement annuel de 6kg par arbre
	 */
	
	public double rendement() {
		if (this.getAge() < 3*24) {
			return 0;
		}
		else if (this.getAge() < 6*24){
			return ((this.getAge()*6)/(3*23*24)-6/24);
		}
		else {
			return 6/24;
		}
	}
	
	public Arbre(double age, double nombre_arbre) {
		this.age = age;
		this.nombre_arbre = nombre_arbre;
		this.rendement = this.rendement();
	}
	
	public void augmenter_age() {
		this.setAge(this.getAge()+1);
	}

}
