package abstraction.eq4Transformateur2;


import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

//Antoine C

public class Transformateur2AchatAO extends Transformateur2AchatCC implements IAcheteurFevesAO {

	public Transformateur2AchatAO() {
		super();
	}
	
	
	@Override
	public OffreAchatFeves getOffreAchat() {
		
		if (get_stock(Feve.FEVE_BASSE) < quantite_totale_demandee_basse / nombre_step_total_basse + mini_stock_feve_basse) {
			this.journal_achat.ajouter("On envoie une offre d'achat pour "+(quantite_totale_demandee_basse / nombre_step_total_basse + mini_stock_feve_basse - get_stock(Feve.FEVE_BASSE))+" de FEVE_BASSE");
			return new OffreAchatFeves(this, Feve.FEVE_BASSE,quantite_totale_demandee_basse / nombre_step_total_basse + mini_stock_feve_basse - get_stock(Feve.FEVE_BASSE));
		}
		if (get_stock(Feve.FEVE_MOYENNE) < quantite_totale_demandee_moyenne / nombre_step_total_moyenne + mini_stock_feve_moyenne) {
			this.journal_achat.ajouter("On envoie une offre d'achat pour "+(quantite_totale_demandee_moyenne / nombre_step_total_moyenne + mini_stock_feve_moyenne - get_stock(Feve.FEVE_MOYENNE))+" de FEVE_MOYENNE");
			return new OffreAchatFeves(this, Feve.FEVE_MOYENNE, quantite_totale_demandee_moyenne / nombre_step_total_moyenne + mini_stock_feve_moyenne - get_stock(Feve.FEVE_MOYENNE));
		}
		else {
			return null;
		}
	}

	@Override
	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.journal_achat.ajouter("--> aucune proposition de vente pour l'offre "+oa);
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
		if (prix_min > cout_max_feve_basse && propositions.get(prop).getFeve() == Feve.FEVE_BASSE) {
			return null;
		}
		if (prix_min > cout_max_feve_moyenne && propositions.get(prop).getFeve() == Feve.FEVE_MOYENNE) {
			return null;
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
		this.journal_achat.ajouter("On reçoit "+quantite+" de "+proposition.getOffreAchateFeves().getFeve()+ " par "+proposition.getVendeur()+" lors d'un appel d'offre ");

	}
	
	// Test
	
}
