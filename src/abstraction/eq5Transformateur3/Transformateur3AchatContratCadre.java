package abstraction.eq5Transformateur3;

import java.util.List;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;

public class Transformateur3AchatContratCadre implements IAcheteurContratCadre {
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		List<Echeancier> precedentesPropositions = contrat.getEcheanciers();
		Echeancier dernierEcheancier = contrat.getEcheancier();
		if () {
			return null;
		} elif () {
			return dernierEcheancier;
		} else {
			return new Echeancier(stepDebut, nbStep, quantiteParStep);
		}
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		List<Double> precedentesPropositions = contrat.getListePrix();
		Double dernierPrix = contrat.getPrix();
		if () {
			return -1;
		} elif(){
			return dernierPrix;
		} else {
			return ;
		}
	}
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		
	}

}
