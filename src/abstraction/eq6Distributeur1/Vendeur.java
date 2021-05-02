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

	//thomas
	public Vendeur() {
		super();
		this.quantiteChocoVendue=new HashMap<ChocolatDeMarque,Double>();
		this.q=new HashMap <ChocolatDeMarque,Double>();
		this.journalVentes = new Journal("Journal ventes", this);

	}


	//thomas, louis
	public void initialiser() {
		super.initialiser();
		this.quantiteTotaleVendue=0;
		journaux.add(journalVentes);
		journalVentes.ajouter("toutes les ventes conclues");

		for (int i=0; i<this.getCatalogue().size(); i++) {
			this.quantiteChocoVendue.put(this.getCatalogue().get(i), 5000.0);
		}

		/*On initialise l'historique, la quantité totale vendue et 
		 *Pour chaque type de chocolat on initialise un dictionnaire à quantite vendue =0
		 */

		for (int i=0; i<this.getCatalogue().size(); i++) {
			this.q.put(this.getCatalogue().get(i), 0.2*this.quantiteEnVente(this.getCatalogue().get(i)));
		}
		//Si les ventes sont inférieures à 20% du stock on diminue le prix de vente.
		this.indicateurs.add(new Variable("Pourcentage Tete de Gondole",this, quantiteEnVenteTG()/quantiteEnVente()));
	}



	//thomas
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



	//thomas
	public double getQuantiteVendue(ChocolatDeMarque choco) {
		if ((choco!=null) && (this.quantiteChocoVendue.containsKey(choco))) {
			return this.quantiteChocoVendue.get(choco);
		}
		else {
			return 0;
		}
	}



	//thomas
	public double prix(ChocolatDeMarque choco) {
		if(choco!=null) {
			return prix.get(choco);
		}else {
			return 0;
		}
	}//retourne le prix de vente du chocolat "choco"



	//thomas
	public List<ChocolatDeMarque> getCatalogue() {
		List<ChocolatDeMarque> c = new ArrayList<ChocolatDeMarque>();
		for (ChocolatDeMarque choco : stock.keySet()) {
			c.add(choco);
		}
		return c;
	}//retourne le catalogue (liste des produits disponibles)



	//thomas
	public double quantiteEnVente(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stock.get(choco).getValeur();
		}
		else {
			return 0;

		}//retourne la quantité du chocolat "choco" en vente
	}



	//louis
	public double quantiteEnVente() {
		double total=0;
		for (ChocolatDeMarque choco : getCatalogue()) {
			total += quantiteEnVente(choco);
		}
		return total;
	}



	//thomas
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		if (choco!=null && this.stockTG.get(choco)!=null) {
			return this.stockTG.get(choco).getValeur();

		}
		else {
			return 0;
		}//retourne la quantité disponible du chocolat choco en tete de gondole
	}



	//louis
	public double quantiteEnVenteTG() {
		double total=0;
		for (ChocolatDeMarque choco : getCatalogue()) {
			total += quantiteEnVenteTG(choco);
		}
		return total;
	}



	//thomas
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		if(choco!=null && quantite>0 && quantite<=this.quantiteEnVente(choco)) {
			this.ajouterStock((ChocolatDeMarque)choco, (-1)*quantite, false);
			//System.out.println(stock.get(choco).getValeur()+" "+choco.toString());
			this.quantiteTotaleVendue+=quantite;
			this.quantiteChocoVendue.put(choco, quantite);
			journalVentes.ajouter("vente de "+quantite+" "+choco.name()+" a "+client.getNom()+" pour un prix de "+ montant);

		}//on retire du stock ce qui a été vendu, et on ajoute la quantite à quantiteTotaleVendue
	}// on actualise aussi quantiteChocoVendue



	//thomas
	public void notificationRayonVide(ChocolatDeMarque choco) {
		if (quantiteEnVente(choco)==0) {
			//	System.out.println("Plus de : "+ choco.getMarque()+" en rayon");
		}
	}	



	//thomas
	public void NouveauPrix(ChocolatDeMarque choco) {
		//prix correspond au prix de vente initial
		if (this.getQuantiteVendue(choco)==0 || this.getQuantiteVendue(choco)<q.get(choco)) {
			this.setPrix(choco, prix(choco)*0.9); 
			//Si les ventes ne sont pas convenables, on baisse le prix de vente de 10% pour la prochaine période
		}

	}


}

