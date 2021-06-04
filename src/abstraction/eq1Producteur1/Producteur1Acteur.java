package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq2Producteur2.Producteur2Valeurs;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IFabricantChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class Producteur1Acteur extends Producteur1Valeurs implements IFabricantChocolatDeMarque, IMarqueChocolat {
	private int cryptogramme;
	private Color couleur = new Color(26, 188, 156);
	private Transformation transformation;
	
	
	public List<String> getMarquesChocolat() {
	List<String> liste = new ArrayList<String>();
	liste.add("Ghanao");
	return liste;
	}
	
	
	public List<ChocolatDeMarque> getChocolatsProduits() {
		List<ChocolatDeMarque> liste = new ArrayList<ChocolatDeMarque>();
		liste.add(new ChocolatDeMarque(Chocolat.POUDRE_MOYENNE, "Ghanao"));
		liste.add(new ChocolatDeMarque(Chocolat.POUDRE_MOYENNE_EQUITABLE, "Ghanao"));
		return liste;
	}
	
	public Producteur1Acteur() {
		this.init_historiques();
		
		
	}

	public Producteur1Acteur(boolean b) {
		
	}
	


	public void initialiser() {
		transformation = new Transformation();
		Producteur1Valeurs.init();

	}
	

	
	/**
	 * @author Alb1x
	 * @author lebra pour l'ajout dans le journal
	 */
	private void produireFeve() {
		IActeur a = Filiere.LA_FILIERE.getActeur("EQ1");
		//this.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).addQuantite(this.production_mqe());
		if (this.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).getQuantite()+this.production_mqe()<=10000000) {
			this.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).addQuantite(this.production_mqe());
		} else {
			this.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).setQuantite(a, 10000000);
		}
		if (this.getStocks().get(Feve.FEVE_MOYENNE).getQuantite()+this.production_mq()<=50000000) {
			this.getStocks().get(Feve.FEVE_MOYENNE).addQuantite(this.production_mq());
		} else {
			this.getStocks().get(Feve.FEVE_MOYENNE).setQuantite(a, 50000000);	
		}
		if (this.getStocks().get(Feve.FEVE_BASSE).getQuantite()+this.production_bq() <= 20000000){
			this.getStocks().get(Feve.FEVE_BASSE).addQuantite(this.production_bq());
		} else {
			this.getStocks().get(Feve.FEVE_BASSE).setQuantite(a, 20000000);
		}
		
		this.getJournal("Ghanao Production").ajouter("Ajout de "+this.production_mqe()+" fèves de qualité moyenne équitable");
		this.getJournal("Ghanao Production").ajouter("Ajout de "+this.production_mq()+" fèves de qualité moyenne");
		this.getJournal("Ghanao Production").ajouter("Ajout de "+this.production_bq()+" fèves de qualité basse");
	}
	
	public String getNom() {
		return "EQ1";
	}

	public String getDescription() {
		return "Producteur Ghanao";
	}

	public Color getColor() {
		return couleur;
	}


	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}


	public void next() {
		this.majHist_AO();
		this.produireFeve();
		Cout.cout(this); // coût proportionel à la qualité et à la quantité de fèves produites
		this.transformation.Transformation_Feve(this);
		this.maj_plantation(this.getStocks2(), this);
	}

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		for (Stock stck : this.getStocks().values()) { //On ajoute les valeurs des stocks.
			res.add(stck.getVariable());
		}
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
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
	
	/**
	 * @author arthurlemgit
	 * Pour les coûts fixes et de transformation
	 */
	protected void perteargent(double quantite) {
		if (quantite>0 ) {
				Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("EQ1"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(),quantite );
		}
	}
	/**
	 * @param i
	 * @return
	 */
	public abstract Journal getJournal(String n);
	public abstract ArrayList<Journal> getJournaux();
	public abstract Stock getStock(Object o);
	public abstract HashMap<Object, Stock> getStocks();
	protected abstract void init_historiques();
	protected abstract void majHist_AO();
	public abstract double production_mq ();
	public abstract double production_mqe ();
	public abstract double production_bq ();
	public abstract void maj_plantation(Stocks s, Producteur1Acteur a);
	public abstract Stocks getStocks2();

} 