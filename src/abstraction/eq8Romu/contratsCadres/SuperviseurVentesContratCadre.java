package abstraction.eq8Romu.contratsCadres;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;
import abstraction.fourni.Banque;
import abstraction.fourni.Filiere;

public class SuperviseurVentesContratCadre implements IActeur {
	public static final int MAX_PRIX_NEGO = 14 ; // Les negociations sur le prix s'arretent apres au plus MAX_PRIX_NEGO propositions de prix
	public static final double QUANTITE_MIN_ECHEANCIER = 1000.0; // Il ne peut pas etre propose de contrat avec un echeancier de moins de QUANTITE_MIN_ECHEANCIER Kg
	public static int NB_SUPRVISEURS_CONTRAT_CADRE = 0;
	private int numero;
	private Journal journal, journalEcheances;
	private List<ContratCadre> contratsEnCours;
	private List<ContratCadre> contratsTermines;
	private Integer crypto;
	//	private long cryptogramme;


	public static final int MAX_MEME_VENDEUR_PAR_STEP = 15; 
	// Au cours d'un meme step un acheteur peut negocier au plus MAX_MEME_VENDEUR_PAR_STEP
	// fois avec le meme vendeur. Si l'acheteur demande a negocier avec le vendeur v alors qu'il a 
	// deja negocie MAX_MEME_VENDEUR_PAR_STEP fois avec v durant le step courant alors l'acheteur
	// ne pourra plus negocier durant le step.

	public SuperviseurVentesContratCadre() {
		NB_SUPRVISEURS_CONTRAT_CADRE++;
		this.numero = NB_SUPRVISEURS_CONTRAT_CADRE;
		this.journal = new Journal("Journal "+this.getNom(), this);
		this.journalEcheances = new Journal("Journal echeances "+this.getNom(), this);
		this.contratsEnCours= new ArrayList<ContratCadre>();
		this.contratsTermines= new ArrayList<ContratCadre>();
	}
	public String getNom() {
		return "Sup."+(this.numero>1?this.numero+"":"")+"CCadre";
	}

	public void initialiser() {
	}

	public List<IVendeurContratCadre> getVendeurs(Object produit) {
		List<IVendeurContratCadre> vendeurs = new LinkedList<IVendeurContratCadre>();
		List<IActeur> acteurs = Filiere.LA_FILIERE.getActeurs();
		for (IActeur acteur : acteurs) {
			if (acteur instanceof IVendeurContratCadre && ((IVendeurContratCadre)acteur).vend(produit)) {
				vendeurs.add(((IVendeurContratCadre)acteur));
			}
		}
		return vendeurs;
	}
	
