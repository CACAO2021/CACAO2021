package abstraction.eq1Producteur1;

import java.util.ArrayList;
import java.util.HashMap;

import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
/**
 * @author arthurlemgit
 * classe l_journauxEq1 pour manipuler plus facilement nos l_journaux.
 */

public class JournauxEq1 {

	private HashMap<Object, Journal> journaux;


	public JournauxEq1() {
		this.journaux = new HashMap<Object, Journal>();

	}

	public JournauxEq1 (HashMap<Object, Journal> journaux) {
		this.journaux = journaux;
	}

	public Journal getJournal(String nom) {
		return this.journaux.get(nom);
	}

	public void addJournal(String n, IActeur a) {
		Journal journal = new Journal(n, a);
		this.journaux.put(n, journal);
	}


	public ArrayList<Journal> getJournaux(){
		ArrayList<Journal> list = new ArrayList<Journal>();
		for (Object journal: this.journaux.keySet()) {
			list.add(this.journaux.get(journal));
		}
		return list;
	}
}
