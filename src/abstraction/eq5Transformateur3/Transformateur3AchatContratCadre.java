package abstraction.eq5Transformateur3;


import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.Feve;


//Manuelo
public class Transformateur3AchatContratCadre extends Transformateur3Stock implements IAcheteurContratCadre {
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Echeancier dernierEcheancier = contrat.getEcheancier();
		int nbStep = dernierEcheancier.getNbEcheances();
		double quantiteTotale = dernierEcheancier.getQuantiteTotale();
		
		if ((nbStep>12) && (nbStep<24) && (quantiteTotale>=10000)) {
			return dernierEcheancier;
		} else{
			return null;
		} 
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		Double dernierPrix = contrat.getPrix();
		if (dernierPrix>this.prix_max_fèves.getValeur()) {
			return -1;
		} else {
			return dernierPrix;
		} 
	}	
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.ajouter((Feve)produit, quantite);
	}
	
}

	