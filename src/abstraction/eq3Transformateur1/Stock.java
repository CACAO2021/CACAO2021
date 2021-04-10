package abstraction.eq3Transformateur1;

import java.util.HashMap;
import java.util.Map;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

import java.util.List;
import java.util.ArrayList;


// Paul GIRAUD

public class Stock extends Transformateur1Acteur {
	
	private Transformateur1Acteur eticao;
	private List<Variable> indicateurs;
	protected Map<Feve, ArrayList<ArrayList<Variable>>> stockFeves;
	protected Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolats; 
	protected Map<Feve,Double> coutFeves;
	protected Map<Chocolat,Double> coutChocolat;
	private Variable PrixTransforatmionFeve;
	private Variable PrixStockage;
	private double PRIX_STOCKAGE = 2000;
	
	public Stock() { 
		super();
		
		this.stockChocolats = new HashMap<Chocolat, ArrayList<ArrayList<Variable>>>();
		this.stockFeves = new HashMap<Feve, ArrayList<ArrayList<Variable>>>();
		
		this.stockFeves.put(Feve.FEVE_BASSE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockFeves.put(Feve.FEVE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.TABLETTE_BASSE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.CONFISERIE_BASSE, new ArrayList<ArrayList<Variable>>());
		
		this.stockChocolats.put(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_HAUTE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_MOYENNE_EQUITABLE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_MOYENNE, new ArrayList<ArrayList<Variable>>());
		this.stockChocolats.put(Chocolat.POUDRE_BASSE, new ArrayList<ArrayList<Variable>>());

		this.PrixTransforatmionFeve = new Variable(this.getNom() + " Cout transformation feve à chocolat", this, 5000);

		this.PrixStockage = new Variable(this.getNom() + " Coutdu stockage", this, PRIX_STOCKAGE);
		
		this.indicateurs = new ArrayList<Variable>();
	}
	
	public ArrayList<Feve> nosFeves() {
		ArrayList<Feve> list = new ArrayList<Feve>();
		list.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE);
		list.add(Feve.FEVE_BASSE);
		return list;
	}
	
	public ArrayList<Chocolat> nosChocolats() {
		ArrayList<Chocolat> list = new ArrayList<Chocolat>();
		list.add(Chocolat.CONFISERIE_BASSE);
		list.add(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.CONFISERIE_HAUTE_EQUITABLE);
		list.add(Chocolat.CONFISERIE_MOYENNE);
		list.add(Chocolat.CONFISERIE_MOYENNE_EQUITABLE);
		list.add(Chocolat.POUDRE_BASSE);
		list.add(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.POUDRE_HAUTE_EQUITABLE);
		list.add(Chocolat.POUDRE_MOYENNE);
		list.add(Chocolat.POUDRE_MOYENNE_EQUITABLE);
		list.add(Chocolat.TABLETTE_BASSE);
		list.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
		list.add(Chocolat.TABLETTE_HAUTE_EQUITABLE);
		list.add(Chocolat.TABLETTE_MOYENNE);
		list.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		return list;
	}
	
	public double getStockFeves(Feve feve) {
		
		double total = 0;
		Map<Feve, ArrayList<ArrayList<Variable>>> stockFevesT = this.stockFeves;
		ArrayList<ArrayList<Variable>> stockFeves = stockFevesT.get(feve);
		for(ArrayList<Variable> QuantitePrix : stockFeves) {
			total += QuantitePrix.get(0).getValeur();
		}
		return total;	
		
	}
	
	public double getStockFeves() {
		
		double total = 0;
		ArrayList<Feve> ListFeve = this.nosFeves();
		for(Feve feve : ListFeve) {
			total += this.getStockFeves(feve);
		}
		return total;
	}
	
	public double getStockChocolats(Chocolat chocolat) {
		
		double total = 0;
		Map<Chocolat, ArrayList<ArrayList<Variable>>> stockChocolatsT = this.stockChocolats;
		ArrayList<ArrayList<Variable>> stockChocolats = stockChocolatsT.get(chocolat);
		for(ArrayList<Variable> QuantitePrix : stockChocolats) {
			total += QuantitePrix.get(0).getValeur();
		}
		return total;	
		
	}
	
	public double getStockChocolats() {
		
		double total = 0;
		ArrayList<Chocolat> ListChocolat = this.nosChocolats();
		for(Chocolat chocolat : ListChocolat) {
			total += this.getStockChocolats(chocolat);
		}
		return total;
	}
	
	public void setStockFeve(Feve feve, Variable quantite, Variable prix ) {
		ArrayList<Variable> QuantitePrix = new ArrayList<>();
		QuantitePrix.add(quantite);
		QuantitePrix.add(prix);
		this.stockFeves.get(feve).add(QuantitePrix);
	}
	
	public void setStockChocolat(Chocolat chocolat, Variable quantite, Variable prix ) {
		ArrayList<Variable> QuantitePrix = new ArrayList<>();
		QuantitePrix.add(quantite);
		QuantitePrix.add(prix);
		this.stockChocolats.get(chocolat).add(QuantitePrix);
	}
	
	public void transformationFeveChocolat(Feve feve, Variable quantite, Chocolat chocolat) {


	}
	
	
	
	
	
}
