package abstraction.eq4Transformateur2;


import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Antoine C
public class Transformateur2AchatCC extends Transformateur2Vente implements IAcheteurContratCadre{

	private SuperviseurVentesContratCadre supCCadre;
	public Transformateur2AchatCC() {
		super();
	}
	
	@Override
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		if (contrat.getEcheancier().getNbEcheances() > 24 || contrat.getEcheancier().getQuantiteTotale() < 1000) {
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
		List<Feve> feves = new LinkedList<Feve>();
		feves.add(Feve.FEVE_BASSE);
		feves.add(Feve.FEVE_MOYENNE);
		for(Feve feve : feves ) {
			LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) this.supCCadre.getVendeurs(feve);
			if (vendeurs.size()!=0){
				int i = (int) (Math.random()*vendeurs.size());
				IVendeurContratCadre vendeur = vendeurs.get(i);
				List<Double> besoin = new LinkedList<Double>();
				Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 24, mini_stock_bas);
				supCCadre.demande((IAcheteurContratCadre)this, vendeur, feve, echeancier, this.cryptogramme, false);
			}
		}
	}
}
