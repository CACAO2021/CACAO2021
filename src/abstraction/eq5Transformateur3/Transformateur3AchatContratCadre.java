package abstraction.eq5Transformateur3;


import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;


//Manuelo
public class Transformateur3AchatContratCadre extends Transformateur3Stock implements IAcheteurContratCadre {
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Echeancier dernierEcheancier = contrat.getEcheancier();
		int nbStep = dernierEcheancier.getNbEcheances();
		double quantiteTotale = dernierEcheancier.getQuantiteTotale();
		
		if ((nbStep>1) && (nbStep<24) && (quantiteTotale>=1000)) {
			return dernierEcheancier;
		} else{
			return null;
		} 
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		Double dernierPrix = contrat.getPrix();
		if (dernierPrix>this.prix_max_fèves.getValeur()) {
			return this.prix_max_fèves.getValeur();
		} else {
			return dernierPrix;
		} 
	}
	
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.ajouter((Feve)produit, quantite);
	}
	
}

	