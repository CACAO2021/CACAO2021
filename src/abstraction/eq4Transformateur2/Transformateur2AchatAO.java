package abstraction.eq4Transformateur2;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

//Antoine C

public class Transformateur2AchatAO extends Transformateur2 implements IAcheteurFevesAO {


	@Override
	public OffreAchatFeves getOffreAchat() {
		return new OffreAchatFeves(this, Feve.FEVE_BASSE, (double) 1000);
	}

	@Override
	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.journal.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}

	@Override
	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		int prop = 0;
		double prix_min = propositions.get(0).getPrixKG();
		for (int i=1;i<propositions.size();i++) {
			if (propositions.get(i).getPrixKG() < prix_min) {
				prix_min = propositions.get(i).getPrixKG();
				prop = i;
			}
		}
		return propositions.get(prop);
	}

	@Override
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		return this.cryptogramme;
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		double quantite = proposition.getOffreAchateFeves().getQuantiteKG();
		Feve feve = proposition.getFeve();
		add_stock(feve, quantite);
	}
}
