package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.fevesAO.FiliereTestAOFeves;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class Producteur2Acteur extends Producteur2Valeurs implements IActeur {
	protected int cryptogramme;
	
	//ensemble fait par DIM
	
	public Producteur2Acteur() {
		super();
	}

	public void initialiser() {
		// override en producteur2, ne rien ecrire ici
	}
	
	public String getNom() {
		return "Baratao";
	}

	public String getDescription() {
		return "Les meilleurs producteurs";
	}

	public Color getColor() {
		return new Color(46, 204, 113);
	}
	

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	} 
	

	public void next() {
		// override en producteur2, ne rien ecrire ici
	}
	
	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTVENTECC");
		filieres.add("TESTVENTEAO");
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTVENTECC" : return new Producteur2TestDesVentesCC();
		case "TESTVENTEAO" : return new Producteur2TestDesVentesAO();
	    default : return null;
		}
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
	
	public abstract void perdreArgent(double montant) ;
	

}