package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Map;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

public abstract class Producteur2Aleas extends Producteur2Param  {

	public Producteur2Aleas() {
		super();
	}

	public void lesProblemes() {
		// intempéries -> destruction stock + arbres
		intemperies();
		//faire focntion revolte des prod si plus dargent pour les payer
		revolte();
		
	}
	
	public void intemperies() {
		if (Math.random() < PROBA_INTEMPERIE) {
			// l'intemperie à lieu dans ce cas
			// on tire un pourcentage sur chaque element stocké
			for (Feve e : listeProd) {
				double pourcentageStockADetruire = Math.random() / 2. ;
				double stockActuel = qttTotale(e).getValeur();
				// pourcentage du stock qui sera détruit lors de l'intempérie 
				// il n'est pas possible que plus de la moitie du stock soit détruite
				// le nombre obtenu est contenu entre 0 et 0.5
				vente(pourcentageStockADetruire * stockActuel, e);
			}
			// on tire un pourcentage sur chaque arbre
			// for (arbre a : ){
		}
	}

	//DIM
	//révolution si pas assez d'argent pour payer les prods
	public void revolte() {
		if (Math.random() < PROBA_REVOLTE) {
			JournalRevolte.ajouter(Color.RED, Color.BLACK, "révolution au step " + Filiere.LA_FILIERE.getEtape());
			// les couts de production augmente (ce qui correspond à un salaire qui augmente pour les producteurs)
			COUT_PRODUCTION_FEVE_B *=  1.2; // cout de prod multiplier par 1.2
			COUT_PRODUCTION_FEVE_M *= 1.2;
			COUT_PRODUCTION_FEVE_ME *= 1.3;
			COUT_PRODUCTION_FEVE_HE *= 1.4;
			COUT_PRODUCTION_FEVE_HBE *= 1.5;
			// pdt la révolte, une faible qtt de stock est perdue 
			// seulement les faible basse et moyenne sont affectées
			LinkedList<Feve> listeProdRevolte = new LinkedList<Feve>();
			this.listeProd.add(Feve.FEVE_MOYENNE); 
			this.listeProd.add(Feve.FEVE_BASSE);
			for (Feve e : listeProdRevolte) {
				double qtt = qttTotale(e).getValeur() * (Math.random() / 2);
				vente(qtt, e);				
			}
						
			PROBA_REVOLTE /= 4; // la proba diminue a chaque fois qu une revolte arrive
		}
	}
	
	
	
}
