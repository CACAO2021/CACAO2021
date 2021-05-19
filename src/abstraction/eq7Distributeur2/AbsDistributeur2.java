package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;



//----------------------//
//fait par Elio Granger //
//----------------------//

public abstract class AbsDistributeur2 {
	
	// Liste des Chocolats que nous proposons aux clients finaux
	public ArrayList<Chocolat> chocolatPropose;
	public List<ChocolatDeMarque> catalogue;
	public HashMap<Chocolat, Double> marges;

	public AbsDistributeur2() {
		chocolatPropose = new ArrayList<Chocolat>();
		marges = new HashMap<Chocolat, Double>();
		chocolatPropose.add(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE);
		chocolatPropose.add(Chocolat.CONFISERIE_HAUTE_EQUITABLE);
		chocolatPropose.add(Chocolat.CONFISERIE_MOYENNE_EQUITABLE);
		chocolatPropose.add(Chocolat.CONFISERIE_MOYENNE);
		chocolatPropose.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
		chocolatPropose.add(Chocolat.TABLETTE_HAUTE_EQUITABLE);
		chocolatPropose.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		chocolatPropose.add(Chocolat.TABLETTE_MOYENNE);
		chocolatPropose.add(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE);
		chocolatPropose.add(Chocolat.POUDRE_HAUTE_EQUITABLE);
		chocolatPropose.add(Chocolat.POUDRE_MOYENNE_EQUITABLE);
		chocolatPropose.add(Chocolat.POUDRE_MOYENNE);
	}
	
	/**
	 * @return the chocolatPropose
	 */
	public ArrayList<Chocolat> getChocolatsProposes() {
		return chocolatPropose;
	}

		// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color alertColor = Color.RED;
		public Color descriptionColor = Color.YELLOW;
		public Color positiveColor = Color.GREEN;
		public Color warningColor = Color.ORANGE;
		public Color behaviorColor = Color.BLUE;
	
		
		
	// Nous cherchons les marques de chocolat dans la filière qui correspondent aux types de chocolat que nous proposons
	// pour créer notre catalogue
		
		public void initialiserCatalogue() {
			for(Chocolat nosChocolats : this.chocolatPropose) {
				for (ChocolatDeMarque chocolatDeLaFiliere : Filiere.LA_FILIERE.getChocolatsProduits()) {
					if(chocolatDeLaFiliere.getChocolat().name().equals(nosChocolats.name())) {
						catalogue.add(chocolatDeLaFiliere);
					}
				}
				
			}
			
		}
		
		
	// Nous créons une liste de marge pour chaque type de chocolat proposés dans l'odre de la liste "chocolatPropose" plus haut
		
		public void initialiserMarges() {
			ArrayList<Double> valeurs = new ArrayList<>(Arrays. asList( 0.6, 0.5, 0.3, 0.2,0.6, 0.5, 0.3, 0.2, 0.6, 0.5, 0.3, 0.2));
			for(Chocolat noschocos : this.chocolatPropose) {
				this.marges.put(noschocos,valeurs.get(0));
				valeurs.remove(0);
			}
		}
		
		
		
		
		

		
	

}
