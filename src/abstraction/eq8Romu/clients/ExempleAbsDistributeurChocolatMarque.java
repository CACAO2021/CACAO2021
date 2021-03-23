package abstraction.eq8Romu.clients;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class ExempleAbsDistributeurChocolatMarque implements IActeur {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Integer cryptogramme;
	protected Journal journal;
	protected Variable[] stocksChocolats;
	protected ChocolatDeMarque[] chocolats;

	public ExempleAbsDistributeurChocolatMarque(ChocolatDeMarque[] chocos, double[] stocks) {	
		if (chocos==null || chocos.length<1 || stocks==null || stocks.length!=chocos.length) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsDistributeurChocolatMarqe avec des arguments non valides");
		}		
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.chocolats = chocos;
		this.stocksChocolats=new Variable[stocks.length];
		for (int i=0; i<stocks.length; i++) {
			stocksChocolats[i]=new Variable(this.getNom()+"Stock"+chocos[i].name(), this, 0.0, 1000000000.0,stocks[i]);
		}
		this.journal = new Journal(this.getNom()+" activites", this);
	}

	public String toString() {
		return this.getNom();
	}
	public String getNom() {
		return "D.Choco"+this.numero;
	}

	public String getDescription() {
		return "Distributeur de chocolat "+this.numero;
	}

	public Color getColor() {
		return new Color(128+((numero)*(127/NB_INSTANCES)), 64+((numero)*(191/NB_INSTANCES)), 0);
	}

	public void initialiser() {
	}

	public void next() {
		journal.ajouter("Etape="+Filiere.LA_FILIERE.getEtape());
		if (Filiere.LA_FILIERE.getEtape()>=1) {
			journal.ajouter("Le prix moyen du chocolat \""+chocolats[0].name()+"\" a l'etape precedente etait de "+Filiere.LA_FILIERE.prixMoyen(chocolats[0], Filiere.LA_FILIERE.getEtape()-1));
			journal.ajouter("Les ventes de chocolat \""+chocolats[0]+" il y a un an etaient de "+Filiere.LA_FILIERE.getVentes(chocolats[0], Filiere.LA_FILIERE.getEtape()-24));
		}
	}

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		for (Variable stock : stocksChocolats) {
		res.add(stock);
		}
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}

	public void notificationFaillite(IActeur acteur) {
	}

	public void notificationOperationBancaire(double montant) {
	}
}