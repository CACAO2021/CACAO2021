package abstraction.eq3Transformateur1;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.IFabricantChocolatDeMarque;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Transformateur1FabricantChocolatDeMarque extends Transformateur1Acteur implements IFabricantChocolatDeMarque {

	private List<ChocolatDeMarque> chocolats;
	private String marque;
	
	public Transformateur1FabricantChocolatDeMarque() {
		super();

	}
		
	public String getMarque() {
		return this.marque;
	}
		
	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		return this.chocolats; }
	

}
