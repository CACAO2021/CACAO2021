package abstraction.eq2Producteur2;

import java.util.LinkedList;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Beurre;

// Elo

public class Producteur2Transfo extends Producteur2Prod {
	private LinkedList<Stock> FevesHEtransformees;
	private LinkedList<Stock> FevesMtransformees;
	private LinkedList<Stock> FevesMEtransformees;
	
	private LinkedList<Object> listeTransfo;

	public Producteur2Transfo() {
		super();
		this.FevesHEtransformees = new LinkedList<Stock>();
		this.FevesHEtransformees.add(new Stock(0.1*prodParStep(Feve.FEVE_HAUTE_EQUITABLE), 0));
		this.FevesMtransformees = new LinkedList<Stock>();
		this.FevesMtransformees.add(new Stock(0.1*prodParStep(Feve.FEVE_MOYENNE), 0));
		this.FevesMEtransformees = new LinkedList<Stock>();
		this.FevesMEtransformees.add(new Stock(0*prodParStep(Feve.FEVE_MOYENNE_EQUITABLE), 0));
		
		this.listeTransfo = new LinkedList<Object>();
		this.listeTransfo.add(Beurre.BEURRE_HAUTE_EQUITABLE);
		this.listeTransfo.add(Beurre.BEURRE_MOYENNE_EQUITABLE);
		this.listeTransfo.add(Beurre.BEURRE_MOYENNE);
		
	}
	
	public void transfo() {
		for (Object p : listeTransfo) {
			double qtt = prodParStep(p);
			addStock(qtt, p);
			JournalProd.ajouter(""+ p +" "+qtt);	
			coutTransfo(p);
		}
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
	
	public int tempsTransf(Object produit) {
		if (estPoudreHE(produit)) {
			return (int)(prodParStep(produit)/qteParStepHE);
		} else if (estPoudreM(produit)){
			return (int)(prodParStep(produit)/qteParStepM);
		} else if (estPoudreME(produit)){
			return (int)(prodParStep(produit)/qteParStepME);
		} return 0;
	}
	
	public double coutTransfo(Object produit) {
		if (estPoudreHE(produit)) {
			return prodParStep(produit)*prixHEparKilo;
		} else if (estPoudreM(produit)) {
			return prodParStep(produit)*prixMparKilo;
		} else if (estPoudreME(produit)) {
			return prodParStep(produit)*prixMEparKilo;
		} return 0;
	}

	@Override
	public void perdreArgent(double montant) {
		// TODO Auto-generated method stub
		
	}

}

