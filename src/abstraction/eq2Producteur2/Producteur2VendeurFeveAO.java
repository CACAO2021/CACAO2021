package abstraction.eq2Producteur2;


import java.util.LinkedList;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

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
		majStock(oa.getFeve());
		double stock = qttTotale(oa.getFeve()).getValeur();
		double qttAProd = aProduireAuStep(oa.getFeve(), Filiere.LA_FILIERE.getEtape());	
		if (stock > oa.getQuantiteKG() + qttAProd) {// * 1.2 pour prendre de la marge
			double p = Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 4;		// *4 pck AO			
			double min = Producteur2VeudeurFeveCC.minAcceptee(oa.getFeve()); 
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
				if (c.getAcheteur() == oa.getAcheteur() && c.getFeve()==oa.getFeve()) {
					double pp = c.getPrixKG() - Producteur2VeudeurFeveCC.difAcceptee(oa.getFeve());
					if(pp<p) {
						p = pp;
					}
				}
			}
			if (p >= min*2) {return p;} //*2 pck AO
			else {return min*2;}
		}else {
			return 0.0;
		}
	}


	protected abstract double aProduireAuStep(Object produit, int step);

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
		if(mesContratsAORefusess.size()>0) {
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
				if (c.getAcheteur() == proposition.getAcheteur()) {
					rem.add(c);
				}this.mesContratsAORefusess.removeAll(rem);
			}
		}
	}
}
