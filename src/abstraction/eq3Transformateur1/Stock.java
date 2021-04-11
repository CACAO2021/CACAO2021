package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.Map;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;


// Paul GIRAUD

public class Stock extends Transformateur1Acteur {
	

	private List<Variable> indicateurs;
	protected Map<Feve, ArrayList<ArrayList<Variable>>> stockFeves;
	protected Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolats; 
	protected Map<Feve,Double> coutFeves;
	protected Map<Chocolat,Double> coutChocolat;
	private Variable PrixTransformationFeve;
	private Variable PrixStockage;
	private Variable RapportTransformation;
	

	private double PRIX_STOCKAGE_FIXE = 1000;
	private double PRIX_STOCKAGE_VARIABLE = 6; // 6€/tonne/unité temporelle
	private double COUT_TRANSFORMATION = 500;
	private double COEFFICIENT_COUT_BIO = 1.15;
	private double RAPPORT_TRANSFORMATION = 2.5;  // 0,04kg de fèves pour 0,1 kg de chocolat
	
	
	
	public Stock() { 
		super();
		
		this.stockChocolats = new HashMap<Chocolat, ArrayList<ArrayList<Variable>>>();
		this.stockFeves = new HashMap<Feve, ArrayList<ArrayList<Variable>>>();
		
		this.stockFeves.put(Feve.FEVE_BASSE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_BASSE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_BASSE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_BASSE, new ArrayList<ArrayList<Variable>>());

		this.PrixTransformationFeve = new Variable(this.getNom() + " Cout transformation feve à chocolat pour 1tonne euros", this, COUT_TRANSFORMATION);
		this.PrixStockage = new Variable(this.getNom() + " Coût du stockage", this, PRIX_STOCKAGE_FIXE);
		this.RapportTransformation = new Variable(this.getNom() + " rapport entre quantité de fève et chocolat", this, RAPPORT_TRANSFORMATION);
		
		this.indicateurs = new ArrayList<Variable>();
	}
	
	public Variable getPrixTransformation() {
		return this.PrixTransformationFeve;
	}
	
	public Variable getPrixStockage () {
		return this.PrixStockage;
	}
	
	public Variable getRapportTransformation () {
		return this.RapportTransformation;
	}
	
	public void setPrixStockage(double cout) {
		this.getPrixStockage().setValeur(this, cout);
	}
	
	public List<Variable> getIndicateur() {
		return this.indicateurs;
	}
	
