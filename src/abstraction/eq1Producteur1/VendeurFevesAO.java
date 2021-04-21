package abstraction.eq1Producteur1;

import java.util.List;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;

public abstract class VendeurFevesAO extends Producteur1Acteur implements IVendeurFevesAO  {
	private static double PRIX_I_F_M=1.70;//prix de vente initial en €/kg des fèves moyenne qualité non équitable.
	private static double PRIX_I_F_B=1.52;//idem pour les fèves basse qualité non équitable.
	private static double PRIX_P_F_M=1.05;//prix plancher en €/kg des fèves moyenne qualité non équitable.
	private static double PRIX_P_F_B=0.91;//idem pour les fèves basse qualité non équitable.
	private static double PAS_DE_NEGO=0.1;//Pas de négociationen  €/kg
	private Producteur1Acteur a;
	
	/**
	 * @author Alb1x
	 * Par appel d'offres on vend les fèves non équitables
	 * càd les fèves moyenne qualité non équitable et les
	 * fèves basse qualité non équitable.
	 * On applique la stratégie mise en place dans le cahier des charges.
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		double res = 0;
		Feve feve = oa.getFeve();
		double q = oa.getQuantiteKG();		
		if ((feve==Feve.FEVE_BASSE || feve==Feve.FEVE_MOYENNE)  //On ne veut que 2 types de fèves par AO.
				&& (this.getStocks().get(feve).getQuantite()>q)) { //Si on a la quantité de fève demandée alors on étudie l'oa.
			if(this.historiques.get(feve).get(step_actuel-2).etatVente()) {  //Si au step précédent la vente a été conclue alors on repropose le prix initial.
				res = feve==Feve.FEVE_MOYENNE ? PRIX_I_F_M*q : PRIX_I_F_B*q;
			}
			else {
				double nouveau_prix = this.historiques.get(feve).get(step_actuel-2).prixVente()-PAS_DE_NEGO*q;
				if(nouveau_prix>(feve==Feve.FEVE_MOYENNE ? PRIX_P_F_M : PRIX_P_F_B)) {// Si le précedent prix moins le pas de négo est toujours supérieur au pris palier alors on propose ce prix.
					res = nouveau_prix;
				}
				else {
					res = feve==Feve.FEVE_MOYENNE ? PRIX_P_F_M : PRIX_P_F_B; //sinon, on propose le prix palier.
				}
			}
		}
		return res;
	}

	/**
	 * @author Alb1x
	 * Si on refuse notre proposition alors on l'enregistre dans notre historique des propositions
	 * pour pouvoir s'en servir pour les prochaines ventes.
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		List<VenteAO> hist=this.historiques.get(proposition.getFeve()); 
		hist.get(hist.size()-1).set_etatVente(false);  //si la vente est refusée alors on la rentre en mémoire pour les prochaines négociations.
		hist.get(hist.size()-1).set_prixVente(proposition.getMontant());
	}

	/**
	 * @author Alb1x
	 * @author lebra seulement pour les journaux
	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		List<VenteAO> hist=this.historiques.get(proposition.getFeve()); 
		hist.get(hist.size()-1).set_etatVente(true); //si on conclue une vente alors on la rentre dans l'historique des ventes par AO.
		hist.get(hist.size()-1).set_prixVente(proposition.getMontant());

		this.getStocks().get(proposition.getFeve()).removeQuantite(proposition.getQuantiteKg());//on retire les fèves vendues de notre stock.
		this.getJournaux().getJournal(2).ajouter("Vente de " + proposition.getQuantiteKg() + "kg de " + proposition.getFeve() );
	}

}
