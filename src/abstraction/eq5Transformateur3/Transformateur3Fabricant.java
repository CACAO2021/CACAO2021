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

public class Transformateur3Fabricant extends Transformateur3Acteur implements IFabricantChocolatDeMarque {
	private List<ChocolatDeMarque> chocolats;
	
	public Transformateur3Fabricant(ChocolatDeMarque choco) {
		this.chocolats = new LinkedList<ChocolatDeMarque>();}
	
	@Override
	public List<ChocolatDeMarque> getChocolatsProduits() {
		return this.chocolats; }
	
}