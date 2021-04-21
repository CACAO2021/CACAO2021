package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import abstraction.fourni.*;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.contratsCadres.*;


public class Achat extends Distributeur2Acteur implements IAcheteurContratCadre {
	
	private Distributeur2Acteur wonka;
	private HashMap<ChocolatDeMarque, Variable> besoinsChoco;
	private HashMap<ChocolatDeMarque, Variable> quantiteMax;
	private LinkedList<ExemplaireContratCadre> contrats;
	private SuperviseurVentesContratCadre supCCadre;
	private HashMap<ChocolatDeMarque, Variable> quantiteLimite;
	private double paiements; //variable servant à connaître réellement l'état du compte en banque
	private HashMap<ChocolatDeMarque, LinkedList<Double>> prixParChocolat; //pour avoir une moyenne du prix d'achat par chocolat
	private LinkedList<Double> prixChocolat; //idem 
	private HashMap<ChocolatDeMarque, Double> quantiteARecevoir; // pour ne pas acheter du chocolat qu'on a pas encore en stock alors qu'il arrive dans x étapes selon l'échéancier d'un contrat déjà signé
	private HashMap<ChocolatDeMarque,Double> quantiteEnRouteTG; // quantité de choco à mettre en tete de gondole en cours de livraison
	
	public Color titleColor = Color.BLACK;
	public Color metaColor = Color.CYAN;
	public Color alertColor = Color.RED;
	public Color newContratColor = Color.GREEN;
	public Color newPropositionColor = Color.ORANGE;
	public Color descriptionColor = Color.YELLOW;

	
	

	public Achat(Distributeur2Acteur wonka) {
		this.wonka = wonka;
		this.besoinsChoco = new HashMap<ChocolatDeMarque,Variable>();		
		for(IActeur recherche_superviseur : Filiere.LA_FILIERE.getActeurs()) {
//			System.out.println(recherche_superviseur.getNom());
			if(recherche_superviseur.getNom().equals("Sup.CCadre")) {
//			if(recherche_superviseur.getColor().equals(new Color(96, 125, 139)) && !recherche_superviseur.getNom().equals("Banque")) {
//				System.out.println(recherche_superviseur);
				this.supCCadre = (SuperviseurVentesContratCadre)(recherche_superviseur);
			}
		}
		this.contrats = new LinkedList<ExemplaireContratCadre>();
		this.quantiteLimite = new HashMap<ChocolatDeMarque, Variable>();
		this.quantiteMax = new HashMap<ChocolatDeMarque, Variable>();
		this.quantiteEnRouteTG = new HashMap<ChocolatDeMarque, Double>();
		
		//Premiere commande de l'année en fonction de 12 mois auparavant, quantité limite = 1/3 de l'an passé
		this.prixParChocolat = new HashMap<ChocolatDeMarque, LinkedList<Double>>();
		this.prixChocolat = new LinkedList<Double>();
		this.quantiteARecevoir = new HashMap<ChocolatDeMarque, Double>() ;
		
		for(ChocolatDeMarque choco : wonka.getCatalogue()) {
			this.quantiteARecevoir.put(choco, 0.);
		}
		
		for(ChocolatDeMarque choco : wonka.getCatalogue()) {
			this.quantiteEnRouteTG.put(choco, 0.);
		}
		
		for(ChocolatDeMarque nosChoco : wonka.getCatalogue()) {
			this.prixParChocolat.put(nosChoco, this.prixChocolat);
			}


		}
		
	
	public void next() {
		
		for(ExemplaireContratCadre contrat : contrats) {
			ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
			this.quantiteARecevoir.put(choco, contrat.getQuantiteRestantALivrer());
		}
		
		for(ExemplaireContratCadre contrat : contrats) {
			if (contrat.getTeteGondole()) {
				ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
				this.quantiteEnRouteTG.put(choco, contrat.getQuantiteRestantALivrer());
			}
		}
		
		this.mettreAJourContrats(); //supprime les contrats caduques
		paiements = this.paiementsEnAttente();
		//Modifie les quantités min et max pour chaque chocolat en fonction de l'année précédente
		for(ChocolatDeMarque choco : wonka.getCatalogue() ) {
			List<IDistributeurChocolatDeMarque> Concurrents = Filiere.LA_FILIERE.getDistributeurs();
			Concurrents.remove((IDistributeurChocolatDeMarque)this);
			//si les concurrents vendent le même chocolat que nous, on en commande moins que le total de l'année passée
			if (Concurrents.size()!=0 && Concurrents.get(0).getCatalogue().contains(choco)) {
				Variable quantiteMin = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)*0.25  - wonka.quantiteEnVente(choco) );
				quantiteLimite.put(choco, quantiteMin);
				Variable quantite = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)*0.75  - wonka.quantiteEnVente(choco) );
				quantiteMax.put(choco, quantite);
			}
			else {
				Variable quantiteMin = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)/2  - wonka.quantiteEnVente(choco) );
				quantiteLimite.put(choco, quantiteMin);
				Variable quantite = new Variable(choco.name(), wonka, Filiere.LA_FILIERE.getVentes(choco, Filiere.LA_FILIERE.getEtape()-24)  - wonka.quantiteEnVente(choco) );
				quantiteMax.put(choco, quantite);
			}
			
		}
		this.majDemande();
		this.nouveauContrat();
