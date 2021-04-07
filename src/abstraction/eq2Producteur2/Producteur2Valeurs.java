package abstraction.eq2Producteur2;


import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

public class Producteur2Valeurs {
	public Producteur2Valeurs() {super();}
	
	
	// partie stockage
	
	protected static double QTT_FEVE_TOTALE = 7.2*Math.pow(10,9)/48;
	
	protected static double QTT_FEVE_HBE_DEPART = QTT_FEVE_TOTALE*0.03;
	protected static double QTT_FEVE_HE_DEPART = QTT_FEVE_TOTALE*0.01;
	protected static double QTT_FEVE_ME_DEPART = QTT_FEVE_TOTALE*0.01;
	protected static double QTT_FEVE_M_DEPART = QTT_FEVE_TOTALE*0.4833333;
	protected static double QTT_FEVE_B_DEPART = QTT_FEVE_TOTALE*0.483333;
	
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
	protected static double DIF_ACCEPTEE_FEVE_ME = 1.0;
	protected static double DIF_ACCEPTEE_FEVE_M = 1.0;
	protected static double DIF_ACCEPTEE_FEVE_B = 1.0;
	// a finir
	
	
	// partie production
	protected int ARBRE_DEBUT_HBE = 5;
	protected int ARBRE_DEBUT_HE = 0;
	protected int ARBRE_DEBUT_ME = 0;
	protected int ARBRE_DEBUT_M = 0;
	protected int ARBRE_DEBUT_B = 0;
	//a finir
	
	protected int ARBRE_TPS_VIE_HBE = 0;
	protected int ARBRE_TPS_VIE_HE = 0;
	protected int ARBRE_TPS_VIE_ME=0;
	protected int ARBRE_TPS_VIE_M=0;
	protected int ARBRE_TPS_VIE_B=0;
	
	protected int PROD_HBE = 0;
	protected int PROD_HE = 0;
	protected int PROD_ME = 0;
	protected int PROD_M = 0;
	protected int PROD_B = 0;
	
	
	// verification du type de produit
	
	public static boolean estFeveHBE(Object produit) {return produit.equals(Feve.FEVE_HAUTE_BIO_EQUITABLE);}
	public static boolean estFeveHE(Object produit) {return produit.equals(Feve.FEVE_HAUTE_EQUITABLE);}
	public static boolean estFeveME(Object produit) {return produit.equals(Feve.FEVE_MOYENNE_EQUITABLE);}
	public static boolean estFeveM(Object produit) {return produit.equals(Feve.FEVE_MOYENNE);}
	public static boolean estFeveB(Object produit) {return produit.equals(Feve.FEVE_BASSE);}
	
	public static boolean estFeve(Object produit) {return produit instanceof Feve;}
	
	public static boolean estPoudreHE(Object produit) {return produit.equals(Chocolat.POUDRE_HAUTE_EQUITABLE);}
	public static boolean estPoudreM(Object produit) {return produit.equals(Chocolat.POUDRE_MOYENNE);}
	
	
	public static boolean estPoudre(Object produit) { return produit instanceof Chocolat && produit.equals(Categorie.POUDRE);}
	

}
