package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Distributeur2Acteur extends AbsDistributeur2 implements IActeur,IDistributeurChocolatDeMarque {
	
	protected int cryptogramme;
	protected Stocks stocks;
	protected Journal journalActivités, journalVentes, journalStocks, journalAchats;
	protected List<ChocolatDeMarque> catalogue;
	protected List<Variable> indicateurs;
	protected List<Variable> parametres;
	

	public Distributeur2Acteur() {
		this.stocks = new Stocks(this);
		catalogue = new ArrayList<ChocolatDeMarque>();
		initialisationJournaux();
		
		
		
	}
	
	public void initialisationJournaux() {
		journalActivités = new Journal(getNom() + " : Informations générales", this);
		journalActivités.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal d'activités"));
		journalActivités.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant"));
		journalActivités.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "l'acteur (changement de stratégie, faillite, ...)."));
		
		journalVentes = new Journal(getNom() + " : Registre des ventes", this);
		journalVentes.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal des ventes"));
		journalVentes.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les ventes de produits"));
		
		journalStocks = new Journal(getNom() + " : Registre des stocks", this);
		journalStocks.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal des stocks"));
		journalStocks.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant l'état des stocks"));
		
		journalAchats = new Journal(getNom() + " : Registre des achats", this);
		journalAchats.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal des acahats"));
		journalAchats.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les achats de produits"));
	}

	
	
	public String getNom() {
		return "EQ7";
	}

	public String getDescription() {
		return "Nous sommes Wonka & Sons, fort d'une experience de 50 années, nous sommes les leaders dans les produits de luxe";
	}

	public Color getColor() {
		return new Color(240, 195, 15); 
	}

	public void initialiser() {
		for (ChocolatDeMarque CDM : Filiere.LA_FILIERE.getChocolatsProduits()) {
			catalogue.add(CDM);
		}
		Filiere.LA_FILIERE.getBanque().creerCompte(this);
		
		
		
		
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
		List<Variable> res = new ArrayList<Variable>();
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		return this.parametres;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> journaux = new ArrayList<Journal>();
		journaux.add(journalActivités);
		journaux.add(journalVentes);
		journaux.add(journalStocks);
		journaux.add(journalAchats);
		return journaux;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
		journalActivités.ajouter(descriptionColor, Color.BLUE, "Attention " + acteur.getNom() + " est out");
	}

	public void notificationOperationBancaire(double montant) {
		if (montant>0) {
			journalActivités.ajouter(descriptionColor, Color.GREEN, "Vous avez reçu un virement de " + montant);
		}
		else {
			journalActivités.ajouter(descriptionColor, Color.RED, "Votre compte vient d'être débité de" + montant); }
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}

	@Override
	public List<ChocolatDeMarque> getCatalogue() {
		return this.catalogue;
	}

	@Override
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		
	}

}
