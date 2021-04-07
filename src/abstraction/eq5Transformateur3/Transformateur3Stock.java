package abstraction.eq5Transformateur3;

import java.util.HashMap;

import abstraction.eq8Romu.produits.Chocolat;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class Transformateur3Stock extends Transformateur3Acteur {
	protected HashMap<Chocolat, Variable> chocolats;
	
	public Transformateur3Stock() {
		HashMap<Chocolat, Variable> enStock = new HashMap<Chocolat, Variable>();
		Variable value_Tablette_Haute_Bio_Equitable = new Variable("Quantite disponible", this, 5000);
		Variable value_Tablette_moyenne = new Variable("Quantite disponible", this, 5000);
		Variable value_Confiserie_moyenne = new Variable("Quantite disponible", this, 5000);
		enStock.put(Chocolat.TABLETTE_HAUTE_BIO_EQUITABLE, value_Tablette_Haute_Bio_Equitable);
		enStock.put(Chocolat.TABLETTE_MOYENNE, value_Tablette_moyenne);
		enStock.put(Chocolat.CONFISERIE_MOYENNE, value_Confiserie_moyenne);
	}
	
	public void ajouter(Chocolat chocolat, double delta) {
		Variable variable = this.getChocolats().get(chocolat);
		variable.ajouter(this, delta);		
		this.JournalAjoutStock.ajouter("Ajout de "+delta+"kg de "+chocolat.toString());
	}
	
	public void retirer(Chocolat chocolat, double delta) {
		Variable variable = this.getChocolats().get(chocolat);
		if(variable.getValeur()>=delta) {
			variable.retirer(this, delta);
		}
		this.JournalRetraitStock.ajouter("Retrait de "+delta+"kg de "+chocolat.toString());
	}

	public HashMap<Chocolat, Variable> getChocolats() {
		return chocolats;
	}
	
}
