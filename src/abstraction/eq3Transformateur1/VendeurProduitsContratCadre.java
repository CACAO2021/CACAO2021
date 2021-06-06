package abstraction.eq3Transformateur1;


import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

// Gaspard (À part méthode ou il y a un autre prénom)
public class VendeurProduitsContratCadre extends Transformateur1Marque implements IVendeurContratCadre {
	

	
	//test si le produit désiré est dans notre catalogue
	public boolean peutVendre(Object produit) {
		if ((produit instanceof Chocolat)) {
			if (((Chocolat)produit).getGamme() != Gamme.BASSE) {
			return true;	
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean vend(Object produit) {
		//System.out.println(this.getStock().getFinancier().sommesNousVendeur(produit));
		return this.getStock().getFinancier().sommesNousVendeur(produit);
	}
	

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		return contrat.getEcheancier(); 
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (contrat.getProduit() instanceof Chocolat) {
			return this.getStock().prixDeVenteKG((Chocolat) contrat.getProduit());
		} else { 
			if (contrat.getProduit() instanceof ChocolatDeMarque){
				return this.getStock().prixDeVenteKG(((ChocolatDeMarque)contrat.getProduit()).getChocolat());
			} else {
				return 0;
			}
		}

	}

	@Override
	
	
	//Paul GIRAUD
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {

		// On accepte la premiere proposition pour l'instant
		double prixPropose = contrat.getPrix();

		Chocolat chocolat = null;
		if( contrat.getProduit() instanceof Chocolat) {
			chocolat = (Chocolat)contrat.getProduit() ;
		} else if (contrat.getProduit() instanceof ChocolatDeMarque) {
			chocolat = ((ChocolatDeMarque)contrat.getProduit()).getChocolat();
		}
		double margeDeBase = this.getStock().getMarge(this.getStock().equivalentFeve(chocolat));
		
			
		if (margeDeBase != 0) {
			double prixSansMarge = contrat.getListePrix().get(0)/margeDeBase;
			if (prixPropose == contrat.getListePrix().get(0)) {

				return prixPropose;
			} else {
				double nouvelleMarge = margeDeBase-0.01*contrat.getListePrix().size();
					if (nouvelleMarge>1.29) {
						if (prixPropose>= nouvelleMarge*prixSansMarge) {

							return prixPropose;
					} else {

						return prixSansMarge*nouvelleMarge;
					}
						
				} else {

					return 1.30*prixSansMarge; // on veut au minimum une marge de 30%
				}
			}
		} else {
			return 0.0;
		}
	}
 
	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {

	}
	
	
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double qdisp = 0;
		if (produit instanceof Chocolat) {
			qdisp = Math.min(this.getStock().getStockChocolats((Chocolat)produit), quantite);
			if(qdisp>0) {
				Variable quantitelivre = new Variable (this.getNom()+"quantitelivre",this,(-1)*qdisp);
				Variable prix = new Variable (this.getNom()+"prix",this,contrat.getPrix()*qdisp/contrat.getEcheancier().getQuantiteTotale());
				Variable date = new Variable(this.getNom(), this, Filiere.LA_FILIERE.getEtape());
				this.getStock().setStockChocolat((Chocolat)produit, quantitelivre, prix, date);
				return qdisp;
				
			}
		} else {
			if (produit instanceof ChocolatDeMarque) {
				qdisp = Math.min(this.getStock().getStockChocolats(((ChocolatDeMarque)produit).getChocolat()), quantite);
				if(qdisp>0) {
					Variable quantitelivre = new Variable (this.getNom()+"quantitelivre",this,(-1)*qdisp);
					Variable date = new Variable(this.getNom(), this, Filiere.LA_FILIERE.getEtape());
					Variable prix = new Variable (this.getNom()+"prix",this,contrat.getPrix()*qdisp/contrat.getEcheancier().getQuantiteTotale());
					this.getStock().setStockChocolat(((ChocolatDeMarque)produit).getChocolat(), quantitelivre, prix, date);
				
					return qdisp;
				}
			}
		}
		return 0.0;
	}
	
	

}


