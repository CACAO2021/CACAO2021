package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;

//Emeline

public abstract class Producteur2Prod extends Producteur2Stockage {
	private LinkedList<Stock> arbrePlantesHBE;
	private LinkedList<Stock> arbrePlantesHE;
	private LinkedList<Stock> arbrePlantesME;
	private LinkedList<Stock> arbrePlantesM; 
	private LinkedList<Stock> arbrePlantesB;

	protected HashMap<Feve, LinkedList<Stock>> arbrePlantes;
	protected HashMap<Feve, Variable> qttTotArbrePlantes;

	protected Variable qttArbreTotalFHBE;
	protected Variable qttArbreTotalFHE;
	protected Variable qttArbreTotalFME;
	protected Variable qttArbreTotalFM;
	protected Variable qttArbreTotalFB;
	protected Variable qttArbreTotalPHE;
	protected Variable qttArbreTotalPM;



	// ensemble fait par DIM

	public Producteur2Prod() {
		super();

		//On plante tous les arbres en respectant l'âge de début
		planterArbresAuDebut();		

	}

	//Dim
	public void planterArbresAuDebut() { // fonctionnement à vérif mais je pense que c ok

		// on creer une liste vide pour chaque type de fève
		this.arbrePlantesHBE = new LinkedList<Stock>();
		this.arbrePlantesHE = new LinkedList<Stock>();
		this.arbrePlantesME = new LinkedList<Stock>(); 
		this.arbrePlantesM = new LinkedList<Stock>();
		this.arbrePlantesB = new LinkedList<Stock>();

		// création de la hashmap associée
		arbrePlantes = new HashMap<Feve, LinkedList<Stock>>();
		arbrePlantes.put(listeProd.get(0), arbrePlantesHBE);
		arbrePlantes.put(listeProd.get(1), arbrePlantesHE);
		arbrePlantes.put(listeProd.get(2), arbrePlantesME);
		arbrePlantes.put(listeProd.get(3), arbrePlantesM);
		arbrePlantes.put(listeProd.get(4), arbrePlantesB);

		// on remplit les listes
		// il faudra tenir compte du fait que les arbres nont pas tous le meme age au début
		int stepActuel = 0;
		double stepArbreLePlusVieu = TPS_RENOUVELLEMENT_ARBRE - 2;
		double step = - stepArbreLePlusVieu;
		HashMap<Feve, Double> qttdb = new HashMap<Feve, Double>();
		qttdb.put(listeProd.get(0), ARBRE_DEBUT_HBE/(stepArbreLePlusVieu));
		qttdb.put(listeProd.get(1), ARBRE_DEBUT_HE/(stepArbreLePlusVieu));
		qttdb.put(listeProd.get(2), ARBRE_DEBUT_ME/(stepArbreLePlusVieu));
		qttdb.put(listeProd.get(3), ARBRE_DEBUT_M/(stepArbreLePlusVieu));
		qttdb.put(listeProd.get(4), ARBRE_DEBUT_B/(stepArbreLePlusVieu));

		while (step != stepActuel) {
			for (Feve f : listeProd) {
				arbrePlantes.get(f).add(new Stock(qttdb.get(f), (int)step));
			}			
			step++;
		}

		qttArbreTotalFHBE = new Variable("qttArbreTotal feve HBE", this, 0);
		qttArbreTotalFHE = new Variable("qttArbreTotal feve HE", this, 0);
		qttArbreTotalFME = new Variable("qttArbreTotal feve ME", this, 0);
		qttArbreTotalFM = new Variable("qttArbreTotal feve M", this, 0);
		qttArbreTotalFB = new Variable("qttArbreTotal feve B", this, 0);


		qttTotArbrePlantes = new HashMap<Feve, Variable>();
		qttTotArbrePlantes.put(listeProd.get(0), qttArbreTotalFHBE );
		qttTotArbrePlantes.put(listeProd.get(1), qttArbreTotalFHE );
		qttTotArbrePlantes.put(listeProd.get(2), qttArbreTotalFME );
		qttTotArbrePlantes.put(listeProd.get(3), qttArbreTotalFM );
		qttTotArbrePlantes.put(listeProd.get(4), qttArbreTotalFB );		

		// ancienne version sans tenir compte de l'age
		//		this.arbrePlantesHBE = new LinkedList<Stock>();
		//		this.arbrePlantesHBE.add(new Stock(ARBRE_DEBUT_HBE, 0)); 
		//		this.arbrePlantesHE = new LinkedList<Stock>();
		//		this.arbrePlantesHE.add(new Stock(ARBRE_DEBUT_HE, 0));
		//		this.arbrePlantesME = new LinkedList<Stock>();
		//		this.arbrePlantesME.add(new Stock(ARBRE_DEBUT_ME, 0));
		//		this.arbrePlantesM = new LinkedList<Stock>();
		//		this.arbrePlantesM.add(new Stock(ARBRE_DEBUT_M, 0));
		//		this.arbrePlantesB = new LinkedList<Stock>();
		//		this.arbrePlantesB.add(new Stock(ARBRE_DEBUT_B, 0));

	}

