package abstraction.eq5Transformateur3;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.IMarqueChocolat;

public class Transformateur3MarqueChocolat implements IMarqueChocolat {
	private String marque;

	public Transformateur3MarqueChocolat() {
		this.marque = "Côte d'IMT";
	}
	
	public String getMarque() {
		return this.marque;
	}
	
	public List<String> getMarquesChocolat() {
		List<String> marques = new ArrayList<String>();
		marques.add(getMarque());
		return marques;
	}

}
