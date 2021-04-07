package abstraction.eq2Producteur2;


import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Acteur implements IVendeurFevesAO {

	public double prixMinVenteAuKilo = 2.0;
	protected LinkedList<ExemplaireContratCadre> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefuses;
	public LinkedList<PropositionVenteFevesAO> mesContratsAOAcceptes;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<ExemplaireContratCadre>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
	if (stock>=oa.getQuantiteKG()) {
	//Revoir la condition ci-dessus
		return prixMinVenteAuKilo;
	}
	else {
		return 0.0;
	}
	}
/**	@author Maxime Boillot
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		mesContratsAORefuses.add(proposition);
	}

	/**	@author Maxime Boillot
	 
	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		mesContratsAOAcceptes.add(proposition);
		
	}
	
}
