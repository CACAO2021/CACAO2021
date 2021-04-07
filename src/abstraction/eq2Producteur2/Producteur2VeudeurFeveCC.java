package abstraction.eq2Producteur2;

import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;


public class Producteur2VeudeurFeveCC extends Producteur2Banque implements IVendeurContratCadre {
		protected LinkedList<ExemplaireContratCadre> mesContratsCC;

	/**
	 * @param mesContrats
	 */
	public Producteur2VeudeurFeveCC() {
		super();
		this.mesContratsCC = new LinkedList<ExemplaireContratCadre>();
		System.out.println("test");
	}

	@Override
	public boolean peutVendre(Object produit) {
		if (estFeve(produit) || estPoudre(produit)) {
			return true;
		}else {
		return false;
		}
	}

	@Override
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
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double minAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HE;
		} // a finir
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double difAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return DIF_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return DIF_ACCEPTEE_FEVE_HE;
		} // a finir
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
<<<<<<< HEAD
		Echeancier ech =contrat ;
		if(ech==null) { 
			return null;
		}else if (ech!=contrat.getEcheancier()) {
			
		}else { //on ne souhaite pas vendeur donc on retourne null
		return null;
		}
=======
		Object produit = contrat.getProduit();	
		double qttDemandee = contrat.getEcheancier().getQuantiteTotale();
		double qttDispo = qttTotale(produit);
		boolean qtt = qttDemandee < qttDispo;
		//cond sur nos capacite de prod et les contrats deja en cours
		boolean cond = qtt;
		if(cond) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();
		}else { // on propose une nouvelle valeur
			Echeancier e = contrat.getEcheancier();
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2.5); // exemple random de modif du premier step
			boolean cond2 = true;
			if(cond2) {				
				return e;
			} else { //on ne souhaite pas vendeur donc on retourne null
			return null;
		}}
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021
	}

	@Override
	//Dim
	public double propositionPrix(ExemplaireContratCadre contrat) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		return 0;
=======
		double prix = prixEspere(contrat.getProduit());
		return prix;
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021
	}

	@Override
	//Dim
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();		
		double prixPropose = contrat.getPrix();
		boolean cond = estAcceptable(prixPropose , produit);
		if(cond) { // on est daccord avec l'échéancier
			return prixPropose;
		}else { // on propose une nouvelle valeur
			// comparer au cout de production des ressources vendues ( ne pas vendre a perte + payer correctement les producteurs)
			// prendre en compte le nombre de step ou lon na pas vendu et notre tresorerie
			// prendre en compte le prix minima accepte
			double min = minAcceptee(produit);		
			double prix = 0; // calculer un prix a proposer
			boolean cond2 = min<prix;
			if(cond2) {				
				return prix;
			} else { //on ne souhaite pas vendeur donc on retourne null
				return -2;
		}}
	}

	@Override
	//Dim
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// maj var mesContrats
		this.mesContratsCC.add(contrat);
		System.out.println("youpi un contrat");
		// garder en mémoire la production future a assumer
		contrat.getQuantiteTotale(); // va falloir produire ca
		contrat.getEcheancier().getStepFin(); // dernier step ou on doit fournir
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
