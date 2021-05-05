package abstraction.eq4Transformateur2;

import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

//Tout le monde

public class Transformateur2Valeurs {
	
	public Transformateur2Valeurs() {
		var_stock_feve_basse = new Variable("stock feve basse", (IActeur) this, quantite_init_feve_basse);
		var_stock_feve_moyenne = new Variable("stock feve moyenne", (IActeur) this, quantite_init_feve_moyenne);
		var_stock_tablette_basse = new Variable("stock tablette basse", (IActeur) this, quantite_init_tablette_basse);
		var_stock_tablette_moyenne = new Variable("stock tablette moyenne", (IActeur) this, quantite_init_tablette_moyenne);
		var_stock_confiserie_basse = new Variable("stock confiserie basse", (IActeur) this, quantite_init_confiserie_basse);
		var_stock_confiserie_moyenne = new Variable("stock conifserie moyenne", (IActeur) this, quantite_init_confiserie_moyenne);
	}
	
	protected LinkedList<Double> echeancier_basse;
	protected LinkedList<Double> echeancier_moyenne ;
	protected LinkedList<Double> echeancier_total ; 
	
	
	
	protected static double Prix_max_achat;
	
	protected LinkedList<ExemplaireContratCadre> contrats;
	protected int cryptogramme;
	protected Journal journal;
	
	//Stockage
	
	protected HashMap<Feve,Double> stock_feve;
	protected HashMap<Chocolat,Double> stock_chocolat;
	protected double mini_stock_bas = 10000.0;
	protected double mini_stock_moyen = 10000.0;
	
	protected double quantite_moyenne_demandee_echeancier_basse = -1;
	protected double quantite_moyenne_demandee_echeancier_moyenne = -1;
	
	protected static double cout_stockage_unite_feve = 0.006;
	protected static double cout_stockage_unite_choco = 0.006 ;
	
	protected static double cout_fixe_entrepot_feve = 1000;
	protected static double cout_fixe_entrepot_choco = 1000;
	
	//Achat 
	
	protected static double cout_max_feve_basse = 0.23 ; 
	protected static double cout_max_feve_moyenne = 0.25;

	//Production
	
	protected static double charges_fixes = 0.0 ;
	protected static double capacite_production = 100000000000000000.0 ;
	protected static double cout_transformation = 0.5;
	
	//Vente
	
	protected double prix_min_tablette_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_tablette_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
	protected double prix_min_confiserie_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_confiserie_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 

	/*Variables
	
	protected Variable var_stock_feve_basse = new Variable("STOCK_FEVE_BASSE", (IActeur) this, 0, 1000000, stock_feve.get(Feve.FEVE_BASSE));
	protected Variable var_stock_feve_moyenne = new Variable("STOCK_FEVE_MOYENNE", (IActeur) this, 0, 1000000, stock_feve.get(Feve.FEVE_MOYENNE));
	protected Variable var_stock_tablette_basse = new Variable("STOCK_TABLETTE_BASSE", (IActeur) this, 0, 1000000, stock_chocolat.get(Chocolat.TABLETTE_BASSE));
	protected Variable var_stock_tablette_moyenne = new Variable("STOCK_TABLETTE_MOYENNE", (IActeur) this, 0, 1000000, stock_chocolat.get(Chocolat.TABLETTE_MOYENNE));
	protected Variable var_stock_confiserie_basse = new Variable("STOCK_CONFISERIE_BASSE", (IActeur) this, 0, 1000000, stock_chocolat.get(Chocolat.CONFISERIE_BASSE));
	protected Variable var_stock_confiserie_moyenne = new Variable("STOCK_CONFISERIE_MOYENNE", (IActeur) this, 0, 1000000, stock_chocolat.get(Chocolat.CONFISERIE_MOYENNE));
*/

	protected Variable var_stock_feve_basse;
	protected Variable var_stock_feve_moyenne;
	protected Variable var_stock_tablette_basse;
	protected Variable var_stock_tablette_moyenne;
	protected Variable var_stock_confiserie_basse;
	protected Variable var_stock_confiserie_moyenne;
	
	protected static double quantite_init_feve_basse = 100000.0;
	protected static double quantite_init_feve_moyenne = 100000.0;
	protected static double quantite_init_tablette_basse = 100000.0;
	protected static double quantite_init_tablette_moyenne = 100000.0;
	protected static double quantite_init_confiserie_basse = 100000.0;
	protected static double quantite_init_confiserie_moyenne = 100000.0;
	

}
	