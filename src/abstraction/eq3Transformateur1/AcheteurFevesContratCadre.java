package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;



// Jonathan
public class AcheteurFevesContratCadre extends VendeurProduitsContratCadre implements IAcheteurContratCadre {

	
	protected SuperviseurVentesContratCadre supCCadre;
	protected Object produit;

	public AcheteurFevesContratCadre () {
		super();
		this.supCCadre = null;
	}
	

	
	public SuperviseurVentesContratCadre setSupCCadre() {
		return this.supCCadre = (SuperviseurVentesContratCadre)(Filiere.LA_FILIERE.getActeur("Sup.CCadre"));
	}
	
	public SuperviseurVentesContratCadre getSupCCadre() {
		return supCCadre;
	}


	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// Si l'echeancier est juste  plus court de 2 step que l'échéancier demandé (10) on accepte et on s'occupera du stock pour assurer les ventes du prochains steps
		if (contrat.getEcheanciers().get(0).getNbEcheances() >= 3 || contrat.getEcheanciers().get(0).getNbEcheances()  - 2 <= contrat.getEcheancier().getNbEcheances()) {
			return contrat.getEcheancier();
			
		}
		return null;
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		if( contrat.getPrix()*contrat.getQuantiteTotale() >= this.getSolde() || contrat.getPrix() > 100) {
			return 0;
		} else {
			return contrat.getPrix();
		}
	}
	
	public void nosDemandesCC() {
		
		this.getStock().getFinancier().miseAJourContratAcheteur();
		// Proposition d'un nouveau contrat à tous les vendeurs possibles	
		Map<Feve, Double> quantiteaacheter = this.getStock().getFinancier().quantitefeveAAcheter();
		ArrayList<Feve> feveaacheter = this.getStock().getFinancier().feveAAcheter();
		for  (Feve feve : feveaacheter) {
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				boolean t = true;
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(feve) && t) {
					ExemplaireContratCadre contrat = supCCadre.demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), feve, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, quantiteaacheter.get(feve)), cryptogramme, false);
					if (contrat != null) {
						t = false;
						this.getStock().getFinancier().ajoutContratEnTantQueAcheteur(contrat);
					}
				}
			}
		}
	}
	
	public ArrayList<Feve> nosFevesCC() {
		ArrayList<Feve> list = new ArrayList<Feve>();
		list.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		list.add(Feve.FEVE_HAUTE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE_EQUITABLE);
		list.add(Feve.FEVE_MOYENNE);
		list.add(Feve.FEVE_BASSE);
		return list;
		
	}
	
		

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if (produit instanceof Feve) {
			this.getStock().setStockFeve((Feve)produit, new Variable("quantité",this,quantite), new Variable("contrat numéro:"+contrat.getNumero(),this,contrat.getPrix())); // pour avoir le prix du KG
			this.ecritureJournalStock("On réceptionne"+String.valueOf(quantite)+"kg de fèves "+((Feve)produit).name());
		}
	}

}
