package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;


// Paul GIRAUD (À part méthode ou il y a un autre prénom)

public class Stock {
	
	protected Business financier;
	private List<Variable> indicateurs;	
	protected Map<Feve, ArrayList<ArrayList<Variable>>> stockFeves;
	protected Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolats; 
	private Variable PrixTransformationFeve;
	private Variable PrixStockage;
	private Variable RapportTransformation;


	private double PRIX_STOCKAGE_FIXE = 1000;
	private double PRIX_STOCKAGE_VARIABLE = 6; // 6€/tonne/unité temporelle
	private double COUT_TRANSFORMATION = 500; // 500€ pour 1000kg
	private double COEFFICIENT_COUT_BIO = 1.15;
	private double RAPPORT_TRANSFORMATION = 2.5;  // 0,04kg de fèves pour 0,1 kg de chocolat
	private double STOCK_INI = 5000000;
	private Transformateur1Acteur acteur;
	
	
	
	public Stock(Transformateur1Acteur acteur) { 
		
		this.acteur = acteur;
		this.financier = new Business(this);
		
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

		this.PrixTransformationFeve = new Variable(acteur.getNom() + " Cout transformation feve à chocolat pour 1tonne euros", acteur, COUT_TRANSFORMATION);
		this.PrixStockage = new Variable(acteur.getNom() + " Coût du stockage", acteur, PRIX_STOCKAGE_FIXE);
		this.RapportTransformation = new Variable(acteur.getNom() + " rapport entre quantité de fève et chocolat", acteur, RAPPORT_TRANSFORMATION);
		

	}
	
	public void initialiserLeStock() {
		this.setStockChocolat(Chocolat.CONFISERIE_MOYENNE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.CONFISERIE_MOYENNE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.CONFISERIE_HAUTE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_MOYENNE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_MOYENNE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_HAUTE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.POUDRE_MOYENNE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.POUDRE_MOYENNE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.POUDRE_HAUTE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_MOYENNE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockChocolat(Chocolat.TABLETTE_MOYENNE_EQUITABLE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5), new Variable(this.getActeur().getNom(), this.getActeur(),0));
		this.setStockFeve(Feve.FEVE_BASSE, new Variable(this.getActeur().getNom(), this.getActeur(), STOCK_INI), new Variable(this.getActeur().getNom(), this.getActeur(),5));

	}
	
	public Business getFinancier() {
		return this.financier;
	}
	
	public Transformateur1Acteur getActeur() {
		return this.acteur;
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
		this.getPrixStockage().setValeur(this.getActeur(), cout);
	}
	
	public List<Variable> getIndicateur() {
		return this.indicateurs;
	}
	
	// liste contenant tous les types de fèves
	public ArrayList<Feve> nosFeves() {
		ArrayList<Feve> list = new ArrayList<Feve>();
		list.add(Feve.FEVE_BASSE);
		list.add(Feve.FEVE_MOYENNE);
		list.add(Feve.FEVE_MOYENNE_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		return list;
	}
	// liste contenant tous les types de fèves que l'on souhaite  acheter en CC
	public ArrayList<Feve> nosFevesCC() {
		ArrayList<Feve> list = new ArrayList<Feve>();
		list.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE);
		return list;
		
	}
	
