package abstraction.eq5Transformateur3;

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

//Léna

public abstract class Transformateur3Fabricant extends Transformateur3Acteur implements IFabricantChocolatDeMarque {
	private List<ChocolatDeMarque> chocolats;
	private String marque;
	
	public Transformateur3Fabricant() {
		this.marque = "Côte d'IMT";
		this.chocolats = new LinkedList<ChocolatDeMarque>();
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, marque ));
		chocolats.add(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE, marque));
		chocolats.add(new ChocolatDeMarque(Chocolat.CONFISERIE_MOYENNE, marque));}
	
	public String getMarque() {
		return this.marque;
	}
		
	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		return this.chocolats; }
	
}