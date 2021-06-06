package abstraction.eq5Transformateur3;
//Charlotte

import java.util.LinkedList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.ChocolatDeMarque;

public class  AcheteurFevesAO extends Transformateur3VenteContratCadre implements IAcheteurFevesAO {
	private Feve feve;
	private double qmin;
	private double qmax;
	private double prixmax;
	
	public AcheteurFevesAO() {
		super();
	}
		
	
	//public AcheteurFevesAO(Feve feve, double prixmax, double qmin, double qmax) throws Exception{
		//if(this.qmin < OffreAchatFeves.AO_FEVES_QUANTITE_MIN) {
			//throw new Exception("quantité trop faible");
		//}
		//else {
			//this.quantite = new Variable("quantite", this, qmin, qmax,0); //qmin et qmax représentent les quantites en fèves (et non en chocolat!!) minimale et maximale de notre stock
			//this.feve = feve;
			//this.qmax = qmax;
			//this.qmin = qmin ; //mettre qmin assez élevé
			//this.prixmax = prixmax;
		//}
	// }
		
	/*public AcheteurFevesAO(Feve feve, double prixmax, double qmin, double qmax) throws Exception{
		System.out.println("tito");
		if(this.qmin < OffreAchatFeves.AO_FEVES_QUANTITE_MIN) {
			throw new Exception("quantité trop faible");
		}
		else {
			System.out.println("toto");
			this.feve = feve;
			this.qmax = qmax;
			this.qmin = qmin ; //mettre qmin assez élevé
			this.prixmax = prixmax;
		}
<<<<<<< HEAD
	}
=======
	}*/

	/*public double getQmin() {
		return this.quantite.getMin();
	}*/

	
	
	//cette méthode permet de retourner le type de fève utilisée à chaque type de tablette 
	public Feve getFeve(Chocolat chocolat) {
			if(chocolat == Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) {
				return Feve.FEVE_HAUTE_BIO_EQUITABLE;
			}
			if(chocolat == Chocolat.TABLETTE_MOYENNE || chocolat == Chocolat.CONFISERIE_MOYENNE){
				return Feve.FEVE_MOYENNE;
			}
			else {
				return null;
			}		
	}
    // cette méthode permet de garantir une quantité minimale de fèves en stock pour chaque type de fèves
	// elle permet également d'acheter la quantité du step N+1 du contrat cadre au step N pour anticiper et garantir l'apport en chocolat aux distributeurs 

/*OffreAchatFeves getOffreAchat() {
	public OffreAchatFeves getOffreAchat() { 
		Variable feve=this.getFeves().get(Feve.FEVE_MOYENNE);
		OffreAchatFeves OA = new OffreAchatFeves(this, Feve.FEVE_MOYENNE, (double)10);
		if (feve.getValeur()<1000000) {
			this.JournalOA.ajouter("offre d'achat =" + OA);
			return OA; }
		else { 
			this.JournalOA.ajouter("pas d'offre d'achat");
			return null; }
		}
	
>>>>>>> branch 'master' of https://github.com/Charlottederom/CACAO2021
		int nb_OA = 0;
			for(Chocolat chocolat : this.getChocolats().keySet()) {
				OffreAchatFeves OA = new OffreAchatFeves(this, feve, this.quantite.getValeur());
				if(this.getChocolats().get(chocolat).getValeur()*0.4 < this.getQmin()) { //40 g de feves pour 100 g de chocolat (la valeur represente la quantite de chocolat il faut donc convertir pour pouvoir comparer a la quantité de fèves)
					quantite.ajouter(this, this.getQmin()-this.getChocolats().get(chocolat).getValeur()*0.4);
					feve = getFeve(chocolat);
					if(quantite.getValeur()!=0){ 
						this.JournalOA.ajouter("offre d'achat =" + OA);
						nb_OA+=1;
						return OA;
					}
				}
			}
			for(ExemplaireContratCadre contrat : this.getContrats().keySet()) {
				OffreAchatFeves OA = new OffreAchatFeves(this, feve, quantite.getValeur());
				quantite.ajouter(this, contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1));
				if(contrat.getProduit() instanceof Chocolat) {
					feve = getFeve((Chocolat) (contrat.getProduit()));
					if(quantite.getValeur()!=0){
						this.JournalOA.ajouter("offre d'achat =" + OA);
						nb_OA+=1;
						return OA;
					}
				}
			}
			if(nb_OA==0){
				this.JournalOA.ajouter("pas d'offre d'achat");
				return null;
			}
			return null;
	} */
	
	//Remi
	
