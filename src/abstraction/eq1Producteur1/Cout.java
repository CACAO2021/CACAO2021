package abstraction.eq1Producteur1;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

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
	
/**
 * 
 * @param arthurlemgit
 */
	public void payerStockage(Producteur1Acteur a) {
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
