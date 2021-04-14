package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Banque;
import abstraction.fourni.IDistributeurChocolatDeMarque;
import abstraction.fourni.Variable;

public class Vendeur extends Stocks implements IDistributeurChocolatDeMarque{
	

	@Override
	public List<ChocolatDeMarque> getCatalogue() {
		Set<ChocolatDeMarque> Catalogue = stock.keySet();
		List<ChocolatDeMarque> c = new ArrayList<>(Catalogue);
		return c;
	}

	@Override
	public double prix(ChocolatDeMarque choco) {
		if(choco!=null) {
			return prix.get(choco);
		}
		return 0;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stock.get(choco).getValeur();
		}
		else {
			return 0;
		}
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		if (choco!=null) {
			return this.stockTG.get(choco).getValeur();
		}
		else {
			return 0;
		}  
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		List<String> historique = new ArrayList<String>();
		vendre(client, choco, quantite, montant, historique);
	}
	
	public List<String> vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant, List<String> historique) {
		if(choco!=null && quantite>0 && quantite<this.quantiteEnVente(choco)) {
			this.ajouterStock(choco, -1*quantite, false);
			historique.add(choco.getMarque()+" : "+quantite);
			return historique;
		}else {
			return historique;
		}
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco) {
		if (quantiteEnVente(choco)==0) {
			System.out.println("Plus de : "+ choco.getMarque()+" en rayon");
		}
	}	
}
