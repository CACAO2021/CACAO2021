package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IFabricantChocolatDeMarque;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;


// Jonathan
public class Transformateur1FabricantChocolatDeMarque extends Transformateur1Acteur implements IFabricantChocolatDeMarque {

	private List<ChocolatDeMarque> chocolats;
	private String marque;
	
	public Transformateur1FabricantChocolatDeMarque() {
		super();
		this.marque="Eticao";
		this.chocolats=new LinkedList<ChocolatDeMarque> ();
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_MOYENNE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.POUDRE_MOYENNE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_HAUTE_EQUITABLE,marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE_EQUITABLE,marque));
		
		

	}
		
	public String getMarque() {
		return this.marque;
	}
		
	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		return this.chocolats; }
	

}


