package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur1Acteur{
	
	
	protected Map<ChocolatDeMarque, Variable> stock; // tout les stocks, y compris le contenu de stockTG
	protected Map<ChocolatDeMarque, Double> prix;
	protected Map<ChocolatDeMarque,Variable> stockTG;

	//Louis
	public Stocks() {
		super();
		this.stock=new HashMap<ChocolatDeMarque, Variable>(); 
		this.prix=new HashMap<ChocolatDeMarque, Double>();
		this.stockTG=new HashMap<ChocolatDeMarque, Variable>(); 
	}
	
	//Louis
	public void ajouterStock(Object produit, double quantite, boolean tg) {
		//peut-etre que caster produit en ChocolatDeMarque va faire une erreur, il faudrait mettre des verifications ou le caster avant d'utiliser cette methode
		//si tg==true alors on ajoute le produit en tête de gondole, sinon simplement en rayon
		if (tg) {
			stockTG.put((ChocolatDeMarque)produit, new Variable(((ChocolatDeMarque)produit).getMarque()+" Quantite", this, stock.get((ChocolatDeMarque)produit).getValeur()+quantite));
		}
		stock.put((ChocolatDeMarque)produit, new Variable(((ChocolatDeMarque)produit).getMarque()+" Quantite", this, stock.get((ChocolatDeMarque)produit).getValeur()+quantite));

	}
	
	//Louis
	public void setPrix(ChocolatDeMarque choco, double prix) {
		this.prix.put(choco, prix);
	}

	//Louis
	public void initialiser() {
		super.initialiser();
		initCatalogue();
		initPrix();
		int i=0;
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			i++;
			this.indicateurs.add(new Variable(choco.getMarque().toString()+ " " + i +" Quantite stock CC",this,0.));
		}
	}
	
	//Louis
	public void initCatalogue() {
		for (ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {

			stock.put(choco, new Variable(choco.getMarque()+" Quantite",this,10.));

		}
	}

	//Louis
	public void initPrix() {
		for(ChocolatDeMarque choco : stock.keySet()) {
			if(choco.getCategorie()==Categorie.TABLETTE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					prix.put(choco, 2.0);
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					prix.put(choco, 1.5);
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, 1.0);
				}
				if(choco.isEquitable()) {
					prix.put(choco, prix.get(choco)*1.2);
				}
				if(choco.isBio()) {
					prix.put(choco, prix.get(choco)*1.3);
				}

			}
			if(choco.getCategorie()==Categorie.POUDRE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					prix.put(choco, 2.0);
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					prix.put(choco, 1.5);
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, 1.0);
				}
				if(choco.isEquitable()) {
					prix.put(choco, prix.get(choco)*1.2);
				}
				if(choco.isBio()) {
					prix.put(choco, prix.get(choco)*1.3);
				}

			}
			if(choco.getCategorie()==Categorie.CONFISERIE) {
				if(choco.getGamme()==Gamme.HAUTE) {
					prix.put(choco, 2.0);
				} else if (choco.getGamme()==Gamme.MOYENNE) {
					prix.put(choco, 1.5);
				}else if (choco.getGamme()==Gamme.BASSE) {
					prix.put(choco, 1.0);
				}
				if(choco.isEquitable()) {
					prix.put(choco, prix.get(choco)*1.2);
				}
				if(choco.isBio()) {
					prix.put(choco, prix.get(choco)*1.3);
				}

			}
		}
		
	}

}
	

	
	

