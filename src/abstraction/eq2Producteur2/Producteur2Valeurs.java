package abstraction.eq2Producteur2;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2Valeurs {
	public Producteur2Valeurs() {super();}
	
	
	// partie stockage

	protected static double QTT_FEVE_HBE_DEPART = 0;
	protected static double QTT_FEVE_HE_DEPART = 0;
	protected static double QTT_FEVE_ME_DEPART = 0;
	protected static double QTT_FEVE_M_DEPART = 0;
	protected static double QTT_FEVE_B_DEPART = 0;
	
	protected static double QTT_POUDRE_DEPART = 0;
	
	
	// partie vente des fèves
	
	protected static double PRIX_ESPERE_FEVE_HBE = 3.0;
	protected static double PRIX_ESPERE_FEVE_HE = 3.0;
	protected static double PRIX_ESPERE_FEVE_ME = 3.0;
	protected static double PRIX_ESPERE_FEVE_M = 3.0;
	protected static double PRIX_ESPERE_FEVE_B = 3.0;
	
	protected static double PRIX_MIN_ACCEPTEE_FEVE_HBE = 1.0;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_HE = 3.0;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_ME = 3.0;
	protected static double PRIX_MIN_ACCEPTEEE_FEVE_M = 3.0;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_B = 3.0;
	
	protected static double DIF_ACCEPTEE_FEVE_HBE = 1.0;
	protected static double DIF_ACCEPTEE_FEVE_HE = 1.0;
	// a finir
	
	
	// partie production
	protected int ARBRE_DEBUT_HBE = 5;
	protected int ARBRE_DEBUT_HE = 0;
	//a finir
	
	protected int ARBRE_TPS_VIE_HBE = 0;
	protected int ARBRE_TPS_VIE_HE = 0;
	
	protected int PROD_HBE = 0;
	protected int PROD_HE = 0;
	
	
	// verification du type de produit
	
	public static boolean estFeveHBE(Object produit) {return produit.equals(Feve.FEVE_HAUTE_BIO_EQUITABLE);}
	public static boolean estFeveHE(Object produit) {return produit.equals(Feve.FEVE_HAUTE_EQUITABLE);}
	public static boolean estFeveME(Object produit) {return produit.equals(Feve.FEVE_MOYENNE_EQUITABLE);}
	public static boolean estFeveM(Object produit) {return produit.equals(Feve.FEVE_MOYENNE);}
	public static boolean estFeveB(Object produit) {return produit.equals(Feve.FEVE_BASSE);}
	
	public static boolean estFeve(Object produit) {return estFeveHBE(produit) && estFeveHE(produit)
			&& estFeveME(produit) && estFeveM(produit) && estFeveB(produit);}
	
	public static boolean estPoudre(Object produit) { return produit.equals(Categorie.POUDRE);}
	

}
