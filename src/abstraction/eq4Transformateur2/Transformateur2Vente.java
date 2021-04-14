package abstraction.eq4Transformateur2;

import java.util.LinkedList;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Categorie;
import java.util.ArrayList;


//Abigaëlle


public class Transformateur2Vente extends Transformateur2Production implements IVendeurContratCadre {
	
	protected LinkedList<ExemplaireContratCadre> contrats;




	public Transformateur2Vente() {

		super();
		this.contrats = new LinkedList<ExemplaireContratCadre>();
	}
	
	public boolean peutVendre(Object produit) {
		if (produit instanceof Chocolat) {
			if (((Chocolat) produit).getGamme() != Gamme.HAUTE) {
				if (((Chocolat) produit).getCategorie() != Categorie.POUDRE) {
					return true; 
				}
			}
		}
		return false;
	}
	
	public boolean vend(Object produit) {
		if (produit instanceof Chocolat) {
			if (((Chocolat) produit).getGamme() != Gamme.HAUTE) {
				if (((Chocolat) produit).getCategorie() != Categorie.POUDRE) {
					return true; 
				}
				
			}
		}
		return false;
	}
	
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.contrats.add(contrat);
		
	}
	
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = 0;
		if (produit instanceof Chocolat) {
			if ((Chocolat) produit == Chocolat.CONFISERIE_BASSE) {
				livre = Math.min(get_stock(Chocolat.CONFISERIE_BASSE), quantite);
				delete_stock(produit, quantite);
			}
			if ((Chocolat) produit == Chocolat.CONFISERIE_MOYENNE) {
				livre = Math.min(get_stock(Chocolat.CONFISERIE_MOYENNE), quantite);
				delete_stock(produit, quantite);
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_BASSE) {
				livre = Math.min(get_stock(Chocolat.TABLETTE_BASSE), quantite);
				delete_stock(produit, quantite);
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_MOYENNE) {
				livre = Math.min(get_stock(Chocolat.TABLETTE_MOYENNE), quantite);
				delete_stock(produit, quantite);
			}
		}	
		return livre;
	}
	
	public void next() {
		LinkedList<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.contrats) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.contrats.removeAll(contratsObsoletes);
	}
	
	public double propositionPrix(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		if (produit instanceof Chocolat) {
			if ((Chocolat) produit == Chocolat.CONFISERIE_BASSE) {
				return 6.2 ;
			}
			if ((Chocolat) produit == Chocolat.CONFISERIE_MOYENNE) {
				return 9.2  ;
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_BASSE) {
				return 4.6 ; 
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_MOYENNE) {
				return 9.2  ;
			}
			
		}	
		return 10000000000.0 ; //si on ne vend pas ce produit 
	}

	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double prixA = contrat.getListePrix().get(-1);
		double prixV = contrat.getListePrix().get(-2);
		if ((prixV - prixA) <= prixV*0.1 ) {
			return prixA; // pas de négociation car écart faible entre les deux prop
		}
		else {
			if (prixA*0.45 + prixV*0.55 >= contrat.getListePrix().get(0)*0.8) {
				return prixA*0.45 + prixV*0.55 ; 
			}
			else {
				return 0;
			}
		}
	}
	
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		
		 return contrat.getEcheancier();
		
	}


}
