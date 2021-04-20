package abstraction.eq2Producteur2;


import java.util.LinkedList;
import java.util.Map;
import abstraction.eq2Producteur2.contratAO;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {


	protected LinkedList<contratAO> historiqueMesContratsAO;
	

	public Producteur2VendeurFeveAO() {
		super();
		this.historiqueMesContratsAO = new LinkedList<contratAO>();
	}

	/**	@author Maxime Boillot
	 
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		double stock = qttTotale(oa.getFeve()).getValeur();
		if (stock >= oa.getQuantiteKG() ) {
			return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 4;	
		}else {
			return 0;
		}
	}
	
	/**	@author Maxime Boillot
	 
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		historiqueMesContratsAO.add(contratA0(proposition.getPrixKG(),false ,proposition.getOffreAchateFeves().getFeve()));
	}
	
	/**	@author Maxime Boillot
	 
	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.historiqueMesContratsAO.add(proposition);
		vente(proposition.getQuantiteKg(), proposition.getFeve());
		
	}
}


