package abstraction.eq1Producteur1;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IFabricantChocolatDeMarque;

/**
 * 
 * @author lebra
 *la classe entière
 */

public class Transformation {
	Producteur1Acteur nous = (Producteur1Acteur)Filiere.LA_FILIERE.getActeur("EQ1");
	
	public Transformation() {
	}
	
	public void Transformation_Feve_Quantite (Feve f,double quantite,Producteur1Acteur producteur1Acteur) {
		if (f.equals(Feve.FEVE_MOYENNE)) {
			producteur1Acteur.getStocks().get(f).removeQuantite(quantite);
			producteur1Acteur.getStocks().get(Chocolat.POUDRE_MOYENNE).addQuantite(quantite);
			producteur1Acteur.perteargent(0.5*quantite);
			nous.getJournal("Ghanao Coûts").ajouter("La transformation de feves moyenne a couté "+Producteur1Valeurs.PRIX_TRANSFORMATION*quantite+"€");
			producteur1Acteur.getJournal("Ghanao Transformation").ajouter(quantite + " kg de fèves de moyenne qualité ont été transformés en " + quantite + "kg de poudre de moyenne qualité ");
			}
		if (f.equals(Feve.FEVE_MOYENNE_EQUITABLE)) {
			producteur1Acteur.getStocks().get(f).removeQuantite(quantite);
			producteur1Acteur.getStocks().get(Chocolat.POUDRE_MOYENNE_EQUITABLE).addQuantite(quantite);
			producteur1Acteur.perteargent(0.5*quantite);
			nous.getJournal("Ghanao Coûts").ajouter("La transformation de feves moyenne équitables a couté "+0.5*quantite+"€");
			producteur1Acteur.getJournal("Ghanao Transformation").ajouter(quantite + " kg de fèves de moyenne qualité équitable ont été transformés en " + quantite + "kg de poudre de moyenne qualité équitable ");
		}
		
	}
	
	/**
	 * on impose toujours la proportion de 80-20 dans les quantités de poudre
	 */
	
	public void Transformation_Feve(Producteur1Acteur producteur1Acteur) {
		double q_f_m = producteur1Acteur.getStocks().get(Feve.FEVE_MOYENNE).getQuantite();
		double q_f_m_e = producteur1Acteur.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).getQuantite();
		double q_p_m_e = 0.1*q_f_m;
		double m_q_p_m = Math.min(q_p_m_e*4,q_f_m_e);
		if (m_q_p_m == q_f_m_e) {
			q_p_m_e = m_q_p_m/4;
		}
		this.Transformation_Feve_Quantite(Feve.FEVE_MOYENNE_EQUITABLE,q_p_m_e, producteur1Acteur);
		this.Transformation_Feve_Quantite(Feve.FEVE_MOYENNE, m_q_p_m, producteur1Acteur);
	}
	
	

}
