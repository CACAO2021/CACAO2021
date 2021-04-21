package abstraction.eq8Romu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.clients.FiliereTestClientFinal;
import abstraction.eq8Romu.contratsCadres.FiliereTestContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.fevesAO.FiliereTestAOFeves;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Romu implements IActeur {
	@SuppressWarnings("unused")
	private Integer cryptogramme;
	protected Journal journal;
	
	public Romu() {
		this.journal = new Journal("Journal "+this.getNom(), this);
	}

	public String getNom() {
		return "EQ8";
	}

	public String getDescription() {
		return "createur des autres acteurs de la filiere";
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public void initialiser() {
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	
	public void next() {
		SuperviseurVentesContratCadre supcc=	(SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		journal.ajouter("Qui vend des feves via contrat cadre :");
		for (Feve f : Feve.values()) {
			journal.ajouter("---feve :"+f+" -->"+supcc.getVendeurs(f));
		}
		journal.ajouter("Qui vend des chocolat via contrat cadre :");
		for (ChocolatDeMarque c : Filiere.LA_FILIERE.getChocolatsProduits()) {
			journal.ajouter("---choco :"+c+" -->"+supcc.getVendeurs(c));
		}
	}

	public List<String> getNomsFilieresProposees() {
		ArrayList<String> filieres = new ArrayList<String>();
		filieres.add("TESTAOF"); 
		filieres.add("TESTCC");
		filieres.add("TESTCLIENT"); 
		return filieres;
	}

	public Filiere getFiliere(String nom) {
		switch (nom) { 
		case "TESTAOF" : return new FiliereTestAOFeves();
		case "TESTCC" : return new FiliereTestContratCadre();
		case "TESTCLIENT" : return new FiliereTestClientFinal();
	    default : return null;
		}
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res =  new ArrayList<Variable>();
		return res;
	}

	public List<Variable> getParametres() {
		return new ArrayList<Variable>();
	}

	public List<Journal> getJournaux() {
		ArrayList<Journal>l=new ArrayList<Journal>();
		l.add(journal);
		return l;
	}
	
	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
			System.out.println("They killed Romu... ");
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}
	
	public void notificationOperationBancaire(double montant) {
	}
}
