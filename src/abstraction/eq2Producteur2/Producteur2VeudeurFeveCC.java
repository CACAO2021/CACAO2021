package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.contratsCadres.ContratCadre;

import java.util.List;

import abstraction.eq2Producteur2.*;

public class Producteur2VeudeurFeveCC extends Producteur2Banque implements IVendeurContratCadre {

	@Override
	//Dim
	public boolean peutVendre(Object produit) {
		if (produit instanceof Feve || produit.equals(Categorie.POUDRE )) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	//Dim
	public boolean vend(Object produit) {
		if (produit instanceof Feve) {
			// vérifier qtt
			return true;
		}else if (produit.equals(Categorie.POUDRE )) {
			//vérifier qtt
			return true;
		}
		else {
		return false;
		}
	}
	
	//Dim
	/**
	 * vérifie si le prix proposé pour la premiere reponse est acceptable 
	 */
	public static boolean estAcceptable(double i1, double i2, double dif) {
		return (i1 > i2 - dif);
	}
	

	@Override
	//Dim 
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		boolean cond = estAcceptable(contrat.getListePrix().get(contrat.getListePrix().size()) , Producteur2Valeurs.PRIX_ESPERE_FEVE, Producteur2Valeurs.DIFF_ACCEPTEE);
		if(cond) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();
		}else { // on propose une nouvelle valeur
			Echeancier e = contrat.getEcheancier();
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2.5); // on souhaite livrer 2.5 fois plus lors de la 1ere livraison... un choix arbitraire, juste pour l'exemple...
			if(cond) {
				return e;
			} else { //on ne souhaite pas vendeur donc on retourne null
			return null;
		}}
	}

	@Override
	//Dim
	public double propositionPrix(ExemplaireContratCadre contrat) {
		// première proposition de prix
		double prix = Producteur2Valeurs.PRIX_ESPERE_FEVE;
		return prix;
	}

	@Override
	//Dim
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		boolean cond = true;
		if (cond) {// on est ok
			return contrat.getPrix();
		} else if(cond){ // on propose une nvlle valeur
			return 2;
		}else { // on annule
			return -2;
		}
	}

	@Override
	//Dim
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		System.out.println("youpi un contrat");
		// garder en mémoire la production future
		contrat.getQuantiteTotale();
	}

	@Override
	// Dim
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double stock = 0;
		if (stock >= quantite ) {
			// maj stock a faire ici
			return quantite;
		}else {
			return stock;
		}
	}

}
