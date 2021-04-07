package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq1Producteur1.Stock;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.IFabricantChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Distributeur2Acteur extends AbsDistributeur2 implements IActeur,IDistributeurChocolatDeMarque,IFabricantChocolatDeMarque,IMarqueChocolat {
	
	protected int cryptogramme;
	protected Stocks stocks;
	protected Achat achat;

	protected Journal journalTransactions, journalVentes, journalStocks, journalAchats, journalCatalogue, journal;


	protected List<Variable> indicateurs;
	protected List<Variable> parametres;

	private ChocolatDeMarque chocoProduit;
	
	

	public Distributeur2Acteur() {
		catalogue = new ArrayList<ChocolatDeMarque>();
		this.chocoProduit = new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,"Wonka & Sons");
		initialisationJournaux();
		parametres = new ArrayList<Variable>();
		indicateurs = new ArrayList<Variable>();
		
	}
	public int getCryptogramme() {
		return this.cryptogramme;
	}
	
	public void initialisationJournaux() {

		journalTransactions = new Journal(getNom() + " : Relevé de compte", this);
		journalTransactions.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Regsitre des opérations bancaires"));
		journalTransactions.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte toutes les oprérations de l'acteur"));
		
		journalVentes = new Journal(getNom() + " : Registre des ventes", this);
		journalVentes.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal des ventes"));
		journalVentes.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les ventes de produits"));
		
		
		journalAchats = new Journal(getNom() + " : Registre des achats", this);
		journalAchats.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal des acahats"));
		journalAchats.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant les achats de produits"));
		
		journal = new Journal(getNom() + " : Informations générales", this);
		journal.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal d'activités"));
		journal.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant"));
		journal.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "l'acteur (changement de stratégie, faillite, ...)."));
		
		journalCatalogue = new Journal(getNom() + " : Catalogue", this);
		journalCatalogue.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Catalogue de produits"));
		journalCatalogue.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal permet de visualiser les produits de marque que propose l'enseigne Wonka & Sons"));
		
		journalStocks= new Journal(getNom() + " : Registre des Stocks", (IActeur)this);
		journalStocks.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Gestion des Stocks"));
		journalStocks.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal regroupe toutes les variations du Stock"));

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
		
		this.initialiserCatalogue();
		for (ChocolatDeMarque CDM : this.catalogue) {
		journalCatalogue.ajouter(Journal.texteColore(Color.WHITE, Color.BLACK , CDM.getMarque()));
		}

		//Filiere.LA_FILIERE.getBanque().creerCompte(this); Notre acteur a deja un compte
		
		this.stocks = new Stocks((Distributeur2)this);
		this.achat = new Achat((Distributeur2)this);

		
	}

	public void next() {
		this.stocks.initialiserChqEtape();
		this.stocks.ajouterChocolatDeMarque(this.chocoProduit, 4);
		this.stocks.supprimerChocolatDeMarque(this.chocoProduit, 2);
		if (Filiere.LA_FILIERE.getEtape()!=0) {
		this.achat.next();
		}
		//this.
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
		List<Variable> res=new ArrayList<Variable>();
		//for (Variable var : this.stocks.stocksParMarque.values()) { //On ajoute les valeurs des stocks.
			//res.add(var);
		//}
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		return this.parametres;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {

		List<Journal> journaux = new ArrayList<Journal>();
		journaux.add(journalTransactions);
		journaux.add(journalVentes);
		journaux.add(journalStocks);
		journaux.add(journalAchats);
		journaux.add(journal);
		journaux.add(journalCatalogue);
		return journaux;

	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
		journalTransactions.ajouter(descriptionColor, Color.BLUE, "Attention " + acteur.getNom() + " est out");
	}

	public void notificationOperationBancaire(double montant) {
		if (montant>0) {
			journalTransactions.ajouter(descriptionColor, Color.GREEN, "Vous avez reçu un virement de " + montant);
		}
		else {
			journalTransactions.ajouter(descriptionColor, Color.RED, "Votre compte vient d'être débité de" + montant); }
	}
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(Filiere.LA_FILIERE.getActeur(getNom()), this.cryptogramme);
	}
	
	//A coder: doit dire si quand le compte est débité d'un tel montant, le solde total sera supérieur au solde critique (Martin) 
	public boolean getAutorisationTransaction(double prix) {
		return true;
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
		journal.ajouter(Journal.texteColore(warningColor, Color.BLACK, "[RAYON] Le rayon de " + choco.name() + " est vide."));		
	}

	@Override
	public List<String> getMarquesChocolat() {
		List<String> marquesProposes = new ArrayList<String>();
		marquesProposes.add(this.chocoProduit.getMarque());
		return marquesProposes;
	}

	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		List<ChocolatDeMarque> choco = new ArrayList<ChocolatDeMarque>();
		choco.add(this.chocoProduit);
		return choco;
	}
	

}
