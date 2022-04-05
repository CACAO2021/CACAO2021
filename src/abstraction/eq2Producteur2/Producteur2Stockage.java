package abstraction.eq2Producteur2;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

public abstract class Producteur2Stockage extends Producteur2Journaux {
	// les liste de feves par type de feve et step de prod
	private LinkedList<Stock> stockFeveHBE;
	private LinkedList<Stock> stockFeveHE; 
	private LinkedList<Stock> stockFeveME;
	private LinkedList<Stock> stockFeveM;
	private LinkedList<Stock> stockFeveB; 
	private LinkedList<Stock> stockPoudreHE;
	private LinkedList<Stock> stockPoudreM;

	// hashmap des listes de fève
	protected HashMap<Feve, LinkedList<Stock>> stock_F;

	// les qtt totales de chaqye type de feve
	protected Variable stockFHBE;
	protected Variable stockFHE;
	protected Variable stockFME;
	protected Variable stockFM;
	protected Variable stockFB;


	// ensemble fait par DIM

	//Dim
	public Producteur2Stockage() {
		super();
		// a modifier en prenant en compte le fait que le stock de depart n'est pas entierement
		// creer au step 0 mais aussi a des steps anterieurs 
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


		stockFHBE = new Variable("stock feve HBE", this, 0);
		stockFHE = new Variable("stock feve HE", this, 0);
		stockFME = new Variable("stock feve ME", this, 0);
		stockFM = new Variable("stock feve M", this, 0);
		stockFB = new Variable("stock feve B", this, 0);


		// création hashmap
		stock_F = new HashMap<Feve, LinkedList<Stock>>();
		stock_F.put(listeProd.get(0), stockFeveHBE);
		stock_F.put(listeProd.get(1), stockFeveHE);
		stock_F.put(listeProd.get(2), stockFeveME);
		stock_F.put(listeProd.get(3), stockFeveM);
		stock_F.put(listeProd.get(4), stockFeveB);

		for (Feve f : listeProd) {
			majStock(f);
		}	
	}


	//Dim
	/*
	 * retourne la quantité de stock disponible totale d'un produit 
	 */
	public Variable qttTotale(Object produit) {
		majStock(produit);
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
		} else {
			return new Variable("?", this, 0);
		}
	}

	public void majStock(Object produit) {
		double stock = 0;
		if (estFeveHBE(produit)) {			
			for (Stock s : this.stockFeveHBE) {
				stock += s.getQtt();			
			}stockFHBE.setValeur(this, stock);
		}else if (estFeveHE(produit)) {			
			for (Stock s : this.stockFeveHE) {
				stock += s.getQtt();				
			}stockFHE.setValeur(this, stock);
		}else if (estFeveME(produit)) {			
			for (Stock s : this.stockFeveME) {
				stock += s.getQtt();				
			}stockFME.setValeur(this, stock);
		}else if (estFeveM(produit)) {			
			for (Stock s : this.stockFeveM) {
				stock += s.getQtt();				
			}stockFM.setValeur(this, stock);
		}else if (estFeveB(produit)) {			
			for (Stock s : this.stockFeveB) {
				stock += s.getQtt();
			}stockFB.setValeur(this, stock);
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

		majStock(produit);

	}

	//Dim

	public void vente(double qtt, Object produit) {
		// vente fonctionnel
		// tentative de simplification / factorisation du code
		majStock(produit);
		if (qttTotale(produit).getValeur() < qtt) { // ne se déclanche jamais
			System.out.println("on a un pb");
		}
		double q = 0;
		for ( Stock e : (stock_F.get(produit)) ) {
			q = e.getQtt() - qtt;
			if (q>=0) {
				e.setQtt(q);
				return;
			}else {
				//System.out.println("else");
				//System.out.println(qtt);
				e.setQtt(0);
				qtt = Math.floor(Math.abs(q));
				//System.out.println(qtt);
				majStock(produit);
				//System.out.println(qttTotale(produit).getValeur());
			}

			LinkedList<Stock> stockrem = new LinkedList<Stock>(); 
			for ( Stock ee : (stock_F.get(produit)) ) {
				if (ee.getQtt()==0) {
					stockrem.add(ee);	
				}
			}
			stock_F.get(produit).removeAll(stockrem);
		}
		majStock(produit);
	}

	public void verifPeremption() {
		if(Producteur2VeudeurFeveCC.bolVUS) {
			// sans cette fonction le stock augmente indéfiniement si pas d'achat
			// supprime les stock trop vieux
			if (Filiere.LA_FILIERE.getEtape()-this.stockFeveHBE.get(0).getStep()>nbEtapeAvPeremption) {
				LinkedList<Stock> stockFeveHBE2 = new LinkedList<Stock>(this.stockFeveHBE); 
				for (Stock st:this.stockFeveHBE) {
					if (Filiere.LA_FILIERE.getEtape()-st.getStep()>nbEtapeAvPeremption) {
						stockFeveHBE2.remove(st);
					}
				}this.stockFeveHBE = stockFeveHBE2;
			}
			if (Filiere.LA_FILIERE.getEtape()-this.stockFeveHE.get(0).getStep()>nbEtapeAvPeremption) {
				LinkedList<Stock> stockFeveHE2 = new LinkedList<Stock>(this.stockFeveHE);
				for (Stock st:this.stockFeveHE) {
					if (Filiere.LA_FILIERE.getEtape()-st.getStep()>nbEtapeAvPeremption) {
						stockFeveHE2.remove(st);
					}
				}this.stockFeveHE = stockFeveHE2;
			}
			if (Filiere.LA_FILIERE.getEtape()-this.stockFeveME.get(0).getStep()>nbEtapeAvPeremption) {
				LinkedList<Stock> stockFeveME2 = new LinkedList<Stock>(this.stockFeveME);
				for (Stock st:this.stockFeveME) {
					if (Filiere.LA_FILIERE.getEtape()-st.getStep()>nbEtapeAvPeremption) {
						stockFeveME2.remove(st);
					}
				}this.stockFeveME = stockFeveME2;
			}
			if (Filiere.LA_FILIERE.getEtape()-this.stockFeveM.get(0).getStep()>nbEtapeAvPeremption) {
				LinkedList<Stock> stockFeveM2 = new LinkedList<Stock>(this.stockFeveM);
				for (Stock st:this.stockFeveM) {
					if (Filiere.LA_FILIERE.getEtape()-st.getStep()>nbEtapeAvPeremption) {
						stockFeveM2.remove(st);
					}
				}this.stockFeveM = stockFeveM2;
			}
			if (Filiere.LA_FILIERE.getEtape()-this.stockFeveB.get(0).getStep()>nbEtapeAvPeremption) {
				LinkedList<Stock> stockFeveB2 = new LinkedList<Stock>(this.stockFeveB);
				for (Stock st:this.stockFeveB) {
					if (Filiere.LA_FILIERE.getEtape()-st.getStep()>nbEtapeAvPeremption) {
						stockFeveB2.remove(st);
					}
				}this.stockFeveB = stockFeveB2;
			}

		}
	}




}