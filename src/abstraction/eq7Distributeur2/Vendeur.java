package abstraction.eq7Distributeur2;

import java.awt.Color;

import abstraction.eq8Romu.produits.ChocolatDeMarque;
import abstraction.fourni.Journal;

//----------------------//
//fait par Arnaud       //
//----------------------//

public class Vendeur extends Distributeur2Acteur {
	
	public void notificationRayonVide(ChocolatDeMarque choco) {
		// Notifie quand le rayon est vide 	
		journal.ajouter(Journal.texteColore(warningColor, Color.BLACK, "[RAYON] Le rayon de " + choco.name() + " est vide."));
	}
}
