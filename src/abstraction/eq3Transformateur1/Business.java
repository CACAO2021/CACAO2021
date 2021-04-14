package abstraction.eq3Transformateur1;

import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;


// Paul GIRAUD

public class Business {
	
	
	
	private Stock stock;
	
	protected List<Variable> indicateurs;
	
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
		this.indicateurs.add(21,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(22,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(23,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(24,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(25,new Variable(this.getStock().getActeur().getNom() + " prixVenteConfiserieHauteBioEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(26,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(27,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(28,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(29,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(30,new Variable(this.getStock().getActeur().getNom() + " prixVenteTabletteHauteBioEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(31,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreBasse au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(32,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreMoyenne au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(33,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreMoyenneEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(34,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreHauteEquitable au KG", this.getStock().getActeur(), 0));
		this.indicateurs.add(35,new Variable(this.getStock().getActeur().getNom() + " prixVentePoudreHauteBioEquitable au KG", this.getStock().getActeur(), 0));
 
		
		
	}
	
	public Stock getStock() {
		return this.stock;
	}
	
	public double prixVente(Double quantite, Chocolat chocolat) {
		return quantite*this.getStock().prixDeVenteKG(chocolat);
	}
	
	public boolean sommeNousVendeur(Object produit) {
		return (this.getStock().getStockChocolats((Chocolat) produit) > 0);
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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
