package abstraction.eq5Transformateur3;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;

//Léna 
public class Transformateur3MarqueChocolat implements IMarqueChocolat {

	private List<ChocolatDeMarque> chocolats;

	public Transformateur3MarqueChocolat() {
		this.chocolats = new LinkedList<ChocolatDeMarque>(); }

	public List<String> getMarquesChocolat() {
		List<String> marques = new LinkedList<String>();
		for (int i=0; i<this.chocolats.size(); i++) {
			marques.add(this.chocolats.get(i).getMarque()); }
		return marques;
			
		}
	
}