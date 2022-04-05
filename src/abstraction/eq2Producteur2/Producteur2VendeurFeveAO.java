package abstraction.eq2Producteur2;


import java.awt.Color;
import java.util.LinkedList;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

public abstract class Producteur2VendeurFeveAO extends Producteur2Prod implements IVendeurFevesAO {

	// ensemble fait par max


	// on privilégie les CC aux AO
	// donc prix plus cher

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
		if (!(estFeveEquitable(oa.getFeve()))) {
			// que des feves non equitable par cette méthode
			return 0;
		}
		majStock(oa.getFeve());
		double stock = qttTotale(oa.getFeve()).getValeur();
		double qttAProd = aProduireAuStep(oa.getFeve(), Filiere.LA_FILIERE.getEtape());	
		if (stock > oa.getQuantiteKG() + qttAProd) {
			double p = Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 2;		// *2 pck AO			
			double min = Producteur2VeudeurFeveCC.minAcceptee(oa.getFeve()); 
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) { 
				if (c.getAcheteur() == oa.getAcheteur() && c.getFeve()==oa.getFeve()) {
					double pp = c.getPrixKG() * 0.8;
					if(pp<p) {
						p = pp;
					}
				}
			}
			for (PropositionVenteFevesAO c : this.mesContratsAO) {
				if (c.getAcheteur() == oa.getAcheteur() && c.getFeve()==oa.getFeve()) {
					double pp = c.getPrixKG() * 0.8;
					if(pp<p) {
						p = pp;
					}
				}
			}
			// on privilégie les CC aux AO
			// donc prix plus cher
			if (p >= min*1.5) { //*1.5 pck AO
				return p;
			} else {
				return min*1.5;
			}
		}else { // pas assez de qtt
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
		this.JournalVente.ajouter(Color.cyan, Color.black, "nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		Object produit = proposition.getFeve();
		double quantite = proposition.getQuantiteKg();
		majStock(produit);
		double stock = qttTotale(produit).getValeur();		
		if (stock >= quantite ) { // on appelle vente que si on peut assumer la vente
			vente(quantite, produit);
		}else { // situation impossible, on s'assure de ca avant.
			vente(stock, produit);
		}

		LinkedList<PropositionVenteFevesAO> rem = new LinkedList<PropositionVenteFevesAO>();
		if(mesContratsAORefusess.size()>0) {
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
				if (c.getAcheteur() == proposition.getAcheteur() && c.getFeve()== proposition.getFeve()) {
					rem.add(c);
				}this.mesContratsAORefusess.removeAll(rem);
			}
		}
	}
}
