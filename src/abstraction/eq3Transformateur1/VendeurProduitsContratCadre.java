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

// Gaspard
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
		return this.getStock().getFinancier().sommesNousVendeur(produit);
	}
	

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// on accepte toujours la 1ere proposition d'échéancier pour l'instant
		
		return contrat.getEcheancier();
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (contrat.getProduit() instanceof Chocolat) {
			return this.getStock().getFinancier().prixVente(contrat.getQuantiteTotale(), (Chocolat) contrat.getProduit());
		} else { 
			if (contrat.getProduit() instanceof ChocolatDeMarque){
				return this.getStock().getFinancier().prixVente(contrat.getQuantiteTotale(), ((ChocolatDeMarque)contrat.getProduit()).getChocolat());
			} else {
				return 0;
			}
		}

	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// On accepte la premiere proposition pour l'instant
		return contrat.getPrix();
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		//Ajouter un journal.ajouter(pas d'offre) dans toutes les fonctions si le return est null
		this.journalVendeur.ajouter("Offre de vente : "+contrat);
		this.getStock().getFinancier().setMesContratEnTantQueVendeur(contrat);
		
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


