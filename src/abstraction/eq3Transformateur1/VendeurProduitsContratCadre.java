package abstraction.eq3Transformateur1;


import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;


public class VendeurProduitsContratCadre extends Transformateur1Acteur implements IVendeurContratCadre {
	

	//test si le produit désiré est dans notre catalogue
	public boolean peutVendre(Object produit) {
		MarqueTransformateur1 M = new MarqueTransformateur1(); //on rappelle la classe marquetransformateur1
		List<Chocolat> L = M.getProduits(); //liste des produits
		if(L.contains(produit)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean vend(Object produit) {
		// Implementer une fonction booléenne qui indique s'il y a du stock dans un produit spécifique
		return this.getStock().getFinancier().sommesNousVendeur(produit);
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// on accepte toujours la 1ere proposition d'échéancier pour l'instant
		
		return contrat.getEcheancier();
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		return this.getStock().getFinancier().prixVente(contrat.getQuantiteTotale(), (Chocolat) contrat.getProduit());
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
		double qdisp = Math.min(this.getStock().getStockChocolats((Chocolat)produit), quantite);
		if(qdisp>0) {
			Variable quantitelivre = new Variable (this.getNom()+"quantitelivre",this,(-1)*qdisp);
			Variable prix = new Variable (this.getNom()+"prix",this,contrat.getPrix()*qdisp/contrat.getEcheancier().getQuantiteTotale());
			this.getStock().setStockChocolat((Chocolat)produit, quantitelivre, prix);
			return qdisp;
		}
		return 0.0;
	}
	
	

}
