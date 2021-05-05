package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import abstraction.eq2Producteur2.Producteur2Valeurs;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class Producteur1Acteur extends Producteur2Valeurs implements IActeur {
	private int cryptogramme;
	private Color couleur = new Color(26, 188, 156);
	private Transformation transformation;
	protected int step_actuel;
	private List<VenteAO> historique_AO_F_M; //historique des appels d'offre pour les fèves de moyenne qualité non équitable.(0.0 : pas de vente, !=0 : vente à ce prix.)
	private List<VenteAO> historique_AO_F_B; //historique des appels d'offre pour les fèves de basse qualité non équitable. idem
	protected HashMap<Feve,List<VenteAO>> historiques; //dictionnaire qui contient les historiques de ventes par AO.

	
	public Producteur1Acteur() {
		this.init_historiques();
		this.step_actuel = 0;
		
	}
	
	public Producteur1Acteur(boolean b) {
		
	}
	


	public void initialiser() {
		transformation = new Transformation();

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
	 * @author lebra pour l'ajout dans le journal
	 */
	private void produireFeve() {
		this.getStocks().get(Feve.FEVE_MOYENNE_EQUITABLE).addQuantite(1000000);
		this.getStocks().get(Feve.FEVE_MOYENNE).addQuantite(48333000);
		this.getStocks().get(Feve.FEVE_BASSE).addQuantite(48333000);
		this.getJournal(0).ajouter("Ajout de 1000000 fèves de qualité moyenne équitable");
		this.getJournal(0).ajouter("Ajout de 48333000 fèves de qualité moyenne ");
		this.getJournal(0).ajouter("Ajout de 48333000 fèves de qualité basse");
	}
	
	public String getNom() {
		return "EQ1";
	}

	public String getDescription() {
		return "Producteur Ghanao";
	}

	public Color getColor() {
		return couleur;
	}


	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}


	public void next() {
		this.stepSuivant();
		this.majHist_AO();
		this.produireFeve();
		Cout.cout(this); // coût proportionel à la qualité et à la quantité de fèves produites
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
		for (Stock stck : this.getStocks().values()) { //On ajoute les valeurs des stocks.
			res.add(stck.getVariable());
		}
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		return res; 
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
	/**
	 * @param i
	 * @return
	 */
	public abstract Journal getJournal(int i);
	public abstract List<Journal> getJournaux();
	public abstract Stock getStock(Object o);
	public abstract HashMap<Object, Stock> getStocks();
} 