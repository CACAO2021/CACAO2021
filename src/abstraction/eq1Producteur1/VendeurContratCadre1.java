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
	private Stock stock;
	protected List<ExemplaireContratCadre> mesCC;
	

	/**
	 * @author lebra
	 * on ne vend par CC que des feves equitables
	 */
	public boolean peutVendre(Object produit) {
		if ((produit instanceof Feve)
				&& ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)
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
	 * @author arthurlem
	 * Dans un premier temps, on accepte de vendre par CC dès lors qu'on a du produit (donc que notre stock>0)
	 * A ajouter ? on accepte de vendre uniquement aux acheteurs réguliers (-> fidélisation)
	 */

	public boolean vend(Object produit){
	return  ((stock.getQuantite() > 0) && (this.peutVendre(produit)));
}
		

	
	/**
	 * @author arthurlem
	 * Si l'échéancier proposé demande trop de quantité par rapport à notre stock et notre stratégie, ou "pas assez";
	 * propose un nouvel échéancier avec des quantités plus "raisonnables" de notre stock, en accord avec notre stratégie.
	 * Si les quantités totales sont grosso modo ce qu'on a prévu de vendre, on essaie de vendre "plus" au début pour éviter d'accumuler du stock.
	 * 
	 * 
	 */
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale() >=  0.25*stock.getQuantite() || contrat.getEcheancier().getQuantiteTotale() <=  0.05*stock.getQuantite()) {
				double nvlleqte = 0.15*stock.getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				double random = Math.random()/3;
				int step_milieu =(contrat.getEcheancier().getStepDebut()+contrat.getEcheancier().getStepFin())/2;
				double qté_fin = contrat.getEcheancier().getQuantiteAPartirDe(step_milieu);
				 Echeancier e = new Echeancier (contrat.getEcheancier());
				 int i;
				 for (i=e.getStepDebut(); i<step_milieu; i++) {
					 e.set(i, e.getQuantite(i)+ ((double)random*qté_fin/step_milieu));
				 }
				 for (i=step_milieu; i<=e.getStepFin(); i++) {
					 e.set(i, e.getQuantite(i)- ((double)random*qté_fin/step_milieu));
				 }
				return e;
			} 
		} else if (contrat.getProduit() instanceof Chocolat && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE_EQUITABLE))) {
			if (contrat.getEcheancier().getQuantiteTotale() >=  0.30*stock.getQuantite() || contrat.getEcheancier().getQuantiteTotale() <=  0.10 *stock.getQuantite()) {
				double nvlleqte = 0.2*stock.getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				double random = Math.random()/3;
				int step_milieu =(contrat.getEcheancier().getStepDebut()+contrat.getEcheancier().getStepFin())/2;
				double qté_fin = contrat.getEcheancier().getQuantiteAPartirDe(step_milieu);
				 Echeancier e = new Echeancier (contrat.getEcheancier());
				 int i;
				 for (i=e.getStepDebut(); i<step_milieu; i++) {
					 e.set(i, e.getQuantite(i)+ ((double)random*qté_fin/step_milieu));
				 }
				 for (i=step_milieu; i<=e.getStepFin(); i++) {
					 e.set(i, e.getQuantite(i)- ((double)random*qté_fin/step_milieu));
				 }
				return e;
			}
		} else if (contrat.getProduit() instanceof Chocolat && ((((Chocolat)produit) == Chocolat.POUDRE_MOYENNE))) {
			if (contrat.getEcheancier().getQuantiteTotale() <=  0.60*stock.getQuantite() ) {
				double nvlleqte = 0.8*stock.getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				double random = Math.random()/3;
				int step_milieu =(contrat.getEcheancier().getStepDebut()+contrat.getEcheancier().getStepFin())/2;
				double qté_fin = contrat.getEcheancier().getQuantiteAPartirDe(step_milieu);
				 Echeancier e = new Echeancier (contrat.getEcheancier());
				 int i;
				 for (i=e.getStepDebut(); i<step_milieu; i++) {
					 e.set(i, e.getQuantite(i)+ ((double)random*qté_fin/step_milieu));
				 }
				 for (i=step_milieu; i<=e.getStepFin(); i++) {
					 e.set(i, e.getQuantite(i)- ((double)random*qté_fin/step_milieu));
				 }
				return e;
			}
			
		}
		else {
			return null;
		}
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
		this.mesCC.add(contrat);

	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
