package abstraction.eq2Producteur2;


import java.util.LinkedList;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {

	protected LinkedList<ExemplaireContratCadre> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefuses;
	public LinkedList<PropositionVenteFevesAO> mesContratsAOAcceptes;
	//nbOffres=nbOffres.SuperviseurVentesFevesAO;

	public Producteur2VendeurFeveAO() { 
		super();
		this.mesContratsAO = new LinkedList<ExemplaireContratCadre>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		/**Feve feveachetee=oa.getFeve();
	int nbOffres=nbOffres.get(acheteurs.get(acheteurCourant));
	
	if ((feveachetee==Feve.FEVE_HAUTE_BIO_EQUITABLE)&&(qttTotale(Feve.FEVE_HAUTE_BIO_EQUITABLE)>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_HBE;
	}
	else if ((feveachetee==Feve.FEVE_HAUTE_EQUITABLE)&&(qttTotale(Feve.FEVE_HAUTE_EQUITABLE)>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_HE;
	}
	else if ((feveachetee==Feve.FEVE_MOYENNE_EQUITABLE)&&(qttTotale(Feve.FEVE_MOYENNE_EQUITABLE)>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_ME;
	}
	else if ((feveachetee==Feve.FEVE_MOYENNE)&&(qttTotale(Feve.FEVE_MOYENNE)>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_M;
	}
	else if ((feveachetee==Feve.FEVE_BASSE)&&(qttTotale(Feve.FEVE_BASSE)>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_B;
	}
	else {
		return 0.0;
	}*/
		return 0;
	}
	
/**	@author Maxime Boillot
	 
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		this.mesContratsAORefuses.add(proposition);
	}

	/**	@author Maxime Boillot
	 
	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.mesContratsAOAcceptes.add(proposition);
		
	}
	
}
