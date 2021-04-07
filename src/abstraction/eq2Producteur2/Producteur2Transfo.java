package abstraction.eq2Producteur2;

import java.util.LinkedList;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Beurre;
import abstraction.eq2Producteur2.Producteur2Stockage;

// Elo

public class Producteur2Transfo extends Producteur2VendeurFeveAO {
	private LinkedList<Stock> FevesHEtransformees;
	private LinkedList<Stock> FevesMtransformees;
	private LinkedList<Stock> FevesMEtransformees;
	
	private LinkedList<Object> listeTransfo;

	public Producteur2Transfo() {
		super();
		this.FevesHEtransformees = new LinkedList<Stock>();
		this.FevesHEtransformees.add(new Stock(TRANSFO_DEBUT_HE, 0));
		this.FevesMtransformees = new LinkedList<Stock>();
		this.FevesMtransformees.add(new Stock(TRANSFO_DEBUT_M, 0));
		this.FevesMEtransformees = new LinkedList<Stock>();
		this.FevesMEtransformees.add(new Stock(TRANSFO_DEBUT_ME, 0));
		
		this.listeTransfo = new LinkedList<Object>();
		this.listeTransfo.add(Beurre.BEURRE_HAUTE_EQUITABLE);
		this.listeTransfo.add(Beurre.BEURRE_MOYENNE_EQUITABLE);
		this.listeTransfo.add(Beurre.BEURRE_MOYENNE);
		
	}
	
	public void transfo() {
		// a faire
	}
	
	public double qteFeves(Object produit) {
		double nb = 0;
		if (estPoudreHE(produit)) {			
			for (Stock s : this.FevesHEtransformees) {
				nb += s.getQtt();
			}
		} else if (estPoudreM(produit)) {
			for (Stock s : this.FevesMtransformees) {
				nb += s.getQtt();
			}
		}
		else if (estPoudreME(produit)) {
			for (Stock s : this.FevesMtransformees) {
				nb += s.getQtt();
			}
		}
		return nb;
	}
	
	public double coef(Object produit) {
		if (estPoudreHE(produit)) {
			return coefHE; //  coefficient qui indique la qté de poudre HE que l'on peut obtenir en fonction de la qté de fèves (à dterminer dans Producteur2Valeurs)
		} else if (estPoudreM(produit)) {
			return coefM;
		} else if (estPoudreME(produit)) {
			return coefME;
		} return 0;
	}
	
	public double prodParStep(Object produit) {
		return qteFeves(produit)*coef(produit);
	}

}
