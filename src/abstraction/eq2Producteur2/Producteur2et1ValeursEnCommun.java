package abstraction.eq2Producteur2;

public class Producteur2et1ValeursEnCommun {
	
	
	// les valeurs partagées avec l'autre équipe
	public static double COUT_PRODUCTION_FEVE_B = 1.1;
	public static double COUT_PRODUCTION_FEVE_M = 1.8;
	public static double COUT_PRODUCTION_FEVE_ME = 2;
	public static double COUT_PRODUCTION_FEVE_HE = 2.5;
	public static double COUT_PRODUCTION_FEVE_HBE = 3;
	
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
	
	// partie transformation
	protected double coefHE = 0.4; //(à affiner)
	protected double coefM = 0.4;
	protected double coefME = 0.4;
	
	protected int qteParStepHE = 1; //quantité que l'on peut transformer en un step
	protected int qteParStepM = 1;
	protected int qteParStepME = 1;
	
	protected double prixHEparKilo = 1; // coût de transformation d'un kilo de fèves en poudre
	protected double prixMparKilo = 1;
	protected double prixMEparKilo = 1;

}
