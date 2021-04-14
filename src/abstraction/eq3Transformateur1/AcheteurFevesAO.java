package abstraction.eq3Transformateur1;


import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class AcheteurFevesAO extends AcheteurFevesContratCadre implements IAcheteurFevesAO {
	
	
	protected Feve feve;
	protected double prixMax;
	protected double qMin, qMax;
	

	private static double QUANTITE_MAX_FEVE = STOCK_MAX;


	public AcheteurFevesAO(Feve feve, double prixMax, double qMin, double qMax) {
		super();
		this.feve = feve;
		this.prixMax = prixMax;
		this.qMin = qMin;
		this.qMax = qMax;
	}
		
	/**
	 * @return the feve
	 */
	public Feve getFeve() {
		return feve;
	}



	/**
	 * @return the prixMax
	 */
	public double getPrixMax() {
		return prixMax;
	}



	/**
	 * @return the qMin
	 */
	public double getqMin() {
		return qMin;
	}



	/**
	 * @return the qMax
	 */
	public double getqMax() {
		return qMax;
	}



	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		if (superviseur!=null) { // Personne ne peut creer un second Superviseur --> il s'agit bien de l'unique superviseur et on peut lui faire confiance
			return cryptogramme;
		}
		return Integer.valueOf(0);
	}
	
	// Renvoie le stock actuel de ce type de feve
	private double getStockActuelFeve(Feve feve) {
		return this.getStock().getStockFeves(feve);
	}
	
	public OffreAchatFeves getOffreAchat() {
		double quantite = (Double) null ;
		OffreAchatFeves result = new OffreAchatFeves(this, feve, quantite );
		 
		if () { // une chance sur 2
			this.journalAcheteur.ajouter("Offre d'achat = "+result);
			return result;
		} else {
			this.journalAcheteur.ajouter("pas d'offre d'achat");
			return null;
		}
	}
	

	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.journalAcheteur.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}

	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		double p = 0;
		double prixmin = propositions.get(0).getPrixKG();
		if (propositions.size()>0) {
			for (int i=0; col<propositions.size(); i++) {
				if (propositions.get(i).getPrixKG()<prixmin) {
					p=i;
					prixmin=propositions.get(i).getPrixKG();
				}
			}
			if (prixmin<this.prixMax) {
				return propositions.get(p);
			}
		}
		return null;
	}

	public void notifierVente(PropositionVenteFevesAO proposition) {
		Variable quantite = new Variable(this.getNom(),this,proposition.getQuantiteKg());
		Variable prix = new Variable(this.getNom(),this,proposition.getPrixKG());
		this.getStock().setStockFeve(feve, quantite, prix);
		this.journalAcheteur.ajouter("--> le stock de " + proposition.getFeve().toString() + "passe a "+Journal.doubleSur(this.getStock().getStockFeves(proposition.getFeve()), 4));
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

