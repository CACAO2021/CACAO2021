package abstraction.eq8Romu.fevesAO;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Journal;

public class PropositionVenteFevesAO {
	private OffreAchatFeves oaf;
	private IVendeurFevesAO vendeur;
	private double prixKG;

	public PropositionVenteFevesAO(OffreAchatFeves qf, IVendeurFevesAO vendeur, double prixKG) {
		this.oaf = qf;
		this.vendeur = vendeur;
		this.prixKG = prixKG;
	}
	
	public OffreAchatFeves getOffreAchateFeves() {
		return this.oaf;
	}

	public IVendeurFevesAO getVendeur() {
		return this.vendeur;
	}
	
	public IAcheteurFevesAO getAcheteur() {
		return this.getOffreAchateFeves().getAcheteur();
	}
	
	public double getPrixKG() {
		return this.prixKG;
	}
	
	public double getMontant() {
		return this.getPrixKG()*this.getOffreAchateFeves().getQuantiteKG();
	}
	
	public double getQuantiteKg() {
		return this.getOffreAchateFeves().getQuantiteKG();
	}
	
	public Feve getFeve() {
		return this.getOffreAchateFeves().getFeve();
	}
	
	public String toString() {
		return "("+this.getOffreAchateFeves()+","+this.getVendeur().getNom()+","+Journal.doubleSur(this.getPrixKG(),3)+")";
	}
}
