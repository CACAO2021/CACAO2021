package abstraction.eq4Transformateur2;

import abstraction.fourni.Journal;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Chocolat;


public class Transformateur2Valeurs {
	
	public Transformateur2Valeurs() {
	}

	protected int cryptogramme;
	protected Journal journal;
	
	//Stockage
	
	protected HashMap<Feve,Double> stock_feve;
	protected HashMap<Chocolat,Double> stock_chocolat;
	
	//Production
	
	protected static double charges_fixes;
	
	
	
}
