package abstraction.eq8Romu.produits;

public enum Beurre {
	BEURRE_HAUTE_EQUITABLE(Gamme.HAUTE, true),
	BEURRE_MOYENNE_EQUITABLE(Gamme.MOYENNE, true),
	BEURRE_MOYENNE(Gamme.MOYENNE, false);

	private Gamme gamme;
	private boolean equitable;

	Beurre(Gamme gamme, Boolean equitable) {
		this.gamme = gamme;
		this.equitable = equitable;
	}
	public Gamme getGamme() {
		return this.gamme;
	}
	public boolean isEquitable() {
		return this.equitable;
	}
	public boolean isBio() {
		return false; // aucun beurre n'est bio
	}
	public static void main(String[] args) {
		for (Beurre p : Beurre.values()) {
			System.out.println(p);
		}
	}
}
