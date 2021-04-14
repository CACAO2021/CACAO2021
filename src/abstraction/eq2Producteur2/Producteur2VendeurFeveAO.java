package abstraction.eq2Producteur2;


import java.util.LinkedList;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
<<<<<<< HEAD
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
=======
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {

<<<<<<< HEAD

	private static final int nbOffresMaxParStep = nbOffresMaxParEtape.SuperviseurVentesFevesAO ;
	protected LinkedList<ExemplaireContratCadre> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefuses;
	public LinkedList<PropositionVenteFevesAO> mesContratsAOAcceptes;
	//nbOffres=nbOffres.SuperviseurVentesFevesAO;
	public LinkedList<OffreAchatFeves> LAO;
	double PAS_PRIX_STEP_HBE=(PRIX_ESPERE_FEVE_HBE-PRIX_MIN_ACCEPTEE_FEVE_HBE)/nbOffresMaxParStep;
	int step = Filiere.LA_FILIERE.getEtape();
=======
	protected LinkedList<PropositionVenteFevesAO> mesContratsAO;
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<PropositionVenteFevesAO>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
<<<<<<< HEAD
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
=======
		oa.getQuantiteKG()
		return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve());
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021
	
	}
	
/**	@author Maxime Boillot
	  
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
	}

	/**	@author Maxime Boillot
	 
	 */
	
	public void notifierVente(PropositionVenteFevesAO proposition) {
<<<<<<< HEAD
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
	
=======
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		Producteur2Stockage.vente(proposition.getQuantiteKg(), proposition.getFeve());
		
>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021
	}
}
