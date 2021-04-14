package abstraction.eq5Transformateur3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Transformateur3Acteur implements IActeur {
	
	protected int cryptogramme;
	private String nom;
	private String description;
	protected Variable prix_max_fèves, stock_min_feves, stock_min_confiserie, stock_min_tablettes_HBE, stock_min_tablettes_moyenne, coefficient_transformation;
	protected Journal JournalRetraitStock, JournalAjoutStock, JournalAchatContratCadre, JournalVenteContratCadre;

	public Transformateur3Acteur() {
		this.nom = "EQ5";
		this.description = "Côte d'IMT, chocolatier de qualité";
		this.JournalAjoutStock = new Journal(this.getNom()+" ajout dans le stock", this);
		this.JournalRetraitStock = new Journal(this.getNom()+" retrait dans le stock", this);
		this.JournalAchatContratCadre = new Journal(this.getNom()+" achat d'un contrat cadre", this);
		this.JournalVenteContratCadre = new Journal(this.getNom()+" vente d'un contrat cadre", this);
		this.prix_max_fèves = new Variable("Prix max d'achat de fèves", this, 1000);
		this.stock_min_feves = new Variable("Stock minimal de fèves", this, 12000);
		this.stock_min_confiserie = new Variable("Stock minimal de confiseries", this, 12000);
		this.stock_min_tablettes_HBE = new Variable("Stock minimal de tablettes haute bio équitable", this, 12000);
		this.stock_min_tablettes_moyenne = new Variable("Stock minimal de tablettes moyenne", this, 120000);
		this.coefficient_transformation =  new Variable("Coefficient de transformation de fèves en chocolat (40g de fèves pour 100g de chocolat)", this, 2.5);
	}

	public String getNom() {
		return nom;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	
	public Color getColor() {
		return new Color(233, 30, 99);
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
		List<Variable> res = new ArrayList<Variable>();
		return res;
	}

	// Renvoie les paramètres
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(prix_max_fèves);
		res.add(stock_min_feves);
		res.add(stock_min_confiserie);
		res.add(stock_min_tablettes_HBE);
		res.add(stock_min_tablettes_moyenne);
		res.add(coefficient_transformation);
		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.JournalAjoutStock);
		res.add(this.JournalRetraitStock);
		res.add(this.JournalAchatContratCadre);
		res.add(this.JournalVenteContratCadre);
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

}

