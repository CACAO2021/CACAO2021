/**
 * 
 */
package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;

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
		this.journaux.getJournal(0).ajouter(couleur, Color.black, "==== Journal de la production ===");
		this.journaux.addJournal("Ghanao Transformation", this);
		this.journaux.getJournal(1).ajouter(couleur, Color.black,"==== Journal de la transformation ===");
		this.journaux.addJournal("Ghanao VenteAO", this);
		this.journaux.getJournal(2).ajouter(couleur, Color.black,"==== Journal des ventes par offre d'achat ===");
		this.journaux.addJournal("Ghanao VenteContratCadre", this);
		this.journaux.getJournal(3).ajouter(couleur, Color.black,"==== Journal des ventes par contrat cadre ===");
	}
	
	public CreationJournaux() {
		this.init_journaux();
	}
	
	public JournauxEq1 getJournaux() {
		return journaux;
	}
	
	public Journal getJournal(int i) {
		return journaux.getJournal(i);
	}

}
