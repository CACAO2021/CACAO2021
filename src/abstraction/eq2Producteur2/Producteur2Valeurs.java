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
	
	protected static double QTT_POUDRE_HE_DEPART = 0;
	protected static double QTT_POUDRE_M_DEPART = 0;
	
	
	// partie vente des fèves par kilo en euros
	
	protected static double PRIX_ESPERE_FEVE_B = 2.6;
	protected static double PRIX_ESPERE_FEVE_M = 3.6;
	protected static double PRIX_ESPERE_FEVE_ME = PRIX_ESPERE_FEVE_M*1.2;
	protected static double PRIX_ESPERE_FEVE_HE = 5;
	protected static double PRIX_ESPERE_FEVE_HBE = PRIX_ESPERE_FEVE_HE*1.2;



	protected static double PRIX_MIN_ACCEPTEE_FEVE_B = 1.6;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_M = 2.6;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_ME = PRIX_MIN_ACCEPTEE_FEVE_M*1.2;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_HE = 4;
	protected static double PRIX_MIN_ACCEPTEE_FEVE_HBE = PRIX_MIN_ACCEPTEE_FEVE_HE*1.2;


	
	
	protected static double DIF_ACCEPTEE_FEVE_HBE = 0.1;
	protected static double DIF_ACCEPTEE_FEVE_HE = 0.1;
	protected static double DIF_ACCEPTEE_FEVE_ME = 0.1;
	protected static double DIF_ACCEPTEE_FEVE_M = 0.1;
	protected static double DIF_ACCEPTEE_FEVE_B = 0.1;
	
	
	// partie production
	protected int ARBRE_DEBUT_HBE = (int)QTT_FEVE_HBE_DEPART/6;
	protected int ARBRE_DEBUT_HE = (int)QTT_FEVE_HE_DEPART/6;
	protected int ARBRE_DEBUT_ME = (int)QTT_FEVE_ME_DEPART/6;
	protected int ARBRE_DEBUT_M = (int)QTT_FEVE_M_DEPART/6;
	protected int ARBRE_DEBUT_B = (int)QTT_FEVE_B_DEPART/6; //triche j'ai pris le nombre d'arbres qu'on doit avoir pour avoir la production de départ

	
	protected int ARBRE_TPS_VIE_HBE = 40*48;
	protected int ARBRE_TPS_VIE_HE = 40*48;
	protected int ARBRE_TPS_VIE_ME=40*48;
	protected int ARBRE_TPS_VIE_M=40*48;
	protected int ARBRE_TPS_VIE_B=40*48;
	
	//production par tour par arbre 
	protected int PROD_HBE = 6;
	protected int PROD_HE = 6;
	protected int PROD_ME = 6;
	protected int PROD_M = 6;
	protected int PROD_B = 6;
	
	
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
