package abstraction.eq4Transformateur2;

import abstraction.fourni.Journal;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

//Tout le monde

public class Transformateur2Valeurs {
	
	public Transformateur2Valeurs() {
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
	protected double mini_stock_bas;
	protected double mini_stock_moyen;
	

	protected static double cout_stockage_unite_feve = 0.006;
	protected static double cout_stockage_unite_choco = 0.006 ;
	
	protected static double cout_fixe_entrepot_feve = 1000;
	protected static double cout_fixe_entrepot_choco = 1000;
	
	//Achat 
	
	protected static double cout_max_feve_basse = 2.3/1000 ; 
	protected static double cout_max_feve_moyenne = 2.5/1000;

	//Production
	
	protected static double charges_fixes = 0.0 ;
	protected static double capacite_production = 100000000000000000.0 ;
	protected static double cout_transformation = 0.5;
	
	//Vente
	
	protected double prix_min_tablette_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_tablette_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
	protected double prix_min_confiserie_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_confiserie_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
}
	