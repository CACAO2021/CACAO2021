package abstraction.eq6Distributeur1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;

public class Acheteur extends Vendeur implements IAcheteurContratCadre {
	protected int i=0; //Nombre de tours de négociations
	protected LinkedList produitTG;

	
	protected Journal JournalAchats= new Journal(this.getNom()+" achats", this);
	
	public Acheteur() {
		super();
	}
	

	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
			i++;
			Echeancier e = contrat.getEcheancier();
			double maxQuantite= (this.historique.get((ChocolatDeMarque)contrat.getProduit())-this.stock.get((ChocolatDeMarque)contrat.getProduit()).getValeur())*1.15; //J'achete 15% de plus que ce que j'ai vendu moins ce qu'il me reste en stock
			if (e.getQuantite(e.getStepFin())>maxQuantite) {
				e.set(e.getStepDebut(), maxQuantite*(0.90+i/100));}
			else {
				e.set(e.getStepDebut(), e.getQuantite(e.getStepFin()));
			}
			return e;
		
	}
	
	public void choixTG() {
		produitTG= new LinkedList();
		double sommeQuantite=0;
		double sommeTG=0;
		Map<ChocolatDeMarque,Double> maxQuantites= new HashMap<ChocolatDeMarque,Double>();
		for (ChocolatDeMarque produit : this.stock.keySet()) {
			double maxQuantite=(this.historique.get(produit)-this.stock.get(produit).getValeur())*1.15;
			maxQuantites.put(produit, maxQuantite);
			sommeQuantite+=maxQuantite;
		}
		while (sommeTG<0.08*sommeQuantite) {
			int rnd = new Random().nextInt(this.getCatalogue().size());
			sommeTG+=maxQuantites.get(this.getCatalogue().get(rnd));
			produitTG.add(this.getCatalogue().get(rnd));
		}
	}

	public void next() {
		super.next();
		for (ChocolatDeMarque produit : this.stock.keySet()) {
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(produit)) {
					if (produitTG.contains(produit)) {
						((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 5.0), cryptogramme, true);
					}
					else {
						((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 5.0), cryptogramme, false);
					}
				}
			}
		}
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		i++; //Nombre d'etapes de négociation déjà passées.
		double maxPrix= this.prix.get((ChocolatDeMarque)contrat.getProduit())*0.75;
		if (contrat.getTeteGondole()) {
			maxPrix=0.9*maxPrix;}
		if (contrat.getPrix()>maxPrix) {
			return maxPrix*(0.85+i/100);}
		else {
			return contrat.getPrix(); 
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		i=0;
		ajouterStock(produit, quantite,contrat.getTeteGondole());
		JournalAchats.ajouter("achat de "+quantite+" "+produit.toString()+" a "+contrat.getVendeur().toString()+" pour un prix de "+contrat.getPrix());
		
	}
	
	

}
