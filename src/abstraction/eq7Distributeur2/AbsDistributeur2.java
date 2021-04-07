package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.ArrayList;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public abstract class AbsDistributeur2 {
	
	// Liste des Chocolats que nous proposons aux clients finaux
	public ArrayList<Chocolat> chocolatPropose;

	public AbsDistributeur2() {
		chocolatPropose = new ArrayList<Chocolat>();
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
	
	// Couleurs d'arrière-plan pour les messages des journaux
		public Color titleColor = Color.BLACK;
		public Color alertColor = Color.RED;
		public Color descriptionColor = Color.YELLOW;
		public Color positiveColor = Color.GREEN;
		public Color warningColor = Color.ORANGE;
		public Color behaviorColor = Color.BLUE;
	
		

		
	

}
