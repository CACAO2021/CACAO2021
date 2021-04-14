package abstraction.eq6Distributeur1;

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

	
	protected Journal JournalAchats= new Journal(this.getNom()+" achats", this);
	
	public Acheteur() {
		super();
	}
	

	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
			Echeancier e = contrat.getEcheancier();
			double maxQuantite= (this.historique.get((ChocolatDeMarque)contrat.getProduit())-this.stock.get((ChocolatDeMarque)contrat.getProduit()).getValeur())*1.1; //J'achete 10% de plus que ce que j'ai vendu moins ce qu'il me reste en stock
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2);// on propose d'acheter 2 fois plus si le vendeur n'est pas content. A modifier par la suite
			return e;
		
	}

	//est ce que on peut mettre next() ici?
	public void next() {
		super.next();
		for (ChocolatDeMarque produit : this.stock.keySet()) {
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(produit)) {
					((SuperviseurVentesContratCadre)Filiere.LA_FILIERE.getActeur("Sup.CCadre")).demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), produit, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, 5.0), cryptogramme, false);
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
