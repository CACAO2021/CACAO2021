package abstraction.eq1Producteur1;

import java.util.ArrayList;

import abstraction.fourni.Filiere;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe JournauxEq1 pour manipuler plus facilement nos journaux.
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
	
	public void addJournal(int n) {
		for (int i=0; i<n; i++) {
			Journal journal = new Journal(""+this.journaux.size()+1, Filiere.LA_FILIERE.getActeur("EQ1"));
			this.journaux.add(journal);
		}
	}
	
	public ArrayList<Journal> getJournaux(){
		return journaux;
	}
	
}
