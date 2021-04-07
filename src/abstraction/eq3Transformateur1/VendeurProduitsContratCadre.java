package abstraction.eq3Transformateur1;

import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

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
		// on renvoie toujours NON pour l'instant 
		return false;
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// on accepte toujours la 1ere proposition d'échéancier pour l'instant
		
		return contrat.getEcheancier();
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		// Prix arbitraire de 1000 a changer ?
		return 1000;
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
	}

	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
	//besoin d'une fonction du stock qui indique la quantité disponible
	// pour l'instant on ne prend pas en compte les potentiels autres contrats qui partagent des echeanciers
		return 0.0;
	}
	
	

}
