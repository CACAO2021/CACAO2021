package abstraction.eq5Transformateur3;

import java.util.HashMap;


import abstraction.eq8Romu.produits.Chocolat;
import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Variable;

//Manuelo

public class Transformateur3Stock extends Transformateur3Acteur {
	protected HashMap<Chocolat, Variable> chocolats;
	protected HashMap<Feve, Variable> feves;
	
	public Transformateur3Stock() {
		HashMap<Chocolat, Variable> stockChocolat = new HashMap<Chocolat, Variable>();
		HashMap<Feve, Variable> stockFeve = new HashMap<Feve, Variable>();
		
		Variable value_Tablette_Haute_Bio_Equitable = new Variable("Quantite disponible", this, 5000);
		Variable value_Tablette_moyenne = new Variable("Quantite disponible", this, 5000);
		Variable value_Confiserie_moyenne = new Variable("Quantite disponible", this, 5000);
		
		Variable value_Feve_Haute_Bio_Equitable = new Variable("Quantite disponible", this, 5000);
		Variable value_Feve_Moyenne = new Variable("Quantite disponible", this, 5000);
		
		stockChocolat.put(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, value_Tablette_Haute_Bio_Equitable);
		stockChocolat.put(Chocolat.TABLETTE_MOYENNE, value_Tablette_moyenne);
		stockChocolat.put(Chocolat.CONFISERIE_MOYENNE, value_Confiserie_moyenne);
		
		stockFeve.put(Feve.FEVE_HAUTE_BIO_EQUITABLE,  value_Feve_Haute_Bio_Equitable);
		stockFeve.put(Feve.FEVE_MOYENNE, value_Feve_Moyenne);
	}

	public void ajouter(Chocolat chocolat, double delta) {
		Variable variable = this.getChocolats().get(chocolat);
		variable.ajouter(this, delta);		
		this.JournalAjoutStock.ajouter("Ajout de "+delta+"kg de "+chocolat.toString());
	}
	
	public void ajouter(Feve feve, double delta) {
		Variable variable = this.getFeves().get(feve);
		variable.ajouter(this, delta);
		this.JournalAjoutStock.ajouter("Ajout de "+delta+"kg de "+feve.toString());
	}
	
	public void retirer(Chocolat chocolat, double delta) {
		Variable variable = this.getChocolats().get(chocolat);
		if(variable.getValeur()>=delta) {
			variable.retirer(this, delta);
		}
		this.JournalRetraitStock.ajouter("Retrait de "+delta+"kg de "+chocolat.toString());
	}
	
	public void retirer(Feve feve, double delta) {
		Variable variable = this.getFeves().get(feve);
		if(variable.getValeur()>=delta) {
			variable.retirer(this, delta);
		}
		this.JournalRetraitStock.ajouter("Retrait de "+delta+"kg de "+feve.toString());
	}

	public HashMap<Chocolat, Variable> getChocolats() {
		return chocolats;
	}
	
	public HashMap<Feve, Variable> getFeves() {
		return feves;
	}
	
}
