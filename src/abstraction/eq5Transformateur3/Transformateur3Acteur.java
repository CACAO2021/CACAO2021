package abstraction.eq5Transformateur3;

//Manuelo,  Rémi,  Léna

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	protected Variable prix_max_fèves_HBE, prix_max_fèves_moyenne, stock_min_feves_HBE, stock_min_feves_moyenne, stock_min_confiserie, stock_min_tablettes_HBE, stock_min_tablettes_moyenne, coefficient_transformation, pourcentage_confiserie, pourcentage_tablette_moyenne, 
	prix_tablette, prix_tablette_equi, prix_confiserie, stock_avant_transfo_HB, stock_avant_transfo_C, stock_avant_transfo_M, cout_stockage, cout_transfo_M, cout_transfo_HBE, cout_variable_M, cout_variable_HBE, cout_fixe, prix_min_tablette, prix_min_tablette_equi, prix_min_confiserie ;


	public Transformateur3Acteur() {
		this.nom = "EQ5";
		this.description = "Côte d'IMT, chocolatier de qualité";
		
		//Création des journaux
		
		this.JournalAjoutStock = new Journal(this.getNom()+" ajout dans le stock", this);
		this.JournalRetraitStock = new Journal(this.getNom()+" retrait dans le stock", this);
		this.JournalAchatContratCadre = new Journal(this.getNom()+" achat d'un contrat cadre", this);
		this.JournalVenteContratCadre = new Journal(this.getNom()+" vente d'un contrat cadre", this);
		this.JournalOA = new Journal(this.getNom()+ "Offre d'achat", this);
		
		//Création des variables :

		this.prix_max_fèves_HBE = new Variable("Prix max d'achat de fèves HBE", this, 5.1);
		this.prix_max_fèves_moyenne = new Variable("Prix max d'achat de fèves de gamme moyenne", this, 3.5);
		
		this.stock_min_feves_HBE = new Variable("Stock minimal de fèves haute bio équitable", this, 1000000);
		this.stock_min_feves_moyenne = new Variable("Stock minimal de fèves de moyenne gamme", this, 1000000);
		this.stock_min_confiserie = new Variable("Stock minimal de confiseries", this, 1000000);
		this.stock_min_tablettes_HBE = new Variable("Stock minimal de tablettes haute bio équitable", this, 1000000);
		this.stock_min_tablettes_moyenne = new Variable("Stock minimal de tablettes moyenne", this, 1000000);
	    
		this.coefficient_transformation =  new Variable("Coefficient de transformation de fèves en chocolat (40g de fèves pour 100g de chocolat)", this, 2.5);
		this.pourcentage_confiserie = new Variable("Pourcentage de fèves de gamme moyenne transformées en confiseries", this, 0.2);
		
		this.stock_avant_transfo_C= new Variable("stock confiserie avant ajout de la transformation ", this, 10000000);
		this.stock_avant_transfo_HB = new Variable ("stock tablette haute bio et équitable avant ajout de la transformation", this, 10000000);
		this.stock_avant_transfo_M = new Variable ("stock de tablette moyenne avant ajout de la transformation", this, 10000000);
		
		this.cout_fixe = new Variable ("coût de l'entrepot de stockage des fèves et des chocolats", this, 1000);
		this.cout_stockage = new Variable("coût de stockage par kilogramme de fèves ou de chocolats stockés", this, 0.006);
		this.cout_transfo_M = new Variable ("coût de transformation fèves moyenne qualité", this, 0.5);
		this.cout_transfo_HBE = new Variable ("coût de transformation fèves haute qualité, bio et équitable", this, 0.5*1.15);
		this.cout_variable_M = new Variable("coût variable lié à la fabrication de chocolat (fèves moyennes qualité)",this, this.cout_transfo_M.getValeur() + this.prix_max_fèves_HBE.getValeur() + this.cout_stockage.getValeur());
		this.cout_variable_HBE = new Variable("coût variable lié à la fabrication de chocolat (fèves haute qualité, bio et équitables", this, this.cout_transfo_HBE.getValeur() + this.prix_max_fèves_HBE.getValeur() + this.cout_stockage.getValeur()+ this.cout_transfo_HBE.getValeur());
		
		this.prix_tablette = new Variable("Prix tablette moyenne", this, this.cout_variable_M.getValeur()*1.3);
		this.prix_tablette_equi = new Variable("Prix tablette équitable", this, this.cout_variable_HBE.getValeur()*1.1);
		this.prix_confiserie = new Variable("Prix confiserie", this, this.cout_variable_M.getValeur()*1.2);
		
		this.prix_min_tablette = new Variable("Prix min tablette moyenne", this, this.cout_variable_M.getValeur()*0.5);
		this.prix_min_tablette_equi = new Variable("Prix min tablette équitable", this, this.cout_variable_HBE.getValeur()*0.5);
		this.prix_min_confiserie = new Variable("Prix min tablette moyenne", this, this.cout_variable_M.getValeur()*0.5);
		
		
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
		
		
		// Mémorisation du stock de chocolat avant la transformation des fèves :
	    stock_avant_transfo_C.setValeur(this, this.getChocolats().get(Chocolat.CONFISERIE_MOYENNE).getValeur());
	    stock_avant_transfo_HB.setValeur(this, this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE).getValeur());
	    stock_avant_transfo_M.setValeur(this, this.getChocolats().get(Chocolat.TABLETTE_MOYENNE).getValeur());
	    
	    //Transformation des fèves de haute qualité
		Variable feve = this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		if(feve.getValeur()- 500>0) { //garder au minimum 500kg*/
			double transfo = feve.getValeur()-500;
			Variable choco = this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
			this.retirer(Feve.FEVE_HAUTE_BIO_EQUITABLE, transfo ); //retirer le surplus de fèves 
			this.ajouter(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, (transfo)*coefficient_transformation.getValeur()); //pour transformer les fèves en tablettes de haute qualité (multiplié par le coef de transformation)
			
			//Payement des coûts de transformation
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.cout_transfo_HBE.getValeur()*((transfo)*coefficient_transformation.getValeur())); 
		}
		
		//Transformation des fèves de moyenne qualité
		feve = this.getFeves().get(Feve.FEVE_MOYENNE);
		if(feve.getValeur()-500>0) { //garder au minimum 500kg*/
			double transfo = feve.getValeur()-500; 
			this.retirer(Feve.FEVE_MOYENNE, transfo); //retirer le surplus de fèves 
			this.ajouter(Chocolat.TABLETTE_MOYENNE, (transfo)*coefficient_transformation.getValeur()*(1-pourcentage_confiserie.getValeur())); //pour transformer les fèves en tablettes de moyenne qualité (multiplié par le coef de transformation)
			this.ajouter(Chocolat.CONFISERIE_MOYENNE, (transfo)*coefficient_transformation.getValeur()*pourcentage_confiserie.getValeur()); //pour transformer les fèves en confiserie de moyenne qualité
			
			//Payement des coûts de transformation
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.cout_transfo_M.getValeur()*((transfo)*coefficient_transformation.getValeur()*(1-pourcentage_confiserie.getValeur())+(transfo)*coefficient_transformation.getValeur()*pourcentage_confiserie.getValeur()));
			
		}
		
		//Payement du coût fixe (coût de l'entrepot)
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.cout_fixe.getValeur());
		
		//Payement des coûts de stockage
		if (this.cout_stockage.getValeur()*(this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE).getValeur() + this.getFeves().get(Feve.FEVE_MOYENNE).getValeur())>0) {
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.cout_stockage.getValeur()*(this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE).getValeur() + this.getFeves().get(Feve.FEVE_MOYENNE).getValeur()));
		}
		
		if (this.cout_stockage.getValeur()*(this.getChocolats().get(Chocolat.CONFISERIE_MOYENNE).getValeur() + this.getChocolats().get(Chocolat.TABLETTE_MOYENNE).getValeur() + this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE).getValeur())>0) {
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), this.cout_stockage.getValeur()*(this.getChocolats().get(Chocolat.CONFISERIE_MOYENNE).getValeur() + this.getChocolats().get(Chocolat.TABLETTE_MOYENNE).getValeur() + this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE).getValeur()));
		}
		
		
		//Création contrat cadre pour acheter des fèves
		SuperviseurVentesContratCadre SupCCadre1 = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		feve = this.getFeves().get(Feve.FEVE_MOYENNE);
		if(feve.getValeur()<this.stock_min_feves_moyenne.getValeur()){
			IVendeurContratCadre vendeur = null;
			List<IVendeurContratCadre> vendeurs = SupCCadre1.getVendeurs(Feve.FEVE_MOYENNE);
			if(vendeurs.size()>0) {
				if (Filiere.LA_FILIERE.getEtape()<=12 || this.getListePrixNegocies(this.getContratsAchat()).size()==0) {
					vendeur=vendeurs.get((int)( Math.random()*vendeurs.size())); //prend un vendeur aléatoirement
					ExemplaireContratCadre contratCadre = SupCCadre1.demande((IAcheteurContratCadre)this, vendeur, Feve.FEVE_MOYENNE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, ((this.stock_min_feves_moyenne.getValeur())-feve.getValeur()+1000000)/10), cryptogramme, false); 
					if (contratCadre!=null){
						this.JournalAchatContratCadre.ajouter("nouveau contrat cadre entre " + this + " et "+vendeur+" d'une quantité " + contratCadre.getQuantiteTotale() + "kg de " + contratCadre.getProduit() + " pendant " + contratCadre.getEcheancier() + " pour " + contratCadre.getPrix() +" euros le kilo");
					}
				} else {
					ArrayList<Double> listePrixNegocies = this.getListePrixNegocies(this.getContratsAchat());
					Double meilleurPrix = this.getMinListe(listePrixNegocies);
					vendeur = this.getVendeurAvecCePrix(meilleurPrix); //Vendeur avec le meilleur prix dans les précédents contrats
					int indexVendeur = vendeurs.indexOf(vendeur);
					IVendeurContratCadre autreVendeur = vendeurs.get(1-indexVendeur);
					ArrayList<IVendeurContratCadre> dixDerniersVendeurs = this.getVendeurs(10);
					if (!dixDerniersVendeurs.contains(autreVendeur)) {
						ExemplaireContratCadre contratCadre = SupCCadre1.demande((IAcheteurContratCadre)this, autreVendeur, Feve.FEVE_MOYENNE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, ((this.stock_min_feves_moyenne.getValeur())-feve.getValeur()+1000000)/10), cryptogramme, false); 
						if (contratCadre!=null){
							this.JournalAchatContratCadre.ajouter("nouveau contrat cadre entre " + this + " et "+autreVendeur+" d'une quantité " + contratCadre.getQuantiteTotale() + "kg de " + contratCadre.getProduit() + " pendant " + contratCadre.getEcheancier() + " pour " + contratCadre.getPrix() +" euros le kilo");
						}
					} else {
						ExemplaireContratCadre contratCadre = SupCCadre1.demande((IAcheteurContratCadre)this, vendeur, Feve.FEVE_MOYENNE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, ((this.stock_min_feves_moyenne.getValeur())-feve.getValeur()+1000000)/10), cryptogramme, false); 
						if (contratCadre!=null){
							this.JournalAchatContratCadre.ajouter("nouveau contrat cadre entre " + this + " et "+vendeur+" d'une quantité " + contratCadre.getQuantiteTotale() + "kg de " + contratCadre.getProduit() + " pendant " + contratCadre.getEcheancier() + " pour " + contratCadre.getPrix() +" euros le kilo");
						}
					}
				}
			}
		}

		feve=this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		if(feve.getValeur()<this.stock_min_feves_HBE.getValeur()) {
			IVendeurContratCadre vendeur = null;
			List<IVendeurContratCadre> vendeurs = SupCCadre1.getVendeurs(Feve.FEVE_HAUTE_BIO_EQUITABLE);
			if(vendeurs.size()>0) {
				vendeur=vendeurs.get((int)( Math.random()*vendeurs.size())); //prend un vendeur aléatoirement
				ExemplaireContratCadre contratCadre = SupCCadre1.demande((IAcheteurContratCadre)this, vendeur, Feve.FEVE_HAUTE_BIO_EQUITABLE, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, (this.stock_min_feves_HBE.getValeur()-feve.getValeur()+1000000)/10), cryptogramme, false);
				if (contratCadre!=null) {
					this.JournalAchatContratCadre.ajouter(contratCadre.toString());
				}
			}
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
		res.add(this.prix_max_fèves_HBE);
		res.add(this.prix_max_fèves_moyenne);
		res.add(this.stock_min_feves_HBE);
		res.add(this.stock_min_feves_moyenne);
		res.add(this.stock_min_confiserie);
		res.add(this.stock_min_tablettes_HBE);
		res.add(this.stock_min_tablettes_moyenne);

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

	public abstract HashMap<ExemplaireContratCadre, Integer> getContratsAchat();
	public abstract ArrayList<Double> getListePrixNegocies(HashMap<ExemplaireContratCadre, Integer> contratsAchat);
	public abstract Double getMinListe(ArrayList<Double> listePrix);
	public abstract IVendeurContratCadre getVendeurAvecCePrix (Double prix);
	public abstract ArrayList<IVendeurContratCadre> getVendeurs(int i);

	public abstract HashMap<Chocolat, Variable> getChocolats();


}

