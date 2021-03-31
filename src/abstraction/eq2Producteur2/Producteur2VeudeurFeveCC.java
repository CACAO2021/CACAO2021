package abstraction.eq2Producteur2;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VeudeurFeveCC extends Producteur2Acteur implements IVendeurContratCadre {

	@Override
	//Dim
	public boolean peutVendre(Object produit) {
		if (produit instanceof Feve || produit.equals(Categorie.POUDRE )) {
			return true;
		}else {
		return false;
		}
	}

	@Override
	//Dim
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
	//Dim 
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		if(true) { // on est daccord avec l'échéancier
			return contrat.getEcheancier();
		}else if(true) { // on propose une nouvelle valeur
			Echeancier e = contrat.getEcheancier();
			e.set(e.getStepDebut(), e.getQuantite(e.getStepDebut())*2.5);// on souhaite livrer 2.5 fois plus lors de la 1ere livraison... un choix arbitraire, juste pour l'exemple...
			return e;	
		}else { //on ne souhaite pas vendeur donc on retourne null
		return null;
		}
	}

	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		// première proposition de prix
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
