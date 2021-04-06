package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public interface IStocks {
	
	public void initialiser();
	
	public double getStockChocolatDeMarque(ChocolatDeMarque chocolatDeMarque);
	
	public void ajouterChocolatDeMarque(ChocolatDeMarque chocolatDeMarque ,double quantité );
	
	public void supprimerChocolatDeMarque(ChocolatDeMarque chocolatDeMarque ,double quantité );
	
	public void jeterChocolatPerime();
	
	public void getCoutStockage();
	
	
	
	
		
}
