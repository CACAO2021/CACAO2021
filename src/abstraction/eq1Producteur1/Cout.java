package abstraction.eq1Producteur1;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;

/**
 * 
 * @author ppoir
 *
 */

public class Cout extends Producteur2et1ValeursEnCommun {
	
	public static void PayerProducteurs(Producteur1Acteur a){
		double valeur=(COUT_PRODUCTION_FEVE_B*60000000)+(COUT_PRODUCTION_FEVE_M*67500000)+(COUT_PRODUCTION_FEVE_ME*22500000);
		a.perteargent(valeur);
		a.getJournal(0).ajouter("Les producteurs ont été payés d'un montant de "+valeur+"€");

	}

	public static void cout(Producteur1Acteur a){
		Cout.PayerProducteurs(a);
	}
}
