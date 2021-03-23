package abstraction.eq8Romu.fevesAO;

import java.util.List;

import abstraction.fourni.IActeur;

/** @author R. DEBRUYNE  */
public interface IAcheteurFevesAO extends IActeur {
	
	/**
	 * Methode appelee par le superviseur des appels d'offre de feves
	 * afin de connaitre l'appel d'offre que le vendeur souhaite effectuer
	 * @return Retourne une instance de OffreAchatFeves si l'acheteur souhaite faire
	 * une offre (null si il ne souhaite pas/plus faire d'offre d'achat)
	 */
	public OffreAchatFeves getOffreAchat();
	
	/**
	 * Methode appelee par le superviseur des ventes de feves par appels d'offre lorsque qu'aucune proposition
	 * de vente n'a ete formulee par les vendeurs pour l'offre d'achat oa.
	 * @param oa, l'offre d'achat que this avait faite et pour laquelle aucun vendeur n'a fait de proposition.
	 * Remarque : cette methode a pour but de notifier l'acheteur que les vendeurs n'ont pas fait d'offres.
	 * Le code de cette methode peut eventuellement etre vide si l'acheteur ne 
	 * souhaite pas tirer parti de cette information
	 */
	public void notifierAucuneProposition(OffreAchatFeves oa);

	/**
	 * Methode appelee par le superviseur des ventes de feves par appels d'offre afin de connaitre l'offre
	 * choisie par l'acheteur parmi celles faites par les vendeurs
	 * @param propositions, propositions!=null, proposition.size()>=1, les propositions de ventes faites par les vendeurs suite a l'appel d'offre lance par this
	 * @return la proposition choisie par l'acheteur (null si l'acheteur ne souhaite retenir aucune offre).
	 */
	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions);
	
	/**
	 * Methode invoquee par le SuperviseurAOFeves afin qu'il obtienne le cryptogramme de l'acheteur
	 * (necessaire pour que le transfert d'argent ait lieu).
	 * Le parametre fourni permet de s'assurer que c'est bien le superviseur qui demande 
	 * le cryptogramme (Verifier que le parametre n'est pas null suffit a en etre assure car 
	 * aucun acteur n'a de reference vers l'unique superviseur) 
	 */
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur);
	
	
	/**
	 * Methode appelee par le superviseur des ventes de feves par appel d'offres
	 * suite au paiement par l'acheteur (this) de la proposition.
	 * La methode doit a minima ajouter le lot de feve au stock de feves de this. 
	 * @param proposition, la proposition qui vient d'etre honoree par this (et livree par le vendeur)
	 */
	public void notifierVente(PropositionVenteFevesAO proposition);
}
