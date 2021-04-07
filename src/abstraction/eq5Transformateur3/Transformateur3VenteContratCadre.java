package abstraction.eq5Transformateur3;

import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Journal;


//Léna

public class Transformateur3VenteContratCadre extends Transformateur3Stock implements IVendeurContratCadre{
	
	public boolean peutVendre(Object produit) {
		if (produit == Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE || produit == Chocolat.TABLETTE_MOYENNE || produit == Chocolat.CONFISERIE_MOYENNE) {
			return true; }
		else { return false; }
	}

	@Override
	public boolean vend(Object produit) {
		if (this.getChocolats().get(produit).getValeur()>0) {
			return true;}
		else { return false;}

	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if (contrat.getProduit().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) || contrat.getProduit().equals(Chocolat.TABLETTE_MOYENNE) || contrat.getProduit().equals(Chocolat.CONFISERIE_MOYENNE)) {
			if (contrat.getEcheancier().getQuantiteTotale() <= this.getChocolats().get(contrat.getProduit()).getValeur()) {
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
		this.JournalVenteContratCadre.ajouter("nouveau contrat cadre vente " + "avec " + contrat.getAcheteur() + ". Vente de " + contrat.getQuantiteTotale() + "de " + contrat.getProduit() + "pendant " + contrat.getEcheancier() + "pour " + contrat.getPrix());
	}
	
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = Math.min(this.getChocolats().get(produit).getValeur(), quantite);
		if (livre>0.0) {
			this.retirer((Chocolat)produit, livre);
		}
		return livre;
	}
	

}
