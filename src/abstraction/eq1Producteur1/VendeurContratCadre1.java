package abstraction.eq1Producteur1;

import java.awt.Color;
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

public class VendeurContratCadre1 extends Producteur1Acteur implements IVendeurContratCadre{
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	private static double PRIX_PALIER_F_E = 1990; // prix minimal défini par Max Havelaar pour garantir que la fève est équitable
	protected int numero;
	protected Integer cryptogramme;
	protected Object produit;
	protected Journal journal;
	protected SuperviseurVentesContratCadre supCCadre;

	/**
	 * @author lebra
	 */
	public boolean peutVendre(Object produit) {
		if ((produit instanceof Feve)
				&& ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)
						||(((Feve)produit) == Feve.FEVE_MOYENNE)
						||(((Feve)produit) == Feve.FEVE_BASSE)
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

	@Override
	public boolean vend(Object produit) {
		if ((stock.getQuantite() != 0) 
			&& (this.peutVendre(produit))){
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	* @author Alb1x
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
		if (moyenne>VendeurContratCadre1.PRIX_PALIER_F_E) {
			return moyenne;
		}
		else {
			return (liste_prix.get(n-2)+VendeurContratCadre1.PRIX_PALIER_F_E)/2;
		}
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
