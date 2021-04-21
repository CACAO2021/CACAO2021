package abstraction.eq1Producteur1;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

/**
 * 
 * @author ppoir
 *
 */

public class Cout extends CreationJournaux {
	private static double COUT_PRODUCTION_FEVE_B = Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_B;
	private static double COUT_PRODUCTION_FEVE_M =Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_M;
	private static double COUT_PRODUCTION_FEVE_ME =Producteur2et1ValeursEnCommun.COUT_PRODUCTION_FEVE_ME;
	private static double COUT_STOCKAGE_FEVE = 0.02;
	
	public static void PayerProducteurs(Producteur1Acteur producteur1Acteur){
		double valeur=(COUT_PRODUCTION_FEVE_B*48333000)+(COUT_PRODUCTION_FEVE_M*48333000)+(COUT_PRODUCTION_FEVE_ME*1000000);
		producteur1Acteur.perteargent(valeur);
		producteur1Acteur.getJournal(4).ajouter("Les producteurs ont été payés d'un montant de "+valeur+"€");

	}


	public static void cout(Producteur1Acteur a){
		Cout.PayerProducteurs(a);
		//Cout.payerStockage(a);
	}
	
/**
 * 
 * @param arthurlemgit
 */
	public static void payerStockage(Producteur1Acteur a) {
		double qte_stockee = a.getStocks().get(Feve.FEVE_BASSE).getQuantite()
				+ a.getStocks().get(Feve.FEVE_MOYENNE).getQuantite()
				+ a.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).getQuantite()
				+a.getStocks().get(Chocolat.POUDRE_MOYENNE).getQuantite()
				+a.getStocks().get(Chocolat.POUDRE_MOYENNE_EQUITABLE).getQuantite();
		double montant = qte_stockee*COUT_STOCKAGE_FEVE;
		a.perteargent(montant);
		a.getJournal(4).ajouter("Le stockage des produits a coûté ce stet" +montant+"€" );

	}
}
