/**
 * 
 */
package abstraction.eq1Producteur1;

import java.util.HashMap;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

/**
 * @author Alb1x
 *
 */
public class Producteur1Valeurs extends Producteur2et1ValeursEnCommun{
	public static double PRIX_TRANSFORMATION = 0.5;
	private static double PRIX_PALIER_F_M_E = 1.990; // prix minimal défini par Max Havelaar pour garantir que la fève est équitable
	private static double PRIX_PALIER_F_M = 1.05; // prix minimal pour les fèves de moyenne qualité
	private static double PRIX_PALIER_F_B = 0.91; // prix minimal pour les fèves de basse qualité
	private static double PRIX_PALIER_P_M_E = 2.5; // prix minimal pour la poudre de moyenne qualité équitable
	private static double PRIX_PALIER_P_M = 1.5; // prix minimal pour la poudre de moyenne qualité
	public static HashMap<Object, Double> prix_palier = new HashMap<Object,Double>(); 
	
	public static void main(String[] args) {
		prix_palier.put(Feve.FEVE_MOYENNE_EQUITABLE, PRIX_PALIER_F_M_E);
		prix_palier.put(Feve.FEVE_MOYENNE, PRIX_PALIER_F_M);
		prix_palier.put(Feve.FEVE_BASSE, PRIX_PALIER_F_B);
		prix_palier.put(Chocolat.POUDRE_MOYENNE_EQUITABLE, PRIX_PALIER_P_M_E);
		prix_palier.put(Chocolat.POUDRE_MOYENNE, PRIX_PALIER_P_M);
	}
}
