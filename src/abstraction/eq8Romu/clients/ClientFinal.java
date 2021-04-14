package abstraction.eq8Romu.clients;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Gamme;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class ClientFinal implements IActeur {

	protected Categorie categorie;
	protected double pourcentageConsommationBasseQualite;
	protected Map<IDistributeurChocolatDeMarque, List<ChocolatDeMarque>> catalogues;
	protected Map<ChocolatDeMarque, List<IDistributeurChocolatDeMarque>> distributeursParChocolat;
	protected List<String> marques;
	protected List<ChocolatDeMarque> chocolatsDeMarquesDistribues;


	private double distributionsAnnuelles[][] ;
	// Exemple pour confisseries : 
//	{
//			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
//			{ 3.5, 3.5, 6.0, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 9.0, },			
//			{ 3.0, 3.0, 6.0, 3.0, 3.0, 3.0, 3.0, 3.0, 9.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
//			{ 3.0, 3.0, 7.0, 3.0, 3.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
//			{ 3.0, 3.0,10.0, 3.0, 3.0, 3.0, 3.0, 3.0,12.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
//			{ 3.0, 3.0,11.0, 3.0, 3.0, 3.0, 3.0, 3.0,13.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
//	};

	private Map<ChocolatDeMarque, Double> attractiviteChocolat;// Plus un chocolat a une forte attractivite (compare aux autres chocolats), plus ce chocolat aura une place importante dans la consommation globale de chocolat
	private Map<ChocolatDeMarque, Map<IDistributeurChocolatDeMarque, Double>> attractiviteDistributeur;// Pour un chocolat donne, plus l'attractivite d'un distributeur est grande (comparee a celle des autres distributeurs) plus sa part de marche sur ce chocolat sera grande
	private Map<Integer, Map<ChocolatDeMarque, List<Double>>> relevesDePrix; // Pour chaque etape, on memorise les prix de vente des differents chocolats
	private Variable deltaConsoMin, deltaConsoMax, conso, surcoutMemeQualite, surcoutQualitesDifferentes;
//	protected Map<Chocolat, Variable> stocksChocolat;
	protected Integer cryptogramme;
	protected Journal JournalDistribution, journalAttractivites, journalPrix;

	// Evolution de la distribution temporelle de la consommation 
	private Variable dureeMinTransitionDistribution ;// passer d'une distribution de la consommation a une autre prend au moins ce nombre d'etapes
	private Variable dureeMaxTransitionDistribution;// passer d'une distribution de la consommation a une autre prend au plus ce nombre d'etapes
	private int distributionOrigine; // On part de DISTRIBUTIONS_ANNUELLES[ distributionOrigine ]
	private int distributionArrivee; // On evolue vers DISTRIBUTIONS_ANNUELLES[ distributionArrivee ]
	private int etapesEvolutionDistribution; // C'est au bout de etapesEvolutionDistribution qu'on atteindra la distribution DISTRIBUTIONS_ANNUELLES[ distributionArrivee ] 
	private int etapeEvolutionDistribution; // On a effectue etapeEvolutionDistribution etape sur les etapesEvolutionDistribution etapes necessaires pour atteindre la distribution d'arrivee
	private Map<Integer, Map<ChocolatDeMarque, Double>> historiqueVentes;

	/**
	 * 
	 * @param categorie
	 * @param consommationAnnuelle
	 * @param pourcentageConsommationBasseQualite, dans [1.0, 94.0], le pourcentage de la basse qualite dans la consommation de 
	 *    cette categorie de chocolat (le bio_equitable represente 3.0 (bio=3%), le haute_equitable 1.0, 
	 *    le moyenne_equitable 1.0 (equitable=5%), le moyenne qualite 95.0-pourcentageConsommationBasseQualite)
	 */
	public ClientFinal(Categorie categorie, double consommationAnnuelle, double pourcentageConsommationBasseQualite, double[][]distributionsAnnuelles) {
		this.categorie = categorie;
		this.pourcentageConsommationBasseQualite=pourcentageConsommationBasseQualite;
		this.distributionsAnnuelles = distributionsAnnuelles;
		this.conso=new Variable(getNom()+" consommation annuelle", this, consommationAnnuelle);
		this.deltaConsoMin=new Variable(getNom()+" delta annuel min conso", this, 0.0);
		this.deltaConsoMax=new Variable(getNom()+" delta annuel max conso", this, 0.0);
		this.dureeMinTransitionDistribution = new Variable(getNom()+" duree min evolution distribution", this, 24);
		this.dureeMaxTransitionDistribution = new Variable(getNom()+" duree max evolution distribution", this, 48);
		this.catalogues = new HashMap<IDistributeurChocolatDeMarque, List<ChocolatDeMarque>>();
		this.distributeursParChocolat =  new HashMap<ChocolatDeMarque, List<IDistributeurChocolatDeMarque>>();
		this.chocolatsDeMarquesDistribues = new ArrayList<ChocolatDeMarque>();

		this.JournalDistribution= new Journal(this.getNom()+" distribution", this);
		this.journalAttractivites = new Journal(this.getNom()+" attractivites", this);
		this.journalPrix = new Journal(this.getNom()+" prix", this);
		this.surcoutMemeQualite = new Variable(getNom()+" surcout meme qualite", this, 0.25);
		this.surcoutQualitesDifferentes = new Variable(getNom()+" surcout qualites differentes", this, 1.25);
		this.relevesDePrix = new HashMap<Integer, Map<ChocolatDeMarque, List<Double>>> ();
	}
	//--- IACTEUR ---
	public String getNom() {
		return "C.F."+this.categorie.toString().substring(0, 4).toUpperCase();
	}
	public String getDescription() {
		return "Client final "+this.categorie;
	}
	public Categorie getCategorie() {
		return this.categorie;
	}
	public Color getColor() {
		return new Color(187,202,208);
	}
	public void initialiser() {
		this.JournalDistribution.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.marques = Filiere.LA_FILIERE.getMarquesChocolat();
		this.initEvolutionDistributionAnnuelleDesVentes();

		// Initialisation des catalogues et de la liste des chocolats de marque distribues
		List<IDistributeurChocolatDeMarque> distributeurs = Filiere.LA_FILIERE.getDistributeurs();
		for (IDistributeurChocolatDeMarque d : distributeurs) {
			List<ChocolatDeMarque> cat = d.getCatalogue();
			List<ChocolatDeMarque> extraitCat = new ArrayList<ChocolatDeMarque>();
			for (ChocolatDeMarque c : cat) {
				if (c.getChocolat().getCategorie().equals(this.categorie) && !chocolatsDeMarquesDistribues.contains(c)) {
					chocolatsDeMarquesDistribues.add(c);
				}
				if (c.getCategorie().equals(this.categorie)) {
					extraitCat.add(c);
				}
			}
			this.catalogues.put(d, extraitCat); // On memorise la partie du catalogue de la categorie du client final
		}
		this.JournalDistribution.ajouter("Chocolats distribues : "+chocolatsDeMarquesDistribues);

		// Initialisation pour chaque chocolat distribue de la liste des distributeurs
		for (ChocolatDeMarque c : chocolatsDeMarquesDistribues) {
			this.distributeursParChocolat.put(c,  new ArrayList<IDistributeurChocolatDeMarque>());
		}
		for (ChocolatDeMarque c : chocolatsDeMarquesDistribues) {
			for (IDistributeurChocolatDeMarque d : this.catalogues.keySet()) {
				if (this.catalogues.get(d).contains(c)) {
					this.distributeursParChocolat.get(c).add(d);
				}
			}
			this.JournalDistribution.ajouter("Distributeurs de "+c+" = "+this.distributeursParChocolat.get(c));
		}

		// Initialisation de l'attractivite de tous les distributeurs sur tous les types de chocolat
		// L'attractivite globale des distributeurs est la meme mais elle se reparti sur l'ensemble des
		// chocolats propose : un distributeur plus specialise aura une attractivite nulle sur les produits
		// qu'il ne vend pas mais davantage d'attractivite sur les produits qu'il vend qu'un distributeur
		// commercialisant tous les chocolats possibles.
		this.attractiviteDistributeur=new HashMap<ChocolatDeMarque, Map<IDistributeurChocolatDeMarque, Double>>();
		for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
			HashMap<IDistributeurChocolatDeMarque, Double> attract = new HashMap<IDistributeurChocolatDeMarque,Double>();
			for (IDistributeurChocolatDeMarque distri : distributeurs) {
				attract.put(distri, catalogues.get(distri).contains(choco)? (1.0*chocolatsDeMarquesDistribues.size())/catalogues.get(distri).size() : 0.0);
				journalAttractivites.ajouter("Attractivite initiale du chocolat "+choco+" chez "+distri+"="+Journal.doubleSur(attract.get(distri),4 ));
			}
			this.attractiviteDistributeur.put(choco, attract);
		}
		
		// Initialisation de l'attractivite des chocolats de cette categorie.
		// L'attractivite initiale correspond au pourcentage de consommation du choco
		// divise par le nombre de chocolats de marque aux caracteristiques identiques.
		
		// La division sert a repartir l'attractivite d'un type de chocolat entre les marques aux catalogues de ce type de chocolat
		Map<Chocolat, List<ChocolatDeMarque>> chocolatsDeMarqueDistribuesParChocolat=chocolatsDeMarqueDistribuesParChocolat();
		this.attractiviteChocolat = new HashMap<ChocolatDeMarque, Double>();
		journalAttractivites.ajouter("Pourcentage consommation de la qualite basse = "+this.pourcentageConsommationBasseQualite);

		for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
			if (choco.isBio()&&choco.isEquitable()) {  // HAUTE_BIO_EQUITABLE = 3%
				attractiviteChocolat.put(choco, 3.0/chocolatsDeMarqueDistribuesParChocolat.get(choco.getChocolat()).size());
			} else if (choco.getGamme().equals(Gamme.HAUTE)) { // HAUTE_EQUITABLE = 1%
				attractiviteChocolat.put(choco, 1.0/chocolatsDeMarqueDistribuesParChocolat.get(choco.getChocolat()).size());
			} else if (choco.isEquitable()) { // MOYENNE_EQUITABLE = 1%
				attractiviteChocolat.put(choco, 1.0/chocolatsDeMarqueDistribuesParChocolat.get(choco.getChocolat()).size());
			} else if (choco.getGamme().equals(Gamme.BASSE)) {
				attractiviteChocolat.put(choco, this.pourcentageConsommationBasseQualite/chocolatsDeMarqueDistribuesParChocolat.get(choco.getChocolat()).size());
			} else {
				attractiviteChocolat.put(choco, (95.0-this.pourcentageConsommationBasseQualite)/chocolatsDeMarqueDistribuesParChocolat.get(choco.getChocolat()).size());
			}
			journalAttractivites.ajouter("Attractivite initiale du chocolat "+choco+" = "+attractiviteChocolat.get(choco));
		}
		
		// Initialisation de l'historique des volumes de ventes
		this.historiqueVentes=new HashMap<Integer, Map<ChocolatDeMarque, Double>>();
		for (int etape=0; etape<24; etape++) {
			Map<ChocolatDeMarque, Double> ventesEtape = new HashMap<ChocolatDeMarque, Double>();
			double consoStep = conso.getValeur()*ratioStep(etape);
			// Repartition des besoins clients en ventes
			double totalAttractiviteChocolats = 0.0;
			for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
				totalAttractiviteChocolats+=attractiviteChocolat.get(choco);
			}
			for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
				ventesEtape.put(choco,consoStep*attractiviteChocolat.get(choco)/totalAttractiviteChocolats);
			}
			historiqueVentes.put(etape-24, ventesEtape);
		}
	}
	public void next() {
		// Evolution de la distribution annuelle des ventes
		this.evolutionDistributionAnnuelleDesVentes();
		this.JournalDistribution.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");
		this.JournalDistribution.ajouter(" origine="+this.distributionOrigine+" arrive="+this.distributionArrivee+" etape="+this.etapeEvolutionDistribution+"/"+this.etapesEvolutionDistribution);

		// Memorisation des prix moyens a l'etape courante
		Map<ChocolatDeMarque, List<Double>> releve = new HashMap<ChocolatDeMarque, List<Double>>();
		journalPrix.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ===============================");
		journalAttractivites.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ===============================");
		for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
			List<IDistributeurChocolatDeMarque> distribs = this.distributeursParChocolat.get(choco);
			ArrayList<Double>prix = new ArrayList<Double>();
			for (IDistributeurChocolatDeMarque distri : distribs) {
				if (distri.quantiteEnVente(choco)>0.0) { // On ne tient pas compte du prix si il n'en met pas en vente
					this.journalPrix.ajouter(" "+distri.getNom()+" vend "+choco.name()+" au prix de "+Journal.doubleSur(distri.prix(choco), 4));
					prix.add(distri.prix(choco));
				}
			}
			if (prix.size()==0) {
				this.journalPrix.ajouter(" pas d'achat possible de "+choco.name());
				prix.add(Double.MAX_VALUE);
			} 	
			releve.put(choco, prix);
		}
		this.relevesDePrix.put(Filiere.LA_FILIERE.getEtape(), releve);

		// Verification des volumes mis en vente
		HashMap<IDistributeurChocolatDeMarque,Double> quantiteTotaleMiseEnVente = new HashMap<IDistributeurChocolatDeMarque, Double>();
		for (IDistributeurChocolatDeMarque distri : Filiere.LA_FILIERE.getDistributeurs()) {
			double quantiteTotale = 0.0;
			double quantiteTG = 0.0;
			for (ChocolatDeMarque choco : catalogues.get(distri)) {
				if (distri.quantiteEnVente(choco)<0.0) {
					throw new IllegalStateException("Le distributeur "+distri.getNom()+" met en vente un volume de "+distri.quantiteEnVente(choco)+" de "+choco);
				}
				if (distri.quantiteEnVenteTG(choco)<0.0) {
					throw new IllegalStateException("Le distributeur "+distri.getNom()+" met en vente en tete de gondole un volume de "+distri.quantiteEnVente(choco)+" de "+choco);
				}
				quantiteTotale+=distri.quantiteEnVente(choco);
				quantiteTG+=distri.quantiteEnVenteTG(choco);
			}
			quantiteTotaleMiseEnVente.put(distri, quantiteTotale);
			if (quantiteTG>quantiteTotale/10.0) {
				throw new IllegalStateException("Le distributeur "+distri.getNom()+" met en vente en tete de gondole un volume de "+quantiteTG+" superieur a 10% du volume total mis en vente ("+quantiteTotale+")");
			} else {
				this.JournalDistribution.ajouter("---"+Journal.texteColore(distri, distri.getNom())+" met en TG "+Journal.doubleSur(quantiteTG, 2)+" Kg / "+Journal.doubleSur(quantiteTotale, 2)+" Kg");
			}
		}
		
		// Evolution du volume de la consommation annuelle
		if (deltaConsoMin.getValeur()>deltaConsoMax.getValeur()) {
			double max = deltaConsoMin.getValeur();
			deltaConsoMin.setValeur(this,  deltaConsoMax.getValeur());
			deltaConsoMax.setValeur(this,  max);
		}
		double deltaConso = deltaConsoMin.getValeur() + (Math.random()*(deltaConsoMax.getValeur() - deltaConsoMin.getValeur()));
		conso.setValeur(this, conso.getValeur()*(1+(deltaConso/24.0)));

		JournalDistribution.ajouter("Evolution conso globale : delta conso = "+Journal.doubleSur(deltaConso, 4)+" ==> conso = "+Journal.doubleSur(conso.getValeur(),2));

		// Calcul du volume de la consommation a cette etape 
		double consoStep = conso.getValeur()*ratioStep();
		JournalDistribution.ajouter("Conso a cette etape = "+Journal.doubleSur(consoStep, 4)+" (ratio distribution="+Journal.doubleSur(ratioStep(), 4)+")");

		// Repartition des besoins clients en ventes
		JournalDistribution.ajouter("= Repartition des besoins clients en ventes ===");
		double totalAttractiviteChocolats = 0.0;
		for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
			totalAttractiviteChocolats+=attractiviteChocolat.get(choco);
		}
		JournalDistribution.ajouter("&nbsp;&nbsp;Somme des attractivites des chocolats = "+Journal.doubleSur(totalAttractiviteChocolats,4));
		Map<ChocolatDeMarque, Double> ventesEtape = new HashMap<ChocolatDeMarque, Double>();

		for (ChocolatDeMarque choco : chocolatsDeMarquesDistribues) {
			if (!distributeursParChocolat.get(choco).isEmpty()) {// il y a des distributeurs, ce qui doit toujours etre le cas vu qu'on itere sur les chocolats distribues
				double consoStepChoco = consoStep*attractiviteChocolat.get(choco)/totalAttractiviteChocolats;
				JournalDistribution.ajouter(Color.cyan,Color.black,"&nbsp;&nbsp;-- Choco : "+choco.name()+" attractivite="+Journal.doubleSur(attractiviteChocolat.get(choco), 4)+" --> conso etape = "+Journal.doubleSur(consoStepChoco, 2));
				// Cette consommation consoStepChoco est a repartir entre les distributeurs au prorata de leur attractivite sur ce produit
				List<IDistributeurChocolatDeMarque> distributeursDeChoco = distributeursParChocolat.get(choco);
				double totalAttractiviteDistris = 0.0;
				double totalVentes=0.0;// pour memoriser dans l'historique
				for (IDistributeurChocolatDeMarque dist : distributeursDeChoco) {
					totalAttractiviteDistris+=this.attractiviteDistributeur.get(choco).get(dist).doubleValue();
				}
				//journalAttractivites.ajouter(" le total de l'acttractivite des distributeurs est "+Journal.doubleSur(totalAttractiviteDistris, 4));
				double prixMoyen=prixMoyen(choco);
				for (IDistributeurChocolatDeMarque dist : distributeursDeChoco) {
					double quantiteDesiree = consoStepChoco*this.attractiviteDistributeur.get(choco).get(dist)/totalAttractiviteDistris;
					// La non disponibilite, les tetes de gondole et le prix vont pouvoir impacter l'attractivite MAIS cela 
					// influe doucement/a long terme. Or, une augmentation subite du prix peut freiner considerablement les
					// achats car personne n'achete un chocolat hors de prix/beaucoup plus cher que chez le concurrent.
					// Au contraire, un prix bien plus bas que la concurrence peut booster les ventes (==promo).
					// La moitie des ventes n'est pas impactee (achat sans tenir compte du prix pour 50% des achats)
					if (quantiteDesiree>0 && prixMoyen!=Double.MAX_VALUE) { // Si le prixMoyen est de Double.MAX_VALUE c'est qu'aucun distributeur n'est en mesure de fournir ce chocolat
						   quantiteDesiree*= (0.5 + (0.5*prixMoyen/dist.prix(choco)));
					}
					double enVente = dist.quantiteEnVente(choco);
					double quantiteAchetee = Math.max(0.0, Math.min(quantiteDesiree, enVente));
					JournalDistribution.ajouter("&nbsp;&nbsp;&nbsp;&nbsp;pour "+dist.getNom()+" d'attractivite "+Journal.doubleSur(this.attractiviteDistributeur.get(choco).get(dist), 4)+" la quantite desiree est "+Journal.doubleSur(quantiteDesiree,4)+" et quantite en vente ="+Journal.doubleSur(enVente, 4)+" -> quantitee achetee "+Journal.doubleSur(quantiteAchetee, 4));
					if (quantiteAchetee>0.0) {
						totalVentes+=quantiteAchetee;
						Filiere.LA_FILIERE.getBanque().virer(this, cryptogramme, dist, quantiteAchetee*dist.prix(choco));
						dist.vendre(this, choco, quantiteAchetee, quantiteAchetee*dist.prix(choco));
					}
					if (quantiteDesiree>enVente) {
						dist.notificationRayonVide(choco);
					}
					double impactRupture = (quantiteDesiree>enVente ? -0.03 : 0.01); // si le client n'a pas trouve tout ce qu'il souhaite la penalite est de -3%, sinon l'attractivite augmente de 1%
					double impactPrix = ((prixMoyen-dist.prix(choco))/prixMoyen)/10.0; // 10% du pourcentage d'ecart de prix avec la moyenne.
					double impactTG = quantiteTotaleMiseEnVente.get(dist)==0 ? 0.0 : (dist.quantiteEnVenteTG(choco)/quantiteTotaleMiseEnVente.get(dist))/5.0; // 2% au plus repartis sur les differents chocolats mis en tete de gondole
					attractiviteDistributeur.get(choco).put(dist,attractiviteDistributeur.get(choco).get(dist)*(1.0+impactRupture+impactPrix+impactTG));
					if (impactTG!=0.0) {
						attractiviteChocolat.put(choco, attractiviteChocolat.get(choco)*(1.0+impactTG));
						//journalAttractivites.ajouter("   choco "+choco.name()+" en TG chez "+Journal.texteColore(dist, dist.getNom())+" --> impact="+Journal.doubleSur(impactTG,4)+" Attractivite du choco devient "+Journal.doubleSur(attractiviteChocolat.get(choco), 4));
					}
					//journalAttractivites.ajouter("  impact pour rupture de stock de "+dist.getNom()+" pour "+choco+" = "+Journal.doubleSur(impactRupture,  4));
					//journalAttractivites.ajouter("  impact pour l'ecart avec le prix moyen "+dist.getNom()+" pour "+choco+" = "+Journal.doubleSur(impactPrix,  4));
					//journalAttractivites.ajouter("  impact de la mise en tete de gondole "+dist.getNom()+" pour "+choco+" = "+Journal.doubleSur(impactTG,  4));
					journalAttractivites.ajouter("  Attractivite de "+Journal.texteColore(dist, dist.getNom())+" pour "+choco.name()+" = "+Journal.doubleSur(attractiviteDistributeur.get(choco).get(dist), 4)+ " (impact rupture="+Journal.doubleSur(impactRupture,  4)+"  impact prix="+Journal.doubleSur(impactPrix,  4)+"  impact TG="+Journal.doubleSur(impactTG,  4)+")");
				}
				ventesEtape.put(choco,totalVentes);
			}
			this.historiqueVentes.put(Filiere.LA_FILIERE.getEtape(), ventesEtape);
		}

		journalAttractivites.ajouter("= Transfert des attractivites entre les chocolats en fonction des rapports qualite/prix");
		// Transfert d'attractivite entre les chocolats en fonction des rapports qualite/prix
		for (ChocolatDeMarque choco1 : chocolatsDeMarquesDistribues) {
			for (ChocolatDeMarque choco2 : chocolatsDeMarquesDistribues) {
				if (prixMoyen(choco1)!=Double.MAX_VALUE && prixMoyen(choco2)!=Double.MAX_VALUE && choco1!=choco2) {
					ChocolatDeMarque moinsCher=choco1;
					ChocolatDeMarque plusCher=choco2;
					if (prixMoyen(choco2)<prixMoyen(choco1)) {
						moinsCher=choco2;
						plusCher=choco1;
					}
					if (moinsCher.qualitePercue()==plusCher.qualitePercue()) {
						if ((prixMoyen(plusCher)-prixMoyen(moinsCher))/prixMoyen(moinsCher)>this.surcoutMemeQualite.getValeur()) {// a qualite identique un ecart de prix de plus de 25% modifie l'attractivite
							attractiviteChocolat.put(moinsCher, attractiviteChocolat.get(moinsCher)*1.005);// +0.5%						
							attractiviteChocolat.put(plusCher, attractiviteChocolat.get(plusCher)*0.995);// -0.5%						
							this.journalAttractivites.ajouter("&nbsp;&nbsp;prixMoyen("+moinsCher.name()+")="+Journal.doubleSur(prixMoyen(moinsCher), 4)+" et prixMoyen("+plusCher.name()+")="+Journal.doubleSur(prixMoyen(plusCher), 4)+" --> attractivite +0.5% pour le moins cher");
						}
					} else if (moinsCher.qualitePercue()>plusCher.qualitePercue()) {
						attractiviteChocolat.put(moinsCher, attractiviteChocolat.get(moinsCher)*1.05);// +5%						
						attractiviteChocolat.put(plusCher, attractiviteChocolat.get(plusCher)*0.95);// -5%						
						this.journalAttractivites.ajouter("&nbsp;&nbsp;prixMoyen("+moinsCher.name()+")="+Journal.doubleSur(prixMoyen(moinsCher), 4)+" et prixMoyen("+plusCher.name()+")="+Journal.doubleSur(prixMoyen(plusCher), 4)+" --> attractivite +5% pour le moins cher");
					} else {
						if ((prixMoyen(plusCher)-prixMoyen(moinsCher))/prixMoyen(moinsCher)>this.surcoutQualitesDifferentes.getValeur()*(plusCher.qualitePercue()-moinsCher.qualitePercue())) {
							attractiviteChocolat.put(moinsCher, attractiviteChocolat.get(moinsCher)*1.005);						
							attractiviteChocolat.put(plusCher, attractiviteChocolat.get(plusCher)*0.995);					
							this.journalAttractivites.ajouter("&nbsp;&nbsp;prixMoyen("+moinsCher.name()+")="+Journal.doubleSur(prixMoyen(moinsCher), 4)+" et prixMoyen("+plusCher.name()+")="+Journal.doubleSur(prixMoyen(plusCher), 4)+" --> attractivite +0.5% pour le moins cher");
						}
					}
				}
			}
		}
		for (ChocolatDeMarque choco1 : chocolatsDeMarquesDistribues) {
			this.journalAttractivites.ajouter("attractivite du "+choco1.name()+" == "+Journal.doubleSur(attractiviteChocolat.get(choco1), 4));
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
		res.add(this.conso);
		res.add(deltaConsoMin);
		res.add(deltaConsoMax);
		return res;
	}
	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.dureeMinTransitionDistribution);
		res.add(this.dureeMaxTransitionDistribution);
		res.add(this.surcoutMemeQualite);
		res.add(this.surcoutQualitesDifferentes);
		return res;
	}
	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journalAttractivites);
		j.add(this.journalPrix);
		j.add(this.JournalDistribution);
		return j;
	}
	public void notificationFaillite(IActeur acteur) {
	}
	public void notificationOperationBancaire(double montant) {
	}
	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	//--- FIN IACTEUR---

	//--- Evolution de la consommation annuelle
	private void initEvolutionDistributionAnnuelleDesVentes() {
		this.distributionOrigine = (int) (Math.random()*distributionsAnnuelles.length);
		this.distributionArrivee = (int) (Math.random()*distributionsAnnuelles.length);// potentiellement distributionArrivee==distributionOrigine ==> dans ce cas on est dans une periode ou les pics de consommation ne changent pas.
		this.etapesEvolutionDistribution =(int) ( dureeMinTransitionDistribution.getValeur() + (int)(Math.random()*(dureeMaxTransitionDistribution.getValeur()-dureeMinTransitionDistribution.getValeur()))); // Nombre d'etapes pour atteindre la prochaine distribution
		this.etapeEvolutionDistribution=0;
	}
	private void evolutionDistributionAnnuelleDesVentes() {
		this.etapeEvolutionDistribution++;
		if (this.etapeEvolutionDistribution>this.etapesEvolutionDistribution) {
			this.etapeEvolutionDistribution=0;
			this.distributionOrigine=this.distributionArrivee;
			this.distributionArrivee = (int) (Math.random()*distributionsAnnuelles.length);// potentiellement distributionArrivee==distributionOrigine ==> dans ce cas on est dans une periode ou les pics de consommation ne changent pas.
			this.etapesEvolutionDistribution =(int) ( dureeMinTransitionDistribution.getValeur() + (int)(Math.random()*(dureeMaxTransitionDistribution.getValeur()-dureeMinTransitionDistribution.getValeur()))); // Nombre d'etapes pour atteindre la prochaine distribution
		}
	}
	private double ratioStep(int step) {
		double origine = distributionsAnnuelles[distributionOrigine][step%24];
		double arrivee = distributionsAnnuelles[distributionArrivee][step%24];
		return (origine+((arrivee-origine)*etapeEvolutionDistribution)/etapesEvolutionDistribution)/100.0;
	}

	private double ratioStep() {
		return ratioStep(Filiere.LA_FILIERE.getEtape());
	}





	//	public static List<IVendeurChocolatBourse> vendeursChocolatBourse() {
	//		List<IVendeurChocolatBourse> res = new LinkedList<IVendeurChocolatBourse>();
	//		for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
	//			if (acteur instanceof IVendeurChocolatBourse) {
	//				res.add((IVendeurChocolatBourse)acteur);
	//			}
	//		}
	//		return res;
	//	}
	//	public static List<IDistributeurChocolatDeMarque> distributeursChocolatDeMarque() {
	//		List<IDistributeurChocolatDeMarque> res = new LinkedList<IDistributeurChocolatDeMarque>();
	//		for (IActeur acteur : Filiere.LA_FILIERE.getActeurs()) {
	//			if (acteur instanceof IDistributeurChocolatDeMarque) {
	//				res.add((IDistributeurChocolatDeMarque)acteur);
	//			}
	//		}
	//		return res;
	//	}
	//	public static List<ChocolatDeMarque> tousLesChocolatsDeMarquePossibles() {
	//		List<ChocolatDeMarque> resultat = new LinkedList<ChocolatDeMarque>();
	//		for (Chocolat choco : Chocolat.values()) {
	//			List<IVendeurChocolatBourse> vendeursChocolatBourse=vendeursChocolatBourse();
	//			for (IVendeurChocolatBourse vendeur : vendeursChocolatBourse) {
	//				resultat.add( new ChocolatDeMarque(choco, vendeur.getNom()) );
	//			}
	//			List<IDistributeurChocolatDeMarque> distributeursChocolatDeMarque=distributeursChocolatDeMarque();
	//			for (IDistributeurChocolatDeMarque distri : distributeursChocolatDeMarque) {
	//				resultat.add( new ChocolatDeMarque(choco, distri.getNom()) );
	//			}
	//		}
	//		return resultat;
	//	}
	//	public static List<ChocolatDeMarque> chocolatsDeMarqueEnVente() {
	//		List<ChocolatDeMarque> res = new LinkedList<ChocolatDeMarque>();
	//		List<ChocolatDeMarque> tous =  tousLesChocolatsDeMarquePossibles();
	//		List<IDistributeurChocolatDeMarque> distributeurs = distributeursChocolatDeMarque();
	//		for (ChocolatDeMarque choco : tous) {
	//			boolean ajoute=false;
	//			for (int i=0;!ajoute && i<distributeurs.size();i++) {
	//				if (distributeurs.get(i).getCatalogue().contains(choco)) {
	//					res.add(choco);
	//					ajoute=true;
	//				}
	//			}
	//		}
	//		return res;
	//	}
	public Map<Chocolat, List<ChocolatDeMarque>> chocolatsDeMarqueDistribuesParChocolat() {
		Map<Chocolat, List<ChocolatDeMarque>> repartition = new HashMap<Chocolat, List<ChocolatDeMarque>>();
		for (Chocolat choco : Chocolat.values()) {
			if (choco.getCategorie().equals(this.categorie)) {
			repartition.put(choco, new LinkedList<ChocolatDeMarque>());
			}
		}
		for (ChocolatDeMarque chocoMarque : chocolatsDeMarquesDistribues) {
			repartition.get(chocoMarque.getChocolat()).add(chocoMarque);
		}
		return repartition;
	}
	
