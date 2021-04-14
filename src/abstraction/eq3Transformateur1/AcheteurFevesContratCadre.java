package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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



// Paul GIRAUD
public class AcheteurFevesContratCadre extends VendeurProduitsContratCadre implements IAcheteurContratCadre {

	
	protected SuperviseurVentesContratCadre supCCadre;
	protected Object produit;
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;

	public AcheteurFevesContratCadre () {
		super();
		this.mesContratEnTantQuAcheteur=new LinkedList<ExemplaireContratCadre>();
	}
	
	public List<ExemplaireContratCadre> getContractsCadres() {
		return this.mesContratEnTantQuAcheteur;
	}
	
	public void ajoutContratEnTantQueAcheteur(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQuAcheteur.add(contrat);
		this.getStock().getActeur().journalAcheteur.ajouter("Nouveau contrat :"+contrat);
	}
	
	
	
	public void miseAJourContratAchat() {
		ArrayList<ExemplaireContratCadre> contratsObsoletes = new ArrayList<ExemplaireContratCadre>() ;
		for(ExemplaireContratCadre contrat : this.mesContratEnTantQuAcheteur) {
			if(contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQuAcheteur.removeAll(contratsObsoletes);
		for(ExemplaireContratCadre contratobselete : contratsObsoletes ) {
			this.getStock().getActeur().journalAcheteur.ajouter("Contrat terminé :"+contratobselete);
		}
	}

	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// Si l'echeancier est juste plus long de 2 step ou plus court de 2 on accepte et on s'occupera du stock pour assurer les ventes du prochains steps
		if( contrat.getEcheanciers().get(0).getNbEcheances() >= 3 || contrat.getEcheanciers().get(0).getNbEcheances()  - 2 <= contrat.getEcheancier().getNbEcheances()) {
			return contrat.getEcheancier();
			
		}
		return null;
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		//Si le prix est augmenté de 2% max on accepte
		if (contrat.getPrix() <= contrat.getListePrix().get(0)*1.02) {
			return contrat.getPrix();
		} else if (contrat.getPrix() <= contrat.getListePrix().get(0)*1.025 ){ // Si on nous propose une augmentation max de 2,5% on re-propose 2% sinon on refuse
			return contrat.getListePrix().get(0)*1.02;
		} else {
			return 0.0;

		}
	}
	public void nosDemandesCC() {
		
		this.miseAJourContratAchat();
		// Proposition d'un nouveau contrat à tous les vendeurs possibles	
		for  (Feve feve : this.nosFevesCC()) {
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				boolean t = true;
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(feve) && t) {
					Integer nomdreDeContratCadre = this.getContractsCadres().size();
					ExemplaireContratCadre contrat = supCCadre.demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), feve, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, this.getQuantiteStep(feve)), cryptogramme, false);
					if (contrat != null) {
						t = false;
						this.ajoutContratEnTantQueAcheteur(contrat);
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
	
	public double getQuantiteStep(Feve feve) {
		return 5.0;
	}
		

	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		if (produit instanceof Feve) {
			this.getStock().setStockFeve((Feve)produit, new Variable("quantité",this,quantite), new Variable("contrat numéro:"+"contrat.getNumero()",this,contrat.getPrix()));
			this.ecritureJournalStock("On réceptionne"+String.valueOf(quantite)+"kg de fèves "+((Feve)produit).name());
		}
	}

}
