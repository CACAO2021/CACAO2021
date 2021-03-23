package abstraction.eq8Romu.fevesAO;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Journal;

public class OffreAchatFeves {
	public static final double AO_FEVES_QUANTITE_MIN = 250.0;
	
	private IAcheteurFevesAO acheteur;
	private Feve feve;
	private double quantiteKG;
	
	/**
	 * Constructeur initialisant l'offre d'achat avec les informations fournies en parametres 
	 * (leve une IllegalArgumentException si les conditions sur les parametres ne sont
	 * pas respectees). 
	 * @param feve!=null
	 * @param quantite>=AO_FEVES_QUANTITE_MIN
	 */
	public OffreAchatFeves(IAcheteurFevesAO acheteur, Feve feve, Double quantite) {
		if (acheteur==null) {
			throw new IllegalArgumentException("Appel du constructeur de OffreAchatFeves avec null pour acheteur");
		}
		if (feve==null) {
			throw new IllegalArgumentException("Appel du constructeur de OffreAchatFeves avec null pour type de feves");
		}
		if (quantite<0) {
			throw new IllegalArgumentException("Appel du constructeur de OffreAchatFeves avec une quantite de "+quantite+" (min="+AO_FEVES_QUANTITE_MIN+")");
		}
		this.acheteur = acheteur;
		this.feve = feve;
		this.quantiteKG = quantite;
	}
	
	public IAcheteurFevesAO getAcheteur() {
		return this.acheteur;
	}
	
	public Feve getFeve() {
		return this.feve;
	}
	
	public double getQuantiteKG() {
		return this.quantiteKG;
	}
	
	public boolean equals(Object o) {
		return (o instanceof OffreAchatFeves) 
				&& this.getFeve().equals(((OffreAchatFeves)o).getFeve())
				&& this.getQuantiteKG()==((OffreAchatFeves)o).getQuantiteKG();
	}
	
	public String toString() {
		return "("+Journal.doubleSur(this.getQuantiteKG(), 3)+" de "+this.getFeve()+")";
	}

}
