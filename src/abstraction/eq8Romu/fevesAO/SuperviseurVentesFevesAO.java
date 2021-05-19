package abstraction.eq8Romu.fevesAO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;
import abstraction.fourni.Banque;
import abstraction.fourni.Filiere;
/** @author R. DEBRUYNE 

 * Variante de la vente a la criee (un exemple : https://www.maisondelamer.org/ressources/la-vente-en-criee/ )
 * dans laquelle ce n'est pas necessairement la meilleur offre qui l'emporte : c'est
 * le vendeur qui en connaissance de toutes les offres decide quelle offre l'emporte.
 *
 */
public class SuperviseurVentesFevesAO implements IActeur {
	private static int NB_INSTANCES = 0; // Afin de verifier qu'il y a au plus UN superviseur des ventes de feves a la criee

	private Variable nbOffresMaxParEtape;

	private Journal journal;
	
	private List<List<PropositionVenteFevesAO>> historiqueTransactions;


	public SuperviseurVentesFevesAO() {
		NB_INSTANCES++;
		if (NB_INSTANCES>1) {
			throw new Error("Tentative de creer une seconde instance du superviseur des appels d'offre de feves");
		}
		this.journal = new Journal("Ventes de feves (par AO)", this);
		this.nbOffresMaxParEtape = new Variable(this.getNom()+" max offres par etape", this, 1.0, 200.0, 100.0);
		this.historiqueTransactions=new LinkedList<List<PropositionVenteFevesAO>>();
	}

	public String getNom() {
		return "Sup.Feves.A.O.";
	}

