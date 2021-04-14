package abstraction.eq4Transformateur2;

import java.util.LinkedList;
import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.produits.Categorie;


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
		return 0.5 + (5000.0/contrat.getQuantiteTotale());// plus la quantite est elevee, plus le prix est interessant
	}

	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
			return contrat.getPrix(); // pas de négociation
	}
	
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		
		 return null;
		
	}


}
