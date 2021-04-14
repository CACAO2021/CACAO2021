package abstraction.eq2Producteur2;


import java.util.LinkedList;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {

	protected LinkedList<PropositionVenteFevesAO> mesContratsAO;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<PropositionVenteFevesAO>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		oa.getQuantiteKG()
		return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve());
	
	}
	
/**	@author Maxime Boillot
	  
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
	}

	/**	@author Maxime Boillot
	 
	 */
	
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		Producteur2Stockage.vente(proposition.getQuantiteKg(), proposition.getFeve());
		
	}
}
