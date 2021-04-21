package abstraction.eq6Distributeur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Distributeur1Acteur implements IActeur {
	
	protected int cryptogramme;
	List<Variable> parametres = new ArrayList<Variable>();
	List<Variable> indicateurs = new ArrayList<Variable>();
	List<Journal> journaux  = new ArrayList<Journal>();
	public Color titleColor = Color.BLACK;
	public Color descriptionColor = Color.ORANGE;
	public Color warningColor = Color.YELLOW;
	
	protected Journal journalVentes, journalStocks, journalAchats;
	
	public void initialisationJournaux() {


		
		journalVentes = new Journal("Registre des ventes", this);
		journalVentes.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ6 : Journal des ventes"));
		journalVentes.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les ventes de produits"));
		
		
		journalAchats = new Journal("Registre des achats ", this);
		journalAchats.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ6 : Journal des acahats"));
		journalAchats.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les achats de produits"));
		

		journalStocks= new Journal("Registre des Stocks ", (IActeur)this);
		journalStocks.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ6 : Gestion des Stocks"));
		journalStocks.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal regroupe toutes les variations du Stock"));

	}
	

	public Distributeur1Acteur() {
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
		List<Journal> journaux = new ArrayList<Journal>();
		journaux.add(journalVentes);
		journaux.add(journalStocks);
		journaux.add(journalAchats);
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
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

}

