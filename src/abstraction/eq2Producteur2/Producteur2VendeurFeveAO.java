package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Producteur2VendeurFeveAO extends Producteur2Acteur implements IVendeurFevesAO {
	public double prixMinVenteAuKilo = 2.0;
	@Override
	public double proposerPrix(OffreAchatFeves oa) {
	if ((this.pasEnFaillite())) {
		//Condition à revoir pasEnFaillite à changer ou à écrire?
		return prixMinVenteAuKilo;
	}
	else {
		return 0.0;
	}
	}

	@Override
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}
	
}