	// liste contenant tout les types de chocolats
	public ArrayList<Chocolat> nosChocolats() {
		ArrayList<Chocolat> list = new ArrayList<Chocolat>();
		list.add(Chocolat.TABLETTE_BASSE);
		list.add(Chocolat.TABLETTE_MOYENNE);
		list.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		list.add(Chocolat.TABLETTE_HAUTE_EQUITABLE);
		list.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.CONFISERIE_BASSE);
		list.add(Chocolat.CONFISERIE_MOYENNE);
		list.add(Chocolat.CONFISERIE_MOYENNE_EQUITABLE);
		list.add(Chocolat.CONFISERIE_HAUTE_EQUITABLE);
		list.add(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.POUDRE_BASSE);
		list.add(Chocolat.POUDRE_MOYENNE);
		list.add(Chocolat.POUDRE_MOYENNE_EQUITABLE);
		list.add(Chocolat.POUDRE_HAUTE_EQUITABLE);
		list.add(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE);
		return list;
	}
	
	public double getStockFeves(Feve feve) {
		
		double total = 0;
		Map<Feve, ArrayList<ArrayList<Variable>>> stockFevesT = this.stockFeves; 
		ArrayList<ArrayList<Variable>> stockFeves = stockFevesT.get(feve); // On recupere la liste contenant toutes les listes caractéristiques  pour une certaine feve de chaque ajout de feve contenant la quantité et le prix d'achat (on pourra rajouter d'autres informations comme les dates d'achat)
		for(ArrayList<Variable> QuantitePrix : stockFeves) { 
			total += QuantitePrix.get(0).getValeur(); // on somme tous premiers elements qui correspond à chaque quantité
		}
		return total;	
		
	}
	
	public double prixAchatKG(Feve feve) {
	// on cherche a savoir le prix de vente theorique au quel on souhaite vendre au KG
		double compteur = 0.0;
		double prix = 0.0;
		ArrayList<ArrayList<Variable>> stockfeve = this.stockFeves.get(feve);
		for ( ArrayList<Variable> quantPrix: stockfeve) {
			if (quantPrix.get(0).getValeur() > 0) {
				prix += quantPrix.get(1).getValeur();
				compteur += 1;
			}
		}
		if (compteur != 0) {
			prix = prix/compteur;
			return prix;
		} else {
			return 100000000;
		}

	}
	
	
	public double getStockFeves() {
		
		double total = 0;
		ArrayList<Feve> ListFeve = this.nosFeves();
		for(Feve feve : ListFeve) {
			total += this.getStockFeves(feve); // on somme tous les différents stock de feve pour avoir le stock total
		}
		return total;
	}
	
	public double getStockChocolats(Chocolat chocolat) {
		
		double total = 0;
		Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolatsT = this.stockChocolats;
		ArrayList<ArrayList<Variable>> stockChocolats = stockChocolatsT.get(chocolat); // On recupere la liste contenant toutes les listes caractéristiques  pour un certain chocolat de chaque ajout de ce chocolat contenant la quantité et le prix de transformation + achat si la quantite est positive ou de vente si la quantite est négative 
		for(ArrayList<Variable> quantiteprixdate : stockChocolats) {
			if (quantiteprixdate.get(1).getValeur() >0 ) {
				total += quantiteprixdate.get(0).getValeur(); // on somme tous premiers elements qui correspond à chaque quantité (on regarde si le prix est different de 0 car les prix de 0 correspondent aux stocks périmés
			} else if (quantiteprixdate.get(1).getValeur()  == 0 ) {
				total -= quantiteprixdate.get(0).getValeur(); // on somme tous premiers elements qui correspond à chaque quantité (on regarde si le prix est different de 0 car les prix de 0 correspondent aux stocks périmés
			}
		}
		return total;	
		
	}
	
	public double getStockChocolats() {
		
		double total = 0;
		ArrayList<Chocolat> ListChocolat = this.nosChocolats();
		for(Chocolat chocolat : ListChocolat) {
			total += this.getStockChocolats(chocolat); // on somme tous les différents stock de chocolat pour avoir le stock total
		}
		return total;
	}
	
	public void setStockFeve(Feve feve, Variable quantite, Variable prix ) {
		if (quantite.getValeur()+this.getStockFeves() >= 0) {
			ArrayList<Variable> QuantitePrix = new ArrayList<>();
			QuantitePrix.add(quantite);
			QuantitePrix.add(prix);
			this.stockFeves.get(feve).add(QuantitePrix);
			this.getActeur().ecritureJournalStock("On vient d'ajouter || " + feve.name() + "   " +String.valueOf(quantite.getValeur()));
			this.getActeur().ecritureJournalStock(" Le nouveau stock de feve  || " + feve.name() + " est " +String.valueOf(this.getStockFeves(feve)));
		} else {
			throw new IllegalArgumentException(" Stock trop faible");
		}
	}
	
	
	//Chloe
	public void setStockChocolat(Chocolat chocolat, Variable quantite, Variable prix, Variable date) {
		ArrayList<Variable> quantiteprixdate = new ArrayList<>();
		if (quantite.getValeur()+this.getStockChocolats(chocolat) >= 0) {
			quantiteprixdate.add(quantite);
			quantiteprixdate.add(prix);
			quantiteprixdate.add(date);
			this.stockChocolats.get(chocolat).add(quantiteprixdate);
			this.getActeur().ecritureJournalStock("On vient d'ajouter || " + chocolat.name() + "   " + String.valueOf(quantite.getValeur()));
			this.getActeur().ecritureJournalStock(" Le nouveau stock de chocolat  || " + chocolat.name() + " est " + String.valueOf(quantite.getValeur()));
		} else {
			System.out.println(" Stock trop faible");
		}
	}
	
	
	public Chocolat equivalentTabletteFeve(Feve feve) {
		// on retourne le type de tablette avec la meme gamme,les memes caracteristique que la Feve feve
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.TABLETTE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public Chocolat equivalentConfiserieFeve(Feve feve) {
		// on retourne le type de confiserie avec la meme gamme,les memes caracteristique que la Feve feve 
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.CONFISERIE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public Chocolat equivalentPoudreFeve(Feve feve) {
		// on retourne le type de poudre avec la meme gamme,les memes caracteristique que la Feve feve
		for (Chocolat chocolat : this.nosChocolats()) {
			if ( chocolat.getCategorie() == Categorie.POUDRE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public ChocolatDeMarque equivalentTabletteFeveMarque(Feve feve) {
		// on retourne le type de tablette de marque avec la meme gamme,les memes caracteristique que la Feve feve dans le cas de chocolats de marque
		for (ChocolatDeMarque chocolat : this.getChocolatsProduits()) {
			if ( chocolat.getCategorie() == Categorie.TABLETTE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public ChocolatDeMarque equivalentConfiserieFeveMarque(Feve feve) {
		// on retourne le type de confiserie de marque avec la meme gamme,les memes caracteristique que la Feve feve 
		for (ChocolatDeMarque chocolat : this.getChocolatsProduits()) {
			if ( chocolat.getCategorie() == Categorie.CONFISERIE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public ChocolatDeMarque equivalentPoudreFeveMarque(Feve feve) {
		// on retourne le type de poudre de marque avec la meme gamme,les memes caracteristique que la Feve feve
		for (ChocolatDeMarque chocolat : this.getChocolatsProduits()) {
			if ( chocolat.getCategorie() == Categorie.POUDRE && feve.getGamme() == chocolat.getGamme() && feve.isEquitable() == chocolat.isEquitable() && chocolat.isBio() == feve.isBio()) {
				return chocolat;
			}
		}
		return null;
	}
	
	public Feve equivalentFeve(Chocolat chocolat) {
		// on retourne le type de feve qui correspond au Chocolat chocolat (meme gamme,les memes caracteristique que le chocolat en parmètre)
		for ( Feve feve : this.nosFevesCC()) {
			if (feve.isEquitable() == chocolat.isEquitable() && feve.isBio() == chocolat.isBio() && feve.getGamme() == chocolat.getGamme()) {
				return feve;
			}
		}
		return null;
	}
	
	public Feve equivalentFeve(ChocolatDeMarque chocolat) {
		// on retourne le type de feve qui correspond au ChocolatDeMarque chocolat (meme gamme,les memes caracteristique que le chocolat en parmètre)
		for ( Feve feve : this.nosFevesCC()) {
			if (feve.isEquitable() == chocolat.getChocolat().isEquitable() && feve.isBio() == chocolat.getChocolat().isBio() && feve.getGamme() == chocolat.getChocolat().getGamme()) {
				return feve;
			}
		}
		return null;
	}
		
	public double getMarge(Feve feve) {
		
		// renvoie les marges qu'on souhaite faire en fonction du type de feve
		if (feve.equals(Feve.FEVE_BASSE)) {
			return 1.4;
		} else if (feve.equals(Feve.FEVE_MOYENNE)) {
			return 1.4;
		} else if (feve.equals(Feve.FEVE_MOYENNE_EQUITABLE)) {
			return 1.4+0.1;
		} else if (feve.equals(Feve.FEVE_HAUTE_EQUITABLE)) {
			return 1.4+0.1;
			//on prevoit une marge supplémentaire de 10% sur les fèves équitables qui sera redistribuée à la banque (afin de servir comme don ax associations et collectivités locales qui s'assurent du bien être des producteurs)
		} else if (feve.equals(Feve.FEVE_HAUTE_BIO_EQUITABLE)) {
			return 1.4+0.1+0.1;
			//on prevoit une marge supplémentaire de 20% sur les fèves bio-équitables qui sera redistribuée à la banque (afin de servir comme don ax associations et collectivités locales qui s'assurent du bien être des producteurs)
		} else {
			return 0.0;
		}
	}
	
	
	public void coutStock() {
		// on somme le cout fixe du stockage et on calcul le cout variable avec un simple produit en croix
		double stockage = PRIX_STOCKAGE_FIXE + (this.getStockChocolats()+this.getStockFeves())*PRIX_STOCKAGE_VARIABLE/1000;
		this.setPrixStockage(stockage);
		if (stockage>0) {
			Filiere.LA_FILIERE.getBanque().virer(this.getActeur(), this.getActeur().cryptogramme, Filiere.LA_FILIERE.getBanque(), stockage);
		}
		this.getActeur().ecritureJournalTresorie("Virement à la banque pour le coût de stockage d'un montant de " + String.valueOf(stockage));
		
		
	}
	
	
	public double prixDeVenteKG(Chocolat chocolat) {
	// on cherche a savoir le prix de vente theorique auquel on souhaite vendre au KG
		double compteur = 0.0;
		double prix = 0.0;
		ArrayList<ArrayList<Variable>> stockChocolats = this.stockChocolats.get(chocolat);
		for ( ArrayList<Variable> quantprixdate: stockChocolats) {
			if (quantprixdate.get(0).getValeur() > 0 && quantprixdate.get(1).getValeur() > 0 ) {
				prix += quantprixdate.get(1).getValeur();
				compteur += 1;
			}
		}
		if (compteur != 0) {
			return prix/compteur;
		} else {
			return 100000000;
		}

	}
	
	public double prixDejaVenduKG(Chocolat chocolat) {
		// retourne le prix de vente en moyenne reel auquel on a vendu notre chocolat au KG
		double compteur = 0.0;
		double prix = 0.0;
		ArrayList<ArrayList<Variable>> stockChocolats = this.stockChocolats.get(chocolat);
		for ( ArrayList<Variable> quantprixdate: stockChocolats) {
			if (quantprixdate.get(0).getValeur() < 0 && quantprixdate.get(1).getValeur() > 0) {
				prix += quantprixdate.get(1).getValeur();
				compteur += 1;
			}
		}
		if (compteur != 0) {
			return prix/compteur;
		} else {
			return 100000000;
		}

	}
	

	public void gestionDesPeremptions() {
		for (Chocolat chocolat : this.nosChocolats()) {
			double stockajeter =  0.0;
			double stockdejavendu = 0.0;
			double stockdejajete = 0.0;
			double stockachete = 0.0;
			for ( ArrayList<Variable> quantprixdate: this.stockChocolats.get(chocolat)) {
				if ((Filiere.LA_FILIERE.getEtape()-quantprixdate.get(2).getValeur()) > 48) {
					if (quantprixdate.get(0).getValeur() >0 && quantprixdate.get(1).getValeur() > 0) {
						stockachete += quantprixdate.get(0).getValeur();
					} else if (quantprixdate.get(0).getValeur() < 0 && quantprixdate.get(1).getValeur() > 0) {
							stockdejavendu += quantprixdate.get(0).getValeur();
					} else if (quantprixdate.get(1).getValeur() == 0 && quantprixdate.get(0).getValeur() >0 ) {
							stockdejajete += quantprixdate.get(0).getValeur(); 
						}
					}
				}
			stockajeter = stockachete - stockdejajete - stockdejavendu;
			Variable quantite = new Variable(this.getActeur().getNom(), this.getActeur(), stockajeter);
			Variable prix = new Variable(this.getActeur().getNom(),this.getActeur(), 0);
			Variable date = new Variable(this.getActeur().getNom(), this.getActeur(), 0);
			this.setStockChocolat(chocolat, quantite, prix, date);
			}
		}
	
	public double getStockJete(Chocolat chocolat) {
		double stock = 0;
		for(ArrayList<Variable> quantiteprixdate : this.stockChocolats.get(chocolat)) {
			if (quantiteprixdate.get(1).getValeur() == 0) {
				stock += quantiteprixdate.get(0).getValeur(); 	
			}
		}
		return stock;
	}

	public List<ChocolatDeMarque> getChocolatsProduits() {
		String marque="Eticao";
		List<ChocolatDeMarque> chocolats=new LinkedList<ChocolatDeMarque> ();
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_MOYENNE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_MOYENNE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE_EQUITABLE,marque));
		return chocolats;
	}
	
	
	public void transformationFeveChocolat() {
		
		// on prend chaque feve
		for (Feve feve : this.nosFeves()) {
			if (this.getStockFeves(feve) > 0) {
				// on prend la quantite de feve qu'on a de ce type et on le multiplie par le rapport de transformation
				double quant = this.getStockFeves(feve)*this.getRapportTransformation().getValeur();
				// on transforme les feves selon les proportions suivantes : 70% de tablettes, 15% de confiserie, 15% de poudre
				Variable quantitetablette = new Variable(this.getActeur().getNom(),this.getActeur(),quant*this.proportionNecessaire(Categorie.TABLETTE));
				Variable quantiteconfiserie = new Variable(this.getActeur().getNom(),this.getActeur(),quant*this.proportionNecessaire(Categorie.CONFISERIE));
				Variable quantitepoudre = new Variable(this.getActeur().getNom(),this.getActeur(),quant*this.proportionNecessaire(Categorie.POUDRE));
				// on prend le prix moyen de nos feves qu'on multiplie par la marge que l'on souhaiterai se faire pour obtenir le prix de vente de cette quantite
				Variable prix = new Variable(this.getActeur().getNom(),this.getActeur(), this.prixAchatKG(feve)*this.getMarge(feve));
				Variable date = new Variable(this.getActeur().getNom(), this.getActeur(), Filiere.LA_FILIERE.getEtape());
				// on calcul les couts de transformations
				double cout = this.getPrixTransformation().getValeur()*this.getStockFeves(feve)/1000;
				if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
					cout = cout * COEFFICIENT_COUT_BIO;
				}
				Chocolat tablette = this.equivalentTabletteFeve(feve);
				Chocolat confiserie = this.equivalentConfiserieFeve(feve);
				Chocolat poudre = this.equivalentPoudreFeve(feve);
				this.setStockChocolat(tablette, quantitetablette, prix, date);
				this.setStockChocolat(confiserie, quantiteconfiserie, prix, date);
				this.setStockChocolat(poudre, quantitepoudre, prix, date);
				
	
				this.getActeur().ecritureJournalStock("stock de feve -" + feve.name() + " -" +String.valueOf(this.getStockFeves(feve)));
				if( cout > 0) {
					Filiere.LA_FILIERE.getBanque().virer(this.getActeur(), this.getActeur().cryptogramme, Filiere.LA_FILIERE.getBanque(), cout);
					this.getActeur().ecritureJournalTresorie("Virement à la banque pour la tranformation de" + feve.name()+ "d'un montant de " + String.valueOf(cout));
				}
				
				this.stockFeves.get(feve).clear();
			}
		}
	}
	
	
	//Chloe
	// gestion des proportions de transformation pour chaque categorie
	public double proportionNecessaire(Categorie categorie) {
		double quantiteTotale = 0.0;
		double quantiteTablette = 0.0;
		double quantitePoudre = 0.0;
		double quantiteConfiserie = 0.0;
		for (ExemplaireContratCadre contrat: this.getFinancier().getMesContratEnTantQueVendeur()) {
			Chocolat produit = null;
			if (contrat.getProduit() instanceof Chocolat) {
				produit = (Chocolat)contrat.getProduit();
			} else if (contrat.getProduit() instanceof ChocolatDeMarque) {
				produit = ((ChocolatDeMarque)contrat.getProduit()).getChocolat();
			}
			if (produit.getCategorie() == Categorie.CONFISERIE) {
				quantiteConfiserie += contrat.getQuantiteALivrerAuStep();
				quantiteTotale += contrat.getQuantiteALivrerAuStep();
			} else if (produit.getCategorie() == Categorie.TABLETTE) {
				quantiteTablette += contrat.getQuantiteALivrerAuStep();
				quantiteTotale += contrat.getQuantiteALivrerAuStep();
			} else if (produit.getCategorie() == Categorie.POUDRE) {
				quantitePoudre += contrat.getQuantiteALivrerAuStep();
				quantiteTotale += contrat.getQuantiteALivrerAuStep();
			}
		}
		if (categorie == Categorie.CONFISERIE) {
			if (quantiteTotale != 0) {
				return quantiteConfiserie/quantiteTotale;
			} else {
				return 0.33;
			}
			
		} else if (categorie== Categorie.TABLETTE) {
			if (quantiteTotale != 0) {
				return quantiteTablette/quantiteTotale;
			} else {
				return 0.33;
			}
		} else if (categorie == Categorie.POUDRE) {
			if (quantiteTotale != 0) {
				return quantitePoudre/quantiteTotale;
			} else {
				return 0.33;
			}
		} else {
			return 0.33;
		}
		
		
	}
	



}
