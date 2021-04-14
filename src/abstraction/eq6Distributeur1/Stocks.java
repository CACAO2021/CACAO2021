package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur1Acteur /*implements IMarqueChocolat*/{
	
	
	protected Map<ChocolatDeMarque, Variable> stock;
	protected Map<ChocolatDeMarque, Double> prix;
	protected Map<ChocolatDeMarque,Variable> stockTG;

	
	public Stocks() {
		this.stock=new HashMap<ChocolatDeMarque, Variable>();
		this.prix=new HashMap<ChocolatDeMarque, Double>();
		this.stockTG=new HashMap<ChocolatDeMarque, Variable>();
	}
	
	public void ajouterStock(Object produit, double quantite, boolean tg) {
		//peut-etre que caster produit en ChocolatDeMarque va faire une erreur, il faudrait mettre des verifications ou le caster avant d'utiliser cette methode
		//si tg==true alors on ajoute le produit en tête de gondole, sinon simplement en rayon
		if (tg) {
			stockTG.put((ChocolatDeMarque)produit, new Variable(((ChocolatDeMarque)produit).getMarque()+" Quantite", this, quantite));
		}
		stock.put((ChocolatDeMarque)produit, new Variable(((ChocolatDeMarque)produit).getMarque()+" Quantite", this, quantite));
		
	}
	
	public void initPrix() {
		
	}

	/*@Override
	public List<String> getMarquesChocolat() {
		List marque = new ArrayList<String>();
		marque.add("CacaoCaisse");
		return marque;
		}*/
	}
	

	
	
	/*
	 * En fait on achète que les marques des transformateurs donc pas besoin de specifer leur qualite, bio, etc,
	 * tout ça est compris dans l'objet ChocolatDeMarque (pour voir comment il est défini la raccourci c'est ctl+click sur ChocolatDeMarque)
	 * Il faudra juste reflechir a comment initialiser la Map qui definit tout ça vu qu'on connait pas les noms
	 * des marques à l'avance a priori
	 * 
	protected int TABLETTE_HAUTE_BIO_EQUITABLE;
	protected int TABLETTE_HAUTE_BIO;
	protected int TABLETTE_HAUTE_EQUITABLE;
	//protected int TABLETTE_HAUTE;
	protected int TABLETTE_MOYENNE_EQUITABLE;
	protected int TABLETTE_MOYENNE;
	protected int TABLETTE_BASSE;
	
	protected int CONFISERIE_HAUTE_BIO_EQUITABLE;
	//protected int CONFISERIE_HAUTE_BIO;
	protected int CONFISERIE_HAUTE_EQUITABLE;
	//protected int CONFISERIE_HAUTE;
	protected int CONFISERIE_MOYENNE_EQUITABLE;
	protected int CONFISERIE_MOYENNE;
	protected int CONFISERIE_BASSE;
	
	protected int POUDRE_HAUTE_BIO_EQUITABLE;
	//protected int POUDRE_HAUTE_BIO;
	//protected int POUDRE_HAUTE_EQUITABLE;
	//protected int POUDRE_HAUTE;
	protected int POUDRE_MOYENNE_EQUITABLE;
	protected int POUDRE_MOYENNE;
	protected int POUDRE_BASSE;*/
	

