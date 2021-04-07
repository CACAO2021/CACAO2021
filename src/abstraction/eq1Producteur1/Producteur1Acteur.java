package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Producteur1Acteur implements IActeur {
	private int cryptogramme;
	private Stock stock_F_M_E;
	private Stock stock_F_M;
	private Stock stock_F_B;
	private Stock stock_P_M_E;
	private Stock stock_P_M;
	private Transformation transformation;
	protected HashMap<Object, Stock> stocks; //dictionnaire qui contient tous nos stocks.
	protected int step_actuel;
	private List<VenteAO> historique_AO_F_M; //historique des appels d'offre pour les fèves de moyenne qualité non équitable.(0.0 : pas de vente, !=0 : vente à ce prix.)
	private List<VenteAO> historique_AO_F_B; //historique des appels d'offre pour les fèves de basse qualité non équitable. idem
	protected HashMap<Feve,List<VenteAO>> historiques; //dictionnaire qui contient les historiques de ventes par AO.
	protected JournauxEq1 journaux;
	
	public Producteur1Acteur() {
		this.init_stocks(this);
		this.init_historiques();
		this.step_actuel = 0;
		this.journaux = new JournauxEq1();
		

	}

	public void initialiser() {
		this.journaux.addJournal(3);
		transformation = new Transformation();

	}
	/**
	 * @author Alb1x
	 * On crée un stock pour chaque chose que l'on produit.
	 * On range ensuite les stock dans un dictionnaire stocks.
	 */
	private void init_stocks(IActeur a) {
		this.stock_F_M_E = new Stock("Stock F_M_E",0, a); //stock que l'on possède au départ
		this.stock_F_M = new Stock("Stock F_M",0, a);
		this.stock_F_B = new Stock("Stock F_B",0, a);
		this.stock_P_M_E = new Stock("Stock P_M_E",0, a);
		this.stock_P_M = new Stock("Stock P_M",0, a);
		this.stocks = new HashMap<Object, Stock>();
		this.stocks.put(Feve.FEVE_MOYENNE_EQUITABLE, stock_F_M_E);
		this.stocks.put(Feve.FEVE_MOYENNE, stock_F_M);
		this.stocks.put(Feve.FEVE_BASSE, stock_F_B);
		this.stocks.put(Chocolat.POUDRE_MOYENNE_EQUITABLE, stock_P_M_E);
		this.stocks.put(Chocolat.POUDRE_MOYENNE, stock_P_M);
	}
	/**
	 * @author Alb1x
	 * On crée un historique de vente par AO pour chaque fève que l'on vend par AO.
	 * On range ensuite les historiques dans un dictionnaire historiques.
	 */
	private void init_historiques() {
		this.historique_AO_F_M  = new ArrayList<VenteAO>();
		this.historique_AO_F_B  = new ArrayList<VenteAO>();
		this.historiques = new HashMap<Feve,List<VenteAO>>();
		this.historiques.put(Feve.FEVE_MOYENNE, this.historique_AO_F_M);
		this.historiques.put(Feve.FEVE_BASSE, historique_AO_F_B);
	}
	/**
	 * @author Alb1x
	 */
	private void stepSuivant() {
		this.step_actuel += 1;
	}

	/**
	 * @author Alb1x
	 * On rajoute une vente non conclue, cela sera changé si une vente est conclue au cours du step.
	 */
	private void majHist_AO() {
		this.historique_AO_F_M.add(new VenteAO());
		this.historique_AO_F_B.add(new VenteAO());
	}
	/**
	 * @author Alb1x
	 */
	private void produireFeve() {
		this.stocks.get(Feve.FEVE_MOYENNE_EQUITABLE).addQuantite(22500000);
		this.stocks.get(Feve.FEVE_MOYENNE).addQuantite(67500000);
		this.stocks.get(Feve.FEVE_BASSE).addQuantite(60000000);
	}
	
	protected HashMap<Object, Stock> getStocks() {
		return stocks;
	}

	public String getNom() {
		return "EQ1";
	}

	public String getDescription() {
		return "Producteur Ghanao";
	}

	public Color getColor() {
		return new Color(26, 188, 156);
	}


	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}


	public void next() {
		this.stepSuivant();
		this.majHist_AO();
		this.produireFeve();
		this.perteargent(500);//Coûts fixes
		this.transformation.Transformation_Feve(this);
	}

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		for (Stock stck : stocks.values()) { //On ajoute les valeurs des stocks.
			res.add(stck.getVariable());
		}
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
	}

	public List<Journal> getJournaux() {
		return journaux.getJournaux();
	}

	public void notificationFaillite(IActeur acteur) {
		if (this==acteur) {
			System.out.println("I'll be back... or not... "+this.getNom());
		} else {
			System.out.println("Poor "+acteur.getNom()+"... We will miss you. "+this.getNom());
		}
	}

	public void notificationOperationBancaire(double montant) {
	}

	// Renvoie le solde actuel de l'acteur
	public double getSolde() {
		return Filiere.LA_FILIERE.getBanque().getSolde(this, this.cryptogramme);
	}
	
	/**
	 * @author arthurlemgit
	 * Pour les coûts fixes et de transformation
	 */
	protected void perteargent(double quantite) {
		if (quantite>0) {
			Filiere.LA_FILIERE.getBanque().virer(Filiere.LA_FILIERE.getActeur("EQ1"), this.cryptogramme, Filiere.LA_FILIERE.getBanque(),quantite );
		}
	}
} 