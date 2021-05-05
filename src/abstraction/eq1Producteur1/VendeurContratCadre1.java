package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class VendeurContratCadre1 extends VendeurFevesAO implements IVendeurContratCadre{
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	private static double PRIX_PALIER_F_E = 1990; // prix minimal défini par Max Havelaar pour garantir que la fève est équitable
	protected int numero;
	protected Integer cryptogramme;
	protected Object produit;
	protected Journal journal;
	protected SuperviseurVentesContratCadre supCCadre;
	protected List<ExemplaireContratCadre> mesCC; 
	

	/**
	 * @author lebra
	 */
	public boolean peutVendre(Object produit) {
		if ((produit instanceof Feve)
				&& ( (((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)
						|| (((Feve)produit) == Feve.FEVE_MOYENNE)
						|| (((Feve)produit) == Feve.FEVE_BASSE)
					)
			) {
				return(true);
		}
		else if ((produit instanceof Chocolat)
				&& ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE_EQUITABLE)
					||(((Chocolat)produit) == Chocolat.POUDRE_MOYENNE))) {
			return (true); 
		}
		else {
			return (false);
		}
		
	}
	/**
	 * @author arthurlemgit
	 * Dans un premier temps, on accepte de vendre par CC dès lors qu'on a du produit (donc que notre stock>0)
	 * A ajouter ? on accepte de vendre uniquement aux acheteurs réguliers (-> fidélisation)
	 */

	public boolean vend(Object produit){
		boolean res = false;
		if (this.peutVendre(produit)) {
			res = this.getStock(produit).getQuantite() > 0;
		}
	return  res;
}
		

	
	/**
	 * @author arthurlemgit
	 * Si l'échéancier proposé demande trop de quantité par rapport à notre stock et notre stratégie, ou "pas assez";
	 * propose un nouvel échéancier avec des quantités plus "raisonnables" de notre stock, en accord avec notre stratégie.
	 * 
	 * 
	 */
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.25*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.05*this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.15*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			} 
		} else if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.55*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.35*this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.45*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			} 
		} else if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_BASSE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.5*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.30*this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.40*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			} 
		} else if (contrat.getProduit() instanceof Chocolat && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE_EQUITABLE))) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.30*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.10 *this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.2*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			}
		} else if (contrat.getProduit() instanceof Chocolat && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE))) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.60*this.getStocks().get(contrat.getProduit()).getQuantite()/contrat.getEcheancier().getNbEcheances() ) {
				double nvlleqte = 0.8*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			}
		} else {
			return contrat.getEcheancier();
		}
	}

	/** 
	* @author Alb1x
	* Trouver un prix pour la poudre
	* Pour l'instant on n'en produit pas
	*/
	public double propositionPrix(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		double prix= 0;
		if (produit instanceof Feve) {
			if ((Feve)produit==Feve.FEVE_MOYENNE_EQUITABLE) {
				prix=2150;
			}
			else if ((Feve)produit == Feve.FEVE_MOYENNE) {
				prix = 1800;
			}
			else if ((Feve)produit == Feve.FEVE_BASSE) {
				prix = 1500;
			}
		}
		if (produit instanceof Chocolat) {
			if ((Chocolat)produit==Chocolat.POUDRE_MOYENNE_EQUITABLE) {
				prix=3000;
			}
			if ((Chocolat)produit==Chocolat.POUDRE_MOYENNE) {
				prix=2700;
			}
		}
		return prix;
	}

	/**
	 * @author Alb1x
	 * La méthode contrat cadre ne concerne que les fèves équitables
	 * On essaye de couper la poire en deux si le prix précedent ne convient pas.
	 * Si le nouveau prix est au dessus du seuil requis alors on le propose
	 * sinon on fait la moyenne du prix proposé et du prix seuil.
	 */
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		List<Double> liste_prix = contrat.getListePrix();
		int n = liste_prix.size();
		double moyenne = (liste_prix.get(n-2)+liste_prix.get(n-1))/2; // on coupe la poire en deux entre notre proposition et la proposition de l'acheteur
		if (moyenne>PRIX_PALIER_F_E) {
			return moyenne;
		}
		else {
			return (liste_prix.get(n-2)+PRIX_PALIER_F_E)/2;
		}
	}


	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (this.mesCC==null) {
			this.mesCC= new LinkedList<ExemplaireContratCadre>();
		}
		this.mesCC.add(contrat);

	}

	/**
	 * @author arthurlemgit
	 * Retourne la quantité de produit livré et met à jour le stock, selon le type de produit.
	 * On livre ici systématiquement la quantité maximale qu'on puisse. 
	 * @author lebra seulement pour les this.getJournaux()
	 */
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if ((produit instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE))) {
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal(3).ajouter("Livraison de " + livre + "kg de " + produit);
				}
			return livre;
		} else if ((produit instanceof Chocolat) && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE_EQUITABLE))) {
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal(3).ajouter("Livraison de " + livre + "kg de " + produit);
			}
			return livre;
		} else if ((produit instanceof Chocolat) && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE))) {
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal(3).ajouter("Livraison de " + livre + "kg de " + produit);
			}
			return livre;
		}
		return 0; 
	}
}
