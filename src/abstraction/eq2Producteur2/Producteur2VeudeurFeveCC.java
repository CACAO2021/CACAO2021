package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;


public class Producteur2VeudeurFeveCC extends Producteur2Banque implements IVendeurContratCadre {

	@Override
	//Dim
	public boolean peutVendre(Object produit) {
		if (estFeve(produit) || estPoudre(produit)) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	//Dim
	public boolean vend(Object produit) {
		double stock = qttTotale(produit);
		return stock>0;
	}
	
	//Dim
	/**
	 * vérifie si le prix proposé pour la premiere reponse est acceptable 
	 */
	public static boolean estAcceptable(double i1, Object produit) {
		double i2 = prixEspere(produit);
		double dif = difAcceptee(produit);
		return (i1 > i2 - dif);
	}
	
	public static double prixEspere(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_ESPERE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_ESPERE_FEVE_HE;
		} // a finir
	}
	public static double minAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HE;
		} // a finir
	}
	public static double difAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return DIF_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return DIF_ACCEPTEE_FEVE_HE;
		} // a finir
	}
	

	@Override
	//Dim 
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();		
		boolean cond = estAcceptable(contrat.getPrix() , produit);
		if(cond) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();
		}else { // on propose une nouvelle valeur
			Echeancier e = contrat.getEcheancier();
			// comparer au cout de production des ressources vendues ( ne pas vendre a perte + payer correctement les producteurs)
			// prendre en compte le nombre de step ou lon na pas vendu et notre tresorerie
			// prendre en compte le prix minima accepte
			double min = minAcceptee(produit);
			if(cond) {
				e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2.5); // on souhaite livrer 2.5 fois plus lors de la 1ere livraison... un choix arbitraire, juste pour l'exemple...
				return e;
			} else { //on ne souhaite pas vendeur donc on retourne null
			return null;
		}}
	}

	@Override
	//Dim
	public double propositionPrix(ExemplaireContratCadre contrat) {
		double prix = prixEspere(contrat.getProduit());
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
			// ecouler en priorite les stocks ancien
			return quantite;
		}else {
			return stock;
		}
	}

}
