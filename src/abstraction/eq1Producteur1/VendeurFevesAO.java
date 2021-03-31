package abstraction.eq1Producteur1;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public class VendeurFevesAO extends Producteur1Acteur implements IVendeurFevesAO  {

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
