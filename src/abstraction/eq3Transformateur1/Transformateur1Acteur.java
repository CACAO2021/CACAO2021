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

public class Transformateur1Acteur implements IActeur {
	
	protected Stock stock;
	protected Integer cryptogramme;
	protected Journal journalAcheteur;
	protected Journal journalVendeur;
	protected Journal journalStock;
	protected Journal journalTransformation;
	protected Journal journalTresorie;
	protected List<Variable> indicateurs;
	
	public static double STOCK_MAX = 10000000000.0;

	public Transformateur1Acteur() {
		
		this.journalAcheteur = new Journal(this.getNom()+" achat", this);
		this.journalVendeur = new Journal(this.getNom()+" vente ", this);
		this.journalStock = new Journal(this.getNom()+" stock ", this);
		this.journalTransformation = new Journal(this.getNom()+" transformation", this);
		this.journalTresorie = new Journal(this.getNom()+" trésorie", this);
		this.stock = new Stock(this);
		this.indicateurs = new ArrayList<Variable>();
		
		this.indicateurs.add(0,new Variable(this.getNom() + " Stock fève basse qualité", this, 0));
		this.indicateurs.add(1,new Variable(this.getNom() + " Stock fève moyenne qualité", this, 0));
		this.indicateurs.add(2,new Variable(this.getNom() + " Stock fève moyenne qualité equitable", this, 0));
		this.indicateurs.add(3,new Variable(this.getNom() + " Stock fève haute qualité equitable", this, 0));
		this.indicateurs.add(4,new Variable(this.getNom() + " Stock fève haute qualité équitable et bio", this, 0));
		this.indicateurs.add(5,new Variable(this.getNom() + " Stock tablette basse qualité", this, 0));
		this.indicateurs.add(6,new Variable(this.getNom() + " Stock tablette moyenne qualité", this, 0));
		this.indicateurs.add(7,new Variable(this.getNom() + " Stock tablette moyenne qualité equitable", this, 0));
		this.indicateurs.add(8,new Variable(this.getNom() + " Stock tablette haute qualité equitable", this, 0));
		this.indicateurs.add(9,new Variable(this.getNom() + " Stock tablette haute qualité équitable et bio", this, 0));
		this.indicateurs.add(10,new Variable(this.getNom() + " Stock confiserie basse qualité", this, 0));
		this.indicateurs.add(11,new Variable(this.getNom() + " Stock confiserie moyenne qualité", this, 0));
		this.indicateurs.add(12,new Variable(this.getNom() + " Stock confiserie moyenne qualité equitable", this, 0));
		this.indicateurs.add(13,new Variable(this.getNom() + " Stock confiserie haute qualité equitable", this, 0));
		this.indicateurs.add(14,new Variable(this.getNom() + " Stock confiserie haute qualité équitable et bio", this, 0));		
		this.indicateurs.add(15,new Variable(this.getNom() + " Stock poudre basse qualité", this, 0));
		this.indicateurs.add(16,new Variable(this.getNom() + " Stock poudre moyenne qualité", this, 0));
		this.indicateurs.add(17,new Variable(this.getNom() + " Stock poudre moyenne qualité equitable", this, 0));
		this.indicateurs.add(18,new Variable(this.getNom() + " Stock poudre haute qualité equitable", this, 0));
		this.indicateurs.add(19,new Variable(this.getNom() + " Stock poudre haute qualité équitable et bio", this, 0));
		this.indicateurs.add(20,new Variable(this.getNom() + " Prix de stockage", this, 1000));
		
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
	

	public void next() {
		
		this.getStock().transformationFeveChocolat();
		this.getStock().coutStock();
		this.setIndicateurs();
		this.getStock().setStockFeve(Feve.FEVE_HAUTE_BIO_EQUITABLE, new Variable(this.getNom(), this, 3000),  new Variable(this.getNom(), this, 5000));
		
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
		return this.indicateurs;
	}
	
	
	public void setIndicateurs() {
		Integer compteur = 0;
		for (Feve feve : this.getStock().nosFeves()) {
			this.getIndicateurs().get(compteur).setValeur(this, this.getStock().getStockFeves(feve));
			compteur += 1;
		}
		for(Chocolat chocolat: this.getStock().nosChocolats()) {
			this.getIndicateurs().get(compteur).setValeur(this, this.getStock().getStockChocolats(chocolat));
			compteur += 1;
		}
		this.getIndicateurs().get(compteur).setValeur(this, this.getStock().getPrixStockage().getValeur());
	}
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.journalAcheteur);
		res.add(this.journalVendeur);
		res.add(this.journalTransformation);
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

}