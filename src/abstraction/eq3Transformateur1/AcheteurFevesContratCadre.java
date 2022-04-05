package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadre;
import abstraction.eq8Romu.contratsCadres.IAcheteurContratCadreNotifie;
import abstraction.eq8Romu.contratsCadres.IVendeurContratCadre;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;



// Jonathan (À part méthode ou il y a un autre prénom)
public class AcheteurFevesContratCadre extends VendeurProduitsContratCadre implements IAcheteurContratCadreNotifie {

	
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

		return contrat.getEcheancier(); 
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {

		if( contrat.getPrix()*contrat.getQuantiteTotale() >= this.getSolde()) {
			return 0;
		} else {

			return contrat.getPrix();
		}
	}
	
	public void nosDemandesCC() {
		
		this.getStock().getFinancier().miseAJourContratAcheteur();
		// Proposition d'un nouveau contrat à tous les vendeurs possibles	
		Map<Feve, Triple> quantiteaacheter = this.getStock().getFinancier().getAchats();

		for  (Feve feve : this.getStock().nosFevesCC()) {

			Triple triple = quantiteaacheter.get(feve);
			int nbEcheances = triple.get2();
			double quant = triple.get1();
			boolean achat = triple.get3();
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				int i = 0;
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(feve) && i == 0 && quant >1000 && achat) {
					ExemplaireContratCadre contrat = supCCadre.demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), feve, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, nbEcheances, quant), cryptogramme, false);
					if (contrat != null) {
						i = 1;
					}
				}
				
			}
		}
	}
		

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if (produit instanceof Feve) {
			this.getStock().setStockFeve((Feve)produit, new Variable("quantité",this,quantite), new Variable("contrat numéro:"+contrat.getNumero(),this,contrat.getPrix())); // pour avoir le prix du KG
			this.ecritureJournalStock("On réceptionne"+String.valueOf(quantite)+"kg de fèves "+((Feve)produit).name());
		}
	}
	
	
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat) {
		if (contrat.getAcheteur().getNom() == "Eticao") {

			this.getStock().getFinancier().setMesContratEnTantQueAcheteur(contrat);

			this.getStock().getFinancier().miseAJourAchat(contrat);
			this.nbContratAchat +=1;
			this.ECHEANCIERMOYACHAT += contrat.getEcheancier().getNbEcheances()/this.nbContratAchat;
			 
			
		} else if (contrat.getVendeur().getNom() == "Eticao") {
			Chocolat chocolat = null; 
			if (contrat.getProduit() instanceof Chocolat) {
				chocolat = (Chocolat)contrat.getProduit();
			} else if(contrat.getProduit() instanceof ChocolatDeMarque) {
				chocolat = ((ChocolatDeMarque)contrat.getProduit()).getChocolat();
				}
			this.getStock().getFinancier().setMesContratEnTantQueVendeur(contrat);
			this.getStock().getFinancier().miseAJourVente(contrat);
			double margeAVerser = this.getStock().getMarge(this.getStock().equivalentFeve(chocolat));
			double argentPourLesAsso = contrat.getPrix()*(margeAVerser-1.4)*contrat.getQuantiteTotale();
			if (argentPourLesAsso != Double.POSITIVE_INFINITY && argentPourLesAsso != Double.NEGATIVE_INFINITY) {
				this.SUBVENTION += argentPourLesAsso;
			}
			this.BENEFICE += contrat.getPrix()*contrat.getQuantiteTotale();
			this.nbContratVente += 1;
			this.ECHEANCIERMOYVENTE += contrat.getEcheancier().getNbEcheances()/this.nbContratVente;
			
			Filiere.LA_FILIERE.getBanque().virer(this, this.cryptogramme, Filiere.LA_FILIERE.getBanque(), argentPourLesAsso);
			this.journalTresorie.ajouter(Color.GREEN, Color.BLACK, "Virement à la banque pour les associations d'un montant de " + String.valueOf(argentPourLesAsso));
			
			
			if (chocolat.isBio()) {
				this.quantiteBio += contrat.getQuantiteTotale();
			} else if ( chocolat.isEquitable()){
				this.quantiteEquitable += contrat.getQuantiteTotale();
			} else {
				this.quantiteClassique += contrat.getQuantiteTotale();
			}
		}
	}
}
