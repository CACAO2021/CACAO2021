package abstraction.eq8Romu.clients;

import java.util.Arrays;
import java.util.List;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.IMarqueChocolat;
/**
 * @author R. Debryune
 *
 * Un exemple simpliste d'un distributeur qui ne commercialise qu'une variete de chocolat, 
 * qui peut au plus mettre capaciteDeVente tonnes de chocolat en vente par step, et qui a
 * un prix fixe pour ce chocolat 
 */
public class ExempleDistributeurChocolatMarque extends ExempleAbsDistributeurChocolatMarque implements IDistributeurChocolatDeMarque, IMarqueChocolat {

	private double capaciteDeVente;
	private double[] prix;
	private String[] marques;

	public ExempleDistributeurChocolatMarque(ChocolatDeMarque[] chocos, double[] stocks, double capaciteDeVente, double[] prix, String[]marques) {
		super(chocos, stocks);
		this.capaciteDeVente = capaciteDeVente;
		this.prix = prix;
		this.marques = marques;
	}

	public List<ChocolatDeMarque> getCatalogue() {
		return Arrays.asList(chocolats);
	}

	public double prix(ChocolatDeMarque choco) {
		int pos= (Arrays.asList(chocolats).indexOf(choco));
		if (pos<0) {
			return 0.0;
		} else {
			return prix[pos];
		}
	}

	public double quantiteEnVente(ChocolatDeMarque choco) {
		int pos= (Arrays.asList(chocolats).indexOf(choco));
		if (pos<0) {
			return 0.0;
		} else {
			return Math.min(capaciteDeVente, stocksChocolats[pos].getValeur());
		}
	}

	public void notificationRayonVide(ChocolatDeMarque choco) {
		journal.ajouter(" Aie... j'aurais du mettre davantage de "+choco.name()+" en vente");
	}


	// On met 10% de ce tout ce qu'on met en vente (on pourrait mettre l'accente sur
	// un produit a promouvoir mais il s'agit ici d'un exemple simpliste
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		int pos= (Arrays.asList(chocolats).indexOf(choco));
		if (pos<0) {
			return 0.0;
		} else {
			return Math.min(capaciteDeVente, stocksChocolats[pos].getValeur())/10.0;
		}
	}

	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		int pos= (Arrays.asList(chocolats).indexOf(choco));
		if (pos>=0) {
			stocksChocolats[pos].retirer(this, quantite);
		}
	}


	public List<String> getMarquesChocolat() {
		return Arrays.asList(this.marques);
	}

}