	public void majQttArbre(Object produit) {
		double stock = 0;
		for (Stock s : this.arbrePlantes.get((Feve)produit)) {
			stock += s.getQtt();		
		}
		qttTotArbrePlantes.get((Feve)produit).setValeur(Filiere.LA_FILIERE.getActeur("Baratao"), stock);
	}

	public void prod() {		
		for (Feve p : listeProd) {
			double qtt = prodParStep(p, Filiere.LA_FILIERE.getEtape()) ; //enelver le facteur
			// la production a desormais lieu non pas tous les mois de l'année, mais seulement en février et en septembre
			addStock(qtt, p); 
			JournalProd.ajouter(""+ p +" "+qtt);	
			coutProd(qtt, p);
			majStock(p);
			majQttArbre(p);
		}		
	}

	protected void coutProd(double qtt, Object p) {
		double cout = coutProdUnitaire(p) * qtt;
		if (cout>0) {
			perdreArgent(cout);
		}
	}

	private double coutProdUnitaire(Object p) { 
		// on peut faire une hashmap  
		if(estFeveHBE(p)) {
			return COUT_PRODUCTION_FEVE_HBE;
		} else if(estFeveHE(p)) {
			return COUT_PRODUCTION_FEVE_HE;
		} else if(estFeveME(p)) {
			return COUT_PRODUCTION_FEVE_ME;
		} else if(estFeveM(p)) {
			return COUT_PRODUCTION_FEVE_M;
		}else if(estFeveB(p)) {
			return COUT_PRODUCTION_FEVE_B;
		} else { // un produit que l'on ne vend pas
			return 0;
		}
	}

	public void renouvellement() {
		//pour faire des test
		renouvellmentvieu(); // a enlever


		//Il va falloir s’adapter au marché afin de ne pas perdre de temps et d’argent à
		//produire des ressources dont personne ne veut et que nous allons devoir stocker pendant
		//une longue période sans pouvoir espérer de bénéfice.
		int step = Filiere.LA_FILIERE.getEtape();
		int nbChangement = 0;
		List<Stock> aSupprimer = new ArrayList<Stock>();
		for (Feve f : listeProd) {
			for (Stock s : arbrePlantes.get(f)) {
				if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
					nbChangement += s.getQtt();
					aSupprimer.add(s);					
				}
			}
			// on supprime les arbres trop vieux
			// System.out.println(nbChangement);
			// 26473 arbres à chaque step ( la valeur reste la même à tous les steps )
			arbrePlantes.get(f).removeAll(aSupprimer);
			//System.out.println(qttArbreToujoursPlantes(f));
		}

