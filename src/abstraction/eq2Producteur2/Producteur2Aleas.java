package abstraction.eq2Producteur2;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		// focntion revolte des producteurs
		// pas de facteur eco pr causer la fct
		revolte();

	}

	public void intemperies() {
		if (Math.random() < PROBA_INTEMPERIE && (Filiere.LA_FILIERE.getEtape() > 10)) {
			// l'intemperie à lieu dans ce cas
			// on tire un pourcentage sur chaque element stocké
			for (Feve e : listeProd) {
				majStock(e);
				double pourcentageStockADetruire = Math.random() / 4. ;
				double stockActuel =  0.0;
				if ((stock_F.get(e)).size() != 0) {
					stockActuel = (stock_F.get(e)).get(0).getQtt();
				}	
				// pourcentage du stock qui sera détruit lors de l'intempérie 
				// il n'est pas possible que plus de le quart du stock soit détruite
				// le nombre obtenu est contenu entre 0 et 0.25
				vente(pourcentageStockADetruire * stockActuel, e);
			}
			// on fait la meme chose pour les arbres
			double sum =0;
			List<Stock> toAdd = new ArrayList<Stock>();
			for (Feve f : listeProd) {
				// partie suppression
				// on compte le nombre total darbre à replanter
				// on les detruit
				// et on replante directement
				// en payant les frais associés
				// on replante le meme arbre :
				// pas le temps de reflechir a une strategie en temps de crise
				for (Stock s : arbrePlantes.get(f)) {
					double qttDetruite = s.getQtt() * (Math.random() / 4);
					sum +=  qttDetruite;
					//System.out.println(sum);
					s.setQtt(s.getQtt() - qttDetruite);
					toAdd.add(new Stock(qttDetruite, Filiere.LA_FILIERE.getEtape()));
				}
				for (Stock st : toAdd) {
					arbrePlantes.get(f).add(st);
				}
				toAdd.clear();
			}
			double coutPerte = COUT_CHANGEMENT_ARBRE * sum * 2;
			perdreArgent(coutPerte);
			JournalPB.ajouter(Color.PINK, Color.BLACK, "intempéries, perte de " +coutPerte);
		}
	}

	//DIM
	
	// revolte des producteurs
	public void revolte() {
		if (Math.random() < PROBA_REVOLTE) {
			
			// la révolte a une probabilité de survenir
			// survient à nimporte que moment
			// elle n'est pas lié à un facteur économique
			// si on a trop dargent on le reverse de toute manière
			
			JournalPB.ajouter(Color.RED, Color.BLACK, "révolution au step " + Filiere.LA_FILIERE.getEtape());
			// les couts de production augmente (ce qui correspond à un salaire qui augmente pour les producteurs)
			// on augemente les salaires pour nous mais pas pour lautre equipe
			COUT_PRODUCTION_FEVE_B_ *=  1.2; // cout de prod multiplier par 1.2
			COUT_PRODUCTION_FEVE_M_ *= 1.2;
			COUT_PRODUCTION_FEVE_ME_ *= 1.3;
			COUT_PRODUCTION_FEVE_HE_ *= 1.4;
			COUT_PRODUCTION_FEVE_HBE_ *= 1.5;
			
			// pdt la révolte, une faible qtt de stock est perdue 
			// seulement les faible basse et moyenne sont affectées
			LinkedList<Feve> listeProdRevolte = new LinkedList<Feve>();
			this.listeProd.add(Feve.FEVE_MOYENNE); 
			this.listeProd.add(Feve.FEVE_BASSE);
			for (Feve e : listeProdRevolte) {
				double qtt = qttTotale(e).getValeur() * (Math.random() / 2);
				vente(qtt, e);				
			}						
			PROBA_REVOLTE /= 4;
			// la proba diminue a chaque fois qu une revolte arrive
		}
	}



}