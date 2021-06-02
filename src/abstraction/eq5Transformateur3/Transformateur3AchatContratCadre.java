package abstraction.eq5Transformateur3;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadreNotifie;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;


//Manuelo
public class Transformateur3AchatContratCadre extends Transformateur3Stock implements IAcheteurContratCadreNotifie {
	protected HashMap<ExemplaireContratCadre, Integer> contratsAchat ;
	
	public Transformateur3AchatContratCadre() {
		this.contratsAchat = new HashMap<ExemplaireContratCadre, Integer>();
	}
	
	public HashMap<ExemplaireContratCadre, Integer> getContratsAchat() {
		return contratsAchat;
	}
	
	public ArrayList<ExemplaireContratCadre> getListeContrats(){
		ArrayList<ExemplaireContratCadre> listeContrats = new ArrayList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.getContratsAchat().keySet()) {
			listeContrats.add(contrat);
		}
		return listeContrats;
	}
	
	public ArrayList<IVendeurContratCadre> getVendeurs(int i){
		ArrayList<IVendeurContratCadre> listeVendeurs = new ArrayList<IVendeurContratCadre>();
		for (int index=i; index<this.getListeContrats().size(); index++) {
			ExemplaireContratCadre contrat = this.getListeContrats().get(index);
			listeVendeurs.add(contrat.getVendeur());
		}
		return listeVendeurs;
	}
	
	public ArrayList<Double> getListePrixNegocies(HashMap<ExemplaireContratCadre, Integer> contratsAchat){
		ArrayList<Double> listePrix = new ArrayList<Double>();
		for (ExemplaireContratCadre contrat : this.getContratsAchat().keySet()) {
			listePrix.add(contrat.getPrix());
		}
		return listePrix;
	}
	
	public Double getMinListe(ArrayList<Double> listePrix) {
		Double m = listePrix.get(0);
		for (int i=1; i<listePrix.size(); i++) {
			if (listePrix.get(i)<m) {
				m = listePrix.get(i);
			}
		}
		return m;
	}
	
	public IVendeurContratCadre getVendeurAvecCePrix (Double prix) {
		for (ExemplaireContratCadre contrat : this.getContratsAchat().keySet()) {
			if (contrat.getPrix() == prix) {
				return contrat.getVendeur();
			}
		}
		return null;
	}
	
	public int getEtape(ExemplaireContratCadre contrat) {
		return this.getContratsAchat().get(contrat);
	}
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		Feve feve = (Feve)produit;
		Echeancier dernierEcheancier = contrat.getEcheancier();
		int nbStep = dernierEcheancier.getNbEcheances();
		double quantiteTotale = dernierEcheancier.getQuantiteTotale();
		
		if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
			if ((nbStep>=8) && (nbStep<=18) && (quantiteTotale >=1E7)) {
				return dernierEcheancier;
			} else {
				return null;
			}
		}
		
		else if (feve == Feve.FEVE_MOYENNE) {
			if ((nbStep>=2) && (nbStep<=18) && (quantiteTotale>=1000)) {
				return dernierEcheancier;
			} else {
				return null;
			}
		}
		
		else {
			return null;
		}
	}
	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		Object produit = contrat.getProduit();
		Feve feve = (Feve)produit;
		Double dernierPrix = contrat.getPrix();
		if (feve == Feve.FEVE_HAUTE_BIO_EQUITABLE) {
			if (dernierPrix>this.prix_max_fèves_HBE.getValeur()) {
				return this.prix_max_fèves_HBE.getValeur();
			}else {
				return dernierPrix;
			}
		}
		
		else if (feve==Feve.FEVE_MOYENNE) {
			if (dernierPrix>this.prix_max_fèves_moyenne.getValeur()) {
				return this.prix_max_fèves_moyenne.getValeur();
			}else {
				return dernierPrix;
			}
		}
		
		else {
			return -1;
		}
	
	}
	
	
	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		this.ajouter((Feve)produit, quantite);
	}

	@Override
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (contrat.getAcheteur() == Filiere.LA_FILIERE.getActeur("EQ5")) {
			this.JournalAchatContratCadre.ajouter("nouveau contrat cadre entre " + contrat.getAcheteur() + " et "+contrat.getVendeur()+" d'une quantité " + contrat.getQuantiteTotale() + "kg de " + contrat.getProduit() + " pendant " + contrat.getEcheancier() + " pour " + contrat.getPrix() +" euros le kilo");
			this.contratsAchat.put(contrat, Filiere.LA_FILIERE.getEtape());
		}	
	}	
	
}

	