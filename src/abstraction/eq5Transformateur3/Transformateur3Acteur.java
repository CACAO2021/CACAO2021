package abstraction.eq5Transformateur3;

//Manuelo

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ContratCadre;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

import abstraction.eq8Romu.produits.Feve;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class Transformateur3Acteur implements IActeur {
	
	protected int cryptogramme;
	private String nom;
	private String description;

	protected Journal JournalRetraitStock, JournalAjoutStock, JournalAchatContratCadre, JournalVenteContratCadre, JournalOA;
	protected Variable prix_max_fèves, stock_min_feves, stock_min_confiserie, stock_min_tablettes_HBE, stock_min_tablettes_moyenne, coefficient_transformation, pourcentage_confiserie, pourcentage_tablette_moyenne, prix_min_vente_MG, prix_min_vente_EQ, prix_min_vente_confiserie, prix_tablette, prix_tablette_equi, prix_confiserie;


	public Transformateur3Acteur() {
		this.nom = "EQ5";
		this.description = "Côte d'IMT, chocolatier de qualité";
		this.JournalAjoutStock = new Journal(this.getNom()+" ajout dans le stock", this);
		this.JournalRetraitStock = new Journal(this.getNom()+" retrait dans le stock", this);
		this.JournalAchatContratCadre = new Journal(this.getNom()+" achat d'un contrat cadre", this);
		this.JournalVenteContratCadre = new Journal(this.getNom()+" vente d'un contrat cadre", this);
		this.JournalOA = new Journal(this.getNom()+ "Offre d'achat", this);
		this.prix_max_fèves = new Variable("Prix max d'achat de fèves", this, 1000);
		this.stock_min_feves = new Variable("Stock minimal de fèves", this, 12000);
		this.stock_min_confiserie = new Variable("Stock minimal de confiseries", this, 12000);
		this.stock_min_tablettes_HBE = new Variable("Stock minimal de tablettes haute bio équitable", this, 12000);
		this.stock_min_tablettes_moyenne = new Variable("Stock minimal de tablettes moyenne", this, 120000);
		this.prix_min_vente_MG = new Variable("Prix min vente de chocolat moyenne gamme", this, 1);
	    this.prix_min_vente_EQ = new Variable("Prix min vente de chocolat equitable", this, 1);
	    this.prix_min_vente_confiserie = new Variable("Prix min de vente confiserie", this, 1);
		this.coefficient_transformation =  new Variable("Coefficient de transformation de fèves en chocolat (40g de fèves pour 100g de chocolat)", this, 2.5);
		this.pourcentage_confiserie = new Variable("Pourcentage de fèves de gamme moyenne transformées en confiseries", this, 0.2);
		this.prix_tablette = new Variable("Prix tablette moyenne", this, 2);
		this.prix_tablette_equi = new Variable("Prix tablette équitable", this, 2);
		this.prix_confiserie = new Variable("Prix confiserie", this, 2);

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
	
	public void actualiserJournaux() {
		this.JournalAjoutStock.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
		this.JournalRetraitStock.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
		this.JournalAchatContratCadre.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
		this.JournalVenteContratCadre.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
		this.JournalOA.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
	}



	public void next() {
		this.actualiserJournaux();
		
		Variable feve = this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		if(feve.getValeur()- 500>0) { //garder au minimum 500kg
			double transfo = feve.getValeur()-500;
			this.retirer(Feve.FEVE_HAUTE_BIO_EQUITABLE, transfo ); //retirer le surplus de fèves 
			this.ajouter(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, (transfo)*coefficient_transformation.getValeur()); //pour le transformer en tablette haute qualité (multiplié par le coef de transformation)
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), 500*1.15*(transfo)*coefficient_transformation.getValeur()/1000); }
	
		
		feve = this.getFeves().get(Feve.FEVE_MOYENNE);
		if(feve.getValeur()-500>0) { //garder au minimum 500kg
			double transfo = feve.getValeur()-500; 
			this.retirer(Feve.FEVE_MOYENNE, transfo); //retirer le surplus de fèves 
			this.ajouter(Chocolat.TABLETTE_MOYENNE, (transfo)*coefficient_transformation.getValeur()*(1-pourcentage_confiserie.getValeur())); //pour le transformer en tablette haute qualité (multiplié par le coef de transformation)
			this.ajouter(Chocolat.CONFISERIE_MOYENNE, (transfo)*coefficient_transformation.getValeur()*pourcentage_confiserie.getValeur()); 
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), 500*((transfo)*coefficient_transformation.getValeur()*(1-pourcentage_confiserie.getValeur())+(transfo)*coefficient_transformation.getValeur()*pourcentage_confiserie.getValeur())/1000);
		} 
		
		SuperviseurVentesContratCadre SupCCadre = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		feve = this.getFeves().get(Feve.FEVE_MOYENNE);
		if(feve.getValeur()<this.stock_min_feves.getValeur()) {
			IVendeurContratCadre vendeur = null;
			List<IVendeurContratCadre> vendeurs = SupCCadre.getVendeurs(Feve.FEVE_MOYENNE);
			vendeur=vendeurs.get((int)( Math.random()*vendeurs.size())); //prend le premier vendeur de la liste...à modifier
			ExemplaireContratCadre contratCadre = SupCCadre.demande((IAcheteurContratCadre)this, vendeur, Feve.FEVE_MOYENNE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10), cryptogramme, false); 
			this.JournalAchatContratCadre.ajouter(contratCadre.toString());
		}
		
		//SuperviseurVentesContratCadre SupCCadre2 = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		feve=this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		if(feve.getValeur()<this.stock_min_feves.getValeur()) {
			IVendeurContratCadre vendeur = null;
			List<IVendeurContratCadre> vendeurs = SupCCadre.getVendeurs(Feve.FEVE_HAUTE_BIO_EQUITABLE);
			vendeur=vendeurs.get((int)( Math.random()*vendeurs.size())); //prend le premier vendeur de la liste...à modifier
			ExemplaireContratCadre contratCadre = SupCCadre.demande((IAcheteurContratCadre)this, vendeur, Feve.FEVE_HAUTE_BIO_EQUITABLE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER/10), cryptogramme, false);
			this.JournalAchatContratCadre.ajouter(contratCadre.toString());
		}
	
	
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
		List<Variable> res = new ArrayList<Variable>();
		res.add(this.coefficient_transformation);
		res.add(this.pourcentage_confiserie);
		res.add(this.prix_max_fèves);
		res.add(this.stock_min_feves);
		res.add(this.stock_min_confiserie);
		res.add(this.stock_min_tablettes_HBE);
		res.add(this.stock_min_tablettes_moyenne);
		res.add(this.prix_min_vente_MG);
		res.add(this.prix_min_vente_EQ);

		return res;
	}

	// Renvoie les journaux
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.JournalAjoutStock);
		res.add(this.JournalRetraitStock);
		res.add(this.JournalAchatContratCadre);
		res.add(this.JournalVenteContratCadre);
		res.add(this.JournalOA);
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
	
	public abstract void retirer(Feve feve, double delta);
	public abstract void ajouter(Chocolat chocolat, double delta);
	public abstract HashMap<Feve, Variable> getFeves();

}

