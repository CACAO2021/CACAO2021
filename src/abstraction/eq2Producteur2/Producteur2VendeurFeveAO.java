package abstraction.eq2Producteur2;


import java.util.LinkedList;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public abstract class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {
	
	// ensemble fait par max


	protected LinkedList<PropositionVenteFevesAO> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefusess;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<PropositionVenteFevesAO>();
		this.mesContratsAORefusess = new LinkedList<PropositionVenteFevesAO>();
	}

	/**	@author Maxime Boillot

	 */
	public double proposerPrix(OffreAchatFeves oa) {
		double stock = qttTotale(oa.getFeve()).getValeur();
		if (stock >= oa.getQuantiteKG() ) {
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
				if (c.getAcheteur() == oa.getAcheteur()) {
					double p = c.getPrixKG() - Producteur2VeudeurFeveCC.difAcceptee(oa.getFeve());
					double min = Producteur2VeudeurFeveCC.minAcceptee(oa.getFeve());
					if (p >= min) {return p;}else {return min;}
				}
			} return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 4;	
		}else {
			return 0.0;
		}
	}

	/**	@author Maxime Boillot

	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		this.mesContratsAORefusess.add(proposition);
	}

	/**	@author Maxime Boillot

	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		vente(proposition.getQuantiteKg(), proposition.getFeve());
		LinkedList<PropositionVenteFevesAO> rem = new LinkedList<PropositionVenteFevesAO>();
		for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
			if (c.getAcheteur() == proposition.getAcheteur()) {
				rem.add(c);
			}this.mesContratsAORefusess.removeAll(rem);
		}
	}
}


