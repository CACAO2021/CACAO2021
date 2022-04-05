package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//ensemble fait par DIM

public abstract class Producteur2VeudeurFeveCC extends Producteur2VendeurFeveAO implements IVendeurContratCadre {
	protected LinkedList<ExemplaireContratCadre> mesContratsCC;
	public static boolean bolVUS;

	/**
	 * @param mesContrats 
	 */
	public Producteur2VeudeurFeveCC() {
		super();
		this.mesContratsCC = new LinkedList<ExemplaireContratCadre>();
		bolVUS = true; // voir verifUtiliteStock()
	}

	@Override
	public boolean peutVendre(Object produit) {
		if (estFeve(produit)) { //  || estPoudre(produit) // on ne vend pas de poudre finalement
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean vend(Object produit) {
		majStock(produit);
		double stock = qttTotale(produit).getValeur();
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
		} else if(estFeveME(produit)) {
			return PRIX_ESPERE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return PRIX_ESPERE_FEVE_M;
		} else if(estFeveB(produit)) {
			return PRIX_ESPERE_FEVE_B;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double minAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_HE;
		} else if(estFeveME(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_M;
		} else if(estFeveB(produit)) {
			return PRIX_MIN_ACCEPTEE_FEVE_B;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}
	public static double difAcceptee(Object produit) {
		if(estFeveHBE(produit)) {
			return DIF_ACCEPTEE_FEVE_HBE;
		} else if(estFeveHE(produit)) {
			return DIF_ACCEPTEE_FEVE_HE;
		} else if(estFeveME(produit)) {
			return DIF_ACCEPTEE_FEVE_ME;
		} else if(estFeveM(produit)) {
			return DIF_ACCEPTEE_FEVE_M;
		} else if(estFeveB(produit)) {
			return DIF_ACCEPTEE_FEVE_B;
		}
		else { // un produit que l'on ne vend pas
			return 0;
		}
	}

	public double aProduire(Object produit) {
		double prod = 0;
		for (ExemplaireContratCadre e: mesContratsCC) {
			if (e.getProduit() == produit) {
				prod += e.getQuantiteRestantALivrer();
			}
		}
		return prod;
	}

	public double aProduireAuStep(Object produit, int step) { 
		double prod = 0;
		for (ExemplaireContratCadre e: mesContratsCC) {
			if (e.getProduit() == produit) {
				prod += e.getEcheancier().getQuantite(step);
			}
		}
		return prod;
	}

	//DIM
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();	
		int nbEcheance = contrat.getEcheancier().getNbEcheances();
		Echeancier e = contrat.getEcheancier();
		// la quantite totale demande
		double qttDemandee = contrat.getEcheancier().getQuantiteTotale();

		// la quantite que lon peut produire sur la meme duree + la qtt que lon possède
		double qttQuiSeraProduite = qttQuiSeraProduite(nbEcheance, produit);		
		double qttDispo = qttTotale(produit).getValeur();
		double qttTotaleSurLaPeriode = qttDispo + qttQuiSeraProduite;

		// la quantite que lon sait devoir depenser sur la periode
		double qttContratEnCours = aProduire(produit);

		//condition qui decoule des stocks
		boolean condQtt = qttDemandee + qttContratEnCours < qttTotaleSurLaPeriode; 
		//System.out.println("cond qtt " + condQtt + " avec " + contrat.getAcheteur() + " car "+ qttDemandee + " " + qttContratEnCours + " < " + qttTotaleSurLaPeriode );


		// condition sur le fait detre equitable
		boolean condEquitable=true; //true si ne concerne pas les produits equitable

		// deux bool utiles que si la feve est equitable
		boolean equiNbEcheance = nbEcheance > EQUI_NB_ECHEANCE_MINI; 
		boolean equiQtt = qttDemandee > EQUI_QTT_MINI; 

		if (estFeveEquitable(produit)) {
			//pour que ce soit equitable
			// il faut une longue période
			// et une grande qtt
			condEquitable = condEquitable && equiNbEcheance && equiQtt ; 
		}

		JournalCC.ajouter(contrat.getAcheteur() + " " + qttDemandee + " " + produit + "en " + nbEcheance + " avec nos conditions: " +condQtt + condEquitable);	


		//les actions qui decoulent de nos conditions
		if(condQtt && condEquitable) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();

		}else if(condQtt && !(condEquitable)){
			// la demande ne peut pas etre accepte car pas assez equitable
			//mais on pourra fournir cette quantité de fève	
			if(!(equiQtt)) {
				// la qtt n'est pas assez importante 
				double qttMin = EQUI_QTT_MINI;
				double qttStep = qttMin/nbEcheance;				
				int i=contrat.getEcheancier().getStepDebut();
				while (i< contrat.getEcheancier().getStepFin()) {
					e.set(i, qttStep);
					i++;
				}
			}
			if(!(equiNbEcheance)) {
				// la durée n'est pas assez importante
				int i = e.getStepDebut();
				double qttStep = qttDemandee/EQUI_NB_ECHEANCE_MINI;
				while(i <= e.getStepDebut() + EQUI_NB_ECHEANCE_MINI) {
					e.set(i, qttStep);
					i++;
				}
			}			
			return e;			
		}else if(condEquitable && !(condQtt)) {
			// on  ne peut pas fournir autant de fève sur la période
			double qttStep = qttDispo/nbEcheance;				
			int i=contrat.getEcheancier().getStepDebut();
			while (i< contrat.getEcheancier().getStepFin()) {
				e.set(i, qttStep);
				i++;
			}			
			if (qttDispo>EQUI_QTT_MINI) {
				return e;
			}else {
				return null;
			}

		}else {
			// si la demande ne correspond pas à de l'équitable et que nous ne pouvons pas produire assez de fèves
			if(qttDispo<EQUI_QTT_MINI) {
				return null;
			}else {
				double qttStep = qttDispo/EQUI_NB_ECHEANCE_MINI;				
				int i=e.getStepDebut();
				while (i< e.getStepDebut() + EQUI_NB_ECHEANCE_MINI) {
					e.set(i, qttStep);
					i++;
				}		
				return e; 
			}
		}
	}


	@Override
	//Dim
	public double propositionPrix(ExemplaireContratCadre contrat) {
		double prix = prixEspere(contrat.getProduit());
		return prix ; 
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
			// donc, prendre en compte le prix minima accepte
			// prendre en compte le nombre de step ou lon na pas vendu et notre tresorerie

			double prix = contrat.getListePrix().get(contrat.getListePrix().size() - 2) * 0.8;
			// on descend le prix petit a petit ( *0.8 )
			if (prix < prixPropose ) {
				prix = prixPropose;
			}
			double min = minAcceptee(produit);					
			boolean cond2 = min<prix;
			if(cond2) {				
				return prix;
			} else {
				//on retourne le minimum accepté dans tous les cas
				// au cas ou l'acheteur accepte ce prix malgré tout
				return minAcceptee(produit);
			}
		}
	}

