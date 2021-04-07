package abstraction.eq7Distributeur2;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import abstraction.fourni.*;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.contratsCadres.*;


public class Achat extends Distributeur2Acteur implements IAcheteurContratCadre {
	
	private Distributeur2Acteur wonka;
	private HashMap<ChocolatDeMarque, Variable> besoinsChoco;
	private double quantiteLimite, quantiteMax;
	private LinkedList<ExemplaireContratCadre> contrats;
	private SuperviseurVentesContratCadre supCCadre;
	

	public Achat(Distributeur2Acteur wonka) {
		this.wonka = wonka;
		this.besoinsChoco = new HashMap<ChocolatDeMarque,Variable>();
		this.quantiteLimite =  10;
		this.quantiteMax = 40;
		this.supCCadre = (SuperviseurVentesContratCadre) Filiere.LA_FILIERE.getActeur("supCCadre");
	}
	public void majDemande() {
		for(ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits()) {
			if(stocks.getStockChocolatDeMarque(choco) <= quantiteLimite) {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, quantiteMax - stocks.getStockChocolatDeMarque(choco)));
			}
			else {
				besoinsChoco.put(choco, new Variable("Quantité", wonka, 0));
			}
		}		
	}
	
	//cherche un nouveau contrat : peu importe le chocolat, et peu importe la quantité pour l'instant
	public void nouveauContrat() {
		for(ChocolatDeMarque choco : Filiere.LA_FILIERE.getChocolatsProduits() ) {
			LinkedList<IVendeurContratCadre> vendeurs = (LinkedList<IVendeurContratCadre>) supCCadre.getVendeurs(choco);
			int i = (int) (Math.random()*vendeurs.size()) ;
			IVendeurContratCadre vendeur = vendeurs.get(i);
			Echeancier echeancier = new Echeancier(Filiere.LA_FILIERE.getEtape()+1, 10, besoinsChoco.get(choco).getValeur()/10);
			supCCadre.demande((IAcheteurContratCadre)wonka, vendeur, choco, echeancier, wonka.getCryptogramme(), false);
		}
	}
	
	public Echeancier contrePropositionDeLAcheteur(ExemplaireContratCadre contrat) {
		return null;
	}

	
	public double contrePropositionPrixAcheteur(ExemplaireContratCadre contrat) {
		return 0;
	}


	public void receptionner(Object produit, double quantite, ExemplaireContratCadre contrat) {
		
		
	}



}
