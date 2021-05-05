/**
 * 
 */
package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.fourni.Journal;

/**
 * @author albou
 *
 */
public class CreationJournaux extends Plantations {
	protected JournauxEq1 journaux;
	private Color couleur = new Color(26, 188, 156);
	
	private void init_journaux() {
		this.journaux = new JournauxEq1();
		this.journaux.addJournal("Ghanao Production", this);
		this.journaux.getJournal("Ghanao Production").ajouter(couleur, Color.black, "==== Journal de la production ===");
		this.journaux.addJournal("Ghanao Transformation", this);
		this.journaux.getJournal("Ghanao Transformation").ajouter(couleur, Color.black,"==== Journal de la transformation ===");
		this.journaux.addJournal("Ghanao VenteAO", this);
		this.journaux.getJournal("Ghanao VenteAO").ajouter(couleur, Color.black,"==== Journal des ventes par offre d'achat ===");
		this.journaux.addJournal("Ghanao VenteContratCadre", this);
		this.journaux.getJournal("Ghanao VenteContratCadre").ajouter(couleur, Color.black,"==== Journal des ventes par contrat cadre ===");
		this.journaux.addJournal("Ghanao Coûts", this);
		this.journaux.getJournal("Ghanao Coûts").ajouter(couleur, Color.black,"==== Journal des coûts ===");
	}
	
	public CreationJournaux() {
		this.init_journaux();
	}
	
	public ArrayList<Journal> getJournaux() {
		return journaux.getJournaux();
	}
	
	public Journal getJournal(String n) {
		return this.journaux.getJournal(n);
	}


}
