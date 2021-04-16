package abstraction.eq7Distributeur2;

import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;

public class FiliereTestContratCadreWS extends Filiere{
	
	private SuperviseurVentesContratCadre superviseurCC;
	private static final double DISTRIBUTIONS_ANNUELLES_CONF[][] = {
			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
			{ 3.5, 3.5, 6.0, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 9.0, },			
			{ 3.0, 3.0, 6.0, 3.0, 3.0, 3.0, 3.0, 3.0, 9.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0, 7.0, 3.0, 3.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
			{ 3.0, 3.0,10.0, 3.0, 3.0, 3.0, 3.0, 3.0,12.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0,11.0, 3.0, 3.0, 3.0, 3.0, 3.0,13.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
	};

	public FiliereTestContratCadreWS() {

			super();
			this.ajouterActeur(new Distributeur2());
			this.ajouterActeur(new ExempleTransformateurContratCadreVendeurWS(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,"test1")));
			this.ajouterActeur(new ClientFinal(Categorie.CONFISERIE, 1627200000.0, 50.0, DISTRIBUTIONS_ANNUELLES_CONF));
//			this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,"test2")));
//			this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE,"test3")));
//			this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.POUDRE_HAUTE_BIO_EQUITABLE,"test4")));
//			this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE,"test5")));
//			this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE,"test6")));

			this.superviseurCC=new SuperviseurVentesContratCadre();
			this.ajouterActeur(this.superviseurCC);
			
		}
}
