package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;


// Paul GIRAUD
public class Business {
	
	
	
	private Stock stock;
	private double QUANTITE_INI = 10000;
	protected List<Variable> indicateurs;
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;
	protected List<ExemplaireContratCadre> mesContratEnTantQueAcheteur;
	private Variable prixVenteTabletteBasse;
	private Variable prixVenteTabletteMoyenne;
	private Variable prixVenteTabletteMoyenneEquitable;
	private Variable prixVenteTabletteHauteEquitable;
	private Variable prixVenteTabletteHauteBioEquitable;
	
	private Variable prixVenteConfiserieBasse;
	private Variable prixVenteConfiserieMoyenne;
	private Variable prixVenteConfiserieMoyenneEquitable;
	private Variable prixVenteConfiserieHauteEquitable;
	private Variable prixVenteConfiserieHauteBioEquitable;
	
	private Variable prixVentePoudreBasse;
	private Variable prixVentePoudreMoyenne;
	private Variable prixVentePoudreMoyenneEquitable;
	private Variable prixVentePoudreHauteEquitable;
	private Variable prixVentePoudreHauteBioEquitable;
	
	
	public Business(Stock stock) {
		this.stock = stock;
		
		this.prixVenteConfiserieBasse = new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieBasse au KG", this.getStock().getActeur(), 0);
		this.prixVenteConfiserieMoyenne = new Variable(this.getStock().getActeur().getNom() + "prixVenteConfiserieMoyenne au KG", this.getStock().getActeur(), 0);
		this.prixVenteConfiserieMoyenneEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteConfiserieMoyenneEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVenteConfiserieHauteEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteConfiserieHauteEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVenteConfiserieHauteBioEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteConfiserieHauteBioEquitable au KG", this.getStock().getActeur(), 0);
		
		this.prixVenteTabletteBasse = new Variable(this.getStock().getActeur().getNom() + "prixVenteTabletteBasse au KG", this.getStock().getActeur(), 0);
		this.prixVenteTabletteMoyenne = new Variable(this.getStock().getActeur().getNom() + "prixVenteTabletteMoyenne au KG", this.getStock().getActeur(), 0);
		this.prixVenteTabletteMoyenneEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteTabletteMoyenneEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVenteTabletteHauteEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteTabletteHauteEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVenteTabletteHauteBioEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVenteTabletteHauteBioEquitable au KG", this.getStock().getActeur(), 0);
		
		this.prixVentePoudreBasse = new Variable(this.getStock().getActeur().getNom() + "prixVentePoudreBasse au KG", this.getStock().getActeur(), 0);
		this.prixVentePoudreMoyenne = new Variable(this.getStock().getActeur().getNom() + "prixVentePoudreMoyenne au KG", this.getStock().getActeur(), 0);
		this.prixVentePoudreMoyenneEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVentePoudreMoyenneEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVentePoudreHauteEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVentePoudreHauteEquitable au KG", this.getStock().getActeur(), 0);
		this.prixVentePoudreHauteBioEquitable = new Variable(this.getStock().getActeur().getNom() + "prixVentePoudreHauteBioEquitable au KG", this.getStock().getActeur(), 0);
		
		this.indicateurs = new ArrayList<Variable>();
		
		this.indicateurs.add(0,new Variable(this.getStock().getActeur().getNom() + " Stock fève basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(1,new Variable(this.getStock().getActeur().getNom() + " Stock fève moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(2,new Variable(this.getStock().getActeur().getNom() + " Stock fève moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(3,new Variable(this.getStock().getActeur().getNom() + " Stock fève haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(4,new Variable(this.getStock().getActeur().getNom() + " Stock fève haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(5,new Variable(this.getStock().getActeur().getNom() + " Stock tablette basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(6,new Variable(this.getStock().getActeur().getNom() + " Stock tablette moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(7,new Variable(this.getStock().getActeur().getNom() + " Stock tablette moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(8,new Variable(this.getStock().getActeur().getNom() + " Stock tablette haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(9,new Variable(this.getStock().getActeur().getNom() + " Stock tablette haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(10,new Variable(this.getStock().getActeur().getNom() + " Stock confiserie basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(11,new Variable(this.getStock().getActeur().getNom() + " Stock confiserie moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(12,new Variable(this.getStock().getActeur().getNom() + " Stock confiserie moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(13,new Variable(this.getStock().getActeur().getNom() + " Stock confiserie haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(14,new Variable(this.getStock().getActeur().getNom() + " Stock confiserie haute qualité équitable et bio", this.getStock().getActeur(), 0));		
		this.indicateurs.add(15,new Variable(this.getStock().getActeur().getNom() + " Stock poudre basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(16,new Variable(this.getStock().getActeur().getNom() + " Stock poudre moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(17,new Variable(this.getStock().getActeur().getNom() + " Stock poudre moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(18,new Variable(this.getStock().getActeur().getNom() + " Stock poudre haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(19,new Variable(this.getStock().getActeur().getNom() + " Stock poudre haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(20,new Variable(this.getStock().getActeur().getNom() + " Prix de stockage", this.getStock().getActeur(), 1000));
		this.indicateurs.add(21,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(22,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(23,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(24,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(25,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteHauteBioEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(26,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(27,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(28,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(29,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(30,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieHauteBioEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(31,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(32,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(33,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(34,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(35,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreHauteBioEquitable au KG", this.getStock().getActeur(), 0));
 
		this.mesContratEnTantQueVendeur = new ArrayList<ExemplaireContratCadre>() ;
		this.mesContratEnTantQueAcheteur = new ArrayList<ExemplaireContratCadre>() ;		
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public double prixVente(Double quantite, Chocolat chocolat) {
		return quantite*this.getStock().prixDeVenteKG(chocolat);
	}
	
	public boolean sommesNousVendeur(Object produit) {
		if (produit instanceof Chocolat) {
			return (this.getStock().getStockChocolats((Chocolat) produit) > 0);
		}
		else return false;
	}
	
	public void venteDeChocolat() {

	}
	
	public List<Variable> getIndicateurs() {;
	return this.indicateurs;
}
	
	public void setIndicateurs() {
		Integer compteur = 0;
		for (Feve feve : this.getStock().nosFeves()) {
			this.getStock().getActeur().getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getStockFeves(feve));
			compteur += 1;
		}
		for(Chocolat chocolat: this.getStock().nosChocolats()) {
			this.getStock().getActeur().getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getStockChocolats(chocolat));
			compteur += 1;
		}
		this.getStock().getActeur().getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getPrixStockage().getValeur());
		
		for (Chocolat chocolat : this.getStock().nosChocolats()) {
			compteur +=1;
			this.getStock().getActeur().getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().prixDeVenteKG(chocolat));
		}
	}
	
	public void setMesContratEnTantQueVendeur(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueVendeur.add(contrat);
		this.getStock().getActeur().journalVendeur.ajouter("Nouveau Contrat :"+contrat);
	}
	
	public void miseAJourContratVendeur() {
		ArrayList<ExemplaireContratCadre> contratsObsoletes = new ArrayList<ExemplaireContratCadre>() ;
		for(ExemplaireContratCadre contrat : this.mesContratEnTantQueVendeur) {
			if(contrat.getQuantiteRestantALivrer()== 0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueVendeur.removeAll(contratsObsoletes);
		for(ExemplaireContratCadre contratobselete : contratsObsoletes ) {
			this.getStock().getActeur().journalVendeur.ajouter("Contrat terminé :"+contratobselete);
		}
	}
	
	public List<ExemplaireContratCadre> getMesContratEnTantQueVendeur() {
		return this.mesContratEnTantQueVendeur;
	}
	
	public void ajoutContratEnTantQueAcheteur(ExemplaireContratCadre contrat) {
		this.mesContratEnTantQueAcheteur.add(contrat);
		this.getStock().getActeur().journalAcheteur.ajouter("Nouveau contrat :"+contrat);
	}
	
	public void miseAJourContratAcheteur() {
		ArrayList<ExemplaireContratCadre> contratsObsoletes = new ArrayList<ExemplaireContratCadre>() ;
		for(ExemplaireContratCadre contrat : this.mesContratEnTantQueAcheteur) {
			if(contrat.getQuantiteRestantALivrer()==0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueAcheteur.removeAll(contratsObsoletes);
		for(ExemplaireContratCadre contratobselete : contratsObsoletes ) {
			this.getStock().getActeur().journalAcheteur.ajouter("Contrat terminé :"+contratobselete);
		}
	}
	
	public List<ExemplaireContratCadre> getContractsCadresAcheteur() {
		return this.mesContratEnTantQueAcheteur;
	}
	
	public Map<Chocolat, Double> stockAFournir(int step) {
		Map<Chocolat, Double> stockafournir = new HashMap<Chocolat, Double>();
		for (Chocolat chocolat : this.getStock().nosChocolats()) {
			stockafournir.put(chocolat, 0.0);
		}
		for (ExemplaireContratCadre contrat : this.getMesContratEnTantQueVendeur()) {
			if (contrat.getProduit() instanceof Chocolat) {
				Double ancienstock = stockafournir.get((Chocolat) contrat.getProduit());
				stockafournir.replace((Chocolat)contrat.getProduit(), contrat.getEcheancier().getQuantite(step)+ancienstock);
			}
		}
		return stockafournir; 
	}
	
	public Map<Feve, Double> stockARecevoir(int step) {
		Map<Feve, Double> stockarecevoir = new HashMap<Feve, Double>();
		for (Feve feve : this.getStock().nosFeves()) {
			stockarecevoir.put(feve, 0.0);
		}
		for (ExemplaireContratCadre contrat : this.getContractsCadresAcheteur() ){
			if (contrat.getProduit() instanceof Feve) {
				Double ancienstock = stockarecevoir.get((Feve) contrat.getProduit());
				stockarecevoir.replace((Feve)contrat.getProduit(), contrat.getEcheancier().getQuantite(step)+ancienstock);
			}
		}
		return stockarecevoir; 
	}
	
	public double differenceStockArrivePart(Feve feve, int step) {
		return this.stockARecevoir(step).get(feve) - this.stockAFournir(step).get(this.getStock().equivalentConfiserieFeve(feve)) - this.stockAFournir(step).get(this.getStock().equivalentTabletteFeve(feve)) - this.stockAFournir(step).get(this.getStock().equivalentPoudreFeve(feve));
	}
	
	public  Map<Feve, Double> listeDifferenceStockArrivePart() {
		Map<Feve, Double> listedifstock = new HashMap<Feve, Double>(); 
		for (Feve feve : this.getStock().nosFeves()) {
			double difference = 0;
			for (int i = 1; i < 11; i++) {
				difference += this.differenceStockArrivePart(feve, i);
			}
			listedifstock.put(feve, difference);
			
		}
		return listedifstock;
	}
	
	public Map<Feve, Double> quantitefeveAAcheter() {
		Map<Feve, Double> stockaacheter = new HashMap<Feve, Double>(); 
		for (Feve feve : this.getStock().nosFeves()) {
			double diff = this.listeDifferenceStockArrivePart().get(feve);
			if (diff < 0) {
				stockaacheter.put(feve,this.listeDifferenceStockArrivePart().get(feve)*1.5);
			} else if (diff == 0) {
				stockaacheter.put(feve, QUANTITE_INI);
			}
		}
		return stockaacheter;
	}
	
	public ArrayList<Feve> feveAAcheter() {
		ArrayList<Feve> listefeve = new ArrayList<Feve>();
		for (Feve feve : this.getStock().nosFeves()) {
			if (this.listeDifferenceStockArrivePart().get(feve) <= 0) {
				listefeve.add(feve);
			} 
		}
		return listefeve;
	}

}
