package abstraction.eq4Transformateur2; 

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Tout le monde

public class Transformateur2Acteur extends Transformateur2Valeurs implements IActeur {

	
	public Transformateur2Acteur() {
		super();
		this.journal_achat = new Journal("Journal d'achat de Boni Suci", this);
		this.journal_vente = new Journal("Journal de vente de Boni Suci", this);
		this.journal_transformation = new Journal("Journal de production de Boni Suci", this);
		this.journal_stock = new Journal("Journal de stockage de Boni Suci", this);
		echeancier_basse = new LinkedList<Double>();
		echeancier_moyenne = new LinkedList<Double>();
		echeancier_total = new LinkedList<Double>();
		for (int i=0;i<24;i++) {
			echeancier_basse.add(0.0);
			echeancier_moyenne.add(0.0);
			echeancier_total.add(0.0);
		}
	}

	public void initialiser() {
	}
	
	public String getNom() {
		return "Boni Suci";
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
	
	
	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return Filiere.LA_FILIERE;
	}
	
	public List<Variable> getIndicateurs() {
		// on choisit les indicateurs qui nous seront donnés lors de la simu
		List<Variable> res=new ArrayList<Variable>();
		res.add(var_stock_feve_basse);
		res.add(var_stock_feve_moyenne);
		res.add(var_stock_tablette_basse);
		res.add(var_stock_tablette_moyenne);
		res.add(var_stock_confiserie_basse);
		res.add(var_stock_confiserie_moyenne);
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
		res.add(journal_stock);
		res.add(journal_achat);
		res.add(journal_vente);
		res.add(journal_transformation);
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
		System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("RIP "+acteur.getNom()+". "+this.getNom() + " is happy to take all your money :)");
		}
	}
	
	public void notificationOperationBancaire(double montant) {
		//notifie
	}
	
	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}

	public void update_echeanciers() {
		for (int i=1;i<24;i++) {
			echeancier_basse.set(i-1, echeancier_basse.get(i));
			echeancier_moyenne.set(i-1, echeancier_moyenne.get(i));
			echeancier_total.set(i-1, echeancier_total.get(i));
		}
		echeancier_basse.set(23, 0.0);
		echeancier_moyenne.set(23, 0.0);
		echeancier_total.set(23, 0.0);
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	
	// Test
	
}
