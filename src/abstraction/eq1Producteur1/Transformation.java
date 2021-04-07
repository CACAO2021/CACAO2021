package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;

/**
 * 
 * @author lebra
 *la classe entière
 */

public class Transformation extends Producteur1Acteur {
	
	public Transformation() {
		
	}
	
	public void Transformation_Feve_Quantite (Feve f,double quantite) {
		
		if (f == Feve.FEVE_MOYENNE) {
			stocks.get(f).removeQuantite(quantite);
			stocks.get(Chocolat.POUDRE_MOYENNE).addQuantite(quantite);
			this.perteargent(0.5*quantite);
		}
		if (f == Feve.FEVE_MOYENNE_EQUITABLE) {
			stocks.get(f).removeQuantite(quantite);
			stocks.get(Chocolat.POUDRE_MOYENNE_EQUITABLE).addQuantite(quantite);
			this.perteargent(0.5*quantite);
		}
		
	}
	
	/**
	 * on impose toujours la proportion de 80-20 dans les quantités de poudre
	 */
	
	public void Transformation_Feve() {
		double q_f_m = stocks.get(Feve.FEVE_MOYENNE).getQuantite();
		double q_f_m_e = stocks.get(Feve.FEVE_MOYENNE_EQUITABLE).getQuantite();
		double q_p_m_e = 0.1*q_f_m;
		double m_q_p_m = Math.min(q_p_m_e*4,q_f_m_e);
		if (m_q_p_m == q_f_m_e) {
			q_p_m_e = m_q_p_m/4;
		}
		this.Transformation_Feve_Quantite(Feve.FEVE_MOYENNE_EQUITABLE,q_p_m_e);
		this.Transformation_Feve_Quantite(Feve.FEVE_MOYENNE, m_q_p_m);
	}

}
