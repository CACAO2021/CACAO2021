package abstraction.eq5Transformateur3;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;

//Léna 
public abstract class Transformateur3MarqueChocolat extends AcheteurFevesAO implements IMarqueChocolat {
	
	public List<String> getMarquesChocolat() {
		List<String> touteslesmarques = new LinkedList<String>();
		touteslesmarques.add("Côte d'IMT");
		return touteslesmarques;
		}
	
}