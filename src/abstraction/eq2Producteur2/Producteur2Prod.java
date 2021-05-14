package abstraction.eq2Producteur2;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;

//Emeline

public abstract class Producteur2Prod extends Producteur2Stockage {
	private LinkedList<Stock> arbrePlantesHBE;
	private LinkedList<Stock> arbrePlantesHE;
	private LinkedList<Stock> arbrePlantesME;
	private LinkedList<Stock> arbrePlantesM; 
	private LinkedList<Stock> arbrePlantesB;
	
	protected HashMap<Feve, LinkedList<Stock>> arbrePlantes;
	

	
	// ensemble fait par DIM
	
	public Producteur2Prod() {
		super();
		
		//On plante tous les arbres en respectant l'âge de début
		planterArbresAuDebut();		

		}
	
	//Dim
	public void planterArbresAuDebut() {
		
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
		int stepArbreLePlusVieu = TPS_RENOUVELLEMENT_ARBRE;
		int step = stepArbreLePlusVieu;
		while (step != stepActuel) {
			for (Feve f : listeProd) {
				arbrePlantes.get(f);
			}
			
			step++;
		}
		this.arbrePlantesHBE = new LinkedList<Stock>();
		this.arbrePlantesHBE.add(new Stock(ARBRE_DEBUT_HBE, 0)); 
		this.arbrePlantesHE = new LinkedList<Stock>();
		this.arbrePlantesHE.add(new Stock(ARBRE_DEBUT_HE, 0));
		this.arbrePlantesME = new LinkedList<Stock>();
		this.arbrePlantesME.add(new Stock(ARBRE_DEBUT_ME, 0));
		this.arbrePlantesM = new LinkedList<Stock>();
		this.arbrePlantesM.add(new Stock(ARBRE_DEBUT_M, 0));
		this.arbrePlantesB = new LinkedList<Stock>();
		this.arbrePlantesB.add(new Stock(ARBRE_DEBUT_B, 0));
		
		
	}

	public void prod() {
		for (Object p : listeProd) {
			double qtt = prodParStep(p)*12;
			if ((Filiere.LA_FILIERE.getMois()=="fevrier") || (Filiere.LA_FILIERE.getMois()=="septembre")  ) {
				addStock(qtt, p); 
				JournalProd.ajouter(""+ p +" "+qtt);	
				coutProd(qtt, p);
				majStock(p);
			}
		}		
		}
	
	protected void coutProd(double qtt, Object p) {
		double cout = coutProdUnitaire(p) * qtt;
		if (cout>0) {
			perdreArgent(cout);
		}
	}
	
	private double coutProdUnitaire(Object p) {  
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
		//Il va falloir s’adapter au marché afin de ne pas perdre de temps et d’argent à
		//produire des ressources dont personne ne veut et que nous allons devoir stocker pendant
		//une longue période sans pouvoir espérer de bénéfice.
		
		
		int step = Filiere.LA_FILIERE.getEtape();
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
	
	protected double prodParStep(Object p) {
		// prod en fonction de la qtt darbre plante
		//production réfléchie en fct de la demande a faire
		
		// a faire production ne va pas être constante tout au 
		//long de la simulation mais va plutôt varier selon les saisons et des paramètres aléatoires
		return qttArbre(p) * prodParArbre(p);
		// pour tenir compte du rnedement changeant
		// boucle a faire sur chaque arbre
		//utiliser  rendement (step, p);
	}

	private double prodParArbre(Object p) {	
		if(estFeveHBE(p)) {
			return PROD_HBE;
		} else if(estFeveHE(p)) {
			return PROD_HE;
		} else if(estFeveME(p)) {
			return PROD_ME;
		} else if(estFeveM(p)) {
			return PROD_M;
		}else if(estFeveB(p)) {
			return PROD_B;
		} else { // un produit que l'on ne vend pas
			System.out.println("pb");
			return 0;
		}
	}
	
	
	// a prendre en compte pour la suite :
	
	//il faut du temps avant que le nouvel arbre pousse et produise des fèves
	//car l’arbre ne produit pas immédiatement de cabosse et son rendement évolue au cours du temps
	// cela vainfluencer le nombre darbre qui produit effectivement
	
	
	// pas encore utilisé
	protected double rendement(int step, Object p) {
		// step correspond à l'age de larbre
		if (step<TPS_BON_RENDEMENT_ARBRE) {
			return 0;
		}else if(step < TPS_RENDEMENT_MAX_ARBRE) {
			return  prodParArbre(p) - ((TPS_RENDEMENT_MAX_ARBRE - step)/(TPS_RENDEMENT_MAX_ARBRE - TPS_BON_RENDEMENT_ARBRE+1) * prodParArbre(p));// fonctionnement OK
		}else if(step<TPS_RENOUVELLEMENT_ARBRE) {
			return prodParArbre(p);
		}else {
			return 0;
		}
	}
	
	public double qttArbre(Object produit) {		
		double nb = 0;
		if (estFeveHBE(produit)) {			 
			for (Stock s : this.arbrePlantesHBE) {
				nb += s.getQtt();
			}
		}else if (estFeveHE(produit)) {
			for (Stock s : this.arbrePlantesHE) {
				nb += s.getQtt();
			}
		}else if (estFeveME(produit)) {
			for (Stock s : this.arbrePlantesME) {
				nb += s.getQtt();
			}
		}else if (estFeveM(produit)) {
			for (Stock s : this.arbrePlantesM) {
				nb += s.getQtt();
			}
		}else if (estFeveB(produit)) {
			for (Stock s : this.arbrePlantesB) {
				nb += s.getQtt();
			}
		}		
		return nb;
		}
		
	
	

}
