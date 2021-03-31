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

public class Producteur2VendeurFeveAO extends Producteur2VeudeurFeveCC implements IVendeurFevesAO {

	@Override
	public double proposerPrix(OffreAchatFeves oa) {
		// TODO Auto-generated method stub
		return 0;
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
