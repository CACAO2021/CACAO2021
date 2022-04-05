package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.Variable;

public abstract class Producteur2Param extends Producteur2Banque {
	
	//ensemble fait par DIM
	
	public Producteur2Param() {
		super();
	}

	public List<Variable> getIndicateurs() {
		// la maniere dont on gère nos stocks ne permet pas de modifier leurs valeurs depuis l'interface une fois le code lancé
		List<Variable> res=new ArrayList<Variable>();
		res.add(stockFHBE);
		res.add(stockFHE);
		res.add(stockFME);
		res.add(stockFM);
		res.add(stockFB);
		
		res.add(qttArbreTotalFHBE);
		res.add(qttArbreTotalFHE);
		res.add(qttArbreTotalFME);
		res.add(qttArbreTotalFM);
		res.add(qttArbreTotalFB);
		
		
		//res.add(stockPHE);
		//res.add(stockPM);		
		return res;
	}
	
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

}