//	public static Map<ChocolatDeMarque, List<IDistributeurChocolatDeMarque>> distributeursParChocolatDeMarque() {
//		Map<ChocolatDeMarque, List<IDistributeurChocolatDeMarque>> repartition = new HashMap<ChocolatDeMarque, List<IDistributeurChocolatDeMarque>>();
//		List<IDistributeurChocolatDeMarque> distributeursChocolatDeMarque = distributeursChocolatDeMarque();
//		List<ChocolatDeMarque> chocolatsDeMarqueEnVente = chocolatsDeMarqueEnVente();
//		for (ChocolatDeMarque chocoMarque : chocolatsDeMarqueEnVente) {
//			repartition.put(chocoMarque, new LinkedList<IDistributeurChocolatDeMarque>());
//			for (IDistributeurChocolatDeMarque distri : distributeursChocolatDeMarque) {
//				if (distri.getCatalogue().contains(chocoMarque)) {
//					repartition.get(chocoMarque).add(distri);
//				}
//			}
//		}
//		return repartition;
//	}

	//	public static List<ChocolatDeMarque> chocolatsDeMarqueEnRayon() {
	//		List<ChocolatDeMarque> res = new LinkedList<ChocolatDeMarque>();
	//		List<ChocolatDeMarque> tous =  chocolatsDeMarqueEnVente();
	//		List<IDistributeurChocolatDeMarque> distributeurs = distributeursChocolatDeMarque();
	//		for (ChocolatDeMarque choco : tous) {
	//			boolean ajoute=false;
	//			for (int i=0;!ajoute && i<distributeurs.size();i++) {
	//				if (distributeurs.get(i).quantiteEnVente(choco)>0.0) {
	//					res.add(choco);
	//					ajoute=true;
	//				}
	//			}
	//		}
	//		return res;
	//	}
	//
	//	public boolean campagneDePubAutorisee(IDistributeurChocolatDeMarque distri) {
	//		return (Filiere.LA_FILIERE.getEtape() - troisDernieresPubs.get(distri).get(0)) >=24;
	//	}
	//	
	//	public void memorisePubs(IDistributeurChocolatDeMarque distri) {
	//		troisDernieresPubs.get(distri).add(Filiere.LA_FILIERE.getEtape());
	//		troisDernieresPubs.get(distri).remove(0);
	//	}

	public double getVentes(int etape, ChocolatDeMarque choco) {
		if (this.historiqueVentes.keySet().contains(etape)) {
			if (this.historiqueVentes.get(etape).containsKey(choco)) {
				return this.historiqueVentes.get(etape).get(choco);
			} else {
				return 0.0;
			}
		} else {
			if (etape<-24 || etape>=Filiere.LA_FILIERE.getEtape()) {
				throw new IllegalArgumentException(" Appel de ClientFinal.getVentes avec etape=="+etape+" alors que les etapes valides sont "+this.historiqueVentes.keySet());
			} else {
				return 0.0;
			}
		}
	}

	public double getVentes(int etape, Chocolat choco) {
		if (this.historiqueVentes.keySet().contains(etape)) {
			Map<ChocolatDeMarque, Double> ventes = this.historiqueVentes.get(etape);
			double totalVentes=0.0;
			for (ChocolatDeMarque cdm : ventes.keySet()) {
				if (cdm.getChocolat().equals(choco)) {
					totalVentes=totalVentes+ventes.get(cdm);
				}
			}
			return totalVentes;
		} else {
			return 0;
		}
	}

	public void initAttractiviteChoco(ChocolatDeMarque choco, double val) {
		if (Filiere.LA_FILIERE==null || Filiere.LA_FILIERE.getEtape()<1) {
			if (val<0.1) {
				throw new IllegalArgumentException("la methode initAttractiviteChoco de ClientFinal n'accepte pas une valeur inferieure a 0.1");
			} else {
				attractiviteChocolat.put(choco, val);
			}
		} else {
			throw new IllegalArgumentException("la methode initAttractiviteChoco de ClientFinal ne peut etre appelee qu'avant le premier step");
		}
	}

	private double prixMoyen(ChocolatDeMarque choco) {
		List<Double> prix = relevesDePrix.get(Filiere.LA_FILIERE.getEtape()).get(choco);
		double somme = prix.get(0);
		for (int i=1; i<prix.size(); i++) {
			somme+=prix.get(i);
		}
		return somme/prix.size();

	}
	public double prixMoyen(ChocolatDeMarque choco, int etape) {
		if (etape<0 || etape>=Filiere.LA_FILIERE.getEtape() || !choco.getCategorie().equals(this.categorie)) {
			throw new IllegalArgumentException("Appel de prixMoyen("+choco+", "+etape+") de ClientFinal avec des arguments non autorises");
		}
		List<Double> prix = relevesDePrix.get(etape).get(choco);
		double somme = prix.get(0);
		for (int i=1; i<prix.size(); i++) {
			somme+=prix.get(i);
		}
		return somme/prix.size();
	}
