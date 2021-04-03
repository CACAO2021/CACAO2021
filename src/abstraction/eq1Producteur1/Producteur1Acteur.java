package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Producteur1Acteur implements IActeur {
	protected int cryptogramme;
	protected Stock stock_F_M_E;
	protected Stock stock_F_M;
	protected Stock stock_F_B;
	protected Stock stock_P_M_E;
	protected Stock stock_P_M;
	protected HashMap<String, Stock> stocks;

	public Producteur1Acteur() {
	}

	public void initialiser() {
		this.init_stocks();
		
	}
	
	private void init_stocks() {
		this.stock_F_M_E = new Stock(0);
		this.stock_F_M = new Stock(0);
		this.stock_F_B = new Stock(0);
		this.stock_P_M_E = new Stock(0);
		this.stock_P_M = new Stock(0);
		this.stocks = new HashMap<String, Stock>();
		this.stocks.put("F_M_E", stock_F_M_E);
		this.stocks.put("F_M", stock_F_M);
		this.stocks.put("F_B", stock_F_B);
		this.stocks.put("P_M_E", stock_P_M_E);
		this.stocks.put("P_M", stock_P_M);
	}
	public String getNom() {
		return "EQ1";
	}

	public String getDescription() {
		return "Producteur Ghanao";
	}

	public Color getColor() {
		return new Color(26, 188, 156);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	

	public void next() {
	}
	
	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}
	
	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
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