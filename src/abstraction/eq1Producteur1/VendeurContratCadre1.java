package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class VendeurContratCadre1 extends Producteur1Acteur implements IVendeurContratCadre{

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		// TODO Auto-generated method stub
		return null;
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
