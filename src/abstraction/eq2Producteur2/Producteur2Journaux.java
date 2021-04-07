package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Producteur2Journaux extends Producteur2Acteur {
	protected Journal JournalProd, JournalVente;

	public Producteur2Journaux() {
		super();
		this.JournalProd= new Journal(this.getNom()+" production", this);
		this.JournalVente= new Journal(this.getNom()+" vente", this);
	}
	
	public void initJournaux() {
		this.JournalProd.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalVente.ajouter("=== initialisation du client "+this.getNom()+" ===");
	}
	
	public void majJournaux() {
		this.JournalProd.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
		this.JournalVente.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
	}
	
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(JournalProd);
		res.add(JournalVente);
		return res;
	}


}
