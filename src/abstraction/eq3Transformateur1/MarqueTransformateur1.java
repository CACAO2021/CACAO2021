package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.IMarqueChocolat;

public class MarqueTransformateur1 implements IMarqueChocolat {
	private List<Chocolat> MProduits;
	private List<Chocolat> HProduits;
	private List<String> Marques;
	
//Pour l'instant on ne modélise qu'avec 1 seul produit par marque.
	
	public MarqueTransformateur1(){
		this.Marques = new ArrayList<String>() ;
		this.Marques.add("Milieu de gamme Ethicao");
		this.Marques.add("Haut de gamme Ethicao");
		this.MProduits = new ArrayList<Chocolat>() ;
		this.MProduits.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		this.HProduits = new ArrayList<Chocolat>() ;
		this.HProduits.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
	}
	
	public List<Chocolat> ProduitsMarque(String Marque) {
		if(this.Marques.get(0).equals(Marque)) {
			return this.MProduits;
		}
		if(this.Marques.get(1).equals(Marque)) {
			return this.HProduits;
		}
		return null;
	}
	
	@Override
	public List<String> getMarquesChocolat() {
		// TODO Auto-generated method stub
		return this.Marques;
	}

}
