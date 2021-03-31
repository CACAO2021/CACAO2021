package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VeudeurFeveCC extends Producteur2Acteur implements IVendeurContratCadre {

	@Override
	public boolean peutVendre(Object produit) {
		if (produit instanceof Feve || produit.equals(Categorie.POUDRE )) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	public boolean vend(Object produit) {
		if (produit instanceof Feve) {
			return true;
		}else if (produit.equals(Categorie.POUDRE )) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if(true) { 
			Echeancier ech = ;
			return ech;
		}else if() {
			
		}else { //on ne souhaite pas vendeur donc on retourne null
		return null;
		}
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return 0;
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
