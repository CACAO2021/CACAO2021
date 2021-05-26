/**
 * 
 */
package abstraction.eq1Producteur1;

import abstraction.eq2Producteur2.Producteur2et1ValeursEnCommun;

/**
 * @author Alb1x
 *
 */
public class Producteur1Valeurs extends Producteur2et1ValeursEnCommun{
	public static double PRIX_TRANSFORMATION = 0.5;
	public static double PRIX_PALIER_F_M_E = 1.990; // prix minimal défini par Max Havelaar pour garantir que la fève est équitable
	public static double PRIX_PALIER_F_M = 1.05; // prix minimal pour les fèves de moyenne qualité
	public static double PRIX_PALIER_F_B = 0.91; // prix minimal pour les fèves de basse qualité
	public static double PRIX_PALIER_P_M_E = 2.5; // prix minimal pour la poudre de moyenne qualité équitable
	public static double PRIX_PALIER_P_M = 1.5; // prix minimal pour la poudre de moyenne qualité
}
