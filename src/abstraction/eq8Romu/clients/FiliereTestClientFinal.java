package abstraction.eq8Romu.clients;

import abstraction.eq8Romu.produits.Categorie;
//import abstraction.eq8Romu.chocolatBourse.ExempleVendeurChocolatBourse;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;

public class FiliereTestClientFinal extends Filiere {
	private static final double DISTRIBUTIONS_ANNUELLES_CONF[][] = {
			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
			{ 3.5, 3.5, 6.0, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 9.0, 9.0, },			
			{ 3.0, 3.0, 6.0, 3.0, 3.0, 3.0, 3.0, 3.0, 9.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0, 7.0, 3.0, 3.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
			{ 3.0, 3.0,10.0, 3.0, 3.0, 3.0, 3.0, 3.0,12.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0,15.0,15.0, },			
			{ 3.0, 3.0,11.0, 3.0, 3.0, 3.0, 3.0, 3.0,13.0, 3.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 3.0, 3.0,10.0, 3.0, 3.0,11.0,10.0, },			
	};
	private static final double DISTRIBUTIONS_ANNUELLES_TABL[][] = {
			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
			{ 4.5, 4.5, 4.5, 4.5, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 4.5, 4.5, 4.5, 4.5, },			
			{ 5.5, 5.5, 5.0, 5.0, 4.5, 4.0, 4.0, 4.0, 4.0, 3.5, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.5, 4.0, 4.0, 4.5, 5.0, 5.0, 5.5, 5.5, },			
	};
	private static final double DISTRIBUTIONS_ANNUELLES_POUD[][] = {
			//Jan1 Jan2 Fev1 Fev2 Mar1 Mar2 Avr1 Avr2 Mai1 Mai2 Jui1 Jui2 Jul1 Jul2 Aou1 Aou2 Sep1 Sep2 Oct1 Oct2 Nov1 Nov2 Dec1 Dec2
			{ 7.5, 7.5, 7.0, 6.5, 6.0, 6.0, 5.0, 4.0, 3.0, 1.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.5, 4.0, 5.0, 6.0, 6.5, 7.0, 7.5, 7.5, },			
			{10.5,10.5,10.0, 9.5, 6.0, 5.0, 1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 2.0, 5.0, 6.0, 6.5, 7.0, 9.5,10.5, },			
			{15.5,11.5,10.0, 9.0, 6.0, 3.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.5, 5.0, 6.5, 7.0, 9.0,15.0, },			
	};

	private ClientFinal cfC, cfT, cfP ;
	public FiliereTestClientFinal() {
		super();
		this.cfC = new ClientFinal(Categorie.CONFISERIE, 1627200000.0, 50.0, DISTRIBUTIONS_ANNUELLES_CONF);
		this.ajouterActeur(cfC);
		this.cfT = new ClientFinal(Categorie.TABLETTE, 4197600000.0, 32.0, DISTRIBUTIONS_ANNUELLES_TABL);
		this.ajouterActeur(cfT);
		this.cfP = new ClientFinal(Categorie.POUDRE, 1375200000.0, 63.0, DISTRIBUTIONS_ANNUELLES_POUD);
		this.ajouterActeur(cfP);
		this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE, "lindt")));
		this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.TABLETTE_BASSE, "ivoria")));
		this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, "lindt")));
		this.ajouterActeur(new ExempleTransformateurImproductif(new ChocolatDeMarque(Chocolat.POUDRE_BASSE, "ivoria")));
		ChocolatDeMarque[] chocos1= {
				new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE, "lindt"),
				new ChocolatDeMarque(Chocolat.TABLETTE_BASSE, "ivoria"),
				new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, "lindt"),
		};
		double[] stocks1= {1000000, 1000000, 1000000};
		double[] prix1= {1.6, 1.3, 2.4};
		String[] marques1 = {"lindt"};
		this.ajouterActeur(new ExempleDistributeurChocolatMarque(chocos1, stocks1, 100000, prix1, marques1));

		ChocolatDeMarque[] chocos2= {
				new ChocolatDeMarque(Chocolat.TABLETTE_MOYENNE, "lindt"),
				new ChocolatDeMarque(Chocolat.POUDRE_BASSE, "ivoria"),
				new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE, "lindt"),
		};
		double[] stocks2= {1000000, 1000000, 1000000};
		double[] prix2= {1.5, 0.85, 2.3};
		String[] marques2 = {"ivoria"};
		this.ajouterActeur(new ExempleDistributeurChocolatMarque(chocos2, stocks2, 50000, prix2, marques2));

//		this.ajouterActeur(new ExempleVendeurChocolatBourse(Chocolat.CHOCOLAT_BASSE));
//		this.ajouterActeur(new ExempleVendeurChocolatBourse(Chocolat.CHOCOLAT_MOYENNE));
//		this.ajouterActeur(new ExempleVendeurChocolatBourse(Chocolat.CHOCOLAT_MOYENNE));
//		this.ajouterActeur(new ExempleDistributeurChocolatMarque(new ChocolatDeMarque(Chocolat.CHOCOLAT_BASSE, "V.ChocoBourse1CHOCOLAT_BASSE"), 50000.0, 3750.0));
//		this.ajouterActeur(new ExempleDistributeurChocolatMarque(new ChocolatDeMarque(Chocolat.CHOCOLAT_MOYENNE, "V.ChocoBourse2CHOCOLAT_MOYENNE"), 40000.0, 10000.0));
//		this.ajouterActeur(new ExempleDistributeurChocolatMarque(new ChocolatDeMarque(Chocolat.CHOCOLAT_MOYENNE, "V.ChocoBourse2CHOCOLAT_MOYENNE"), 35000.0, 9000.0));
//		this.ajouterActeur(new ExempleDistributeurChocolatMarque(new ChocolatDeMarque(Chocolat.CHOCOLAT_MOYENNE, "V.ChocoBourse3CHOCOLAT_MOYENNE"), 35000.0, 9000.0));
		
//		this.getIndicateur("D.Choco1CHOCOLAT_BASSE stock CHOCOLAT_BASSE").setValeur(cf, 1000000);
//		this.getIndicateur("D.Choco2CHOCOLAT_BASSE stock CHOCOLAT_BASSE").setValeur(cf, 1000000);
//		this.getIndicateur("D.Choco3CHOCOLAT_MOYENNE stock CHOCOLAT_MOYENNE").setValeur(cf, 1000000);
//		this.getIndicateur("D.Choco4CHOCOLAT_MOYENNE stock CHOCOLAT_MOYENNE").setValeur(cf, 1000000);
	}
	
	
	public void initialiser() {
		super.initialiser();
		// il est possible de modifier l'attractivite initiale d'un chocolat (impossible d'appeler cette methode plus tard)
		//cfC.initAttractiviteChoco(new ChocolatDeMarque(Chocolat.CHOCOLAT_BASSE,"D.Choco1CHOCOLAT_BASSE"), 4.5);
	}

	
	/**
	 * Redefinition afin d'interdire l'acces direct au client final
	 */
	public IActeur getActeur(String nom) {
		if (!nom.startsWith("C.F.")) {
			return super.getActeur(nom); 
		} else {
			return null;
		}
	}
}