	public String getDescription() {
		return "Superviseur des appels d'offre de feves";
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public void initialiser() {
	}

	public void setCryptogramme(Integer crypto) {
	}

	public void next() {
		// On recree les listes de vendeurs et acheteurs a chaque next car :
		// (1) dans la simulation la creation de nouveaux acteurs en cours
		// de simulation est autorisee et
		// (2) certains acteurs ont pu faire faillite
		List<PropositionVenteFevesAO> transactions = new ArrayList<PropositionVenteFevesAO>(); // les propositions qui ont abouti a une transaction durant l'etape courante
		List<IVendeurFevesAO> vendeurs;
		List<IAcheteurFevesAO> acheteurs;
		Banque laBanque = Filiere.LA_FILIERE.getBanque();
		vendeurs = new ArrayList<IVendeurFevesAO>();
		acheteurs = new ArrayList<IAcheteurFevesAO>();
		for (IActeur ac : Filiere.LA_FILIERE.getActeurs()) {
			if (ac instanceof IVendeurFevesAO && !laBanque.aFaitFaillite(ac)) {
				vendeurs.add((IVendeurFevesAO)ac);
			}
			if (ac instanceof IAcheteurFevesAO && !laBanque.aFaitFaillite(ac)) {
				acheteurs.add((IAcheteurFevesAO)ac);
			}
		}
		Collections.shuffle(vendeurs);
		Collections.shuffle(acheteurs);

		int acheteurCourant = -1;

		this.journal.ajouter(Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"-------------------- Echange a l'etape "+Filiere.LA_FILIERE.getEtape()+" --------------------"));
		if (vendeurs.size()==0) {
			this.journal.ajouter(Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"Aucun vendeur --> Aucune transaction"));
		} else if (acheteurs.size()==0) {
			this.journal.ajouter(Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"Aucun acheteur --> Aucune transaction"));
		}
		String texteVendeurs="Vendeurs : ";
		for (IVendeurFevesAO v : vendeurs) {
			texteVendeurs+=Journal.texteColore(v,  v.getNom());
		}
		this.journal.ajouter(texteVendeurs);
		String texteAcheteurs="Acheteurs : ";
		for (IAcheteurFevesAO a : acheteurs) {
			texteAcheteurs+=Journal.texteColore(a,  a.getNom());
		}
		this.journal.ajouter(texteAcheteurs);

		Map<IAcheteurFevesAO,Integer> nbOffres=new HashMap<IAcheteurFevesAO,Integer>();
		for (IAcheteurFevesAO a : acheteurs) {
			nbOffres.put(a, 0);
		}

		while (vendeurs.size()>0 
				&& acheteurs.size()>0 ) { // Il faut au moins un vendeur et un acheteur pour qu'il y ait des echanges
			acheteurCourant=acheteurCourant+1<acheteurs.size() ? acheteurCourant+1 : 0 ;
			OffreAchatFeves offreAchat =  acheteurs.get(acheteurCourant).getOffreAchat();
			if (offreAchat==null) { // L'acheteur courant n'a pas/plus besoin d'acheter des feves via des appels d'offre a cette etape
				this.journal.ajouter(Journal.texteColore(acheteurs.get(acheteurCourant), acheteurs.get(acheteurCourant).getNom()+" ne fait pas d'appel d'offre --> retrait de la liste des acheteurs de cette etape"));
				acheteurs.remove(acheteurCourant);
				acheteurCourant--;
			} else {
				nbOffres.put(acheteurs.get(acheteurCourant), 1+nbOffres.get(acheteurs.get(acheteurCourant)));
				if (nbOffres.get(acheteurs.get(acheteurCourant))==this.nbOffresMaxParEtape.getValeur()) {
					this.journal.ajouter(Journal.texteColore(acheteurs.get(acheteurCourant), acheteurs.get(acheteurCourant).getNom()+" a atteint le nombre maximum d'offres possibles par etape ("+this.nbOffresMaxParEtape.getValeur()+") --> retrait de la liste des acheteurs de cette etape"));
					acheteurs.remove(acheteurCourant);
					continue;
				}
				this.journal.ajouter(Journal.texteColore(acheteurs.get(acheteurCourant), acheteurs.get(acheteurCourant).getNom()+" fait l'offre d'achat "+offreAchat));
				List<PropositionVenteFevesAO> propositions = new ArrayList<PropositionVenteFevesAO>();
				for (IVendeurFevesAO vendeur : vendeurs) {
					double prix = vendeur.proposerPrix(offreAchat);
					this.journal.ajouter("&nbsp;&nbsp;"+Journal.texteColore(vendeur, vendeur.getNom()+" fait une proposition de prix de "+Journal.doubleSur(prix, 4)));
					if (prix>0.0) {
						propositions.add(new PropositionVenteFevesAO(offreAchat, vendeur, prix));
					}
				}
				if (propositions.size()==0) {
					this.journal.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;"+Journal.texteColore(Color.RED, Color.WHITE,"   Aucune proposition de vente"));
					acheteurs.get(acheteurCourant).notifierAucuneProposition(offreAchat);
				} else {
					PropositionVenteFevesAO choisie = acheteurs.get(acheteurCourant).choisirPropositionVenteAOFeves(new ArrayList<PropositionVenteFevesAO>(propositions));
					if (choisie==null) {
						this.journal.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;"+Journal.texteColore(Color.RED, Color.WHITE,"   L'acheteur ne retient aucune proposition"));
						for (PropositionVenteFevesAO p : propositions) {
							p.getVendeur().notifierPropositionRefusee(p);
						}
					} else if (!propositions.contains(choisie)) {
						throw new IllegalStateException("L'acheteur de feves "+acheteurs.get(acheteurCourant).getNom()+" a une implementation de choisirPropositionVenteAOFeves(propositions) qui retourne une proposition ne figurant pas dans propositions");
					} else {
						this.journal.ajouter(Journal.texteColore(acheteurs.get(acheteurCourant)," L'acheteur choisit la proposition de "+ choisie.getVendeur().getNom()));
						// on notifie les autres vendeurs que leur proposition n'a pas ete retenue
						for (PropositionVenteFevesAO p : propositions) {
							if (p!=choisie) {
								p.getVendeur().notifierPropositionRefusee(p);
							}
						}
						// On procede si possible au paiement
						Integer crypto = acheteurs.get(acheteurCourant).getCryptogramme(this);
						this.journal.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;"+Journal.texteColore(Color.WHITE, Color.BLACK,"Virement de "+choisie.getMontant()+" de "+choisie.getAcheteur().getNom()+" vers "+choisie.getVendeur().getNom()));
						boolean virementEffectue = choisie.getMontant()>0 ? Filiere.LA_FILIERE.getBanque().virer(acheteurs.get(acheteurCourant), crypto, choisie.getVendeur(), choisie.getMontant()):true;
						if (virementEffectue) {
							this.journal.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;"+Journal.texteColore(Color.green, Color.black, "-------------------- Notification de vente --------------------"));
							choisie.getVendeur().notifierVente(choisie);
							acheteurs.get(acheteurCourant).notifierVente(choisie);
							transactions.add(choisie );
						} else {
							this.journal.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;"+Journal.texteColore(Color.RED, Color.WHITE,"- Le virement n'a pas pu avoir lieu : annulation de la vente et retrait de l'acheteur \""+acheteurs.get(acheteurCourant).getNom()+"\" de la liste des acheteurs"));
							acheteurs.remove(acheteurCourant);
							acheteurCourant--;
						}
					}
				}
			}
		}
		this.historiqueTransactions.add(transactions);
		for (int etape=0; etape<this.historiqueTransactions.size(); etape++) {
			transactions=this.historiqueTransactions.get(etape);
			if (transactions.size()==0) {
				this.journal.ajouter(Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"Aucune transaction effectuee a l'etape "+etape+" --------------------"));
			} else {
				this.journal.ajouter(Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"Recapitulatif des transaction effectuees a l'etape "+etape+" --------------------"));
				for (PropositionVenteFevesAO p : transactions) {
					this.journal.ajouter(
							Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK,"&nbsp;&nbsp;"+Journal.doubleSur(p.getQuantiteKg(), 2)+"Kgs de "+p.getFeve().name()
									+" au prix de "+Journal.doubleSur(p.getPrixKG(), 4)+" de ")
							+Journal.texteColore(p.getVendeur(), p.getVendeur().getNom())
							+Journal.texteColore(Color.LIGHT_GRAY, Color.BLACK," a ")
							+Journal.texteColore(p.getAcheteur(), p.getAcheteur().getNom()));
				}
			}
		}
	}

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.nbOffresMaxParEtape);
		return res;
	}

	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(this.journal);
		return res;
	}

	public void notificationFaillite(IActeur acteur) {
	}

	public void notificationOperationBancaire(double montant) {
	}
}
