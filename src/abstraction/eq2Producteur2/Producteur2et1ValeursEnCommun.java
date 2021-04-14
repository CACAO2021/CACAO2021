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
	
	protected int qteParStepHE = 15000; //quantité que l'on peut transformer en un step (100kg/h, 10h/jour)
	protected int qteParStepM = 15000;
	protected int qteParStepME = 15000;
	
	protected double coutMachines = 21; // coût de transformation des fèves en poudre, un seul coût: entretien des machines

	
	// cout stockage
	protected double COUT_STOCKAGE_FEVE = 0.02; // cout en euro par kg par step
}
