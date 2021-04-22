package abstraction.eq2Producteur2;

public class Producteur2et1ValeursEnCommun {
	
	
	// les valeurs partagées avec l'autre équipe, participation de tous
	// responsable emeline
	
	//cout prod
	public static double COUT_PRODUCTION_FEVE_B = 1.1/1000.;
	public static double COUT_PRODUCTION_FEVE_M = 1.3/1000.;
	public static double COUT_PRODUCTION_FEVE_ME = 2/1000.;
	public static double COUT_PRODUCTION_FEVE_HE = 2.5/1000.;
	public static double COUT_PRODUCTION_FEVE_HBE = 3/1000.;
	
	//cout changement arbre
	public double COUT_CHANGEMENT_ARBRE_HBE = 0;
	public double COUT_CHANGEMENT_ARBRE_HE = 0;
	public double COUT_CHANGEMENT_ARBRE_ME = 0;
	public double COUT_CHANGEMENT_ARBRE_M = 0;
	public double COUT_CHANGEMENT_ARBRE_B = 0;
	
	//production par tour par arbre 
	public int PROD_ARBRE = 6;
	
	//si on decide de donner des valeurs différentes:
	public int PROD_HBE = 6;
	public int PROD_HE = 6;
	public int PROD_ME = 6; 
	public int PROD_M = 6;
	public int PROD_B = 6;
	
	// partie transformation
	public double coefHE = 0.4; //(à affiner)
	public double coefM = 0.4;
	public double coefME = 0.4;
	
	public int qteParStepHE = 15000; //quantité que l'on peut transformer en un step (100kg/h, 10h/jour)
	public int qteParStepM = 15000;
	public int qteParStepME = 15000;
	
	public double coutMachines = 21; // coût de transformation des fèves en poudre, un seul coût: entretien des machines

	public int nbEtapeAvPeremption=14;
	// cout stockage
	public double COUT_STOCKAGE_FEVE = 0.02; // cout en euro par kg par step
	
	
	//cout renouvellment arbre
	//Cacaotier durée de vie 40 ans, bonne culture à partir de 5 ans et rendement maximal à partir de 7 ans. 
	public int TPS_RENOUVELLEMENT_ARBRE=40*24;
	public int TPS_BON_RENDEMENT_ARBRE=3*24;
	public int TPS_RENDEMENT_MAX_ARBRE=6*24;
	

	
	
}
