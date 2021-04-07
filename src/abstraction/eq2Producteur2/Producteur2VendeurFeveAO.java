package abstraction.eq2Producteur2;


import java.util.LinkedList;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public class Producteur2VendeurFeveAO extends Producteur2Acteur implements IVendeurFevesAO {
<<<<<<< HEAD
	public double prixMinVenteAuKilo = 2.0;
=======
	protected LinkedList<ExemplaireContratCadre> mesContratsAO;

	public Producteur2VendeurFeveAO() {
		super();
		this.mesContratsAO = new LinkedList<ExemplaireContratCadre>();
	}

>>>>>>> branch 'master' of https://github.com/dim-correia/CACAO2021
	@Override
	public double proposerPrix(OffreAchatFeves oa) {
	if ((this.pasEnFaillite())) {
		//Condition à revoir pasEnFaillite à changer ou à écrire?
		return prixMinVenteAuKilo;
	}
	else {
		return 0.0;
	}
	}

	@Override
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}
	
}
