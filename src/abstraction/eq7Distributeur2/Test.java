package abstraction.eq7Distributeur2;

import java.util.ArrayList;
import java.util.HashMap;

import abstraction.eq4Transformateur2.Transformateur2;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, HashMap<String, Variable>> hashmap1 = new HashMap<Integer, HashMap<String, Variable> >();
		HashMap<String,Variable> test = new HashMap<String,Variable>();
		IActeur act = new Transformateur2();
		test.put("TEST1", new Variable("test", act));
		hashmap1.put(0, test);
		
		System.out.println(hashmap1.toString());
		System.out.println(hashmap1.get(0).get("TEST1").getValeur());
		hashmap1.get(0).get("TEST1").ajouter(act, 3);
		System.out.println(hashmap1.get(0).get("TEST1").getValeur());
		//Chocolat.CONFISERIE_BASSE, 13
		
		

	}

}
