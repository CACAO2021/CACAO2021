package abstraction.eq1Producteur1;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;

/**
 * 
 * @author ppoir
 *
 */

public class Cout extends JournauxEq1 {
	private static double COUT_PRODUCTION_FEVE_B = Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_B;
	private static double COUT_PRODUCTION_FEVE_M =Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_M;
	private static double COUT_PRODUCTION_FEVE_ME =Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_ME;
	
	public static void PayerProducteurs(Producteur1Acteur producteur1Acteur){
		double valeur=(COUT_PRODUCTION_FEVE_B*60000000)+(COUT_PRODUCTION_FEVE_M*67500000)+(COUT_PRODUCTION_FEVE_ME*22500000);
		producteur1Acteur.perteargent(valeur);
		producteur1Acteur.getJournal(0).ajouter("Les producteurs ont été payés d'un montant de "+valeur+"€");

	}

	public static void cout(Producteur1Acteur producteur1Acteur){
		Cout.PayerProducteurs(producteur1Acteur);
	}
}
