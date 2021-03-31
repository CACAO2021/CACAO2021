package abstraction.eq1Producteur1;

import java.awt.Color;
import java.util.List;

import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class VendeurFevesAO implements IVendeurFevesAO{

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getNomsFilieresProposees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Filiere getFiliere(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variable> getIndicateurs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Variable> getParametres() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Journal> getJournaux() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCryptogramme(Integer crypto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationFaillite(IActeur acteur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationOperationBancaire(double montant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double proposerPrix(OffreAchatFeves oa) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifierVente(PropositionVenteFevesAO proposition) {
		// TODO Auto-generated method stub
		
	}

}
