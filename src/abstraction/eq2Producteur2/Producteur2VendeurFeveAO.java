package abstraction.eq2Producteur2;


import java.util.LinkedList;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {


	protected LinkedList<LinkedList<PropositionVenteFevesAO> > mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefuses;
	public LinkedList<PropositionVenteFevesAO> mesContratsAOAcceptes;
	//nbOffres=nbOffres.SuperviseurVentesFevesAO;
	public LinkedList<OffreAchatFeves> LAO;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<LinkedList<PropositionVenteFevesAO> >();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		// verif qtt
		return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve());	
	}
	
/**	@author Maxime Boillot
	 
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		this.mesContratsAORefuses.add(proposition);
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
	
	//DIM
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		vente(proposition.getQuantiteKg(), proposition.getFeve());
		
	}
}


