package abstraction.eq8Romu.produits;

public enum Feve {

	FEVE_HAUTE_BIO_EQUITABLE(Gamme.HAUTE, true, true),
	FEVE_HAUTE_EQUITABLE(Gamme.HAUTE, false, true),
	FEVE_MOYENNE_EQUITABLE(Gamme.MOYENNE, false, true),
	FEVE_MOYENNE(Gamme.MOYENNE, false, false),
	FEVE_BASSE(Gamme.BASSE, false, false);
	
	private Gamme gamme;
	private boolean bio;
	private boolean equitable;
	
	Feve(Gamme gamme, boolean bio, boolean equitable) {
		this.gamme = gamme;
		this.bio = bio;
		this.equitable = equitable;
	}
	public Gamme getGamme() {
		return this.gamme;
	}
	public boolean isBio() {
		return this.bio;
	}
	public boolean isEquitable() {
		return this.equitable;
	}

	public static void main(String[] args) {
		for (Feve f : Feve.values()) {
			System.out.println(f);
		}
	}
}
