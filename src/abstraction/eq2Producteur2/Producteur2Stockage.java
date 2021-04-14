package abstraction.eq2Producteur2;

import java.util.LinkedList;

import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

public abstract class Producteur2Stockage extends Producteur2Journaux {
	private LinkedList<Stock> stockFeveHBE;
	private LinkedList<Stock> stockFeveHE; 
	private LinkedList<Stock> stockFeveME;
	private LinkedList<Stock> stockFeveM;
	private LinkedList<Stock> stockFeveB; 
	private LinkedList<Stock> stockPoudreHE;
	private LinkedList<Stock> stockPoudreM; 
	
	protected Variable stockFHBE;
	protected Variable stockFHE;
	protected Variable stockFME;
	protected Variable stockFM;
	protected Variable stockFB;
	protected Variable stockPHE;
	protected Variable stockPM;

	
	//Dim
	/**
	 * @param stockFeve
	 * @param stockPoudre
	 */
	public Producteur2Stockage() {
		super();
		// a modifier en prenant en compte le fait que le stock de depart n'est pas entierement creer au step 0 mais aussi a des steps anterieurs (pour plus tard)
		this.stockFeveHBE = new LinkedList<Stock>();
		this.stockFeveHBE.add(new Stock(QTT_FEVE_HBE_DEPART, 0));
		this.stockFeveHE = new LinkedList<Stock>();
		this.stockFeveHE.add(new Stock(QTT_FEVE_HE_DEPART, 0));
		this.stockFeveME = new LinkedList<Stock>();
		this.stockFeveME.add(new Stock(QTT_FEVE_ME_DEPART, 0));
		this.stockFeveM = new LinkedList<Stock>();
		this.stockFeveM.add(new Stock(QTT_FEVE_M_DEPART, 0));
		this.stockFeveB = new LinkedList<Stock>();
		this.stockFeveB.add(new Stock(QTT_FEVE_B_DEPART, 0));
		
		this.stockPoudreHE = new LinkedList<Stock>();
		this.stockPoudreHE.add(new Stock(QTT_POUDRE_HE_DEPART, 0));
		this.stockPoudreM = new LinkedList<Stock>();
		this.stockPoudreM.add(new Stock(QTT_POUDRE_M_DEPART, 0));
		
		stockFHBE = new Variable("stock feve HBE", this, QTT_FEVE_HBE_DEPART);
		stockFHE = new Variable("stock feve HE", this, QTT_FEVE_HE_DEPART);
		stockFME = new Variable("stock feve ME", this, QTT_FEVE_ME_DEPART);
		stockFM = new Variable("stock feve M", this, QTT_FEVE_M_DEPART);
		stockFB = new Variable("stock feve B", this, QTT_FEVE_B_DEPART);
		
		stockPHE = new Variable("stock poudre HE", this, QTT_POUDRE_HE_DEPART);
		stockPM = new Variable("stock poudre B", this, QTT_POUDRE_M_DEPART);
	}
	
	//Dim
	/*
	 * retourne la quantité de stock disponible totale d'un produit 
	 */
	public Variable qttTotale(Object produit) {
		if (estFeveHBE(produit)) {			
			return stockFHBE;
		} else if (estFeveHE(produit)) {			
			return stockFHE;
		}else if (estFeveME(produit)) {			
			return stockFME;
		}else if (estFeveM(produit)) {			
			return stockFM;
		}else if (estFeveB(produit)) {			
			return stockFB;
		}else if (estPoudreHE(produit)) {
			return stockPHE;
		}else if (estPoudreM(produit)) {
			return stockPM;
		} else {
			return new Variable("?", this, 0);
		}
	}
	
	public void majStock(Object produit) {
		double stock = 0;
		if (estFeveHBE(produit)) {			
			for (Stock s : this.stockFeveHBE) {
				stock += s.getQtt();
				stockFHBE.setValeur(this, stock);
			}
		}else if (estFeveHE(produit)) {			
			for (Stock s : this.stockFeveHE) {
				stock += s.getQtt();
			}
		}else if (estFeveME(produit)) {			
			for (Stock s : this.stockFeveME) {
				stock += s.getQtt();
			}
		}else if (estFeveM(produit)) {			
			for (Stock s : this.stockFeveM) {
				stock += s.getQtt();
			}
		}else if (estFeveB(produit)) {			
			for (Stock s : this.stockFeveB) {
				stock += s.getQtt();
			}
		}else if (estPoudreHE(produit)) {
			for (Stock s : this.stockPoudreHE) {
				stock += s.getQtt();
			}
		}else if (estPoudreM(produit)) {
			for (Stock s : this.stockPoudreM) {
				stock += s.getQtt();
			}
		}
	}
	
	//Dim
	public void addStock(double qtt, Object produit) {
		int step = Filiere.LA_FILIERE.getEtape();
		Stock s = new Stock(qtt, step);
		if (estFeveHBE(produit)) {
			this.stockFeveHBE.add(s);
		} else if (estFeveHE(produit)) {
			this.stockFeveHE.add(s);
		}else if (estFeveME(produit)) {
			this.stockFeveME.add(s);
		}else if (estFeveM(produit)) {
			this.stockFeveM.add(s);
		}else if (estFeveB(produit)) {
			this.stockFeveB.add(s);
		}else if (estPoudreHE(produit)) {
			this.stockPoudreHE.add(s);
		}else if (estPoudreM(produit)) {
			this.stockPoudreM.add(s);
		}else {
			System.out.println("erreur");
		}
		
	}
	
	public void verifPeremption() {
		LinkedList<Stock> feveHBE = stockFeveHBE;
		return;
	}

	@Override
	public abstract void perdreArgent(double montant) ;

}
