package abstraction.eq2Producteur2;

public class Producteur2et1ValeursEnCommun {
	
	
	// les valeurs partagées avec l'autre équipe, participation de tous
	// responsable emeline
	
	//cout prod
	public static final double COUT_PRODUCTION_FEVE_B = 1.1;
	public static final double COUT_PRODUCTION_FEVE_M = 1.3;
	public static final double COUT_PRODUCTION_FEVE_ME = 2.;
	public static final double COUT_PRODUCTION_FEVE_HE = 2.5;
	public static final double COUT_PRODUCTION_FEVE_HBE = 3.;
	
	//cout changement arbre
	public static final double COUT_CHANGEMENT_ARBRE_HBE = 0.1; // pour ne pas mettre 0€
	public static final double COUT_CHANGEMENT_ARBRE_HE = 0.1;
	public static final double COUT_CHANGEMENT_ARBRE_ME = 0.1;
	public static final double COUT_CHANGEMENT_ARBRE_M = 0.1;
	public static final double COUT_CHANGEMENT_ARBRE_B = 0.1;
	
	// production par an arbre
	public static final int PROD_ARBRE_AN = 6;
			
	//production par tour par arbre 
	public static final int PROD_ARBRE = PROD_ARBRE_AN/24;
	
	//si on decide de donner des valeurs différentes:
	public static final double PROD_HBE = 6./24.;
	public static final double PROD_HE = 6./24.;
	public static final double PROD_ME = 6./24.; 
	public static final double PROD_M = 6./24.;
	public static final double PROD_B = 6./24.;
	
	// partie transformation
	public static final double coefHE = 0.4; //(à affiner)
	public static final double coefM = 0.4;
	public static final double coefME = 0.4;
	
	public static final int qteParStepHE = 15000; //quantité que l'on peut transformer en un step (100kg/h, 10h/jour)
	public static final int qteParStepM = 15000;
	public static final int qteParStepME = 15000;
	
	public static final double coutMachines = 21; // coût de transformation des fèves en poudre, un seul coût: entretien des machines

	public static final int nbEtapeAvPeremption=14;
	
	// cout stockage
	public static final double COUT_STOCKAGE_FEVE = 0.02; // cout en euro par kg par step
	public static final double COUT_STOCKAGE_FIXE = 0.0; // cout en euro par step
	
	
	//cout renouvellment arbre
	//Cacaotier durée de vie 40 ans, bonne culture à partir de 3 ans et rendement maximal à partir de 6 ans. 
	public static final int TPS_RENOUVELLEMENT_ARBRE=40*24;
	public static final int TPS_BON_RENDEMENT_ARBRE=3*24;
	public static final int TPS_RENDEMENT_MAX_ARBRE=6*24;
	

	
	
}
