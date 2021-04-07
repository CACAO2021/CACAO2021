package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.clients.ClientFinal;
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
	protected Journal journal;
	

	protected List<ChocolatDeMarque> catalogue;
	private ChocolatDeMarque chocoProduit;
	
	

	public Distributeur2Acteur() {
		this.stocks = new Stocks((Distributeur2)this);
		catalogue = new ArrayList<ChocolatDeMarque>();
		initialisationJournaux();
		this.chocoProduit = new ChocolatDeMarque();
		
		
	}
	
	public void initialisationJournaux() {
		journal = new Journal(getNom() + " : Informations générales", this);
		journal.ajouter(Journal.texteColore(titleColor, Color.WHITE, "EQ7 : Journal d'activités"));
		journal.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "Ce journal rapporte les informations majeures concernant"));
		journal.ajouter(Journal.texteColore(descriptionColor, Color.BLACK, "l'acteur (changement de stratégie, faillite, ...)."));
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
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.journal);
		res.add(this.stocks.journalStocks);
		return res;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
		
	}

	public void notificationFaillite(IActeur acteur) {
	}

	public void notificationOperationBancaire(double montant) {
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

	@Override
	public List<String> getMarquesChocolat() {
		List<String> marquesProposes = new ArrayList<String>();
		marquesProposes.add("Wonka & Sons");
		return marquesProposes;
	}

	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		List<ChocolatDeMarque> marquesProposes = new ArrayList<ChocolatDeMarque>();
		marquesProposes.add(this.chocoProduit);
		return null;
	}

}
