package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Variable;

public class Catalogue {
	
	public Catalogue() {
		ArrayList<Chocolat> chocolatPropose = new ArrayList<Chocolat>();
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
}
