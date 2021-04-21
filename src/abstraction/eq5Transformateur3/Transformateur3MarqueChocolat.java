package abstraction.eq5Transformateur3;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;

//Léna 
public abstract class Transformateur3MarqueChocolat extends Transformateur3Fabricant implements IMarqueChocolat {
	
	public List<String> getMarquesChocolat() {
<<<<<<< HEAD
		List<String> touteslesmarques = new LinkedList<String>();
		touteslesmarques.add("Côte d'IMT");
		return touteslesmarques;
=======
		List<String> marques = new LinkedList<String>();
		marques.add(super.getMarque());
		return marques;
>>>>>>> branch 'master' of https://github.com/CACAO2021/CACAO2021
		}
	
}