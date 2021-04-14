package abstraction.eq4Transformateur2;

import abstraction.fourni.Journal;

import java.util.HashMap;
import java.util.LinkedList;

import abstraction.eq8Romu.produits.Feve;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;

//Antoine C

public class Transformateur2Valeurs {
	
	public Transformateur2Valeurs() {
	}
	protected LinkedList<Double> echeancier_tablette_basse;
	protected LinkedList<Double> echeancier_tablette_moyenne;
	protected LinkedList<Double> echeancier_confiserie_basse;
	protected LinkedList<Double> echeancier_confiserie_moyenne;
	protected LinkedList<Double> echeancier_total;
	
	protected LinkedList<ExemplaireContratCadre> contrats;
	protected int cryptogramme;
	protected Journal journal;
	
	//Stockage
	
	protected HashMap<Feve,Double> stock_feve;
	protected HashMap<Chocolat,Double> stock_chocolat;
	protected double mini_stock_bas;
	protected double mini_stock_moyen;
	
	protected static double cout_stockage_unite_feve;
	protected static double cout_stockage_unite_choco;
	
	protected static double cout_fixe_entrepot_feve;
	protected static double cout_fixe_entrepot_choco;
	
	//Achat 
	
	protected static double cout_max_feve_basse ; 
	protected static double cout_max_feve_moyenne;
	
	//Production
	
	protected static double charges_fixes;
	protected static double capacite_production;
	protected static double cout_transformation = 1.0;
	
	//Vente
	
	protected double prix_min_tablette_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_tablette_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
	protected double prix_min_confiserie_basse = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_basse; 
	protected double prix_min_confiserie_moyenne = cout_transformation + cout_stockage_unite_choco+ cout_stockage_unite_feve + cout_max_feve_moyenne; 
}