//		for (ChocolatDeMarque choco : wonka.getCatalogue()) {
//		System.out.println(choco.name());
//		System.out.println(this.quantiteLimite.get(choco).getValeur());
//		System.out.println("------------");
//		System.out.println(this.quantiteMax.get(choco).getValeur());
//			}
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
			if(wonka.stocks.getStockChocolatDeMarque(choco) + this.quantiteARecevoir.get(choco) <= quantiteLimite.get(choco).getValeur()) {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, quantiteMax.get(choco).getValeur() - wonka.stocks.getStockChocolatDeMarque(choco) - this.quantiteARecevoir.get(choco)));
			}
			else {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, 0));
			}
		}
	}
	
	//besoin de savoir ce qu'il nous reste à payer pour connaître l'état réel des comptes et non seulement le montant sur notre compte bancaire
	public double paiementsEnAttente() {
		double valeur = 0;
		for(ExemplaireContratCadre contrat : contrats) {
			valeur += contrat.getMontantRestantARegler();
		}
		return valeur;
	}
	
	//supprime les contrats caduques
	public void mettreAJourContrats() {
		for(ExemplaireContratCadre contrat : contrats) {
			if(contrat.getMontantRestantARegler() == 0 && contrat.getQuantiteRestantALivrer() == 0) {
				contrats.remove(contrat);
			}
		}
	}
	
	//cherche des nouveaux contrats cadres pour tous les chocolats dont le stock est inférieur à quantiteLimite
	// il faut encore prendre en compte le chocolat qui sera reçu aux prochaines étapes A CODER !!!
	public void nouveauContrat() {
		for(ChocolatDeMarque choco : wonka.getCatalogue() ) {
			LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) this.getSupCCadre().getVendeurs(choco);
			if (vendeurs.size()!=0 && this.besoinsChoco.get(choco).getValeur()>SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER){
				int i = (int) (Math.random()*vendeurs.size());
				IVendeurContratCadre vendeur = vendeurs.get(i);


				//on répartie la valeur totale commandée sur 5 étapes 
				Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 5, besoinsChoco.get(choco).getValeur()/5);

				supCCadre.demande((IAcheteurContratCadre)wonka, vendeur, choco, echeancier, wonka.getCryptogramme(), false);
				wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre :" + " Vendeur :"+vendeur.getNom()+" | Acheteur :"+wonka.getNom()+" | Produit :"+choco.name()+" | Echeancier :"+echeancier.toString());
			}
		}
	}
	
	//Chercher des nouveaux contrats pour des chocolats en tête de gondole
	public void nouveauContratEnTG() {
		for(ChocolatDeMarque choco : wonka.getCatalogue()) {
			double quantiteTG = wonka.stocks.qtePossibleTG(choco) - this.quantiteEnRouteTG.get(choco);
			LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) this.getSupCCadre().getVendeurs(choco);
			if (vendeurs.size()!=0 && quantiteTG >SuperviseurVentesContratCadre.QUANTITE_MIN_ECHEANCIER){
				int i = (int) (Math.random()*vendeurs.size());
				IVendeurContratCadre vendeur = vendeurs.get(i);
				
				
				Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 5, quantiteTG/5);

				supCCadre.demande((IAcheteurContratCadre)wonka, vendeur, choco, echeancier, wonka.getCryptogramme(), true);
				wonka.journalAchats.ajouter(newPropositionColor, Color.BLACK, "Nouvelle demande de contrat cadre en TG :" + " Vendeur :"+vendeur.getNom()+" | Acheteur :"+wonka.getNom()+" | Produit :"+choco.name()+" | Echeancier :"+echeancier.toString());
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
		//Attention, si l'étape est 0, on ne peut pas utiliser le prix moyen.
		if (Filiere.LA_FILIERE.getEtape()==0) {
			wonka.journalAchats.ajouter(newContratColor, Color.BLACK, "Nouveau contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+contrat.getEcheancier().toString());
			contrats.add(contrat);
			//on ajoute le contrat aux contrats signés
			
		
			ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
			this.prixChocolat = this.prixParChocolat.get(choco);
			this.prixChocolat.add(contrat.getPrix());
			this.prixParChocolat.put(choco, this.prixChocolat);
			return contrat.getPrix();
		}else {
		
		double prix = contrat.getListePrix().get(contrat.getListePrix().size()-1);
		//On compare le prix d'achat par rapport au prix d'achat moyen de ce produit : si trop différent on demande moins cher
		//De plus, si notre compte bancaire ne nous permet pas d'acheter ce produit à ce prix : on demande moins cher
		double ancienPrix = Filiere.LA_FILIERE.prixMoyen((ChocolatDeMarque)contrat.getProduit(), Filiere.LA_FILIERE.getEtape()-1);
		
		if((ancienPrix * 1.10 <= prix && ancienPrix != 0 ) || !wonka.getAutorisationTransaction(prix + paiements)) {
			return contrat.getPrix()*0.90;
		}
		
		else {
			//les comptes sont suffisants pour accepter le contrat tel quel, la contre proposition reste inchangée
			//De plus, la proposition est convenable par rapport au prix moyen du produit : on achète
			wonka.journalAchats.ajouter(newContratColor, Color.BLACK, "Nouveau contrat cadre :" + "Vendeur :"+contrat.getVendeur().getNom()+"Acheteur :"+wonka.getNom()+"Produit :"+contrat.getProduit().toString()+"Echeancier :"+contrat.getEcheancier().toString());
			contrats.add(contrat);
			//on ajoute le contrat aux contrats signés
			
		
			ChocolatDeMarque choco = (ChocolatDeMarque)contrat.getProduit();
			this.prixChocolat = this.prixParChocolat.get(choco);
			this.prixChocolat.add(contrat.getPrix());
			this.prixParChocolat.put(choco, this.prixChocolat);
			return contrat.getPrix();
		}
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
	public double getMoyenne(LinkedList<Double> liste) {
		double moy = 0;
		for(double val : liste) {
			moy+= val;
		}
		return moy/(liste.size());
	}
	
	public double moyennePrixChoco(ChocolatDeMarque choco) {
		LinkedList<Double> liste = this.prixParChocolat.get(choco);
		return this.getMoyenne(liste);
	}
	

	


}
