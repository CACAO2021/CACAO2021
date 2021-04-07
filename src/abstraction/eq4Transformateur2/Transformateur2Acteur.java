package abstraction.eq4Transformateur2; 

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.Filiere;

//Antoine C

public class Transformateur2Acteur extends Transformateur2Valeurs implements IActeur {

	public Transformateur2Acteur() {
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "EQ4";
	}

	public String getDescription() {
		return "Boni Suci est une entreprise indépendante spécialisée dans la transformation du chocolat. Chez Boni Suci, la satisfaction du client est notre maître mot. Niente braccia niente ciocolati, Boni Suci";
	}

	public Color getColor() {
		return new Color(155, 89, 182);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public void next() {
		getIndicateurs().get(0).setValeur(this, stock_feve_basse);
		getIndicateurs().get(1).setValeur(this, stock_feve_moyenne);
		getIndicateurs().get(2).setValeur(this, stock_tablette_basse);
		getIndicateurs().get(3).setValeur(this, stock_tablette_moyenne);
		getIndicateurs().get(4).setValeur(this, stock_confiserie_basse);
		getIndicateurs().get(5).setValeur(this, stock_confiserie_moyenne);
	}
	
	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}
	
	public List<Variable> getIndicateurs() {
		// on choisit les indicateurs qui nous seront donnés lors de la simu
		List<Variable> res=new ArrayList<Variable>();
		res.add(new Variable("STOCK_FEVE_BASSE", this, 0, 100000, 0));
		res.add(new Variable("STOCK_FEVE_MOYENNE", this, 0, 100000, 0));
		res.add(new Variable("STOCK_TABLETTE_BASSE", this, 0, 100000, 0));
		res.add(new Variable("STOCK_TABLETTE_MOYENNE", this, 0, 100000, 0));
		res.add(new Variable("STOCK_CONFISERIE_BASSE", this, 0, 100000, 0));
		res.add(new Variable("STOCK_CONFISERIE_MOYENNE", this, 0, 100000, 0));
		return res;
	}
	
	public List<Variable> getParametres() {
		// on choisit les paramètres qui seront pris en compte à l'initialisation de la filière
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

	public List<Journal> getJournaux() {
		// pas très utile à notre stade
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
		//notifie
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}

}
