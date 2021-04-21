package abstraction.eq1Producteur1;

import java.util.ArrayList;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe JournauxEq1 pour manipuler plus facilement nos journaux.
 */

public class JournauxEq1 extends CreationJournaux {

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

	public void addJournal(String n, IActeur a) {
		Journal journal = new Journal(n, a);
		this.journaux.add(journal);
	}

	public ArrayList<Journal> getListeJournaux(){
		return journaux;
	}

}
