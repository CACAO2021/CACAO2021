package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.Map;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;

public class Stock extends Transformateur1Acteur {
	
	private Transformateur1Acteur eticao;
	private List<Variable> indicteurs;
	protected Map<Feve, Double> stocksFeves;
	private double stockFeveBio;
	
	public Stock() { 
		super();
		this.stockFeveBio = this.stocksFeves.get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
	}
	
}
