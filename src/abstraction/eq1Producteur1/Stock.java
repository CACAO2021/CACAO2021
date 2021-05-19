package abstraction.eq1Producteur1;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;

/**
 * @author arthurlemgit
 * @author Alb1x
 * Implémentation basique du stock pour le moment (juste un nombre de fèves), quantité en kg.
 */
public class Stock {
	private Variable quantite;
	private IActeur acteur;
	
	public Stock(String nom, double quantite, IActeur a) {
		this.acteur = a;
		this.quantite= new Variable(nom, acteur);
		this.quantite.setValeur(acteur, quantite);
	}
	public Stock() {
		
	}
	public double getQuantite() {
		return this.quantite.getValeur();
	}
	
	public Variable getVariable() {
		return quantite;
	}
	
	
	public void addQuantite(double d) {
		this.quantite.setValeur(acteur, this.quantite.getValeur()+d);
	}
	
	public void removeQuantite(double d) {
		this.quantite.setValeur(acteur, this.quantite.getValeur()-d);
	}
}
