package abstraction.eq7Distributeur2;

import java.util.ArrayList;
import java.util.HashMap;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<Chocolat, Double > hashmap1 = new HashMap<Chocolat, Double >();
				
		
		hashmap1.put(Chocolat.CONFISERIE_BASSE,  13.0);
		System.out.println(hashmap1.entrySet());
		hashmap1.put(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, 40.0);
		System.out.println(hashmap1.entrySet());
		System.out.println(hashmap1.get(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE));
		//Chocolat.CONFISERIE_BASSE, 13
		
		

	}

}
