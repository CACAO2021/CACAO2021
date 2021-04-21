package abstraction.eq6Distributeur1;

import abstraction.eq8Romu.Romu;
import abstraction.eq8Romu.clients.ClientFinal;
import abstraction.eq8Romu.contratsCadres.ExempleTransformateurContratCadreVendeur;
import abstraction.eq8Romu.contratsCadres.SuperviseurVentesContratCadre;
import abstraction.eq8Romu.produits.Categorie;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Filiere;

public class FiliereTestCacaocaisse extends Filiere {

	private ClientFinal cfC, cfT, cfP ;
	private SuperviseurVentesContratCadre superviseurCC;
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
	
	
	public FiliereTestCacaocaisse() {

		super();
		this.ajouterActeur(new Distributeur1());
		this.ajouterActeur(new ExempleTransformateurContratCadreVendeur(new ChocolatDeMarque(Chocolat.CONFISERIE_HAUTE_BIO_EQUITABLE,"test1")));
		this.cfC = new ClientFinal(Categorie.CONFISERIE, 1627200000.0, 50.0, DISTRIBUTIONS_ANNUELLES_CONF);
		this.ajouterActeur(cfC);
		this.cfT = new ClientFinal(Categorie.TABLETTE, 4197600000.0, 32.0, DISTRIBUTIONS_ANNUELLES_TABL);
		this.ajouterActeur(cfT);
		this.cfP = new ClientFinal(Categorie.POUDRE, 1375200000.0, 63.0, DISTRIBUTIONS_ANNUELLES_POUD);
		this.ajouterActeur(cfP);
		this.ajouterActeur(new Romu());
		
		
		this.superviseurCC=new SuperviseurVentesContratCadre();
		this.ajouterActeur(this.superviseurCC);
		
	}
	
	public void initialiser() {
		super.initialiser();
	}
	
}
