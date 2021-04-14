package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import abstraction.fourni.*;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.contratsCadres.*;


public class Achat extends Distributeur2Acteur implements IAcheteurContratCadre {
	
	private Distributeur2Acteur wonka;
	private HashMap<ChocolatDeMarque, Variable> besoinsChoco;
	private HashMap<ChocolatDeMarque, Variable> quantiteMax;
	private LinkedList<ExemplaireContratCadre> contrats;
	private SuperviseurVentesContratCadre supCCadre;
	private HashMap<ChocolatDeMarque, Variable> quantiteLimite; 
	
	public Color titleColor = Color.BLACK;
	public Color metaColor = Color.CYAN;
	public Color alertColor = Color.RED;
	public Color newContratColor = Color.GREEN;
	public Color newPropositionColor = Color.ORANGE;
	public Color descriptionColor = Color.YELLOW;
	
	

	public Achat(Distributeur2Acteur wonka) {
		this.wonka = wonka;
		this.besoinsChoco = new HashMap<ChocolatDeMarque,Variable>();		
		this.supCCadre = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
		this.contrats = new LinkedList<ExemplaireContratCadre>();
		this.quantiteLimite = new HashMap<ChocolatDeMarque, Variable>();
		this.quantiteMax = new HashMap<ChocolatDeMarque, Variable>();
		//Premiere commande de l'année en fonction de 12 mois auparavant, quantité limite = 1/3 de l'an passé

		}
		
	
	public void next() {
		//Modifie les quantités min et max pour chaque chocolat en fonction de l'année précédente
		for(ChocolatDeMarque choco : wonka.getCatalogue() ) {
			Variable quantiteMin = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)/4  - wonka.quantiteEnVente(choco) );
			quantiteLimite.put(choco, quantiteMin);
			Variable quantite = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)/1.5  - wonka.quantiteEnVente(choco) );
			quantiteMax.put(choco, quantite);
		}
		this.majDemande();
		this.nouveauContrat();
	}
	//public void init() {
	//	HashMap<ChocolatDeMarque, Double> initCommande = new HashMap<ChocolatDeMarque, Double>();
	//	for(ChocolatDeMarque choco : wonka.getCatalogue()) {
	//		initCommande.put(choco,30.);
	//		LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) supCCadre.getVendeurs(choco);
	//		int i = (int) (Math.random()*vendeurs.size()) ;
	//		IVendeurContratCadre vendeur = vendeurs.get(i);
	//		Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, besoinsChoco.get(choco).getValeur()/10);
	//		supCCadre.demande((IAcheteurContratCadre)wonka, vendeur, choco, echeancier, wonka.getCryptogramme(), false);
	//		
	//	}
	//}
	public void majDemande() {
		//crée un tableau avec la quantité qu'on doit commander pour chaque chocolat
		for(ChocolatDeMarque choco : wonka.getCatalogue()) {
			if(wonka.stocks.getStockChocolatDeMarque(choco) <= quantiteLimite.get(choco).getValeur()) {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, quantiteMax.get(choco).getValeur() - stocks.getStockChocolatDeMarque(choco)));
			}
			else {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, 0));
			}
		}		
	}
	
	//cherche des nouveaux contrats cadres pour tous les chocolats dont le stock est inférieur à quantiteLimite
	// il faut encore prendre en compte le chocolat qui sera reçu aux prochaines étapes A CODER !!!
	public void nouveauContrat() {
		for(ChocolatDeMarque choco : wonka.getCatalogue() ) {
			LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) this.getSupCCadre().getVendeurs(choco);
			if (vendeurs.size()!=0){
				int i = (int) (Math.random()*vendeurs.size()) ;
				IVendeurContratCadre vendeur = vendeurs.get(i);
				Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, besoinsChoco.get(choco).getValeur()/10);
				supCCadre.demande((IAcheteurContratCadre)wonka, vendeur, choco, echeancier, wonka.getCryptogramme(), false);
				wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre :" + "Vendeur :"+vendeur.toString()+"Acheteur :"+wonka.toString()+"Produit :"+choco.toString()+"Echeancier :"+echeancier.toString());
			}
		}
	}
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Echeancier e = contrat.getEcheancier();
		if(e.getNbEcheances()>=10) { //si l'échéancier est réparti sur plus de 10 étapes : trop long (arbitraire)
			//on rajoute à l'étape 0 toutes les quantités qui auraient du être livrées après l'étape 10
			e.set(e.getStepDebut(), e.getQuantiteAPartirDe(10)+e.getQuantite(0));
			for(int i=10; i<e.getNbEcheances(); i++) {
				//pour chaque étape au dessus de 10, on met quantité = 0
				e.set(i, 0);
			}
			wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+e.toString());
			return e;
		}
		//si la quantité proposée par le vendeur est inférieure à 0,9 * la quantité voulue : pas acceptable
		if(e.getQuantiteTotale()<=0.9*besoinsChoco.get(contrat.getProduit()).getValeur()) {
			e.set(e.getStepDebut(), e.getQuantite(0)+(besoinsChoco.get(contrat.getProduit()).getValeur() * 0.9-e.getQuantiteTotale()));
			wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+e.toString());
			return e;
			
		}
		//si la quantité proposée par le vendeur est supérieure à 1,1 * la quantité voulue : pas acceptable
		
		else if(e.getQuantiteTotale()>=1.1*besoinsChoco.get(contrat.getProduit()).getValeur()) {
			e.set(e.getStepDebut(), e.getQuantiteTotale()+e.getQuantite(0) - (besoinsChoco.get(contrat.getProduit()).getValeur() * 1.1));
			wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+e.toString());
			return e;	
		}
		//Quantité comprise entre 0.9 * quantité voulue et 1.1 * quantité voulue : ok
		else {
			return e;
		}
	}

	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		double prix = contrat.getListePrix().get(contrat.getListePrix().size()-1);
		//On compare le prix d'achat par rapport au prix d'achat moyen de ce produit : si trop différent on demande moins cher
		//De plus, si notre compte bancaire ne nous permet pas d'acheter ce produit à ce prix : on demande moins cher
		double ancienPrix = Filiere.LA_FILIERE.prixMoyen((ChocolatDeMarque)contrat.getProduit(), Filiere.LA_FILIERE.getEtape());
		
		if((ancienPrix * 1.10 <= prix && ancienPrix != 0 )|| !wonka.getAutorisationTransaction(prix)) {
			return contrat.getPrix()*0.90;
		}
		
		else {
			//les comptes sont suffisants pour accepter le contrat tel quel, la contre proposition reste inchangée
			//De plus, la proposition est convenable par rapport au prix moyen du produit : on achète
			wonka.journalAchats.ajouter(newContratColor, Color.BLACK, "Nouveau contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+contrat.getEcheancier().toString());
			contrats.add(contrat);
			//on ajoute le contrat aux contrats signés
			return contrat.getPrix();
		}

		
	}
// Il faut ajouter les quantités de chocolat reçues par mois selon l'échéancier de chaque contrat, 
// et non ajouter la quantité totale de chocolat du contrat dès la signature

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		wonka.stocks.ajouterChocolatDeMarque((ChocolatDeMarque)contrat.getProduit(), quantite);
		
	}

	public SuperviseurVentesContratCadre getSupCCadre() {
		return supCCadre;
	}
	




}
