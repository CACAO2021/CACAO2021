package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class VendeurContratCadre1 extends VendeurFevesAO implements IVendeurContratCadre{
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Integer cryptogramme;
	protected Object produit;
	protected Journal journal;
	protected SuperviseurVentesContratCadre supCCadre;
	protected List<ExemplaireContratCadre> mesCC; 
	protected HistCC cceq2;
	protected HistCC cceq3;
	protected HistCC cceq4;
	protected HistCC cceq5;
	protected HistCC cceq6;
	protected HistCC cceq7;	
	/**
	 * @author arthurlemerle
	 * On initialise la fidélité des clients
	 */
	private void initHistCC() {
		this.cceq2 = new HistCC("EQ2");
		this.cceq3=new HistCC("EQ3");
		this.cceq4=new HistCC("EQ4");
		this.cceq5=new HistCC("EQ5");
		this.cceq6=new HistCC("EQ6");
		this.cceq7=new HistCC("EQ7");
	}
	

	/**
	 * @author lebra
	 */
	public boolean peutVendre(Object produit) {
		if ((produit instanceof Feve)
				&& ( (((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)
						|| (((Feve)produit) == Feve.FEVE_MOYENNE)
						|| (((Feve)produit) == Feve.FEVE_BASSE)
					)) {
				return(true);
		}
		else if ((produit instanceof ChocolatDeMarque)
				&& ((((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE_EQUITABLE)
					||(((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE))) {
			return (true); 
		}
		else {
			return (false);
		}
		
	}
	/**
	 * @author arthurlemgit
	 * Permet de déterminer si un client est assez fidèle.
	 * Ici, le client
	 */
	public boolean assezfidele(ExemplaireContratCadre contrat) {
		String client = contrat.getAcheteur().getNom();
		List<HistCC> historique = new LinkedList<HistCC>();
		historique.add(cceq2);
		historique.add(cceq3);
		historique.add(cceq4);
		historique.add(cceq5);
		historique.add(cceq6);
		historique.add(cceq7);
		int l = historique.size();
		for (int i=1; i<l; i++) {
			int x = historique.get(i).getNbcontrats();
			int k = i;
			while (k>0 && x<historique.get(k-1).getNbcontrats()) {
				historique.set(k, historique.get(k-1));
				k -= 1;
			historique.set(k, historique.get(x));
			}
		}
		if ((client.equals(historique.get(l-1).getActeur())) || (client.equals(historique.get(l-2).getActeur()))) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author arthurlemgit
	 * Dans un premier temps, on accepte de vendre par CC dès lors qu'on a du produit (donc que notre stock>0)
	 * A ajouter ? on accepte de vendre uniquement aux acheteurs réguliers (-> fidélisation)
	 */

	public boolean vend(Object produit){
		boolean res = false;
		if (this.peutVendre(produit)) {
			if (produit instanceof ChocolatDeMarque) {
				res = this.getStock(((ChocolatDeMarque)produit).getChocolat()).getQuantite() > 0;
			}
			else {
				res = this.getStock(produit).getQuantite() > 0;
			}
			
		}
	return  res;
}
		

	
	/**
	 * @author arthurlemgit
	 * Si l'échéancier proposé demande trop de quantité par rapport à notre stock et notre stratégie, ou "pas assez";
	 * propose un nouvel échéancier avec des quantités plus "raisonnables" de notre stock, en accord avec notre stratégie.
	 * Pour les produits équitables, on s'assure que l'échelonnement de l'échancier fait plus de 8 steps -ventes dans la durée avec les tranformateurs-
	 * et que la quantitée moyenne demandée par step n'est pas en-deçà d'une certaine limite
	 * Si l'échéancier proposé fait moins de 8 steps, on propose un nouvel échéancier de 8 steps; avec une répartition uniforme sur la durée du contrat.
	 * 
	 */
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		produit=contrat.getProduit();
		if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.25*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.05*this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.15*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			} 
		} else if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE)) ) {
			double duree = contrat.getEcheancier().getStepFin()-contrat.getEcheancier().getStepDebut();
			if (duree > 8 && contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() > EQUI_QTT_MINI) {
				if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.55*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.35*this.getStocks().get(contrat.getProduit()).getQuantite()) {
					double nvlleqte = 0.45*this.getStocks().get(contrat.getProduit()).getQuantite();
					Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
					return e;
				} else {
					Echeancier e = new Echeancier (contrat.getEcheancier());
					return e;
					} 
			} else {
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), 8, contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances());
				return e;
			}
			
		} else if ((contrat.getProduit() instanceof Feve) && ((((Feve)produit) == Feve.FEVE_BASSE)) ) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.5*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.30*this.getStocks().get(contrat.getProduit()).getQuantite()) {
				double nvlleqte = 0.40*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			} 
		} else if (contrat.getProduit() instanceof ChocolatDeMarque && ((((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE_EQUITABLE))) {
			double duree = contrat.getEcheancier().getStepFin()-contrat.getEcheancier().getStepDebut();
			if (duree > 8 && contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() > EQUI_QTT_MINI) {
				if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() >=  0.30*this.getStocks().get(contrat.getProduit()).getQuantite() || contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.10 *this.getStocks().get(contrat.getProduit()).getQuantite()) {
					double nvlleqte = 0.2*this.getStocks().get(contrat.getProduit()).getQuantite();
					Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
					return e;
				} else {
					Echeancier e = new Echeancier (contrat.getEcheancier());
					return e;
				}
			} else {
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), 8, contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances());
				return e;
			}
			
		} else if (contrat.getProduit() instanceof Chocolat && ((((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE))) {
			if (contrat.getEcheancier().getQuantiteTotale()/contrat.getEcheancier().getNbEcheances() <=  0.60*this.getStocks().get(contrat.getProduit()).getQuantite()/contrat.getEcheancier().getNbEcheances() ) {
				double nvlleqte = 0.8*this.getStocks().get(contrat.getProduit()).getQuantite();
				Echeancier e = new Echeancier(contrat.getEcheancier().getStepDebut(), contrat.getEcheancier().getStepFin(), ((double)(nvlleqte/(contrat.getEcheancier().getNbEcheances()))));
				return e;
			} else {
				Echeancier e = new Echeancier (contrat.getEcheancier());
				return e;
			}
		} else {
			return contrat.getEcheancier();
		}
	}

	/** 
	* @author Alb1x
	* Trouver un prix pour la poudre
	* Pour l'instant on n'en produit pas
	*/
	public double propositionPrix(ExemplaireContratCadre contrat) {
		produit = contrat.getProduit();
		double prix= 0;
		if (produit instanceof Feve) {
			if ((Feve)produit==Feve.FEVE_MOYENNE_EQUITABLE) {
				prix=2.150;
			}
			else if ((Feve)produit == Feve.FEVE_MOYENNE) {
				prix = 0.8;
			}
			else if ((Feve)produit == Feve.FEVE_BASSE) {
				prix = 1.500;
			}
		}
		if (produit instanceof ChocolatDeMarque) {
			if (((ChocolatDeMarque)produit).getChocolat()==Chocolat.POUDRE_MOYENNE_EQUITABLE) {
				prix=3.0;
			}
			if (((ChocolatDeMarque)produit).getChocolat()==Chocolat.POUDRE_MOYENNE) {
				prix=2.7;
			}
		}
		return prix;
	}

	/**
	 * @author Alb1x
	 * La méthode contrat cadre ne concerne que les fèves équitables
	 * On essaye de couper la poire en deux si le prix précedent ne convient pas.
	 * Si le nouveau prix est au dessus du seuil requis alors on le propose
	 * sinon on fait la moyenne du prix proposé et du prix seuil.
	 */
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		produit = contrat.getProduit();
		List<Double> liste_prix = contrat.getListePrix();
		int n = liste_prix.size();
		double moyenne = (liste_prix.get(n-2)+liste_prix.get(n-1))/2; // on coupe la poire en deux entre notre proposition et la proposition de l'acheteur
		if (produit == Feve.FEVE_MOYENNE_EQUITABLE && moyenne>PRIX_PALIER_F_M_E) {
			return moyenne;
		}
		if (produit == Feve.FEVE_MOYENNE && moyenne>PRIX_PALIER_F_M) {
			return moyenne;
		}
		if ()
		else {
			return (liste_prix.get(n-2)+PRIX_PALIER_F_E)/2;
		}
		
	}


	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (this.mesCC==null) {
			this.mesCC= new LinkedList<ExemplaireContratCadre>();
		}
		this.mesCC.add(contrat);

	}

	/**
	 * @author arthurlemgit
	 * Retourne la quantité de produit livré et met à jour le stock, selon le type de produit.
	 * On livre ici systématiquement la quantité maximale qu'on puisse. 
	 * @author lebra seulement pour les this.getJournaux()
	 */
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if ((produit instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE_EQUITABLE))) {
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal("Ghanao VenteContratCadre").ajouter("Livraison de " + livre + "kg de " + produit + "au prix de " + contrat.getPrix());
				this.getJournal("Ghanao VenteContratCadre").ajouter("stock : "+ this.getStock(produit).getQuantite());
				}
			return livre;
		} else if ((produit instanceof Feve) && ((((Feve)produit) == Feve.FEVE_MOYENNE))) {
			this.getJournal("Ghanao VenteContratCadre").ajouter("stock : "+ this.getStock(produit).getQuantite());
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal("Ghanao VenteContratCadre").ajouter("Livraison de " + livre + "kg de " + produit + "au prix de " + contrat.getPrix());
			}
			return livre;
		} else if ((produit instanceof Feve) && ((((Feve)produit) == Feve.FEVE_BASSE))) {
			double livre = Math.min(this.getStocks().get(contrat.getProduit()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(produit).removeQuantite(livre);
				this.getJournal("Ghanao VenteContratCadre").ajouter("Livraison de " + livre + "kg de " + produit + "au prix de " + contrat.getPrix());
				this.getJournal("Ghanao VenteContratCadre").ajouter("stock : "+ this.getStock(produit).getQuantite());
			}
			return livre;
		} else if ((produit instanceof ChocolatDeMarque) && ((((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE_EQUITABLE))) {
			double livre = Math.min(this.getStocks().get(((ChocolatDeMarque)produit).getChocolat()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(((ChocolatDeMarque)produit).getChocolat()).removeQuantite(livre);
				this.getJournal("Ghanao VenteContratCadre").ajouter("Livraison de " + livre + "kg de " + produit + "au prix de " + contrat.getPrix());
				this.getJournal("Ghanao VenteContratCadre").ajouter("stock : "+ this.getStock(((ChocolatDeMarque)produit).getChocolat()).getQuantite());
			}
			return livre;
		} else if ((produit instanceof ChocolatDeMarque) && ((((ChocolatDeMarque)produit).getChocolat() == Chocolat.POUDRE_MOYENNE))) {
			double livre = Math.min(this.getStocks().get(((ChocolatDeMarque)produit).getChocolat()).getQuantite(), quantite);
			if (livre>0) {
				this.getStocks().get(((ChocolatDeMarque)produit).getChocolat()).removeQuantite(livre);
				this.getJournal("Ghanao VenteContratCadre").ajouter("Livraison de " + livre + "kg de " + produit + "au prix de " + contrat.getPrix());
				this.getJournal("Ghanao VenteContratCadre").ajouter("stock : "+ this.getStock(((ChocolatDeMarque)produit).getChocolat()).getQuantite());
			}
			return livre;
		}
		return 0; 
	}
}
