package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadreNotifie;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;

public class Acheteur extends Vendeur implements IAcheteurContratCadreNotifie {

	protected SuperviseurVentesContratCadre superviseur;
	protected int i;
	protected int j;
	protected Map<ChocolatDeMarque, Double> listeTG;
	private Journal journalAchats;

	//Elsa

	public Acheteur() {
		super();
		this.journalAchats = new Journal("Journal achats", this);
		this.listeTG=new HashMap<ChocolatDeMarque, Double>();
	}


	//Louis

	/**
	 * initialiser les journaux
	 */

	public void initialiser() {
		super.initialiser();
		journaux.add(journalAchats);
		journalAchats.ajouter("tout les contrats cadres conclus");
	}



	//Louis

	/**
	 * Est appelée au début de chaque tour, on tire au sort un transformateur pour chaque ChocolatDeMarque de notre catalogue et on initialise un contrat cadre d’une durée de un step. 
	 */

	public void next() {

		this.superviseur=(SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre");

		for (ChocolatDeMarque choco : listeTG.keySet()) {
			//System.out.println("demande TG");
			if (listeTG.get(choco)>superviseur.QUANTITE_MIN_ECHEANCIER) {
				//superviseur.demande((IAcheteurContratCadreNotifie)this, (IVendeurContratCadre)Filiere.LA_FILIERE.getProprietaireMarque(choco.getMarque()), choco, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, Filiere.LA_FILIERE.getEtape()+2, listeTG.get(choco)), this.cryptogramme, true);
			}
		}
		listeTG.clear();

		for (ChocolatDeMarque produit : this.getCatalogue()) {
			List<IActeur> vendeurs = new LinkedList<IActeur>();
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				if (acteur!= this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(produit)) {
					vendeurs.add(acteur);
				}
			}

			if (vendeurs.size()!=0) {
				//System.out.println(vendeurs.toString());
				int rnd = new Random().nextInt(vendeurs.size());
				IActeur vendeur = vendeurs.get(rnd);
				//System.out.println(maxQuantite(produit));
				if (maxQuantite(produit)>superviseur.QUANTITE_MIN_ECHEANCIER) {
					superviseur.demande((IAcheteurContratCadreNotifie)this, ((IVendeurContratCadre)vendeur), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+2, Filiere.LA_FILIERE.getEtape()+3, maxQuantite(produit)), cryptogramme, false);
				}
			}
		}

		super.next();
	}

	//au bout d'un moment on achete rien
	//on achete trop peu et pas à cote d'imt
	//tg = cote d'imt



	//Elsa
	/**
	 * Permet de négocier la quantité de produit voulu. 
	 * On essaie de ne pas dépasser une quantité de produit égale à 15% de plus que ce qui s’est vendu au tour précédent (grâce à l’historique) en enlevant la quantité de ce même produit qu’il nous reste en stock. 
	 * On vérifie aussi que cette quantité est supérieure à la quantité minimale demandée par le superviseur. 
	 * Remarque: en l’état actuel des choses, cette méthode n’est jamais appelée lors de la création d’un contrat cadre, comme si les transformateurs acceptaient directement chaque proposition de quantité.
	 */

	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		System.out.println("contre proposition"); //  <= method never called
		this.superviseur=(SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre");
		j=0;
		Echeancier e = contrat.getEcheancier();
		int step=e.getStepDebut();

		double maxQuantite= maxQuantite((ChocolatDeMarque)contrat.getProduit()); 
		if (e.getQuantite(step)>maxQuantite) {
			//System.out.println("quantite > max");
			if(maxQuantite*(0.90+i/100) > superviseur.QUANTITE_MIN_ECHEANCIER) {
				if(i==5) {
					//System.out.println("oui");
					e.set(step, contrat.getEcheancier().getQuantite(step));
				}
				else {
					e.set(step, maxQuantite*(0.90+i/100));}
			}

			else {
				if (!contrat.getTeteGondole()){
					//e.set(step, superviseur.QUANTITE_MIN_ECHEANCIER+1);
				}

			}
		}
		else {

			e.set(step, e.getQuantite(e.getStepFin()));
			//System.out.println(i);
		}

		i++;
		return e;

	}


	//Elsa

	/**
	 * Permet de négocier le prix d’achat des produits avec les transformateurs. 
	 * Nous allons fixer un prix maximal d’achat qui est de 75% de notre prix de vente. 
	 * Si le produit est en tête de gondole, nous allons chercher à l’acheter à 70% de notre prix de vente (car plus attractif). 
	 * Ensuite à chaque tour de négociation nous allons augmenter le prix jusqu’à obtenir une entente avec le transformateur ou arrêter si il dépasse notre prix maximal.
	 */

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		i=0;
		//System.out.println("prix");
		double maxPrix= this.prix.get((ChocolatDeMarque)contrat.getProduit()).getValeur()*0.75;
		if (contrat.getTeteGondole()) {
			maxPrix=0.9*maxPrix;
		}

		if (contrat.getPrix()>maxPrix) {
			j++;
			return maxPrix*(0.85+j/100);}
		else {
			return contrat.getPrix(); 
		}
	}



	//Louis

	/**
	 * Permet de modifier le stock en utilisant ajouterStock(Object produit, double quantite, boolean tg) de la classe stocks.
	 * Elle permet aussi de mettre à jour le journal des achats.
	 */

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if(contrat.getTeteGondole()) {
			//System.out.println("reception TG");
		}
		this.superviseur=(SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre");
		ajouterStock((ChocolatDeMarque)produit, quantite,contrat.getTeteGondole());
		journalAchats.ajouter("achat de "+quantite+" "+produit.toString()+" a "+contrat.getVendeur().toString()+" pour un prix de "+contrat.getPrix());
	}



	//Louis

	/**
	 * Définit la quantité maximale du ChocolatDeMarque “choco” que l’on souhaite acheter.
	 * @param choco
	 * @return
	 */

	public double maxQuantite(ChocolatDeMarque choco) {
		//J'achete au maximum 15% de plus que ce que j'ai vendu moins ce qu'il me reste en stock
		double max=(this.quantiteChocoVendue.get(choco)-this.stock.get(choco).getValeur())*1.15;
		if(max<0) {
			return 0;
		}
		return max;
	}




	//Louis

	/**
	 * Renvoie la liste des chocolats qui sont vendus par les transformateurs durant le step en cours.
	 * @return
	 */

	public List<ChocolatDeMarque> chocolatVendu() {
		ArrayList<ChocolatDeMarque> chocoVendu = new ArrayList<ChocolatDeMarque>();
		for (ChocolatDeMarque choco : this.getCatalogue()) {
			for (IVendeurContratCadre transfo : getTransformateurs()) {
				if (transfo.vend(choco) && !chocoVendu.contains(choco)) {
					chocoVendu.add(choco);
				}
			}
		}
		return chocoVendu;
	}



	//Louis

	/**
	 * Renvoie la liste des transformateurs de la filière
	 * @return
	 */

	public List<IVendeurContratCadre> getTransformateurs(){
		LinkedList<IVendeurContratCadre> transf = new LinkedList<IVendeurContratCadre>();
		for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
			if (acteur!= this && acteur instanceof IVendeurContratCadre) {
				transf.add((IVendeurContratCadre)acteur);
			}
		}
		return transf;
	}


	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (!contrat.getTeteGondole() && contrat.getEcheancier().getQuantiteTotale()*0.1>superviseur.QUANTITE_MIN_ECHEANCIER) {
			listeTG.put((ChocolatDeMarque)contrat.getProduit(), contrat.getEcheancier().getQuantiteTotale()*0.1);
			
		}
	}


}
