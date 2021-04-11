package abstraction.eq5Transformateur3;
//Charlotte
import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.eq8Romu.fevesAO.SuperviseurVentesFevesAO;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;
import abstraction.eq8Romu.produits.Chocolat;

public class  AcheteurFevesAO extends Transformateur3Stock implements IAcheteurFevesAO {
	private Variable quantite;
	private Feve feve;
	private double qmin;
	private double qmax;
	
	public AcheteurFevesAO() {
		quantite = new Variable("quantite", this, qmin,qmax,0); //qmin et qmax représentent les quantites de chocolat (et pas de fèves!!)
		this.feve = feve;
	}
	public double getQmin() {
		return this.quantite.getMin();
	}
	public double getQmax() {
		return this.quantite.getMax();
	}

	@Override
	public OffreAchatFeves getOffreAchat() {
		OffreAchatFeves OA = new OffreAchatFeves(this, feve, quantite.getValeur());
			for(Chocolat chocolat : this.getChocolats().keySet()) {
				if(this.getChocolats().get(chocolat).getValeur() < this.getQmin()) { 
					quantite.ajouter(this, (this.getQmax()-this.getQmin())*25);//40 g de feves pour 100 g de chocolat (la quantité represente la quantite de fèves il faut donc convertir pour pouvoir comparer la )
				}
			}
		
		
		return null;
	}

	@Override
	public void notifierAucuneProposition(OffreAchatFeves oa) {
		// TODO Auto-generated method stub
		
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

		

		