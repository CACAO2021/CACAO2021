package abstraction.eq2Producteur2;

import java.util.ArrayList;
import java.util.List;

import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public abstract class Producteur2Journaux extends Producteur2Acteur {
	protected Journal JournalProd, JournalVente, JournalCC,JournalLivraison, JournalStock, JournalRevolte;
	
	// ensemble fait par DIM

	public Producteur2Journaux() {
		super();
		this.JournalProd= new Journal(this.getNom()+" production", this);
		this.JournalVente= new Journal(this.getNom()+" ventes", this);
		this.JournalCC= new Journal(this.getNom()+" contrats", this);
		this.JournalLivraison= new Journal(this.getNom()+" livraisons", this);
		this.JournalStock= new Journal(this.getNom()+" stocks", this);
		JournalRevolte = new Journal(this.getNom()+" révolution des producteurs", this);
	}
	
	public void initJournaux() {
		this.JournalProd.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalVente.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalCC.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalLivraison.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalStock.ajouter("=== initialisation du client "+this.getNom()+" ===");
		this.JournalRevolte.ajouter("=== initialisation du client "+this.getNom()+" ===");
	}
	
	public void majJournaux() {
		this.JournalProd.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+ " " + Filiere.LA_FILIERE.getMois() + " ======================");	
		this.JournalVente.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
		this.JournalCC.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
		this.JournalLivraison.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
		this.JournalStock.ajouter("=== Etape "+Filiere.LA_FILIERE.getEtape()+" ======================");	
	}
	
	public List<Journal> getJournaux() {
		List<Journal> res=new ArrayList<Journal>();
		res.add(JournalProd);
		res.add(JournalVente);
		res.add(JournalCC);
		res.add(JournalLivraison);
		res.add(JournalStock);
		res.add(JournalRevolte);
		return res;
	}


}
