package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

public abstract class VendeurFevesAO extends Producteur1Acteur implements IVendeurFevesAO  {
	protected List<VenteAO> historique_AO_F_M; //historique des appels d'offre pour les fèves de moyenne qualité non équitable.(0.0 : pas de vente, !=0 : vente à ce prix.)
	protected List<VenteAO> historique_AO_F_B; //historique des appels d'offre pour les fèves de basse qualité non équitable. idem
	protected HashMap<Feve,List<VenteAO>> historiques; //dictionnaire qui contient les historiques de ventes par AO.
	private static double PRIX_I_F_M=1.70;//prix de vente initial en €/kg des fèves moyenne qualité non équitable.
	private static double PRIX_I_F_B=1.52;//idem pour les fèves basse qualité non équitable.
	private static double PRIX_P_F_M=1.05;//prix plancher en €/kg des fèves moyenne qualité non équitable.
	private static double PRIX_P_F_B=0.91;//idem pour les fèves basse qualité non équitable.
	private static double PAS_DE_NEGO=0.1;//Pas de négociationen  €/kg
	private Producteur1Acteur a;
	
	/**
	 * @author Alb1x
	 * On crée un historique de vente par AO pour chaque fève que l'on vend par AO.
	 * On range ensuite les historiques dans un dictionnaire historiques.
	 */
	protected void init_historiques() {
		this.historique_AO_F_M  = new ArrayList<VenteAO>();
		this.historique_AO_F_B  = new ArrayList<VenteAO>();
		this.historiques = new HashMap<Feve,List<VenteAO>>();
		this.historiques.put(Feve.FEVE_MOYENNE, this.historique_AO_F_M);
		this.historiques.put(Feve.FEVE_BASSE, historique_AO_F_B);
	}
	
	/**
	 * @author Alb1x
	 * On rajoute une vente non conclue, cela sera changé si une vente est conclue au cours du step.
	 */
	protected void majHist_AO() {
		this.historique_AO_F_M.add(new VenteAO());
		this.historique_AO_F_B.add(new VenteAO());
	}
	/**
	 * @author Alb1x
	 * Par appel d'offres on vend les fèves non équitables
	 * càd les fèves moyenne qualité non équitable et les
	 * fèves basse qualité non équitable.
	 * On applique la stratégie mise en place dans le cahier des charges.
	 */
	public double proposerPrix(OffreAchatFeves oa) {
		if (Filiere.LA_FILIERE.getEtape()==0) {
			return 0;
		}
		double res = 0;
		Feve feve = oa.getFeve();
		double q = oa.getQuantiteKG();		
		if ((feve==Feve.FEVE_BASSE || feve==Feve.FEVE_MOYENNE)  //On ne veut que 2 types de fèves par AO.
				&& (this.getStocks().get(feve).getQuantite()>q)) { //Si on a la quantité de fève demandée alors on étudie l'oa.
			if(this.historiques.get(feve).get(Filiere.LA_FILIERE.getEtape()-1).etatVente()) {  //Si au step précédent la vente a été conclue alors on repropose le prix initial.
				res = feve==Feve.FEVE_MOYENNE ? PRIX_I_F_M*q : PRIX_I_F_B*q;
			}
			else {
				double nouveau_prix = this.historiques.get(feve).get(Filiere.LA_FILIERE.getEtape()-1).prixVente()-PAS_DE_NEGO*q;
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
		this.getJournal(2).ajouter("Vente de " + proposition.getQuantiteKg() + "kg de " + proposition.getFeve() );
	}

}
