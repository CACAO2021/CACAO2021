package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.IMarqueChocolat;


// Jonathan
public class Transformateur1Marque extends Transformateur1FabricantChocolatDeMarque implements IMarqueChocolat {
	
	public Transformateur1Marque() {
		super();
	}
	
	public List<String> getMarquesChocolat() {
		List<String> marques = new LinkedList<String>();
		marques.add(this.getMarque());
		return marques;
		}
}


