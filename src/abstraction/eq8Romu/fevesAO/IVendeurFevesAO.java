package abstraction.eq8Romu.fevesAO;

import abstraction.fourni.IActeur;

/** @author R. DEBRUYNE  */
public interface IVendeurFevesAO extends IActeur {

	
	/**
	 * @param oa, oa!=null
	 * @return Retourne le prix de vente propose pour la quantite de feve specifiee dans oa
	 * (retourne 0.0 si le vendeur ne souhaite pas/ne peut pas faire d'offre pour oa).
	 */
	public double proposerPrix(OffreAchatFeves oa);
	
	/**
	 * Methode appelee par le superviseur des ventes de feves par appels d'offree
	 * lorsque l'offre de vente proposition emise par this n'a pas ete retenue.
	 * @param proposition, proposition!=null, la proposition que this avait faite et qui a ete refusee
	 * Remarque : Cette methode peut avoir un corps vide si le vendeur ne souhaite pas tirer parti de cette information.
	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition);
	
	/**
	 * Methode appelee par le superviseur des ventes de feves par appel d'offres
	 * suite au paiement par l'acheteur de la proposition.
	 * La methode doit donc a minima retirer du stock le lot de feve qui vient d'etre achete 
	 * @param proposition, la proposition que le vendeur vient d'honorer en payant la somme due.
	 */
	public void notifierVente(PropositionVenteFevesAO proposition);
}