	public ArrayList<Feve> nosFeves() {
		ArrayList<Feve> list = new ArrayList<Feve>();
		list.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE);
		list.add(Feve.FEVE_BASSE);
		return list;
	}
	
	public ArrayList<Chocolat> nosChocolats() {
		ArrayList<Chocolat> list = new ArrayList<Chocolat>();
		list.add(Chocolat.CONFISERIE_BASSE);
		list.add(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.CONFISERIE_HAUTE_EQUITABLE);
		list.add(Chocolat.CONFISERIE_MOYENNE);
		list.add(Chocolat.CONFISERIE_MOYENNE_EQUITABLE);
		list.add(Chocolat.POUDRE_BASSE);
		list.add(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.POUDRE_HAUTE_EQUITABLE);
		list.add(Chocolat.POUDRE_MOYENNE);
		list.add(Chocolat.POUDRE_MOYENNE_EQUITABLE);
		list.add(Chocolat.TABLETTE_BASSE);
		list.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.TABLETTE_HAUTE_EQUITABLE);
		list.add(Chocolat.TABLETTE_MOYENNE);
		list.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		return list;
	}
	
	public double getStockFeves(Feve feve) {
		
		double total = 0;
		Map<Feve, ArrayList<ArrayList<Variable>>> stockFevesT = this.stockFeves;
		ArrayList<ArrayList<Variable>> stockFeves = stockFevesT.get(feve);
		for(ArrayList<Variable> QuantitePrix : stockFeves) {
			total += QuantitePrix.get(0).getValeur();
		}
		return total;	
		
	}
	
	public double getPrixMoyenFeve(Feve feve) {
		
		double total = 0;
		Map<Feve, ArrayList<ArrayList<Variable>>> stockFevesT = this.stockFeves;
		ArrayList<ArrayList<Variable>> stockFeves = stockFevesT.get(feve);
		for(ArrayList<Variable> QuantitePrix : stockFeves) {
			total += QuantitePrix.get(1).getValeur();
		}
		total = total/stockFeves.size();
		return total;
		
	}
	
	public double getStockFeves() {
		
		double total = 0;
		ArrayList<Feve> ListFeve = this.nosFeves();
		for(Feve feve : ListFeve) {
			total += this.getStockFeves(feve);
		}
		return total;
	}
	
	public double getStockChocolats(Chocolat chocolat) {
		
		double total = 0;
		Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolatsT = this.stockChocolats;
		ArrayList<ArrayList<Variable>> stockChocolats = stockChocolatsT.get(chocolat);
		for(ArrayList<Variable> QuantitePrix : stockChocolats) {
			total += QuantitePrix.get(0).getValeur();
		}
		return total;	
		
	}
	
	public double getStockChocolats() {
		
		double total = 0;
		ArrayList<Chocolat> ListChocolat = this.nosChocolats();
		for(Chocolat chocolat : ListChocolat) {
			total += this.getStockChocolats(chocolat);
		}
		return total;
	}
	
	public void setStockFeve(Feve feve, Variable quantite, Variable prix ) {
		if (quantite.getValeur()+this.getStockFeves() >= 0) {
			ArrayList<Variable> QuantitePrix = new ArrayList<>();
			QuantitePrix.add(quantite);
			QuantitePrix.add(prix);
			this.stockFeves.get(feve).add(QuantitePrix);
			this.journalStock.ajouter("stock de feve -" + feve.name() + " " +String.valueOf(this.getStockFeves(feve)));
		} else {
			throw new IllegalArgumentException(" Stock trop faible");
		}
	}
	
	public void setStockChocolat(Chocolat chocolat, Variable quantite, Variable prix ) {
		ArrayList<Variable> QuantitePrix = new ArrayList<>();
		if (quantite.getValeur()+this.getStockChocolats() >= 0) {
			QuantitePrix.add(quantite);
			QuantitePrix.add(prix);
			this.stockChocolats.get(chocolat).add(QuantitePrix);
			this.journalStock.ajouter("stock de chocolat de type - " + chocolat.name() + " " + String.valueOf(quantite));
		} else {
			throw new IllegalArgumentException(" Stock trop faible");
		}
	}
	
	
	public Chocolat equivalentTabletteFeve(Feve feve) {
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.TABLETTE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		
		return null;
	}
	
	public Chocolat equivalentConfiserieFeve(Feve feve) {
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.CONFISERIE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		
		return null;
	}
	
	public Chocolat equivalentPoudreFeve(Feve feve) {
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.POUDRE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		
		return null;
	}
	
	public double getMarge(Feve feve) {
		if (feve.equals(Feve.FEVE_BASSE)) {
			return 1.4;
		} else if (feve.equals(Feve.FEVE_MOYENNE)) {
			return 1.4*1.05;
		} else if (feve.equals(Feve.FEVE_MOYENNE_EQUITABLE)) {
			return 1.4*1.05*1.05;
		} else if (feve.equals(Feve.FEVE_HAUTE_EQUITABLE)) {
			return 1.4*1.1*1.05;
		} else if (feve.equals(Feve.FEVE_HAUTE_BIO_EQUITABLE)) {
			return 1.4*1.1*1.05*1.1;
		} else {
			return 0.0;
		}
	}
	
	public void transformationFeveChocolat() {
		
		// on prend chaque feve
		for (Feve feve : this.nosFeves()) {
			// on prend la quantite de feve qu'on a de ce type et on le multiplie par le rapport de transformation
			double quant = this.getStockFeves(feve)*this.getRapportTransformation().getValeur();
			
			Variable quantite = new Variable(this.getNom(),this,quant);
			// on prend le prix moyen de nos feves qu'on multiplie par la marge que l'on souhaiterai se faire pour obtenir le prix de vente de cette quantite
			Variable prix = new Variable(this.getNom(),this, this.getPrixMoyenFeve(feve)*this.getMarge(feve));
			// on calcul les couts de transformations
			double cout = this.getPrixTransformation().getValeur()*this.getStockFeves(feve)/1000;
			if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
				cout = cout * COEFFICIENT_COUT_BIO;
			}
			
			if( Math.random() <= 0.3) {
				Chocolat chocolat = this.equivalentTabletteFeve(feve);
				this.journalStock.ajouter("stock de chocolat de type - " + chocolat.name() + " + " + String.valueOf(quantite));
				this.setStockChocolat(chocolat, quantite, prix);
			} else if ( Math.random() >= 0.6) {
				Chocolat chocolat = this.equivalentConfiserieFeve(feve);
				this.journalStock.ajouter("stock de chocolat de type - " + chocolat.name() + " + " + String.valueOf(quantite));
				this.setStockChocolat(chocolat, quantite, prix);
			} else {
				Chocolat chocolat = this.equivalentPoudreFeve(feve);
				this.journalStock.ajouter("stock de chocolat de type - " + chocolat.name() + " + " + String.valueOf(quantite));
				this.setStockChocolat(chocolat, quantite, prix);
			}
			

			this.journalStock.ajouter("stock de feve -" + feve.name() + " -" +String.valueOf(this.getStockFeves(feve)));
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), cout);
			this.journalTresorie.ajouter("Virement à la banque pour la tranformation de" + feve.name()+ "d'un montant de" + String.valueOf(cout));
			this.stockFeves.get(feve).clear();
		}
	}
	
	public void coutStock() {
		
		double stockage = PRIX_STOCKAGE_FIXE + (this.getStockChocolats()+this.getStockFeves())*PRIX_STOCKAGE_VARIABLE/1000;
		this.setPrixStockage(stockage);
		Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), stockage);
		this.journalTresorie.ajouter("Virement à la banque pour le coût de stockage d'un montant de" + String.valueOf(stockage));
		
		
	}
	
	
	public double prixDeVente(Chocolat chocolat) {
		
		double prix = 0.0;
		Integer compteur = 0;
		ArrayList<ArrayList<Variable>> stockChocolats = this.stockChocolats.get(chocolat);
		for ( ArrayList<Variable> quantPrix: stockChocolats) {
			if (quantPrix.get(0).getValeur() > 0) {
				compteur += 1;
				prix += quantPrix.get(1).getValeur();
			}
		} 
		prix = prix/compteur;
		return prix;
	}

	
	public void setIndicateur() {
		Integer compteur = 0;
		for (Feve feve : this.nosFeves()) {
			this.getIndicateur().get(compteur).setValeur(this, this.getStockFeves(feve));
			compteur += 1;
		}
		for(Chocolat chocolat: this.nosChocolats()) {
			this.getIndicateur().get(compteur).setValeur(this, this.getStockChocolats(chocolat));
			compteur += 1;
		}

	}
	
	
	
	
	
	
	
}
