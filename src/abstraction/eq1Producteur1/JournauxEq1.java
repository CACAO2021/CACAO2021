package abstraction.eq1Producteur1;

import java.util.HashMap;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe JournauxEq1 pour manipuler plus facilement nos journaux.
 */

public class JournauxEq1 {

	private HashMap<Object, Journal> journaux;

	public JournauxEq1() {
		this.journaux = new HashMap<Object, Journal>();
	}

	public JournauxEq1 (HashMap<Object, Journal> journaux) {
		this.journaux = journaux;
	}

	public Journal getJournal(char nom) {
		return this.journaux.get(nom);
	}

	public void addJournal(String n, IActeur a) {
		Journal journal = new Journal(n, a);
		this.journaux.put(n, journal);
	}

	public HashMap<Object,Journal> getJournaux(){
		return journaux;
	}

}
