package abstraction.eq5Transformateur3;

import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Journal;

public class Transformateur3VenteContratCadre extends Transformateur3Acteur implements IVendeurContratCadre{
	
	public boolean peutVendre(Object produit) {
		if (produit == Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE || produit == Chocolat.TABLETTE_MOYENNE || produit == Chocolat.CONFISERIE_MOYENNE) {
			return true; }
		else { return false; }
	}

	@Override
	public boolean vend(Object produit) {
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getProduit().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) || contrat.getProduit().equals(Chocolat.TABLETTE_MOYENNE) || contrat.getProduit().equals(Chocolat.CONFISERIE_MOYENNE)) {
			if (contrat.getEcheancier().getQuantiteTotale()<500) { //quantité demandée inférieure au stock
				return contrat.getEcheancier(); } 
			else { 
				return null; } }
		else { return null; }
	}
		
	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return 2*contrat.getQuantiteTotale();
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getPrix()>1.8*contrat.getQuantiteTotale()) { //si le prix proposé est inférieur à 90% du prix initiale
			return contrat.getPrix(); }
		else { return 1.8*contrat.getQuantiteTotale();} //retourne un prix égal à 90% du prix initial
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
	}
		
	
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
