package abstraction.eq2Producteur2;


import java.util.LinkedList;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {


	private static final int nbOffresMaxParStep = nbOffresMaxParEtape.SuperviseurVentesFevesAO ;
	protected LinkedList<ExemplaireContratCadre> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefuses;
	public LinkedList<PropositionVenteFevesAO> mesContratsAOAcceptes;
	//nbOffres=nbOffres.SuperviseurVentesFevesAO;
	public LinkedList<OffreAchatFeves> LAO;
	double PAS_PRIX_STEP_HBE=(PRIX_ESPERE_FEVE_HBE-PRIX_MIN_ACCEPTEE_FEVE_HBE)/nbOffresMaxParStep;
	int step = Filiere.LA_FILIERE.getEtape();

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<ExemplaireContratCadre>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		Feve feveachetee=oa.getFeve();
		
	/**On cherche à récupérer le nombre de contre-propositions ayant eu lieu pour cet OA de fèves
		for(PropositionVenteFevesAO propVFAO:mesContratsAORefuses) {
			if (oa==propVFAO.getOffreAchateFeves()) {
				
				 On récupère la date d'émission de la proposition 
				 int i = propVFAO.getNbStep()
			
			}
		}
	}
	*/
	if ((feveachetee==Feve.FEVE_HAUTE_BIO_EQUITABLE)&&(qttTotale(Feve.FEVE_HAUTE_BIO_EQUITABLE).getValeur()>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_HBE;
	}
	else if ((feveachetee==Feve.FEVE_HAUTE_EQUITABLE)&&(qttTotale(Feve.FEVE_HAUTE_EQUITABLE).getValeur()>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_HE;
	}
	else if ((feveachetee==Feve.FEVE_MOYENNE_EQUITABLE)&&(qttTotale(Feve.FEVE_MOYENNE_EQUITABLE).getValeur()>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_ME;
	}
	else if ((feveachetee==Feve.FEVE_MOYENNE)&&(qttTotale(Feve.FEVE_MOYENNE).getValeur()>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_M;
	}
	else if ((feveachetee==Feve.FEVE_BASSE)&&(qttTotale(Feve.FEVE_BASSE).getValeur()>oa.getQuantiteKG())) {
		return PRIX_ESPERE_FEVE_B;
	}
	else {
		return 0.0;
	}
	
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
	/**@author Maxime Boillot
	  Sert à créer une liste des offres d'achat à partir des propositions
	 */
	public OffreAchatFeves conversion(LinkedList<PropositionVenteFevesAO> mesContratsAORefuses) {
		this.LAO = new LinkedList<OffreAchatFeves>();
		for (int i=0;i=mesContratsAORefuses.size();i++) {
			LAO.add(mesContratsAORefuses.get(i).getOffreAchateFeves());
		}
	
	}
}
