package abstraction.eq4Transformateur2;

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.IFabricantChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.ChocolatDeMarque;


//Abigaëlle
public class Transformateur2Vente extends Transformateur2Production implements IVendeurContratCadre,IMarqueChocolat, IFabricantChocolatDeMarque {
	
	protected LinkedList<ExemplaireContratCadre> contrats;

	public Transformateur2Vente() {

		super();
		this.contrats = new LinkedList<ExemplaireContratCadre>();
	}
	
	//on peut vendre ce produit
	public boolean peutVendre(Object produit) {
		if (produit instanceof ChocolatDeMarque) {
			if (((ChocolatDeMarque) produit).getGamme() != Gamme.HAUTE) {
				if (((ChocolatDeMarque) produit).getCategorie() != Categorie.POUDRE) {
					if (((ChocolatDeMarque) produit).getMarque() == "Boni Suci" ){
						return true; 
					}
				}
			}
		}
		return false;
	}
	
	//on veut vendre ce produit à cet instant
	public boolean vend(Object produit) {
		if (peutVendre(produit)) {
				Chocolat choco = ((ChocolatDeMarque) produit).getChocolat();
				if (get_stock(choco) > 0) {
					return true;
			}
		}
		return false;
	}
	
	//notification nouveau contrat
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		this.contrats.add(contrat);
		Object choco = contrat.getProduit();
		if (choco instanceof ChocolatDeMarque) {
			Chocolat produit = ((ChocolatDeMarque) choco).getChocolat(); 
			if ((Chocolat) produit == Chocolat.CONFISERIE_BASSE) {
				nombre_step_total_basse = nombre_step_total_basse + contrat.getEcheancier().getNbEcheances();
				quantite_totale_demandee_basse = quantite_totale_demandee_basse + contrat.getQuantiteTotale();	
				for(int i = 0 ; i<contrat.getEcheancier().getNbEcheances(); i++) {
					echeancier_total.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_total.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24));
					echeancier_basse.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_basse.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24)); 
				}
			}
			if ((Chocolat) produit == Chocolat.CONFISERIE_MOYENNE) {
				nombre_step_total_moyenne = nombre_step_total_moyenne + contrat.getEcheancier().getNbEcheances();
				quantite_totale_demandee_moyenne = quantite_totale_demandee_moyenne + contrat.getQuantiteTotale();	
				for(int i = 0 ; i<contrat.getEcheancier().getNbEcheances(); i++) {
					echeancier_total.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_total.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24));
					echeancier_moyenne.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_moyenne.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24)); 
				}
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_BASSE) {
				nombre_step_total_basse = nombre_step_total_basse + contrat.getEcheancier().getNbEcheances();
				quantite_totale_demandee_basse = quantite_totale_demandee_basse + contrat.getQuantiteTotale();	
				for(int i = 0 ; i<contrat.getEcheancier().getNbEcheances(); i++) {
					echeancier_total.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_total.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24));
					echeancier_basse.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_basse.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24)); 
				}
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_MOYENNE) {
				nombre_step_total_moyenne = nombre_step_total_moyenne + contrat.getEcheancier().getNbEcheances();
				quantite_totale_demandee_moyenne = quantite_totale_demandee_moyenne + contrat.getQuantiteTotale();	
				for(int i = 0 ; i<contrat.getEcheancier().getNbEcheances(); i++) {
					echeancier_total.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_total.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24));
					echeancier_moyenne.set( (contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24  ,contrat.getEcheancier().getQuantite(i)+ echeancier_moyenne.get((contrat.getEcheancier().getStepDebut()+i-Filiere.LA_FILIERE.getEtape())%24)); 
				}
			}
			
		}
	}
	
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = 0;
		if (produit instanceof ChocolatDeMarque) {
			Chocolat choco = ((ChocolatDeMarque) produit).getChocolat();
			
			if ((Chocolat) choco == Chocolat.CONFISERIE_BASSE) {
				livre = Math.min(get_stock(Chocolat.CONFISERIE_BASSE), quantite);
				delete_stock(choco, quantite);
			}
			if ((Chocolat) choco == Chocolat.CONFISERIE_MOYENNE) {
				livre = Math.min(get_stock(Chocolat.CONFISERIE_MOYENNE), quantite);
				delete_stock(choco, quantite);
			}
			if ((Chocolat) choco == Chocolat.TABLETTE_BASSE) {
				livre = Math.min(get_stock(Chocolat.TABLETTE_BASSE), quantite);
				delete_stock(choco, quantite);
			}
			if ((Chocolat) choco == Chocolat.TABLETTE_MOYENNE) {
				livre = Math.min(get_stock(Chocolat.TABLETTE_MOYENNE), quantite);
				delete_stock(choco, quantite);
			}
		}	
		return livre;
	}
	
	/*
	public void next() {
		LinkedList<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.contrats) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.contrats.removeAll(contratsObsoletes);
	}
	*/
	
	public double propositionPrix(ExemplaireContratCadre contrat) {
		Object choco = contrat.getProduit();
		if (choco instanceof ChocolatDeMarque) {
			Chocolat produit = ((ChocolatDeMarque) choco).getChocolat(); 
			if ((Chocolat) produit == Chocolat.CONFISERIE_BASSE) {
				return prix_min_confiserie_basse * 1.4 ;
			}
			if ((Chocolat) produit == Chocolat.CONFISERIE_MOYENNE) {
				return prix_min_confiserie_moyenne *1.4  ;
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_BASSE) {
				return prix_min_tablette_basse * 1.4 ; 
			}
			if ((Chocolat) produit == Chocolat.TABLETTE_MOYENNE) {
				return prix_min_tablette_moyenne *1.4  ;
			}
			
		}	
		return -1 ; //si on ne vend pas ce produit 
	}

	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		double prixA = contrat.getListePrix().get(contrat.getListePrix().size()-1);
		double prixV = contrat.getListePrix().get(contrat.getListePrix().size()-2);
		
		if ((prixV - prixA) <= prixV*0.05 ) {
			return prixA; // pas de négociation car écart faible entre les deux prop
		}
		else {
			if (contrat.getTeteGondole()) {
				if (prixA*0.5 + prixV*0.5 >= contrat.getListePrix().get(0)*0.8) {
					return prixA*0.5 + prixV*0.5 ; 
				}
				else {
					return -1;
				}
			}
			else {
				if (prixA*0.45 + prixV*0.55 >= contrat.getListePrix().get(0)*0.85) {
					return prixA*0.45 + prixV*0.55 ; 
				}
				else {
					return -1;
				}
			}	
		}
	}
	
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		ChocolatDeMarque produit = (ChocolatDeMarque) contrat.getProduit();
		Chocolat choco = produit.getChocolat();
			if (24 - contrat.getEcheancier().getStepDebut() + Filiere.LA_FILIERE.getEtape() - contrat.getEcheancier().getNbEcheances() >= 0) {
				if (contrat.getEcheancier().getStepDebut() == Filiere.LA_FILIERE.getEtape()) {
					if (get_stock(choco) > contrat.getEcheancier().getQuantite(0)){
						return contrat.getEcheancier();
					}	
					else {
						if (contrat.getEcheancier().getNbEcheances() >1){
							List<Double> liste = new LinkedList<Double>();
							int deb = contrat.getEcheancier().getStepDebut();
							liste.add(0, get_stock(choco));
							liste.add(1,contrat.getEcheancier().getQuantite(1) + (contrat.getEcheancier().getQuantite(0) - get_stock(choco)));
							for (int i = 2; i< contrat.getEcheancier().getNbEcheances(); i++ ) {
								liste.add(i, contrat.getEcheancier().getQuantite(i));
							}
							Echeancier ech = new Echeancier(deb, liste);
							return ech;
						}
						else {
							List<Double> liste = new LinkedList<Double>();
							int deb = contrat.getEcheancier().getStepDebut();
							liste.add(0, get_stock(choco));
							liste.add(1, (contrat.getEcheancier().getQuantite(0) - get_stock(choco)));
							Echeancier ech = new Echeancier(deb, liste);
							return ech;
							
						}
						
						
					
					}
				}
				else {
					return contrat.getEcheancier();
				}
			}
		 return null ;
	}	


	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		List<ChocolatDeMarque> produits = new LinkedList<ChocolatDeMarque>();
		produits.add(new ChocolatDeMarque(Chocolat.TABLETTE_BASSE, "Boni Suci"));
		produits.add(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE, "Boni Suci"));
		produits.add(new ChocolatDeMarque(Chocolat.CONFISERIE_BASSE, "Boni Suci"));
		produits.add(new ChocolatDeMarque(Chocolat.CONFISERIE_MOYENNE, "Boni Suci"));
		return produits;
	}

	@Override
	public List<String> getMarquesChocolat() {
		List<String> marque = new LinkedList<String>();
		marque.add("Boni Suci");
		return marque;
	}
	
	// Test
	
}
