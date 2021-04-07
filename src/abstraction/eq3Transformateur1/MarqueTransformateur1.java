package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.IMarqueChocolat;

public class MarqueTransformateur1 implements IMarqueChocolat {
	
	private List<String> Marques;
	
	public MarqueTransformateur1(){
		this.Marques = new ArrayList<String>() ;
		this.Marques.add("Milieu de gamme Ethicao");
		this.Marques.add("Haut de gamme Ethicao");
		
	}

	@Override
	public List<String> getMarquesChocolat() {
		// TODO Auto-generated method stub
		return this.Marques;
	}

}