	public OffreAchatFeves getOffreAchat(){
		int nb_OA = 0;
		/*if (this.getFeves().get(Feve.FEVE_MOYENNE).getValeur()< this.stock_min_feves_moyenne.getValeur()) {
			OffreAchatFeves OA = new OffreAchatFeves(this, Feve.FEVE_MOYENNE, this.stock_min_feves_moyenne.getValeur()-this.getFeves().get(Feve.FEVE_MOYENNE).getValeur());
			this.JournalOA.ajouter("offre d'achat =" + OA);
			nb_OA+=1;
			return OA;
			}
		if (this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE).getValeur()< this.stock_min_feves_HBE.getValeur() ) {
			OffreAchatFeves OA = new OffreAchatFeves(this, Feve.FEVE_HAUTE_BIO_EQUITABLE, this.stock_min_feves_HBE.getValeur()-this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE).getValeur());
			this.JournalOA.ajouter("offre d'achat =" + OA);
			nb_OA+=1;
			return OA;
			}*/
		 for(ExemplaireContratCadre contrat : this.getContratsVente().keySet()) {
			ChocolatDeMarque chocoMarque = ((ChocolatDeMarque)contrat.getProduit());
			Chocolat choco = chocoMarque.getChocolat();
			if (choco==Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE) {
				Variable feve = this.getFeves().get(Feve.FEVE_HAUTE_BIO_EQUITABLE);
				if (feve.getValeur()<contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1)) {
					OffreAchatFeves OA = new OffreAchatFeves(this, Feve.FEVE_HAUTE_BIO_EQUITABLE, contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1));
		            nb_OA+=1;
					return OA;
				}
			}
			if (choco==Chocolat.CONFISERIE_MOYENNE || choco==Chocolat.TABLETTE_MOYENNE) {
				Variable feve=this.getFeves().get(Feve.FEVE_MOYENNE);
				if (feve.getValeur()<contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1)) {
					OffreAchatFeves OA = new OffreAchatFeves(this, Feve.FEVE_MOYENNE, contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1));
		            nb_OA+=1;
					return OA;
				}
			}
		 }
				
				/*
				quantite.ajouter(this, contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1));
			if(contrat.getProduit() instanceof Chocolat) {
				feve = getFeve((Chocolat) (contrat.getProduit()));
				if(quantite.getValeur()!=0){
					this.JournalOA.ajouter("offre d'achat =" + OA);
					nb_OA+=1;

					return OA; */

		return null;
	}
		 
	
	
		


	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.JournalOA.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}

	
	//On va choisir ici la proposition la moins chère pour être cohérent avec notre objectif de rentabilité
	// on choisit cependant des AO dont les quantités respectent les quantités voulues initialement 
	//(j'ajoute ici une variable delta qui indique cb peut varier la quantité demandée)

	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {

		LinkedList<PropositionVenteFevesAO> propositions_interessantes_HBE=new LinkedList<PropositionVenteFevesAO>();
		LinkedList<PropositionVenteFevesAO> propositions_interessantes_M=new LinkedList<PropositionVenteFevesAO>();

		if (propositions.size()>0) {
			for(PropositionVenteFevesAO proposition : propositions) {
				if (proposition.getFeve()==Feve.FEVE_HAUTE_BIO_EQUITABLE) {
					if(proposition.getPrixKG() < this.prix_max_fèves_HBE.getValeur()
						&& proposition.getFeve() == proposition.getOffreAchateFeves().getFeve()){
							propositions_interessantes_HBE.add(proposition);} }
				if (proposition.getFeve()==Feve.FEVE_MOYENNE) {
					Variable feve = this.getFeves().get(Feve.FEVE_MOYENNE);
					double delta=this.stock_min_feves_moyenne.getValeur()-feve.getValeur();
					if(proposition.getPrixKG() < this.prix_max_fèves_moyenne.getValeur()
							&& proposition.getQuantiteKg()< proposition.getOffreAchateFeves().getQuantiteKG()+ delta
							&& proposition.getQuantiteKg()> proposition.getOffreAchateFeves().getQuantiteKG()- delta
							&& proposition.getFeve() == proposition.getOffreAchateFeves().getFeve()){
								propositions_interessantes_M.add(proposition);} }}}
		
		if(propositions_interessantes_HBE.size()!=0) {
			double prix_interessant=1e13;
			int i=-1;
			int index=0;
			for (PropositionVenteFevesAO proposition : propositions_interessantes_HBE) {
				i=i+1;
				if (proposition.getPrixKG()<prix_interessant) {
					prix_interessant=proposition.getPrixKG();
					index=i;}
			}

			return propositions_interessantes_HBE.get(index);}
		
		if(propositions_interessantes_M.size()!=0) {
			double prix_interessant=1e13;
			int i=-1;
			int index=0;
			for (PropositionVenteFevesAO proposition : propositions_interessantes_M) {
				i=i+1;
				if (proposition.getPrixKG()<prix_interessant) {
					prix_interessant=proposition.getPrixKG();
					index=i;}
			}
			return propositions_interessantes_M.get(index);}
			
		else {return null;}

	} 


	@Override
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		// TODO Auto-generated method stub
		return this.cryptogramme;}
	

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		Feve feve = proposition.getFeve();
		this.ajouter(feve, proposition.getQuantiteKg());
		this.JournalOA.ajouter("Achat de " + proposition.getQuantiteKg() + " kg de " + feve + " pour un montant de " + proposition.getMontant() + " euros ");
		this.JournalOA.ajouter("--> le stock de feve passe à " +Journal.doubleSur(this.getFeves().get(feve).getValeur(), 4));
	}
	
}
	

		

		