		// on remplace les arbres en tenant compte de la demande
		// 26 473 arbres à répartir
		for (Feve f : listeProd) {
			// on récupère le nb d'arbre et de fève déjà ramassée pour chaque type de feve
			int nbArbre = qttArbreToujoursPlantes(f);
			double nbFeve = (qttTotale(f)).getValeur();
			int qtt = 0; // réfléchir à la répartition des arbres a replanter
			arbrePlantes.get(f).add(new Stock(qtt, step));
		}	
		// on dépense de l'argent pour remplacer els arbres
		perdreArgent(COUT_CHANGEMENT_ARBRE_HBE * nbChangement);
		System.out.println("perte " +COUT_CHANGEMENT_ARBRE_HBE * nbChangement);

	}

	public void renouvellmentvieu() {
		int step=0;
		// ancienne version 
		for (Stock s : arbrePlantesHBE) {
			if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
				s.setStep(step); // on change le step de l'arbre pour simuler le fait quil soit replanté
				perdreArgent(COUT_CHANGEMENT_ARBRE_HBE);
			}}
		for (Stock s : arbrePlantesHE) {
			if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
				s.setStep(step);
				perdreArgent(COUT_CHANGEMENT_ARBRE_HE);
			}}
		for (Stock s : arbrePlantesME) {
			if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
				s.setStep(step);
				perdreArgent(COUT_CHANGEMENT_ARBRE_ME);
			}}
		for (Stock s : arbrePlantesM) {
			if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
				s.setStep(step);
				perdreArgent(COUT_CHANGEMENT_ARBRE_M);
			}}
		for (Stock s : arbrePlantesB) { 
			if (step - s.getStep() == TPS_RENOUVELLEMENT_ARBRE) {
				s.setStep(step);
				perdreArgent(COUT_CHANGEMENT_ARBRE_B);
			}}
	}

	/*
	 * donne la qtt darbre planté pour un type de feve
	 */
	protected int qttArbreToujoursPlantes(Feve f) { 
		int stock = 0;			
		for (Stock s : arbrePlantes.get(f)) {
			stock += s.getQtt();			
		}
		return stock;
	}

	protected double prodParStep(Object p, int step) {
		// production non constante tout au long de la simulation
		// 2 mois de prod : fevrier et septembre
		double qttProd = 0;
		if ((  (step - 2) % 24 == 0 ) || (  (step - 16) % 24 == 0   )  ) {
			// les 1er fevrier et 1er septembre uniquement
			// on boucle sur les arbres pour obtenir la prod totale
			for (Stock s : arbrePlantes.get((Feve)p)) {
				double rdm = rendement( step - s.getStep(), (Feve)p);
				if (rdm>0) {
					//System.out.println("yes");
					qttProd += rdm; // prod de chaque arbre depend de son age
					//System.out.println(qttProd);
				}
			}
		}		
		//System.out.println(qttProd);
		return qttProd;
		// ancienne version 
		// return qttArbreToujoursPlantes((Feve)p) * prodParArbreParStep(p);
	}

	private double prodParArbre(Object p) {
		// prod par arbre par période de récolte ( 2 par an)
		// parametre p si on veut faire dependre la valeur du type de feve, ce n'est pour l'instant pas le cas
		return PROD_ARBRE_AN;
	}


	//il faut du temps avant que le nouvel arbre pousse et produise des fèves
	//car l’arbre ne produit pas immédiatement de cabosse et son rendement évolue au cours du temps
	// cela vainfluencer le nombre darbre qui produit effectivement
	protected double rendement(int step, Object p) {
		//System.out.println("rdm = " + step);
		// step correspond à l'age de larbre
		//Cacaotier durée de vie 40 ans, bonne culture à partir de 3 ans et rendement maximal à partir de 6 ans. 
		if (step<TPS_BON_RENDEMENT_ARBRE) {
			return 0;
		}else if(step < TPS_RENDEMENT_MAX_ARBRE) { // surement un pb ici
			double qttProd = prodParArbre(p) * ( 1.0 - (((double)(TPS_RENDEMENT_MAX_ARBRE - step))/((double)(TPS_RENDEMENT_MAX_ARBRE - TPS_BON_RENDEMENT_ARBRE) ) ) );
			return  qttProd;
		}else if(step<TPS_RENOUVELLEMENT_ARBRE) { // plein rendement
			return prodParArbre(p);
		}else {
			return 0;
		}
	}

	protected double qttQuiSeraProduite( int nbEcheance, Object p) {
		// retourne la qtt de fève que l'on va produire sur un certian nb de step
		int step = Filiere.LA_FILIERE.getEtape();
		int stepMax = step+ nbEcheance;
		double prod = 0;
		while (step<stepMax) {
			prod += prodParStep(p, step);
			step++;
		}
		return prod;
	}



}
