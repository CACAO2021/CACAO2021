package abstraction.eq6Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Distributeur1Acteur implements IActeur {

	protected int cryptogramme;
	List<Variable> parametres = new ArrayList<Variable>();
	List<Variable> indicateurs = new ArrayList<Variable>();
	List<Journal> journaux  = new ArrayList<Journal>();
	Journal operationBancaire;



	public Distributeur1Acteur() {
		this.operationBancaire = new Journal("Cacaocaisse : toutes les opération bancaires", this);
	}


	public String getNom() {

		return "EQ6";
	}

	public String getDescription() {
		return "CacaoCaisse est un distributeur de type grande surface, il achète le chocolat aux transformateurs et le revend au client final.";
	}

	public Color getColor() {
		return new Color(230, 126, 34);
	}


	public void initialiser() {
		journaux.add(operationBancaire);
	}

	public void next() {

	}


	// Renvoie la liste des filières proposées par l'acteur
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		return(filieres);
	}

	// Renvoie une instance d'une filière d'après son nom
	public Filiere getFiliere(String nom) {
		return Filiere.LA_FILIERE;
	}

	// Renvoie les indicateurs
	public List<Variable> getIndicateurs() {
		return indicateurs;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {

		return parametres;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		//journaux.add(journalVentes);
		//journaux.add(journalStocks);
		//journaux.add(journalAchats);
		return journaux;

	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}

	//Quand un autre acteur fait faillite cette methode est appelee automatiquement pour si on veut l'utiliser
	public void notificationFaillite(IActeur acteur) {
	}

	// quand la banque fait un dépot ou un retrait cette methode est appelée avec le montant en param, pour si on veut l'utiliser pour quelque chose
	public void notificationOperationBancaire(double montant) {
		operationBancaire.ajouter("nouveau virement de "+ montant);

	}

	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

}

