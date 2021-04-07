package abstraction.eq6Distributeur1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;

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
			return get
		}
		// TODO Auto-generated method stub
		//// a revoir 
		return 0;
	}

	@Override
	public double quantiteEnVente(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double quantiteEnVenteTG(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void vendre(ClientFinal client, ChocolatDeMarque choco, double quantite, double montant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationRayonVide(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		
	}

}
