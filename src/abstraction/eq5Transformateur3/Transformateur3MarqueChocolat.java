package abstraction.eq5Transformateur3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;

public class Transformateur3MarqueChocolat implements IMarqueChocolat {

	private List<ChocolatDeMarque> produits;

	public Transformateur3MarqueChocolat() {
		this.produits = new LinkedList<ChocolatDeMarque>(); }

	public List<String> getMarquesChocolat() {
		List<String> marques = new LinkedList<String>();
		for (int i=0; i<this.produits.size(); i++) {
			marques.add(this.produits.get(i).getMarque()); }
		return marques;
			
		}
	
}