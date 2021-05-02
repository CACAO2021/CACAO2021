package abstraction.eq4Transformateur2;

//Antoine C
public class Transformateur2 extends Transformateur2AchatAO {

	
	public Transformateur2 () {
		super();
		
		for (int i=0; i<24; i++ ) {
			echeancier_basse.add(0.0);
			echeancier_moyenne.add(0.0);
			echeancier_total.add(0.0);
		}
	}

	public String toString() {
		return this.getNom();
	}
}
