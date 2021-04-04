package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2Valeurs {
	// partie stockage
	protected static double QTT_FEVE_DEPART = 0;
	protected static double QTT_POUDRE_DEPART = 0;
	
	// partie vente des fèves
	protected static double PRIX_ESPERE_FEVE = 3.0;
	protected static double DIFF_ACCEPTEE = 1.0;
	protected static double PRIX_MIN_ACCEPTEE = 1.0;
	
	// verification du type de produit
	/*
	 * vérifie si produit est feve
	 */
	public static boolean estFeve(Object produit) {
		return produit instanceof Feve;
	}
	
	/*
	 * vérifie si le produit est de la poudre
	 */
	public static boolean estPoudre(Object produit) {
		return produit.equals(Categorie.POUDRE);
	}
	


}
