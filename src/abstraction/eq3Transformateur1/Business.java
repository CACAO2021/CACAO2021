package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abstraction.eq8Romu.contratsCadres.Echeancier;
import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;


// Paul GIRAUD (À part méthode ou il y a un autre prénom)
public class Business {
	
	
	
	private Stock stock;
	protected List<Variable> indicateurs;
	
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeur;
	protected List<ExemplaireContratCadre> mesContratEnTantQueVendeurNonGere;
	protected List<ExemplaireContratCadre> mesContratEnTantQueAcheteur;
	protected Map<Feve, Triple> achats;

	
	
	public Business(Stock stock) {
		this.stock = stock;

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
		this.indicateurs.add(36,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de tablette basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(37,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de tablette moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(38,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de tablette moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(39,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de tablette haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(40,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de tablette haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(41,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de confiserie basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(42,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de confiserie moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(43,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de confiserie moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(44,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de confiserie haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(45,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de confiserie haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(46,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de poudre basse qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(47,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de poudre moyenne qualité", this.getStock().getActeur(), 0));
		this.indicateurs.add(48,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de poudre moyenne qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(49,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de poudre haute qualité equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(50,new Variable(this.getStock().getActeur().getNom() + " Stock Jeté de poudre haute qualité équitable et bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(51, new Variable(this.getStock().getActeur().getNom() + " Total des subventions versées aux associations ", this.getStock().getActeur(), 0));
		this.indicateurs.add(52, new Variable(this.getStock().getActeur().getNom() + " Proportion des subventions versées aux associations par rapport à nos entrées d'argent (%) ", this.getStock().getActeur(), 0));
		this.indicateurs.add(53, new Variable(this.getStock().getActeur().getNom() + " nb d'échéances moyen par contrat d'achat ", this.getStock().getActeur(), 0));
		this.indicateurs.add(54, new Variable(this.getStock().getActeur().getNom() + " nb d'échéances moyen par contrat de vente ", this.getStock().getActeur(), 0));
		this.indicateurs.add(55, new Variable(this.getStock().getActeur().getNom() + " proportion de vente de Bio", this.getStock().getActeur(), 0));
		this.indicateurs.add(56, new Variable(this.getStock().getActeur().getNom() + " proportion de vente de equitable", this.getStock().getActeur(), 0));
		this.indicateurs.add(57, new Variable(this.getStock().getActeur().getNom() + " proportion de vente de produit sans label ", this.getStock().getActeur(), 0));
		this.mesContratEnTantQueVendeur = new ArrayList<ExemplaireContratCadre>() ;
		this.mesContratEnTantQueAcheteur = new ArrayList<ExemplaireContratCadre>() ;	
		this.mesContratEnTantQueVendeurNonGere = new ArrayList<ExemplaireContratCadre>() ;
		
		this.achats = new HashMap<Feve, Triple>();
		for (Feve feve : this.getStock().nosFevesCC()) {
			this.achats.put(feve, new Triple(0, 1000000, false));
		}
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public double prixVente(Double quantite, Chocolat chocolat) {
		// on renvoie le prix au kg qui multiplie la quantite pour avoir le prix de la quantite
		return quantite*this.getStock().prixDeVenteKG(chocolat);
	}
	
	public boolean sommesNousVendeur(Object produit) {
		// on regarde si on a plus de 1000 de stock, car nous ne pouvons pas vendre en dessous de 1000 kg
		// on regarde si c'est un chocolat sans marque ou avec marque
		if (produit instanceof Chocolat) {
			return (this.getStock().getStockChocolats((Chocolat) produit) > 1000  && (this.dejaContrat((Chocolat)produit) == false));
		} else {
			if (produit instanceof ChocolatDeMarque) {
				return (this.getStock().getStockChocolats(((ChocolatDeMarque) produit).getChocolat()) > 1000 && (((ChocolatDeMarque) produit).getMarque() == "Eticao") && (this.dejaContrat(((ChocolatDeMarque)produit).getChocolat()) == false));
			} else {
				return false;
			}
		}
	}
	
	
	public List<Variable> getIndicateurs() {;
		return this.indicateurs;
}
	
	public void setIndicateurs() {
		// on met à jour tous les indicateurs
		Integer compteur = 0;
		for (Feve feve : this.getStock().nosFeves()) {
			// on set la valeur de votre variable avec la nouvelle valeur du stock de cette feve
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getStockFeves(feve));
			compteur += 1;
		}
		for(Chocolat chocolat: this.getStock().nosChocolats()) {
			// on set la valeur de votre variable avec la nouvelle valeur du stock de ce chocolat
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getStockChocolats(chocolat));
			compteur += 1;
		}
		// on set la valeur du prix de stockage
		this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getPrixStockage().getValeur());
		
		for (Chocolat chocolat : this.getStock().nosChocolats()) {
			compteur +=1;
			// On set la valeur du prix de vente au kg de ce chocolat
			this.getStock().getActeur().getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().prixDeVenteKG(chocolat));
		}
		
		for (Chocolat chocolat : this.getStock().nosChocolats()) {
			compteur +=1;

			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getStockJete(chocolat));
		}
		compteur += 1;
		this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getSubvention());
		
		compteur += 1;
		if (this.getStock().getActeur().getBenefice() != 0) {
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), (this.getStock().getActeur().getSubvention()/this.getStock().getActeur().getBenefice())*100);
		} else {
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(),0);
		}
		compteur += 1;
		this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getEcheancierAchatMoy());
		compteur += 1;
		this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getEcheancierVenteMoy());
		compteur += 1;
		if (this.getStock().getActeur().getQuantiteClassique() + this.getStock().getActeur().getQuantiteBio() + this.getStock().getActeur().getQuantiteEquitable() != 0) {
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getQuantiteBio()/(this.getStock().getActeur().getQuantiteClassique() + this.getStock().getActeur().getQuantiteBio() + this.getStock().getActeur().getQuantiteEquitable()));
			compteur += 1;
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getQuantiteEquitable()/(this.getStock().getActeur().getQuantiteClassique() + this.getStock().getActeur().getQuantiteBio() + this.getStock().getActeur().getQuantiteEquitable()));
			compteur += 1;
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), this.getStock().getActeur().getQuantiteClassique()/(this.getStock().getActeur().getQuantiteClassique() + this.getStock().getActeur().getQuantiteBio() + this.getStock().getActeur().getQuantiteEquitable()));
			
		} else {
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), 0);
			compteur += 1;
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), 0);
			compteur += 1;
			this.getIndicateurs().get(compteur).setValeur(this.getStock().getActeur(), 0);			
		}

	}
	
	public void setMesContratEnTantQueVendeur(ExemplaireContratCadre contrat) {
		//  on ajout nos nouveaux contrats à notre liste
		this.mesContratEnTantQueVendeur.add(contrat);
		this.getStock().getActeur().journalVendeur.ajouter("Nouveau Contrat :"+contrat);
	}
	
	public void miseAJourContratVendeur() {
		// on supprime nos contrats obseletes (tout a été payé et tout a été livré)
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

	public List<ExemplaireContratCadre> getMesContratEnTantQueAcheteur() {
		return this.mesContratEnTantQueAcheteur;
	}
	
	
	public void setMesContratEnTantQueAcheteur(ExemplaireContratCadre contrat) {
		//  on ajout nos nouveaux contrats à notre liste
		this.mesContratEnTantQueAcheteur.add(contrat);
		this.getStock().getActeur().journalAcheteur.ajouter("Nouveau Contrat :"+contrat);
	}
	
	
	public void miseAJourContratAcheteur() {
		// on supprime nos contrats obseletes (tout a été payé et tout a été livré)
		ArrayList<ExemplaireContratCadre> contratsObsoletes = new ArrayList<ExemplaireContratCadre>() ;
		for(ExemplaireContratCadre contrat : this.mesContratEnTantQueAcheteur) {
			if(contrat.getQuantiteRestantALivrer()== 0.0 && contrat.getMontantRestantARegler()==0.0) {
				contratsObsoletes.add(contrat);
			}
		}
		this.mesContratEnTantQueAcheteur.removeAll(contratsObsoletes);
		for(ExemplaireContratCadre contratobselete : contratsObsoletes ) {
			this.getStock().getActeur().journalAcheteur.ajouter("Contrat terminé :"+contratobselete);
		}
	}
	
	//Chloe Jo Paul
	public boolean dejaContrat(Chocolat chocolat) {
		for (ExemplaireContratCadre contrat: this.getMesContratEnTantQueVendeur()) {
			if (contrat.getProduit() instanceof Chocolat) {
				if (contrat.getProduit().equals(chocolat)) {
					return true;
				}
			} else if (contrat.getProduit() instanceof ChocolatDeMarque){
				if(((ChocolatDeMarque) contrat.getProduit()).getChocolat().equals(chocolat)) {
					return true;
				}
			}
		}
		return false;
	}

	// si on a un stock positif a acheter on passe le boolean sur true, et on ajoute au stock a acheter le stock qu'on va recevoir avec le nouveau contrat
	public void miseAJourVente(ExemplaireContratCadre contrat) {
		Chocolat chocolat = null;
		if (contrat.getProduit() instanceof Chocolat) {
			chocolat = ((Chocolat)contrat.getProduit());
		} else {
			chocolat = ((ChocolatDeMarque)contrat.getProduit()).getChocolat();
		}
		Feve feve = this.getStock().equivalentFeve(chocolat);
		
		Triple triple = this.achats.get(feve);
		double stock = triple.get1() + contrat.getQuantiteTotale()/2.5; 
		int duree = Math.min(triple.get2(), contrat.getEcheancier().getNbEcheances()); 
		if (stock >0 && duree > 0) {
			triple.set3(true);
		}
		else {
			triple.set3(false);
		}
		triple.set1(stock);
		triple.set2(duree);
		this.achats.replace(feve, triple);
		
	}
	
	// si on a un stock positif a acheter on passe le boolean sur true, et on retire du stock a acheter le stock qu'on va recevoir avec le nouveau contrat
	public void miseAJourAchat(ExemplaireContratCadre contrat) {
		Feve feve = (Feve)contrat.getProduit();
		Triple triple = this.achats.get(feve);
		double stock = triple.get1() - contrat.getQuantiteTotale(); 
		int duree = Math.min(triple.get2(), contrat.getEcheancier().getNbEcheances()); 
		if (stock >0 && duree > 0) {
			triple.set3(true);
		}
		else {
			triple.set3(false);
		}
		triple.set1(stock);
		triple.set2(duree);
		this.achats.replace(feve, triple);
	}
	
	//retourne un triple pour chaque feve contenant la qantité à acheter, le nbDecheance qu'on aimerait bien avoir et un boolean qui dit si on doit acheter ou pas
	public Map<Feve, Triple> getAchats() {
		return this.achats; 
	}
	

}


