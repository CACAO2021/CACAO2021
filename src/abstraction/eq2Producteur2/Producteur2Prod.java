package abstraction.eq2Producteur2;

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
	private LinkedList<Feve> listeProd; 
	
	public Producteur2Prod() {
		super();
		// il faudra tenir compte du fait que les arbres nont pas tous le meme age au début
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
		
		this.listeProd = new LinkedList<Feve>();
		this.listeProd.add(Feve.FEVE_HAUTE_BIO_EQUITABLE);
		this.listeProd.add(Feve.FEVE_HAUTE_EQUITABLE);
		this.listeProd.add(Feve.FEVE_HAUTE_EQUITABLE);
		this.listeProd.add(Feve.FEVE_MOYENNE_EQUITABLE);
		this.listeProd.add(Feve.FEVE_MOYENNE);
		this.listeProd.add(Feve.FEVE_BASSE);
		}
	
	/**
	 * @return the listeProd
	 */
	protected LinkedList<Feve> getListeProd() {
		return listeProd;
	}

	public void prod() {
		for (Object p : listeProd) {
			double qtt = prodParStep(p);
			addStock(qtt, p); 
			JournalProd.ajouter(""+ p +" "+qtt);	
			coutProd(qtt, p);
			majStock(p);
		}		
		}
	
	protected void coutProd(double qtt, Object p) {
		double cout = coutProdUnitaire(p) * qtt;
		perdreArgent(cout);
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
			if (step - s.getStep() == ARBRE_TPS_VIE_HBE) {
				//arbrePlantesHBE.remove(s);
				perdreArgent(COUT_CHANGEMENT_ARBRE_HBE);
			}}
		for (Stock s : arbrePlantesHE) {
			if (step - s.getStep() == ARBRE_TPS_VIE_HE) {
				//arbrePlantesHE.remove(s);
				perdreArgent(COUT_CHANGEMENT_ARBRE_HE);
			}}
		for (Stock s : arbrePlantesME) {
			if (step - s.getStep() == ARBRE_TPS_VIE_ME) {
				//arbrePlantesME.remove(s);
				perdreArgent(COUT_CHANGEMENT_ARBRE_ME);
			}}
		for (Stock s : arbrePlantesM) {
			if (step - s.getStep() == ARBRE_TPS_VIE_M) {
				//arbrePlantesM.remove(s);
				perdreArgent(COUT_CHANGEMENT_ARBRE_M);
			}}
		for (Stock s : arbrePlantesB) {
			if (step - s.getStep() == ARBRE_TPS_VIE_B) {
				//arbrePlantesB.remove(s);
				perdreArgent(COUT_CHANGEMENT_ARBRE_B);
			}}
	}
	
	private double prodParStep(Object p) {
		// prod en fonction de la qtt darbre plante
		//production réfléchie en fct de la demande a faire
		
		// a faire production ne va pas être constante tout au 
		//long de la simulation mais va plutôt varier selon les saisons et des paramètres aléatoires
		
		return qttArbre(p) * prodParArbre(p);
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
			return 0;
		}
	}
	
	public double qttArbre(Object produit) {
		// a prendre en compte pour la suite :
		
		//il faut du temps avant que le nouvel arbre pousse et produise des fèves
		//car l’arbre ne produit pas immédiatement de cabosse et son rendement évolue au cours du temps
		// cela vainfluencer le nombre darbre qui produit effectivement
		
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
