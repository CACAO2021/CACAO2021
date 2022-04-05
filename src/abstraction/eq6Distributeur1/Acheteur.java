package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.Collections;
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
	protected double i;
	protected double j;
	protected Map<ChocolatDeMarque, Double> listeTG;
	private Journal journalAchats;
	private ChocolatDeMarque chocoprecquant;
	private ChocolatDeMarque chocoprecprix;

	//Elsa

	public Acheteur() {
		super();
		this.journalAchats = new Journal("Journal achats", this);
		this.listeTG=new HashMap<ChocolatDeMarque, Double>();
	}


	
	//Louis

	/**
	 * s'appelle au lancement du programme, permet d'initialiser les journaux
	 */

	public void initialiser() {
		super.initialiser();
		journaux.add(journalAchats);
		journalAchats.ajouter("tout les contrats cadres conclus");
	}



	//Louis

	/**
	 * Est appelée au début de chaque tour, pour chaque ChocolatDeMarque de notre catalogue on initialise un contrat cadre d’une durée de un step. 
	 */

	public void next() {

		this.superviseur=(SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre");

		for (ChocolatDeMarque choco : listeTG.keySet()) {
			if (listeTG.get(choco)>superviseur.QUANTITE_MIN_ECHEANCIER) {
				superviseur.demande((IAcheteurContratCadreNotifie)this, (IVendeurContratCadre)Filiere.LA_FILIERE.getProprietaireMarque(choco.getMarque()), choco, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, Filiere.LA_FILIERE.getEtape()+2, listeTG.get(choco)), this.cryptogramme, true);
			}
		}
		listeTG.clear();

		for (ChocolatDeMarque produit : this.getCatalogue()) {
			if (maxQuantite(produit)>superviseur.QUANTITE_MIN_ECHEANCIER) {
				superviseur.demande((IAcheteurContratCadreNotifie)this, (IVendeurContratCadre)Filiere.LA_FILIERE.getProprietaireMarque(produit.getMarque()), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+2, Filiere.LA_FILIERE.getEtape()+3, maxQuantite(produit)), cryptogramme, false);
		}

		super.next();
		}
	}




	//Elsa
	/**
	 * Permet de négocier la quantité de produit voulu. 
	 * On calcule la maxQuantite 
	 * On vérifie aussi que cette quantité est supérieure à la quantité minimale demandée par le superviseur. 
	
	 */

	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
		if (this.chocoprecquant!=choco) {
			i=0;
		}
		this.chocoprecquant=choco;
		i++;
		Echeancier e = contrat.getEcheancier();
		double maxQuantite = maxQuantite(choco);
		
		
		if (e.getQuantiteTotale()>maxQuantite) {
			if(maxQuantite*(0.90+i/100)>((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).QUANTITE_MIN_ECHEANCIER) {
				e.set(e.getStepDebut(), maxQuantite*(0.90+i/100));
				}
			
			else {
				e.set(e.getStepDebut(), ((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).QUANTITE_MIN_ECHEANCIER);
			}
		}
		else if(e.getQuantiteTotale()>=superviseur.QUANTITE_MIN_ECHEANCIER){
			
			//marché conclu
			e.set(e.getStepDebut(), e.getQuantite(e.getStepFin()));
		}
		else {
			e.set(e.getStepDebut(), ((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).QUANTITE_MIN_ECHEANCIER);
			
		}

		return e;
	
}
	
	//Elsa

	/**
	 * Permet de négocier le prix d’achat des produits avec les transformateurs. 
	 * Nous allons fixer un prix maximal d’achat qui est de 80% de notre prix de vente. 
	 * Si le produit est en tête de gondole, nous allons chercher à l’acheter à 70% de notre prix de vente (car plus attractif). 
	 * Ensuite à chaque tour de négociation nous allons augmenter le prix jusqu’à obtenir une entente avec le transformateur ou arrêter si il dépasse notre prix maximal.
	 */

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
		if (this.chocoprecprix!=choco) {
			j=0;
		}
		this.chocoprecprix=choco;
		double maxPrix= this.prix.get(choco).getValeur()*0.80;
		
		if (contrat.getTeteGondole()) {
			maxPrix=0.9*maxPrix;
		}
		

		if (contrat.getPrix()>maxPrix || contrat.getPrix()==0) {
			j++;
			return maxPrix*(0.85+(j/100));
			}
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
		this.superviseur=(SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre");
		ajouterStock((ChocolatDeMarque)produit, quantite,contrat.getTeteGondole());
		journalAchats.ajouter("achat de "+quantite+" "+produit.toString()+" a "+contrat.getVendeur().toString()+" pour un prix de "+contrat.getPrix());
	}



	//Louis, Elsa

	/**
	 * Définit la quantité maximale du ChocolatDeMarque “choco” que l’on souhaite acheter.
	 * On liste tous les produits de la même marque et de la même catégorie et on regarde quel produit on a acheté au meilleur
	 	prix au tour précédent. On augmente notre maxQuantité du produit le moins cher et on diminue celle des produit les 
	 	plus cher.
	 * @param choco
	 * @return
	 */

	public double maxQuantite(ChocolatDeMarque choco) {
		//J'achete au maximum 15% de plus que ce que j'ai vendu moins ce qu'il me reste en stock
		double max=(this.quantiteChocoVendue.get(choco)-this.stock.get(choco).getValeur())*1.15;
		if(max<0) {
			return 0;
		}
		if (this.quantiteChocoVendue.get(choco)+this.stock.get(choco).getValeur()<0.01 && this.getSolde()<10000000000.) {
			max=superviseur.QUANTITE_MIN_ECHEANCIER*1.2;
		}
		List<String> listTypeChoco=new LinkedList<String>();
		List<Double> listPrixChoco=new LinkedList<Double>();
		
		for (ChocolatDeMarque choco2: this.getCatalogue()) {
			if (choco2.getCategorie()==(choco.getCategorie())) {
				if (choco2.getGamme()==(choco.getGamme())) {
					listTypeChoco.add(choco2.toString());
					listPrixChoco.add(prix.get(choco2).getValeur());
					int minIndex=listPrixChoco.indexOf(Collections.min(listPrixChoco));
					if (choco==choco2){
						max=max*1.5;
					}else {
						max=max*0.7;
					}
				}
			}		
		}
		return max;
	}

	
	//Louis
	
	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
		double quantite = contrat.getEcheancier().getQuantiteTotale();
		double prix = contrat.getPrix();
		
		if (!contrat.getTeteGondole() && quantite*0.1>superviseur.QUANTITE_MIN_ECHEANCIER) {
			if((stockTG.get(choco).getValeur()/stock.get(choco).getValeur())<0.2) {
				listeTG.put(choco, quantite*0.1);
			}
		}
		
		this.prixDAchat.put(choco, prix);
		
	}


}
