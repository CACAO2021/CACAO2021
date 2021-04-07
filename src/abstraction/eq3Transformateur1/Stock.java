package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.Map;

import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;

public class Stock extends Transformateur1Acteur {
	
	private Transformateur1Acteur eticao;
	private List<Variable> indicateurs;
	protected Map<Feve, Double> stocksFeves;
	protected Map<Chocolat, Double> stocksChocolats;
	private double stockFeveHauteBE;
	private double stockFeveHauteE;
	private double stockFeveMoyenneE;
	private double stockFeveMoyenne;
	private double stockConfiserieHBE ;
	private double stockConfiserieMoyenne;
	private double stockConfiserieMoyenneE;
	private double stockConfiserieHauteE;
	private double stockPoudreHauteBE;
	private double stockPoudreHauteE;
	private double stockPoudreMoyenne;
	private double stockPoudreMoyenneE;
	private double stockTabletteHauteBE;
	private double stockTabletteHauteE;
	private double stockTabletteMoyenne;
	private double stockTabletteMoyenneE;
	
	private static double stockInitialFeve = 100.0 ;
	private static double stockInitialChocolat= 100.0;
	private static double QUANTITE_MIN_FEVE = 100.0;
	private static double QUANTITE_MAX_FEVE = STOCK_MAX*0.3;
	
	public Stock() { 
		super();
		this.stockFeveHauteBE = this.stocksFeves.get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		this.stockFeveHauteE = this.stocksFeves.get(Feve.FEVE_HAUTE_EQUITABLE);
		this.stockFeveMoyenneE = this.stocksFeves.get(Feve.FEVE_MOYENNE_EQUITABLE);
		this.stockFeveMoyenne = this.stocksFeves.get(Feve.FEVE_MOYENNE);
		this.stockConfiserieHBE = this.stocksChocolats.get(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE);
		this.stockConfiserieMoyenne = this.stocksChocolats.get(Chocolat.CONFISERIE_MOYENNE);
		this.stockConfiserieMoyenneE = this.stocksChocolats.get(Chocolat.CONFISERIE_MOYENNE_EQUITABLE);
		this.stockConfiserieHauteE = this.stocksChocolats.get(Chocolat.CONFISERIE_HAUTE_EQUITABLE);
		this.stockPoudreHauteBE = this.stocksChocolats.get(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE);
		this.stockPoudreHauteE = this.stocksChocolats.get(Chocolat.POUDRE_HAUTE_EQUITABLE);
		this.stockPoudreMoyenne = this.stocksChocolats.get(Chocolat.POUDRE_MOYENNE);
		this.stockPoudreMoyenneE = this.stocksChocolats.get(Chocolat.POUDRE_MOYENNE_EQUITABLE);
		this.stockTabletteHauteBE = this.stocksChocolats.get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
		this.stockTabletteHauteE = this.stocksChocolats.get(Chocolat.TABLETTE_HAUTE_EQUITABLE);
		this.stockTabletteMoyenne = this.stocksChocolats.get(Chocolat.TABLETTE_MOYENNE);
		this.stockTabletteMoyenneE = this.stocksChocolats.get(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		this.stocksFeves.put(Feve.FEVE_HAUTE_BIO_EQUITABLE , stockInitialFeve);
	}
	
	public void setStock(PropositionVenteFevesAO proposition) {
		for (Feve feve : stocksFeves.keySet()) {
			stocksFeves.put(feve, stocksFeves.get(feve)+proposition.getQuantiteKg());
		}
		
		
	}
	
	
}
