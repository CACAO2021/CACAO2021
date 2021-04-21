package abstraction.eq1Producteur1;

import java.util.ArrayList;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe l_journauxEq1 pour manipuler plus facilement nos l_journaux.
 */

public class JournauxEq1 {

	private ArrayList<Journal> l_journaux;

	public JournauxEq1() {
		this.l_journaux = new ArrayList<Journal>();
	}

	public JournauxEq1 (ArrayList<Journal> l_journaux) {
		this.l_journaux = l_journaux;
	}

	public Journal getJournal(int i) {
		return this.l_journaux.get(i);
	}

	public void addJournal(String n, IActeur a) {
		Journal journal = new Journal(n, a);
		this.l_journaux.add(journal);
	}

	public ArrayList<Journal> getListeJournaux(){
		return l_journaux;
	}

}
