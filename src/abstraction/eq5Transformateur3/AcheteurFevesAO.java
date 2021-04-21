package abstraction.eq5Transformateur3;
//Charlotte
import java.awt.Color;
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
	
	public AcheteurFevesAO(Feve feve, double prixmax, double qmin, double qmax) throws Exception{
		if(this.qmin < OffreAchatFeves.AO_FEVES_QUANTITE_MIN) {
			throw new Exception("quantité trop faible");
		}
		else {
			quantite = new Variable("quantite", this, qmin, qmax,0); //qmin et qmax représentent les quantites en fèves (et non en chocolat!!) minimale et maximale de notre stock
			this.feve = feve;
			this.qmax = 1000000000;
			this.qmin = qmin ; //mettre qmin assez élevé
			this.prixmax = prixmax;
		}
	}
	
	public double getQmin() {
		return this.quantite.getMin();
	}
	public double getQmax() {
		return this.quantite.getMax();
	}

	public OffreAchatFeves getOffreAchat() {
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
	}

	public void notifierAucuneProposition(OffreAchatFeves oa) {
		this.JournalOA.ajouter("--> aucune proposition de vente pour l'offre "+oa);
	}
		
	

	@Override
	public PropositionVenteFevesAO choisirPropositionVenteAOFeves(List<PropositionVenteFevesAO> propositions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCryptogramme(SuperviseurVentesFevesAO superviseur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}
	
}

		

		