package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;



import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;


// Gaspart (À part méthode ou il y a un autre prénom)
public class Transformateur1Acteur implements IActeur {
	
	protected Stock stock;
	protected Integer cryptogramme;
	protected Journal journalAcheteur;
	protected Journal journalVendeur;
	protected Journal journalStock;
	protected Journal journalTresorie;

	
	public static double STOCK_MAX = 10000000000.0;
	protected double SUBVENTION;
	protected double BENEFICE;
	protected double ECHEANCIERMOYACHAT;
	protected double ECHEANCIERMOYVENTE;
	protected double nbContratAchat;
	protected double nbContratVente;
	protected double quantiteBio;
	protected double quantiteEquitable;
	protected double quantiteClassique;

	public Transformateur1Acteur() {
		
		this.journalAcheteur = new Journal(this.getNom()+" achat", this);
		this.journalVendeur = new Journal(this.getNom()+" vente ", this);
		this.journalStock = new Journal(this.getNom()+" stock ", this);
		this.journalTresorie = new Journal(this.getNom()+" trésorie", this);
		this.stock = new Stock(this);
		this.SUBVENTION = 0.0;
		this.BENEFICE = 0.0;
		this.ECHEANCIERMOYACHAT = 0.0;
		this.ECHEANCIERMOYVENTE = 0.0;
		this.quantiteBio = 0.0;
		this.quantiteClassique = 0.0;
		this.quantiteEquitable = 0.0;

		
	}
	public double getQuantiteBio() {
		return this.quantiteBio;
	}
	public double getQuantiteEquitable() {
		return this.quantiteEquitable;
	}
	public double getQuantiteClassique() {
		return this.quantiteClassique;
	}
	
	public double getEcheancierAchatMoy() {
		return this.ECHEANCIERMOYACHAT;
	}
	
	public double getEcheancierVenteMoy() {
		return this.ECHEANCIERMOYVENTE;
	}
	
	public double getSubvention() {
		return this.SUBVENTION;
	}
	
	public double getBenefice() {
		return this.BENEFICE;
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "Eticao";
	}

	public String getDescription() {
		return "Eticao est un transformateur qui prône l'utilisation de cacao éthique";
	}

	public Color getColor() {
		return new Color(52, 152, 219);
	}
	
	public Integer getCryptogramme() {
		return this.cryptogramme;
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public void ecritureJournalAcheteur(String s) {
		this.journalAcheteur.ajouter(s);
	}
	
	public void ecritureJournalVendeur(String s) {
		this.journalVendeur.ajouter(s);
	}
	
	public void ecritureJournalStock(String s) {
		this.journalStock.ajouter(s);
	}
	
	public void ecritureJournalTresorie(String s) {
		this.journalTresorie.ajouter(s);
	}
	
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> res=  new ArrayList<String>();
		return  res ;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) {
		default : return null;
		}

	}
	
	public List<Variable> getIndicateurs() {;
		return this.getStock().getFinancier().getIndicateurs();
	}
	

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.journalAcheteur);
		res.add(this.journalVendeur);
		res.add(this.journalStock);
		res.add(this.journalTresorie);
		return res;
	}
	

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

}