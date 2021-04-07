package abstraction.eq6Distributeur1;

import java.util.List;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.IDistributeurChocolatDeMarque;

public class Vendeur extends Distributeur1Acteur implements IDistributeurChocolatDeMarque{

	@Override
	public List<ChocolatDeMarque> getCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double prix(ChocolatDeMarque choco) {
		// TODO Auto-generated method stub
		
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
