package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Vendeur extends Stocks implements IDistributeurChocolatDeMarque{


	protected int quantiteTotaleVendue;// Quantite totale vendue en une période
	protected Map<ChocolatDeMarque,Double> quantiteChocoVendue; //Quantite vendue par chocolat au step en cours
	protected Map<ChocolatDeMarque,Double> q; //Quantité définie pour chaque produit qu'on vend à partir duquel on considère les ventes convenables
	private Journal journalVentes;

	
	public Vendeur() {
		super();
		this.quantiteChocoVendue=new HashMap<ChocolatDeMarque,Double>();
		this.q=new HashMap <ChocolatDeMarque,Double>();
		this.journalVentes = new Journal("Journal ventes", this);

	}


	//Thomas, Louis, Elsa
	
	/**
	 *On initialise cette Map quantiteChocoVendue à 300000 pour chaque chocolat (valeur arbitraire), et on crée les journaux.
	 */
	
	public void initialiser() {
		super.initialiser();
		this.quantiteTotaleVendue=0;
		journaux.add(journalVentes);
		journalVentes.ajouter("toutes les ventes conclues");

		for (int i=0; i<this.getCatalogue().size(); i++) {
			this.quantiteChocoVendue.put(this.getCatalogue().get(i), 300000.0);
		}

		/*On initialise l'historique, la quantité totale vendue et 
		 *Pour chaque type de chocolat on initialise un dictionnaire à quantite vendue =0
		 */

		for (int i=0; i<this.getCatalogue().size(); i++) {
			this.q.put(this.getCatalogue().get(i), 0.2*this.quantiteEnVente(this.getCatalogue().get(i)));
		}
		//Si les ventes sont inférieures à 20% du stock on diminue le prix de vente.
		this.indicateurs.add(new Variable("Pourcentage Tete de Gondole",this, quantiteEnVenteTG()/quantiteEnVente()));
		for (ChocolatDeMarque choco : getCatalogue()) {
			this.indicateurs.add(prix.get(choco));}
	}



	//Thomas
	
	/**
	 *On rafraîchit le prix des chocolats en fonction de leur consommation et met à jour un indicateur.
	 */
	
	public void next() {
		super.next();
		this.quantiteTotaleVendue=0;
		for(ChocolatDeMarque choco : getCatalogue()) {
			NouveauPrix(choco);
		}
		//mise à jour de l'indicateur "pourcentage Tete de Gondole"
		for (Variable indic : this.getIndicateurs()) {
			if (indic.getNom().equals("Pourcentage Tete de Gondole")) {
				indic.setValeur(this, quantiteEnVenteTG()/quantiteEnVente());
			}
		}
		//System.out.println("TG "+quantiteEnVenteTG()+" / pas TG "+quantiteEnVente());
	}



	//Thomas
	
	/**
	 * Retourne la quantité vendue du paramètre choco au step précédant.
	 * @param choco
	 * @return
	 */
	
	public double getQuantiteVendue(ChocolatDeMarque choco) {
		if ((choco!=null) && (this.quantiteChocoVendue.containsKey(choco))) {
			return this.quantiteChocoVendue.get(choco);
		}
		else {
			return 0;
		}
	}



	//Thomas
	
	/**
	 * Retourne le prix d’un kilo du chocolat «choco».

	 */
	
	public double prix(ChocolatDeMarque choco) {
		if(choco!=null) {
			return prix.get(choco).getValeur();
		}else {
			return 0;
		}
	}



	//Thomas
	
	/**
	 * Permet d’identifier la liste des chocolats de marque que nous nous engageons à vendre au consommateur.
	 */
	
	public List<ChocolatDeMarque> getCatalogue() {
		List<ChocolatDeMarque> c = new ArrayList<ChocolatDeMarque>();
		for (ChocolatDeMarque choco : stock.keySet()) {
			c.add(choco);
		}
		return c;
	}



	//Thomas
	
	/**
	 *Retourne d’une part la quantité totale en Kg de chocolat vendue et d’autre part, la quantité de ce même chocolat en tête de gondole uniquement. 
	 *Les mêmes fonctions sans argument retournent les quantités totales vendues (tout chocolat confondu) respectivement en tout ou en tête de gondole.
	 */
	
	public double quantiteEnVente(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stock.get(choco).getValeur();
		}
		else {
			return 0;

		}//retourne la quantité du chocolat "choco" en vente
	}



	//Louis
	
	public double quantiteEnVente() {
		double total=0;
		for (ChocolatDeMarque choco : getCatalogue()) {
			total += quantiteEnVente(choco);
		}
		return total;
	}



	//Thomas
	
	
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		if (choco!=null && this.stockTG.get(choco)!=null) {
			return this.stockTG.get(choco).getValeur();

		}
		else {
			return 0;
		}//retourne la quantité disponible du chocolat choco en tete de gondole
	}



	//Louis
	
	public double quantiteEnVenteTG() {
		double total=0;
		for (ChocolatDeMarque choco : getCatalogue()) {
			total += quantiteEnVenteTG(choco);
		}
		return total;
	}



	// Elsa
	
	/**
	 *Permet de mettre à jour la quantité de chocolat (on retire du stock ce qui a été vendu) avant de mettre à jour notre Map quantiteChocoVendue pour le tour suivant.
	 */
	
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		if(choco!=null && quantite>0 && quantite<=this.quantiteEnVente(choco)) {
			this.ajouterStock((ChocolatDeMarque)choco, (-1)*quantite, false);
			//System.out.println(stock.get(choco).getValeur()+" "+choco.toString());
			this.quantiteTotaleVendue+=quantite;
			this.quantiteChocoVendue.put(choco, quantite);
			journalVentes.ajouter("vente de "+quantite+" "+choco.name()+" a "+client.getNom()+" pour un prix de "+ montant);
			
		}//on retire du stock ce qui a été vendu, et on ajoute la quantite à quantiteTotaleVendue
	}// on actualise aussi quantiteChocoVendue



	//Thomas
	
	/**
	 *Nous notifie de l'absence d’un produit en rayon, c’est-à-dire lorsque toute la quantité en stock a été vendue.
	 */
	
	public void notificationRayonVide(ChocolatDeMarque choco) {
		if (quantiteEnVente(choco)==0) {
			//	System.out.println("Plus de : "+ choco.getMarque()+" en rayon");
		}
	}	



	//Thomas
	
	/**
	 *Permet de baisser le prix de 10% si la quantité vendue est inférieur à une quantité q convenable que nous définirons (si le produit ne se vend pas bien).
	 * @param choco
	 */
	
	public void NouveauPrix(ChocolatDeMarque choco) {
		//prix correspond au prix de vente initial
		if (this.getQuantiteVendue(choco)==0 || this.getQuantiteVendue(choco)<q.get(choco)) {
			this.setPrix(choco, prix(choco)*0.9); 
			//Si les ventes ne sont pas convenables, on baisse le prix de vente de 10% pour la prochaine période
		}
		else{
			if(prix(choco)<10) {
				this.setPrix(choco, prix(choco)*1.1);}
			this.setPrix(choco, prix(choco));}

	}

}

