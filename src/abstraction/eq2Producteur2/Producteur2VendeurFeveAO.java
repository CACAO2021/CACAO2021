package abstraction.eq2Producteur2;


import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Acteur implements IVendeurFevesAO {
	protected LinkedList<ExemplaireContratCadre> mesContratsAO;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<ExemplaireContratCadre>();
	}

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
