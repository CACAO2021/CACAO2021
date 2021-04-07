package abstraction.eq6Distributeur1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Variable;

public class Stocks extends Distributeur1Acteur{
	
	
	protected Map<ChocolatDeMarque, Variable> stock; 
	
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
	protected int POUDRE_BASSE;

	
	public Stocks() {
		this.stock=new HashMap<ChocolatDeMarque, Variable>();
	}
	
	public void ajouterStock(Object produit, double quantite) {
		//à completer
	}
	

	

}
