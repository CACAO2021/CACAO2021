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
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Variable;


// Paul GIRAUD
public class AcheteurFevesContratCadre extends Transformateur1Acteur implements IAcheteurContratCadre {

	
	protected SuperviseurVentesContratCadre supCCadre;

	protected Variable stock;
	protected Object produit;
	protected List<ExemplaireContratCadre> mesContratEnTantQuAcheteur;

	public AcheteurFevesContratCadre (Object produit) {
		super();
		this.mesContratEnTantQuAcheteur=new LinkedList<ExemplaireContratCadre>();
	}

	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		// Si l'echeancier est juste plus lons de 2 step ou plus court de 2 on accepte et on s'occupera du stock pour assurer les ventes du prochains steps
		if( contrat.getEcheanciers().get(0).getNbEcheances() >= 3 && contrat.getEcheanciers().get(0).getNbEcheances()  - 2 <= contrat.getEcheancier().getNbEcheances()) {
			return contrat.getEcheancier();
			
		}
		return null;
	}

	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		//Si le prix est augmenté de 2% max on accepete
		if (contrat.getPrix() <= contrat.getListePrix().get(0)*1.02) {
			return contrat.getPrix();
		} else if (contrat.getPrix() <= contrat.getListePrix().get(0)*1.025 ){ // Si on nous propose une augmentation max de 2,5% on re-propose 2% sinon on refuse
			return contrat.getListePrix().get(0)*1.02;
		} else {
			return 0.0;

		}
	}
	public void next() {
		// On enleve les contrats obsolete (nous pourrions vouloir les conserver pour "archive"...)
		List<ExemplaireContratCadre> contratsObsoletes=new LinkedList<ExemplaireContratCadre>();
		for (ExemplaireContratCadre contrat : this.mesContratEnTantQuAcheteur) {
			if (contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQuAcheteur.removeAll(contratsObsoletes);
		
		// Proposition d'un nouveau contrat a tous les vendeurs possibles
	
		for  (Feve feve : this.nosFevesCC()) {
			for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
				if (acteur!=this && acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(feve)) {
					supCCadre.demande((IAcheteurContratCadre)this, ((IVendeurContratCadre)acteur), feve, new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, this.getQuantiteStep(feve)), cryptogramme, false);
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
		stock.ajouter(this,  quantite);
	}

}
