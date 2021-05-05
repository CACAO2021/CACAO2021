package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.Variable;

public class Producteur2Param extends Producteur2Banque {
	
	//ensemble fait par DIM
	
	public Producteur2Param() {
		super();
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(stockFHBE);
		res.add(stockFHE);
		res.add(stockFME);
		res.add(stockFM);
		res.add(stockFB);
		res.add(stockPHE);
		res.add(stockPM);		
		return res;
	}
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

}
