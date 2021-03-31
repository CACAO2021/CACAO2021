package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class VendeurContratCadre1 extends Producteur1Acteur implements IVendeurContratCadre{

	@Override
	public boolean peutVendre(Object produit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean vend(Object produit) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	* @author Alban
	* Trouver un prix pour la poudre
	*/
	public double propositionPrix(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		double prix= 0;
		if (produit instanceof Feve) {
			if ((Feve)produit==Feve.FEVE_MOYENNE_EQUITABLE) {
				prix=2150;
			}
			if ((Feve)produit==Feve.FEVE_MOYENNE) {
				prix=1700;
			}
			if ((Feve)produit==Feve.FEVE_BASSE) {
				prix=1520;
			}
		}
		if (produit instanceof Chocolat) {
			if ((Chocolat)produit==Chocolat.POUDRE_MOYENNE_EQUITABLE) {
				prix=0;
			}
			if ((Chocolat)produit==Chocolat.POUDRE_MOYENNE) {
				prix=0;
			}
		}
		return prix;
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

}
