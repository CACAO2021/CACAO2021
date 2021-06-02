package abstraction.eq5Transformateur3;

import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;


//Léna

public class Transformateur3VenteContratCadre extends Transformateur3Fabricant implements IVendeurContratCadre{

	protected HashMap<ExemplaireContratCadre, Integer> contratsVente ;
	
	public Transformateur3VenteContratCadre() {
		this.contratsVente = new HashMap<ExemplaireContratCadre, Integer>();
	}
	public HashMap<ExemplaireContratCadre, Integer> getContratsVente() {
		return contratsVente;
	}
	public int getEtape(ExemplaireContratCadre contrat) {
		return this.getContratsVente().get(contrat);
	}
	
	public boolean peutVendre(Object produit) {
		/*if (produit == Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE || produit == Chocolat.TABLETTE_MOYENNE || produit == Chocolat.CONFISERIE_MOYENNE) {
			return true; }
		else { return false; }*/
		if (this.getChocolatsProduits().contains(produit)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean vend(Object produit) {
			if (produit instanceof ChocolatDeMarque) {
				Chocolat choco = ((ChocolatDeMarque)produit).getChocolat();
			if (!this.peutVendre(produit)) {
				return false;
			} else if (this.getChocolats().get(choco).getValeur()>0) {
				return true;
			} else {
				return false;}	
			
		}
		
		else {
			return false;
		}
	}

	@Override
	public Echeancier contrePropositionDuVendeur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		Chocolat chocolat = ((ChocolatDeMarque)produit).getChocolat();
		if (this.vend(produit)){
			if (chocolat.equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) 
					&& this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE).getValeur()>this.stock_min_tablettes_HBE.getValeur()
					&& contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1)<this.getChocolats().get(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE).getValeur()){
				return contrat.getEcheancier();}
			if (chocolat.equals(Chocolat.TABLETTE_MOYENNE) 
					&& this.getChocolats().get(Chocolat.TABLETTE_MOYENNE).getValeur()>this.stock_min_tablettes_moyenne.getValeur()
					&& contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1)<this.getChocolats().get(Chocolat.TABLETTE_MOYENNE).getValeur()){
				return contrat.getEcheancier();}
			if (chocolat.equals(Chocolat.CONFISERIE_MOYENNE) 
					&& this.getChocolats().get(Chocolat.CONFISERIE_MOYENNE).getValeur()>this.stock_min_confiserie.getValeur()
					&& contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1)<this.stock_avant_transfo_C.getValeur()){
				return contrat.getEcheancier();
			}
			else { return null;
			} 
		}
		else { return null;
		}
	}
		
	
	@Override
	public double propositionPrix(ExemplaireContratCadre contrat) {
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE)) {
			return this.prix_tablette_equi.getValeur()*contrat.getQuantiteTotale();	}
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.TABLETTE_MOYENNE)) {
			return this.prix_tablette.getValeur()*contrat.getQuantiteTotale(); }
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.CONFISERIE_MOYENNE)) {
			return this.prix_confiserie.getValeur()*contrat.getQuantiteTotale(); }
		else { return -1; }
		
	}

	@Override
	public double contrePropositionPrixVendeur(ExemplaireContratCadre contrat) {
		
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE)) {
			if (contrat.getPrix()>this.prix_min_vente_EQ.getValeur()) {
				return contrat.getPrix();}
			else { return this.prix_min_vente_EQ.getValeur()*contrat.getQuantiteTotale(); } }
		
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.TABLETTE_MOYENNE)) {
			if (contrat.getPrix()>this.prix_min_vente_MG.getValeur()) {
				return contrat.getPrix(); }
			else { return this.prix_min_vente_MG.getValeur()*contrat.getQuantiteTotale();} }
			
		if (((ChocolatDeMarque)contrat.getProduit()).getChocolat().equals(Chocolat.CONFISERIE_MOYENNE)) {
			if (contrat.getPrix()>this.prix_min_vente_confiserie.getValeur()) {
				return contrat.getPrix(); }
		    else { return this.prix_min_vente_confiserie.getValeur()*contrat.getQuantiteTotale(); } }
		
		else { return -1;} 
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (contrat.getVendeur() == Filiere.LA_FILIERE.getActeur("EQ5")) {
			this.JournalVenteContratCadre.ajouter("nouveau contrat cadre entre " + contrat.getAcheteur() + " et "+contrat.getVendeur()+" d'une quantité " + contrat.getQuantiteTotale() + "kg de " + contrat.getProduit() + " pendant " + contrat.getEcheancier() + " pour " + contrat.getPrix() +" euros le kilo");
			this.getContratsVente().put(contrat, Filiere.LA_FILIERE.getEtape());
		}
	}
	
	@Override
	public double livrer(Object produit, double quantite, ExemplaireContratCadre contrat) {
		double livre = Math.min(this.getChocolats().get(((ChocolatDeMarque)produit).getChocolat()).getValeur(), quantite);
		if (livre>0.0) {
			this.retirer(((ChocolatDeMarque)produit).getChocolat(), livre);
		}
		return livre;
	}
	
}
	
