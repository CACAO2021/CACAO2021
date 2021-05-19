package abstraction.eq3Transformateur1;


import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

// Chloe
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



	/**
	 * @param qMax the qMax to set
	 */
	public void setqMax(double qMax) {
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
		return this.getStock().getStockFeves(feve);
	}
	
	public OffreAchatFeves getOffreAchat() {
		double quantite = 0 ;
		double prixestime = 0 ;
		OffreAchatFeves result = null;
		if (qMin< this.getStock().stockRestant()) {
			quantite = 0;
			this.journalAcheteur.ajouter("pas d'offre d'achat");
			return result; 
		}
		if (qMax<this.getStock().stockRestant()) {
			quantite = qMax ;
			result = new OffreAchatFeves(this, feve, quantite );
			this.journalAcheteur.ajouter("Offre d'achat = ");
			return result;
		} else {
			quantite = this.dichothomie();
			result = new OffreAchatFeves(this, feve, quantite );
			this.journalAcheteur.ajouter("Offre d'achat = ");
			return result;
		}
	} 
	
	public double dichothomie(){
		double m = (this.getqMin()+ this.getqMax())/2;
		if (m>this.getStock().stockRestant()) {
			this.setqMax(m);
			return this.dichothomie();
		} else {
			return m;
		}
	}

	

	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.journalAcheteur.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}

	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		int p = 0;
		double prixmin = propositions.get(0).getPrixKG();
		if (propositions.size()>0) {
			for (int i=0; i<propositions.size(); i++) {
				if (propositions.get(i).getPrixKG()<prixmin) {
					p=i;
					prixmin=propositions.get(i).getPrixKG();
				}
			}
			if (prixmin<this.prixMax) { 
				if (this.getSolde()>prixmin) {
					return propositions.get(p);
				}
			}else {
				return null ;
			}
		} else {
		return null ;
		}
		return null;
	}

	public void notifierVente(PropositionVenteFevesAO proposition) {
		Variable quantite = new Variable(this.getNom(),this,proposition.getQuantiteKg());
		Variable prix = new Variable(this.getNom(),this,proposition.getPrixKG());
		this.getStock().setStockFeve(feve, quantite, prix);
		this.journalAcheteur.ajouter("--> le stock de " + proposition.getFeve().toString() + "passe a "+Journal.doubleSur(this.getStock().getStockFeves(proposition.getFeve()), 4));
	}
}

