package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Journal;

public class Acheteur extends Vendeur implements IAcheteurContratCadre {
	private double maxQuantite=10000;
	private double minQuantite=0;
	private double maxPrix=10000; //Pour les contrats cadres, on regarde les quantités nécéssaires et notre prix max auquels cas on ne réalise pas le contrat.

	
	protected Journal JournalAchats= new Journal(this.getNom()+" achats", this);
	
	
	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
			Echeancier e = contrat.getEcheancier();
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2);// on propose d'acheter 2 fois plus si le vendeur n'est pas content. A modifier par la suite
			return e;
		
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		int i=contrat.getEcheanciers().size(); //Nombre d'etapes de négociation déjà passées.
		double maxPrix= this.prix.get((ChocolatDeMarque)contrat.getProduit())*0.75;
		if (contrat.getTeteGondole()) {
			maxPrix=0.9*maxPrix;}
		if (contrat.getPrix()>this.maxPrix) {
			return maxPrix*(0.85+i/100);}
		else {
			return contrat.getPrix(); 
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		ajouterStock(produit, quantite,contrat.getTeteGondole());
		JournalAchats.ajouter("achat de "+quantite+" "+produit.toString()+" a "+contrat.getVendeur().toString()+" pour un prix de "+contrat.getPrix());
		
	}
	
	

}