	public ExemplaireContratCadre demande(IAcheteurContratCadre acheteur, IVendeurContratCadre vendeur, Object produit, Echeancier echeancier, int cryptogramme, boolean tg) {
		if (acheteur==null) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec null pour acheteur");
		}
		if (vendeur==null) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec null pour vendeur");
		}
		if (produit==null) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec null pour produit");
		}
		if (echeancier==null) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec null pour echeancier");
		}
		if (echeancier.getQuantiteTotale()<QUANTITE_MIN_ECHEANCIER) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec un echeancier d'un volume total de moins de "+QUANTITE_MIN_ECHEANCIER+" kg");
		}
		if (acheteur==vendeur) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre avec vendeur==acheteur. On ne peut pas faire un contrat cadre avec soi meme");
		}
		if (!Filiere.LA_FILIERE.getBanque().verifier(acheteur, cryptogramme)) {
			throw new IllegalArgumentException(" appel de demande(...) de SuperViseurVentesContratCadre par l'acheteur "+acheteur.getNom()+" avec un cryptogramme qui n'est pas le sien");
		}

		ContratCadre contrat = new ContratCadre(acheteur, vendeur, produit, echeancier, cryptogramme, tg);
		int maxNego = 5 + (int)(Math.random()*11); // Le nombre maximum de contrepropositions est compris dans [5, 15]

		// NEGOCIATIONS SUR L'ECHEANCIER
		Echeancier contrePropositionV, contrePropositionA;
		journal.ajouter(Journal.texteColore(acheteur, acheteur.getNom())+" lance le contrat #"+contrat.getNumero()+" de "+contrat.getQuantiteTotale()+" kg de "+contrat.getProduit()+" a "+Journal.texteColore(vendeur, vendeur.getNom()));
		int numNego=0;
		do { 
			numNego++;
			contrePropositionV= vendeur.contrePropositionDuVendeur(new ExemplaireContratCadre(contrat));
			if (contrePropositionV==null) {
				journal.ajouter("   "+Journal.texteColore(vendeur, vendeur.getNom()+" retourne null pour echeancier : arret des negociations"));
				journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
				return null;// arret des negociations
			} 
			contrat.ajouterEcheancier(contrePropositionV);
			if (!contrat.accordSurEcheancier()) {
				journal.ajouter("   "+Journal.texteColore(vendeur, vendeur.getNom())+" propose un echeancier different de "+Journal.doubleSur(contrat.getQuantiteTotale(),4)+" Kg "+contrat.getEcheancier());
				contrePropositionA=acheteur.contrePropositionDeLAcheteur(new ExemplaireContratCadre(contrat));
				if (contrePropositionA==null) {
					journal.ajouter("   "+Journal.texteColore(acheteur, acheteur.getNom()+" retourne null pour echeancier : arret des negociations"));
					journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
					return null;// arret des negociations
				}
				contrat.ajouterEcheancier(contrePropositionA);
				if (!contrat.accordSurEcheancier()) {
					journal.ajouter("   "+Journal.texteColore(acheteur, acheteur.getNom())+" propose un echeancier different de "+Journal.doubleSur(contrat.getQuantiteTotale(),4)+" Kg "+contrat.getEcheancier());
				}
			}
		} while (numNego<maxNego && !contrat.accordSurEcheancier());
		if (!contrat.accordSurEcheancier()) {
			journal.ajouter("   aucun accord sur l'echeancier n'a pu etre trouve en moins de "+maxNego+" etapes : arret des negociations");
			journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
			return null;
		} else {
			journal.ajouter("   accord sur l'echeancier : "+contrat.getEcheancier());
		}

		// NEGOCIATIONS SUR LE PRIX
		double propositionV = vendeur.propositionPrix(new ExemplaireContratCadre(contrat));
		journal.ajouter("   "+Journal.texteColore(vendeur, vendeur.getNom())+" propose un prix de "+Journal.doubleSur(propositionV,4));
		if (propositionV<=0.0) {
			journal.ajouter("   arret des negociations");
			journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
			return null;// arret des negociations
		}
		contrat.ajouterPrix(propositionV);
		double propositionA;
		numNego=0;
		do {
			propositionA = acheteur.contrePropositionPrixAcheteur(new ExemplaireContratCadre(contrat));
			journal.ajouter("   "+Journal.texteColore(acheteur, acheteur.getNom())+" propose un prix de "+Journal.doubleSur(propositionA,4));
			if (propositionA<=0.0) {
				journal.ajouter("   arret des negociations");
				journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
				return null;// arret des negociations
			}
			contrat.ajouterPrix(propositionA);
			if (!contrat.accordSurPrix()) {
				propositionV = vendeur.contrePropositionPrixVendeur(new ExemplaireContratCadre(contrat));
				journal.ajouter("   "+Journal.texteColore(vendeur, vendeur.getNom())+" propose un prix de "+Journal.doubleSur(propositionV,4));
				if (propositionV<=0.0) {
					journal.ajouter("   arret des negociations");
					journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
					return null;// arret des negociations
				}
				contrat.ajouterPrix(propositionV);
			}
		} while (numNego<maxNego && !contrat.accordSurPrix());

		if (!contrat.accordSurPrix()) {
			journal.ajouter("   aucun accord sur le prix n'a pu etre trouve en moins de "+maxNego+" etapes : arret des negociations");
			journal.ajouter("contrat #"+contrat.getNumero()+Journal.texteColore(Color.RED,  Color.white, " ANNULE "));
			return null;
		}
		contrat.signer();// accord : on realise les previsionnels de livraison et paiement
		vendeur.notificationNouveauContratCadre(new ExemplaireContratCadre(contrat));
		this.contratsEnCours.add(contrat);
		journal.ajouter(Journal.texteColore(Color.GREEN, Color.BLACK,"   contrat #"+contrat.getNumero()+" entre ")+Journal.texteColore(vendeur, vendeur.getNom())+" et "+Journal.texteColore(acheteur, acheteur.getNom())+" sur "+Journal.doubleSur(contrat.getQuantiteTotale(),4)+" de "+contrat.getProduit()+" etales sur "+contrat.getEcheancier());
		return new ExemplaireContratCadre(contrat);
	}

	public void recapitulerContratsEnCours() {
		this.journal.ajouter("Step "+Filiere.LA_FILIERE.getEtape()+" Contrats en cours : ");
		for (ContratCadre cc : this.contratsEnCours) {
			this.journal.ajouter(cc.oneLineHtml());
		}		
	}

	public void gererLesEcheancesDesContratsEnCours() {
		HashMap<Object, Double> echangesAuStep = new HashMap<Object, Double>();
		this.journalEcheances.ajouter("Step "+Filiere.LA_FILIERE.getEtape()+" GESTION DES ECHEANCES DES CONTRATS EN COURS ========");
		for (ContratCadre cc : this.contratsEnCours) {
			Banque banque = Filiere.LA_FILIERE.getBanque();
			if ((banque.aFaitFaillite(cc.getVendeur()) || banque.aFaitFaillite(cc.getAcheteur()) || (cc.getMontantRestantARegler()==0.0 && cc.getQuantiteRestantALivrer()==0.0)) ){
				this.journalEcheances.ajouter(Color.white,Color.RED, "gestion d'un contrat qui aurait du etre archive");	
			} else {	
				this.journalEcheances.ajouter("- contrat :"+cc.oneLineHtml());
				double aLivrer = cc.getQuantiteALivrerAuStep();
				if (aLivrer>0.0) {
					if (cc.getEcheancier().getStepFin()<Filiere.LA_FILIERE.getEtape()-24) {
						this.journal.ajouter(Color.RED, Color.white,""+cc.getVendeur()+" fait faillite car il n'a pas honore ses echeances 24 etapes apres la fin du contrat");
						banque.faireFaillite(cc.getVendeur());
					}					
					IVendeurContratCadre vendeur = cc.getVendeur();
					double effectivementLivre = vendeur.livrer(cc.getProduit(), aLivrer, new ExemplaireContratCadre(cc));
					this.journalEcheances.ajouter(Color.WHITE, (aLivrer==effectivementLivre?Color.BLACK:Color.RED),"  a livrer="+String.format("%.3f",aLivrer)+"  livre="+String.format("%.3f",effectivementLivre));
					if (aLivrer!=effectivementLivre) {
						this.journal.ajouter("Defaut de livraison de "+cc.getVendeur()+" :  a livrer="+String.format("%.3f",aLivrer)+"  livre="+String.format("%.3f",effectivementLivre));
					}
					if (effectivementLivre>0.0) {
						IAcheteurContratCadre acheteur = cc.getAcheteur();
						acheteur.receptionner(cc.getProduit(), effectivementLivre, new ExemplaireContratCadre(cc));
						double dejaEchange = (echangesAuStep.keySet().contains(cc.getProduit())) ? echangesAuStep.get(cc.getProduit()) : 0.0;
						echangesAuStep.put(cc.getProduit(), dejaEchange+effectivementLivre);
						cc.livrer(effectivementLivre);
					} else if (effectivementLivre<0.0) {
						throw new Error(" La methode livrer() du vendeur "+vendeur.getNom()+" retourne un negatif");
					}
				} else {
					this.journalEcheances.ajouter("- rien a livrer a cette etape");
				}
				double aPayer = cc.getPaiementAEffectuerAuStep();
				if (aPayer>0.0) {
					if (cc.getEcheancier().getStepFin()<Filiere.LA_FILIERE.getEtape()-24) {
						this.journal.ajouter(Color.RED, Color.white,""+cc.getAcheteur()+" fait faillite car il n'a pas honore ses echeances 24 etapes apres la fin du contrat");
						banque.faireFaillite(cc.getAcheteur());
					}
					IAcheteurContratCadre acheteur = cc.getAcheteur();
					//Banque banque = Filiere.LA_FILIERE.getBanque();
					boolean virementOk = banque.virer(acheteur, cc.getCryptogramme(), cc.getVendeur(),aPayer);
					double effectivementPaye = virementOk ? aPayer : 0.0; 
					this.journalEcheances.ajouter(Color.WHITE, (aPayer==effectivementPaye?Color.BLACK:Color.RED),"  a payer="+String.format("%.3f",aPayer)+"  paye="+String.format("%.3f",effectivementPaye));
					if (aPayer!=effectivementPaye) {
						this.journal.ajouter("Defaut de paiement de "+cc.getAcheteur()+" : a payer="+String.format("%.3f",aPayer)+"  paye="+String.format("%.3f",effectivementPaye));
					}
					if (effectivementPaye>0.0) {
						cc.payer(effectivementPaye);
					}
				} else {
					this.journalEcheances.ajouter("- rien a payer a cette etape");
				}
			}
		}	
		if (echangesAuStep.keySet().size()>0) {
			this.journal.ajouter("=== Quantites livrees a cette etape ===");
			for (Object produit : echangesAuStep.keySet()) {
				this.journal.ajouter("   -"+produit+" : "+echangesAuStep.get(produit));	
			}	
		} else {
			this.journal.ajouter(Color.white,Color.RED, "aucune livraison de produits a cette etape");	
		}
	}

	public void archiverContrats() {
		Banque banque = Filiere.LA_FILIERE.getBanque();
		List<ContratCadre> aArchiver = new ArrayList<ContratCadre>();
		for (ContratCadre cc : this.contratsEnCours) {
			if (banque.aFaitFaillite(cc.getVendeur()) && !banque.aFaitFaillite(cc.getAcheteur()) && cc.getQuantiteRestantALivrer()>0.0) {
				cc.getAcheteur().receptionner(cc.getProduit(), cc.getQuantiteRestantALivrer(), new ExemplaireContratCadre(cc));
				this.journal.ajouter("livraison de "+cc.getQuantiteRestantALivrer()+" "+cc.getProduit()+" a "+cc.getAcheteur()+" suite a la faillite de "+cc.getVendeur());
				aArchiver.add(cc);
			} else if (!banque.aFaitFaillite(cc.getVendeur()) && banque.aFaitFaillite(cc.getAcheteur()) && cc.getMontantRestantARegler()>0.0) {
				banque.virer(this, this.crypto, cc.getVendeur(),cc.getMontantRestantARegler());
				this.journal.ajouter("paiement de "+cc.getMontantRestantARegler()+" a "+cc.getVendeur()+" suite a la faillite de "+cc.getAcheteur());
				aArchiver.add(cc);
			} else if (banque.aFaitFaillite(cc.getVendeur()) || banque.aFaitFaillite(cc.getAcheteur()) || (cc.getMontantRestantARegler()==0.0 && cc.getQuantiteRestantALivrer()==0.0)) {
				aArchiver.add(cc);
			}
		}
		for (ContratCadre cc : aArchiver) {
			this.journal.ajouter("Archivage du contrat :<br>"+cc.toHtml());
			this.contratsEnCours.remove(cc);
			this.contratsTermines.add(cc);
		}
	}

	public void next() {
		//		etablirDeNouveauxContrats();
		archiverContrats();
		recapitulerContratsEnCours();
		gererLesEcheancesDesContratsEnCours();
	}

	public String getDescription() {
		return this.getNom();
	}

	public Color getColor() {
		return new Color(96, 125, 139);
	}

	public List<String> getNomsFilieresProposees() {
		return new LinkedList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		return new LinkedList<Variable>();
	}

	public List<Variable> getParametres() {
		return new LinkedList<Variable>();
	}

	public List<Journal> getJournaux() {
		List<Journal> res = new LinkedList<Journal>();
		res.add(this.journal);
		res.add(this.journalEcheances);
		return res;
	}

	public void setCryptogramme(Integer crypto) {
		this.crypto=crypto;
	}

	public void notificationFaillite(IActeur acteur) {
	}

	public void notificationOperationBancaire(double montant) {
	}
}
