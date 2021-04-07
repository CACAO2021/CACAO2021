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
	// constructeur
	public MarqueTransformateur1() {
		this.Marques = new ArrayList<String>() ;
		this.Marques.add("Milieu de gamme Ethicao");
		this.Marques.add("Haut de gamme Ethicao");
		this.MProduits = new ArrayList<Chocolat>() ;
		this.MProduits.add(Chocolat.TABLETTE_MOYENNE_EQUITABLE);
		this.HProduits = new ArrayList<Chocolat>() ;
		this.HProduits.add(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE);
	}
	// renvoie les produits d'une marque spécifique
	public List<Chocolat> ProduitsMarque(String Marque) {
		if(this.Marques.get(0).equals(Marque)) {
			return this.MProduits;
		}
		if(this.Marques.get(1).equals(Marque)) {
			return this.HProduits;
		}
		return null;
	}
	// renvoie une liste de tous les produits existants
	public List<Chocolat> getProduits() {
		List<Chocolat> L = new ArrayList<Chocolat>();
		for(int j=0; j<this.MProduits.size(); j++) {
			L.add(this.MProduits.get(j));
		}
		for(int i=0; i<this.HProduits.size(); i++) {
			if(!this.MProduits.contains(this.HProduits.get(i))) {
				L.add(this.HProduits.get(i));
			}
		}
		return L;
	}
	
	@Override
	public List<String> getMarquesChocolat() {
		// TODO Auto-generated method stub
		return this.Marques;
	}

}
