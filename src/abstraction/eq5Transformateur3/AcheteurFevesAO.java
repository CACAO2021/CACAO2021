package abstraction.eq5Transformateur3;
//Charlotte
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.contratsCadres.ExemplaireContratCadre;
import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.produits.Chocolat;

public class  AcheteurFevesAO extends Transformateur3VenteContratCadre implements IAcheteurFevesAO {
	private Variable quantite;
	private Feve feve;
	private double qmin;
	private double qmax;
	private double prixmax;
	
	public AcheteurFevesAO() {
		this.quantite = quantite;
		this.feve = feve;
		this.qmax = qmax;
		this.qmin = qmin ; //mettre qmin assez élevé
		this.prixmax = prixmax;
		}
	
	
	
	public double getQmin() {
		return this.quantite.getMin();
	}
	public double getQmax() {
		return this.quantite.getMax();
	}
    // cette méthode permet de garantir une quantité minimale de fèves en stock pour chaque type de fèves
	// elle permet également d'acheter la quantité du step N+1 du contrat cadre au step N pour anticiper et garantir l'apport en chocolat aux distributeurs 
	
	@Override
	public OffreAchatFeves getOffreAchat() {
		// TODO Auto-generated method stub
		return null;
	}

	/*public OffreAchatFeves getOffreAchat() {
		OffreAchatFeves OA = new OffreAchatFeves(this, feve, quantite.getValeur());
			for(Chocolat chocolat : this.getChocolats().keySet()) {
				if(this.getChocolats().get(chocolat).getValeur()*0.25 < this.getQmin()) { //40 g de feves pour 100 g de chocolat (la valeur represente la quantite de chocolat il faut donc convertir pour pouvoir comparer a la quantité de fèves)
					quantite.ajouter(this, this.getQmin()-this.getChocolats().get(chocolat).getValeur()*0.25);
				}
			}
			for(ExemplaireContratCadre contrat : this.getContrats().keySet()) {
					quantite.ajouter(this, contrat.getEcheancier().getQuantite(Filiere.LA_FILIERE.getEtape()+1));
				}
			if(quantite.getValeur()!=0) {
				this.JournalOA.ajouter("offre d'achat =" + OA);
				return OA;
			}
			else {
				this.JournalOA.ajouter("pas d'offre d'achat");
				return null;
			}
	}*/

	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.JournalOA.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}
	

	@Override
	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//On va choisir ici la proposition la moins chère pour être cohérent avec notre objectif de rentabilité
	// on choisit cependant des AO dont les quantités respectent les quantités voulues initialement 
	//(j'ajoute ici une variable delta qui indique cb peut varier la quantité demandée)
	/*public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		double delta = this.getQmax()-this.getQmin();
		if (propositions.size()>0) {
			for(PropositionVenteFevesAO proposition : propositions) {
				if(proposition.getPrixKG()< this.prixmax 
						&& proposition.getQuantiteKg()< proposition.getOffreAchateFeves().getQuantiteKG()+ delta
						&& proposition.getQuantiteKg()> proposition.getOffreAchateFeves().getQuantiteKG()- delta) {
		}
			
				
			}
		}
		return null;
	}*/

	@Override
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		if (superviseur!=null) { 
			return cryptogramme;
		}
		return Integer.valueOf(0);
		
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}
	
}

		

		