//	private double prixMin(ChocolatDeMarque choco) {
//		return prixMin(choco, Filiere.LA_FILIERE.getEtape());
//	}
//	private double prixMin(ChocolatDeMarque choco, int etape) {
//		List<Double> prix = relevesDePrix.get(etape).get(choco);
//		double min = prix.get(0);
//		for (int i=1; i<prix.size(); i++) {
//			min=Math.min(min, prix.get(i));
//		}
//		return min;
//	}
//	private double prixMax(ChocolatDeMarque choco) {
//		return prixMax(choco, Filiere.LA_FILIERE.getEtape());
//	}
//	private double prixMax(ChocolatDeMarque choco, int etape) {
//		List<Double> prix = relevesDePrix.get(etape).get(choco);
//		double max = prix.get(0);
//		for (int i=1; i<prix.size(); i++) {
//			max=Math.max(max, prix.get(i));
//		}
//		return max;
//	}

//	public double prixMoyenEtapePrecedente(ChocolatDeMarque choco) {
//		if (Filiere.LA_FILIERE.getEtape()<1) {
//			throw new IllegalAccessError(" Il est impossible de faire appel a prixMoyenEtapePrecedente a l'etape 1");
//		} else {
//			return relevesDePrixMoyens.get(Filiere.LA_FILIERE.getEtape()-1).get(choco);
//		}
//	}

	// DEPLACE DANS CHOCOLAT
	//	private static double qualite(ChocolatDeMarque choco) {
	//		POUDRE_HAUTE_BIO_EQUITABLE(Categorie.POUDRE, Gamme.HAUTE, true, true ),
	//		POUDRE_HAUTE_EQUITABLE(Categorie.POUDRE, Gamme.HAUTE, false, true ),
	//		POUDRE_MOYENNE_EQUITABLE(Categorie.POUDRE, Gamme.MOYENNE, false, true ),
	//		POUDRE_MOYENNE(Categorie.POUDRE, Gamme.MOYENNE, false, false ),
	//		POUDRE_BASSE(Categorie.POUDRE, Gamme.BASSE, false, false );
	//
	//		switch (choco.getChocolat()) {
	//		case CHOCOLAT_BASSE : return 1.0;
	//		case CHOCOLAT_MOYENNE : return 2.0;
	//		case CHOCOLAT_HAUTE : return 3.0;
	//		case CHOCOLAT_MOYENNE_EQUITABLE : return 2.5;
	//		default : return 3.5; //CHOCOLAT_HAUTE_EQUITABLE
	//		}
	//	}

}
