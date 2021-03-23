package abstraction.eq8Romu.fevesAO;

import abstraction.eq8Romu.produits.Feve;

public class ExempleVendeurFevesAO extends ExempleAbsVendeurFevesAO implements IVendeurFevesAO {

	public ExempleVendeurFevesAO(Feve feve, double prodMin, double prodMax, double prixMin, double prixMax) {
		super(feve, prodMin, prodMax, prixMin, prixMax);
	}

	public void next() {
		super.next();
	}

	public double proposerPrix(OffreAchatFeves oa) {
		if (!oa.getFeve().equals(this.feve ) || oa.getQuantiteKG()>this.stockFeves.getValeur())  {
			return 0;
		} else {
			return prixMin.getValeur()+(Math.random()*(prixMax.getValeur()-prixMin.getValeur()));
		}
	}

	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		journal.ajouter("proposition refusee");
	}

	public void notifierVente(PropositionVenteFevesAO proposition) {
		journal.ajouter("proposition acceptee");
		this.stockFeves.retirer(this, proposition.getQuantiteKg());
	}
}
