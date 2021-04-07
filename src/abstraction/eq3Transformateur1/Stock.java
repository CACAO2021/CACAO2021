package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.Map;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;

public class Stock extends Transformateur1Acteur {
	
	private Transformateur1Acteur eticao;
	private List<Variable> indicateurs;
	protected Map<Feve, Double> stocksFeves;
	private double stockFeveHauteBio;
	private double stockFeveHauteEquitable;
	private double stockFeveMoyenneEquitable;
	
	private static double stockInitialFeve = 100.0 ;
	private static double stockInitialChocolat= 100.0;
	private static double QUANTITE_MIN_FEVE = 100.0;
	private static double QUANTITE_MAX_FEVE = STOCK_MAX*0.3;
	
	public Stock() { 
		super();
		this.stockFeveHauteBio = this.stocksFeves.get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		this.stockFeveHauteEquitable = this.stocksFeves.get(Feve.FEVE_HAUTE_EQUITABLE);
		this.stockFeveMoyenneEquitable = this.stocksFeves.get(Feve.FEVE_MOYENNE_EQUITABLE);
	}
	
	public void setStock() {
		
		
	}
	
}
