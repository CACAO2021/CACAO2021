package abstraction.eq1Producteur1;

import java.util.ArrayList;

import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe JournauxEq1 pour manipuler plus facilement les journaux.
 */

public class JournauxEq1 {
	private ArrayList<Journal> journaux;
	public JournauxEq1() {
		this.journaux = new ArrayList<>();
	}
	public JournauxEq1 (ArrayList<Journal> journaux) {
		this.journaux = journaux;
	}
	
	public Journal getJournal(int i) {
		return this.journaux.get(i);
	}
	
	public void addJournal() {
		Journal journal = new Journal(""+this.journaux.size(), Filiere.LA_FILIERE.getActeur("EQ1"));
		this.journaux.add(journal);
	}
	
	

}
