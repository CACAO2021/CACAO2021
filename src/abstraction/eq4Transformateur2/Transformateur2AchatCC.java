package abstraction.eq4Transformateur2;


import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.produits.Feve;

//Antoine C
public class Transformateur2AchatCC extends Transformateur2Vente implements IAcheteurContratCadre{

	public Transformateur2AchatCC() {
		super();
	}
	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		int j = 0;
		for (int i=contrat.getEcheancier().getStepDebut();i<contrat.getEcheancier().getStepDebut()+12;i++) {
			if (contrat.getEcheancier().getQuantite(i) > 1000 || contrat.getEcheancier().getQuantite(i) < 0) {
				j = 1;
			}
		}
		if (contrat.getEcheancier().getNbEcheances() > 12 || j == 1) {
			return null;
		}
		else {
			return contrat.getEcheancier();
		}
	}

	@Override
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		List<Double> propositions = (List<Double>) contrat.getListePrix();
		int n = propositions.size();
		if (contrat.getProduit() == Feve.FEVE_BASSE && contrat.getPrix() > cout_max_feve_basse && n>3) {
			if (propositions.get(n-1) < propositions.get(n)) {
				return -1;
			}
		}
		if (contrat.getProduit() == Feve.FEVE_MOYENNE && contrat.getPrix() > cout_max_feve_moyenne) {
			return 1000000000;
		}
		else {
			return contrat.getPrix()*0.95;
		}
	}

	@Override
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		add_stock(produit, quantite);
		
	}
	public void next() {
		
	}

}