	@Override
	//Dim
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		// maj var mesContrats
		this.mesContratsCC.add(contrat);
		this.JournalVente.ajouter(Color.yellow, Color.black,"nouvelle vente CC avec " + contrat.getAcheteur().getNom() + " qtt = " +
				Math.floor(contrat.getQuantiteTotale()) + contrat.getProduit()
				+ " pour " + contrat.getPrix() + "euro au kg, en " + contrat.getEcheancier().getNbEcheances()
				+" échéances" );
	}

	@Override
	// Dim
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		majStock(produit);
		double livraison;
		double stock = qttTotale(produit).getValeur();		
		if (stock >= quantite ) { // on appelle vente que si on peut assumer la vente
			vente(quantite, produit);
			livraison = quantite;
			JournalLivraison.ajouter( contrat.getAcheteur() +" "+ produit + " " +  " avec un ratio de " + livraison/quantite );
		}else {
			vente(stock, produit);
			livraison = stock;
			JournalLivraison.ajouter(Color.red,Color.black, contrat.getAcheteur() +" "+ produit + " " +  " avec un ratio de " + livraison/quantite );
		}
		// journaux dif ( color = red) pr voir qd on assume pas
		return livraison;			
	}


	public void verifUtiliteStock() {
		// on gere la partie si on a trop de stock et plus de vente
		// quand la filiere ne nou sachte plus
		// on arrete de produire
		int stepSansVente = 55;
		if (Filiere.LA_FILIERE.getEtape() > stepSansVente) { 
			// on laisse du temps au début

			if ( bolVUS ) {
				// bolVUS true tant que on a pas utiliser cette fonction
				// devient false ensuite car nos arbres sont déjà supprimés

				// si on ne vend pas pendant "stepSansVente" etapes,
				// on ne vendra plus jamais
				//on supprime donc tous nos arbres
				// afin de ne pas faire faillite meme à la fin
				// (aucun valeur réelle)
				boolean bol = 
						mesContratsCC.getLast().getEcheancier().getStepFin() + stepSansVente
						<  Filiere.LA_FILIERE.getEtape()   ;

				if(bol) {
					System.out.println(this.getNom() + "on vend plus rien :/ on supprime tous nos arbres pour ne pas faire faillite " +this.getNom());
					bolVUS = false;
					// on ne supprime les arbres qu'une seule fois
					// on supprime tous nos arbres pour ne plus perdre d'argent
					// (ca na aucun sens en réalité)
					// sauf si la demande en cacao disparait totalement

					for(Feve e : listeProd) {
						arbrePlantes.get(e).removeAll(arbrePlantes.get(e));
					}		 
				}
			} 
		}
	}


}
