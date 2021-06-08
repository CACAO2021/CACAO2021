package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur1Acteur{


	protected Map<ChocolatDeMarque, Variable> stock; // tout les stocks, y compris le contenu de stockTG
	protected Map<ChocolatDeMarque, Variable> prix;
	protected Map<ChocolatDeMarque,Variable> stockTG;



	//Louis
	public Stocks() {
		super();
		this.stock=new HashMap<ChocolatDeMarque, Variable>(); 
		this.prix=new HashMap<ChocolatDeMarque, Variable>();
		this.stockTG=new HashMap<ChocolatDeMarque, Variable>(); 
		

	}


	//Louis
	public void ajouterStock(ChocolatDeMarque produit, double quantite, boolean tg) {
		if (tg) {
			stockTG.put(produit, new Variable((produit).toString(), this, Math.max(stockTG.get(produit).getValeur()+quantite,0)));
		}
		stock.put(produit, new Variable((produit).toString(), this, stock.get(produit).getValeur()+quantite));

	}



	//Louis
	public void setPrix(ChocolatDeMarque choco, double prix) {
		this.prix.put(choco, new Variable("prix"+ choco.toString(), this, prix));
	}



	//Louis
	public void initialiser() {
		super.initialiser();
		initCatalogue();
		initPrix();

		for (ChocolatDeMarque choco : stock.keySet()) { 
			this.indicateurs.add(stock.get(choco));
		}
		for (ChocolatDeMarque choco : prix.keySet()) {
			this.indicateurs.add(prix.get(choco));
			}
		
	}



	//Louis
	public void next() {
		super.next();
		for (ChocolatDeMarque choco : stock.keySet()) { 
			for (Variable indic : this.getIndicateurs()) {
				if (indic.equals(stock.get(choco))) {
					indic.setValeur(this, stock.get(choco).getValeur());
				}
				if (indic.getNom().equals("prix " + choco)) {
					indic.setValeur(this,prix.get(choco).getValeur());
				}
			}
		}
	}

	
	//Louis
	public void initCatalogue() {
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			if (!choco.getMarque().equals("Wonka & Sons"))
			stock.put(choco, new Variable(choco.toString(),this,1000000.));
			stockTG.put(choco, new Variable(choco.toString(),this,0.));

		}
	}



	//Louis
	public void initPrix() {
		for(ChocolatDeMarque choco : stock.keySet()) {
			if(choco.getCategorie()==Categorie.TABLETTE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 5.0));
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 4.5));
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 4.0));
				}
				if(choco.isEquitable()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.2));
				}
				if(choco.isBio()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.3));
				}

			}
			if(choco.getCategorie()==Categorie.POUDRE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					if(choco.getMarque().toString()=="Eticao") {
						prix.put(choco, new Variable("prix "+choco.toString(),this, 5.5));
					}
					else {
						prix.put(choco, new Variable("prix "+choco.toString(),this, 4.5));
					}
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					if(choco.getMarque().toString()=="Eticao") {
						prix.put(choco, new Variable("prix "+choco.toString(),this, 4.5));
					}
					else {
						prix.put(choco, new Variable("prix "+choco.toString(),this, 4.0));
					}
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 3.5));
				}
				if(choco.isEquitable()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.2));
				}
				if(choco.isBio()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.3));
				}

			}
			if(choco.getCategorie()==Categorie.CONFISERIE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 4.5));
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 4.0));
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, new Variable("prix "+choco.toString(),this, 3.5));
				}
				if(choco.isEquitable()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.2));
					}
				if(choco.isBio()) {
					prix.put(choco, new Variable("prix "+choco.toString(),this,prix.get(choco).getValeur()*1.3));
				}

			}
		}

	}
	
	public double getStockTGTotal() {
		double total=0;
		for (ChocolatDeMarque choco : this.stockTG.keySet()) {
			total+=stockTG.get(choco).getValeur();
		}
		return total;
	}

}

