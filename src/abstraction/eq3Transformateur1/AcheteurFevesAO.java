package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Journal;

public class AcheteurFevesAO extends Transformateur1Acteur implements IAcheteurFevesAO {
	
	
	private Feve feve;
	private double prixMax;
	private double qMin, qMax;

	private static double QUANTITE_MIN_FEVE = 100.0;
	private static double QUANTITE_MAX_FEVE = STOCK_MAX*0.3;

	private static ArrayList<Feve> list = new ArrayList<Feve>();



	public AcheteurFevesAO(Feve feve, double prixMax, double qMin, double qMax) {
		super();
		this.feve = feve;
		this.prixMax = prixMax;
		this.qMin = qMin;
		this.qMax = qMax;
	}
	
	
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		if (superviseur!=null) { // Personne ne peut creer un second Superviseur --> il s'agit bien de l'unique superviseur et on peut lui faire confiance
			return cryptogramme;
		}
		return Integer.valueOf(0);
	}
	
	// Renvoie le stock actuel de ce type de feve
	private double getStockActuelFeve(Feve feve) {
		return this.stocksFeves.get(feve);
	}
	
	// Renvoie l'information si notre stock actuel est compris entre notre intervalle de stock souhaité
	private boolean notEnought(Feve f) {
		return (getStockActuelFeve(f) <= QUANTITE_MAX_FEVE);
		
	}
	

	public OffreAchatFeves getOffreAchat() {
		OffreAchatFeves result = new OffreAchatFeves(this, feve, qMin+(Math.random()*(qMax-qMin)));
		if (Math.random()<0.5) { // une chance sur 2
			this.journal.ajouter("Offre d'achat = "+result);
			return result;
		} else {
			this.journal.ajouter("pas d'offre d'achat");
			return null;
		}
	}

	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.journal.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}

	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		if (propositions.size()>0) {
			int hasard = (int)(Math.random()*propositions.size());
			if (propositions.get(hasard).getPrixKG()<this.prixMax) {
				return propositions.get(hasard);
			}
		}
		return null;
	}

	public void notifierVente(PropositionVenteFevesAO proposition) {
		stocksFeves.put(feve, stocksFeves.get(feve)+proposition.getQuantiteKg());
		this.journal.ajouter("--> le stock de feve passe a "+Journal.doubleSur(this.stocksFeves.get(proposition.getFeve()), 4));
	}

	//	public double proposerAchat(LotCacaoCriee lot) {
	//		double prix = this.prixCourant.get(lot.getFeve())*0.95; // on tente de payer un peu moins
	//		double solde = Filiere.LA_FILIERE.getBanque().getSolde(this, cryptogramme);
	//		if (solde>lot.getQuantiteEnTonnes()*prix) {
	//			this.journal.ajouter("Propose "+Journal.doubleSur(prix,  4)+" pour "+Journal.texteColore(lot.getVendeur(), Journal.doubleSur(lot.getQuantiteEnTonnes(), 2)+" tonnes de "+lot.getFeve().name()));
	//			return prix;
	//		} else {
	//			this.journal.ajouter("Ne souhaite pas acheter un lot de "+Journal.texteColore(lot.getVendeur(), Journal.doubleSur(lot.getQuantiteEnTonnes(), 2)+" tonnes de "+lot.getFeve().name()));
	//			return 0.0;
	//		}
	//	}
	//
	//	public void notifierPropositionRefusee(PropositionCriee proposition) {
	//		double nouveauPrix = Math.max(0.01,  this.prixCourant.get(proposition.getLot().getFeve())*1.02);
	//		this.prixCourant.put(proposition.getLot().getFeve(), nouveauPrix);
	//		this.journal.ajouter("Apprend que sa proposition de "+Journal.doubleSur(proposition.getPrixPourUneTonne(), 4)+" pour "+Journal.texteColore(proposition.getVendeur(), Journal.doubleSur(proposition.getQuantiteEnTonnes(), 2)+" tonnes de "+proposition.getFeve().name())+Journal.texteColore(Color.red, Color.white, " a ete refusee"));
	//		this.journal.ajouter("--> augmente le prix de 2% --> "+Journal.doubleSur(nouveauPrix, 4));
	//	}
	//
	//	public void notifierVente(PropositionCriee proposition) {
	//		this.stocksFeves.put(proposition.getFeve(), this.stocksFeves.get(proposition.getFeve())+proposition.getQuantiteEnTonnes());
	//		this.journal.ajouter("Apprend que sa proposition de "+Journal.doubleSur(proposition.getPrixPourUneTonne(), 4)+" pour "+Journal.texteColore(proposition.getVendeur(), Journal.doubleSur(proposition.getQuantiteEnTonnes(), 2)+" tonnes de "+proposition.getFeve().name())+Journal.texteColore(Color.green, Color.black," a ete acceptee"));
	//		this.journal.ajouter("--> le stock de feve passe a "+Journal.doubleSur(this.stocksFeves.get(proposition.getFeve()), 4));
	//	}
}

