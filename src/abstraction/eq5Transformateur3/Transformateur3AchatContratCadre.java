package abstraction.eq5Transformateur3;


import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadreNotifie;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;


//Manuelo
public class Transformateur3AchatContratCadre extends Transformateur3Stock implements IAcheteurContratCadreNotifie {
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		Feve feve = (Feve)produit;
		Echeancier dernierEcheancier = contrat.getEcheancier();
		int nbStep = dernierEcheancier.getNbEcheances();
		double quantiteTotale = dernierEcheancier.getQuantiteTotale();
		
		if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
			if ((nbStep>=8) && (nbStep<=18) && (quantiteTotale>=1000)) {
				return dernierEcheancier;
			} else {
				return null;
			}
		}
		
		else if (feve == Feve.FEVE_MOYENNE) {
			if ((nbStep>=2) && (nbStep<=18) && (quantiteTotale>=1000)) {
				return dernierEcheancier;
			} else {
				return null;
			}
		}
		
		else {
			return null;
		}
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		Feve feve = (Feve)produit;
		Double dernierPrix = contrat.getPrix();
		if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
			if (dernierPrix>this.prix_max_fèves_HBE.getValeur()) {
				return this.prix_max_fèves_HBE.getValeur();
			}else {
				return dernierPrix;
			}
		}
		
		else if (feve==Feve.FEVE_MOYENNE) {
			if (dernierPrix>this.prix_max_fèves_moyenne.getValeur()) {
				return this.prix_max_fèves_moyenne.getValeur();
			}else {
				return dernierPrix;
			}
		}
		
		else {
			return -1;
		}
	
	}
	
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.ajouter((Feve)produit, quantite);
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.JournalAchatContratCadre.ajouter("nouveau contrat cadre entre " + contrat.getAcheteur() + " et "+contrat.getVendeur()+" d'une quantité " + contrat.getQuantiteTotale() + "kg de " + contrat.getProduit() + " pendant " + contrat.getEcheancier() + " pour " + contrat.getPrix() +" euros le kilo");
	}	
	
}

	