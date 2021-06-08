package abstraction.eq2Producteur2;

public class Producteur2et1ValeursEnCommun {
	
	
	// les valeurs partagées avec l'autre équipe, participation de tous
		// responsable emeline
		
		//cout prod
		public static double COUT_PRODUCTION_FEVE_B = 1.1;
		public static double COUT_PRODUCTION_FEVE_M = 1.3;
		public static double COUT_PRODUCTION_FEVE_ME = 2.;
		public static double COUT_PRODUCTION_FEVE_HE = 2.5;
		public static double COUT_PRODUCTION_FEVE_HBE = 3.;
		// on est donc equitable car on paye bien nos producteurs!
		
		//cout changement arbre
		// correpsond au cout d'un arbre plante ou enleve
		// le même quel que soit le type d'arbre 
		// la différence de cout apparaitra plus tard dans la vie de larbre
		// avec le cout de production
		public static final double COUT_CHANGEMENT_ARBRE = 3.;
		
		// production par an arbre
		// quel que soit le type darbre
		public static final int PROD_ARBRE_AN = 6;
				
		//production par tour par arbre 
		// 12 * 2 = 24 steps
		public static final int PROD_ARBRE = PROD_ARBRE_AN/24;
		
		// partie transformation
		// finalement utile uniquement a l'équipe 1
		// l'équipe 2 ayant finalement choisi de ne plus transformer
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
		public static final double COUT_STOCKAGE_FIXE = 1000/4; // cout en euro par step   //prix de l'équipe boni succi (prix très bas) divisé par 4
		public static double COUT_STOCKAGE_FEVE_DEPASSEMENT = COUT_STOCKAGE_FEVE * 3; // on paye 3 fois plus si on depasse la capacite max de stockage
		
		
		//cout renouvellment arbre
		//Cacaotier durée de vie 40 ans, bonne culture à partir de 3 ans et rendement maximal à partir de 6 ans. 
		// on utilise dans le code une focntion pour déterminer le rendement de chaque arbre pour un step donné
		public static final int TPS_RENOUVELLEMENT_ARBRE=40*24;
		public static final int TPS_BON_RENDEMENT_ARBRE=3*24;
		public static final int TPS_RENDEMENT_MAX_ARBRE=6*24;
		
		//partie vente
		// pour accepter un contrat qui se veut equitable
		// il y a deux conditions mini : 
		protected double EQUI_NB_ECHEANCE_MINI = 8;
		protected double EQUI_QTT_MINI = 1000000; 
		
		
	}