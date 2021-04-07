package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Transformateur1Acteur implements IActeur {
	
	protected int cryptogramme;
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	private int numero;
	private Variable totalStocksFeves;
	protected Map<Feve, Double> stocksFeves;
	protected Integer cryptogramme;
	protected Journal journalAcheteur;
	protected Journal journalVendeur;
	protected Journal journalStock;
	protected Journal journalTransformation;
	
	public static double STOCK_MAX = 10000000000.0;

>>>>>>> branch 'master' of https://github.com/paulgiraudIMT/CACAO2021
	public Transformateur1Acteur() {
		
		this.totalStocksFeves=new Variable(getNom()+" total stocks feves", this, 50);
		stocksFeves=new HashMap<Feve, Double>();
		for (Feve feve : Feve.values()) {
			stocksFeves.put(feve, 0.0);
		}
		this.journalAcheteur = new Journal(this.getNom()+" achat", this);
		this.journalVendeur = new Journal(this.getNom()+" vente ", this);
		this.journalStock = new Journal(this.getNom()+" stock ", this);
		this.journalTransformation = new Journal(this.getNom()+" transformation", this);
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
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	

	public void next() {
		
	}
	
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> res=  new ArrayList<String>();
		return  res ;
	}

	public Filiere getFiliere(String nom) {
<<<<<<< HEAD
		for(String nomFiliere : this.getNomsFilieresProposees()) {
			if (nomFiliere.equals(nom)) {
				return null;
			}
=======
		switch (nom) {
		default : return null;
		}
>>>>>>> branch 'master' of https://github.com/paulgiraudIMT/CACAO2021
	}
	
	public List<Variable> getIndicateurs() {;
	
		List<Variable> res=new ArrayList<Variable>();
		return res;
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