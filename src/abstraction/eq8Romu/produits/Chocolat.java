package abstraction.eq8Romu.produits;

public enum Chocolat {
	TABLETTE_HAUTE_BIO_EQUITABLE(Categorie.TABLETTE, Gamme.HAUTE, true, true ),
	TABLETTE_HAUTE_EQUITABLE(Categorie.TABLETTE, Gamme.HAUTE, false, true ),
	TABLETTE_MOYENNE_EQUITABLE(Categorie.TABLETTE, Gamme.MOYENNE, false, true ),
	TABLETTE_MOYENNE(Categorie.TABLETTE, Gamme.MOYENNE, false, false ),
	TABLETTE_BASSE(Categorie.TABLETTE, Gamme.BASSE, false, false ),
	
	CONFISERIE_HAUTE_BIO_EQUITABLE(Categorie.CONFISERIE, Gamme.HAUTE, true, true ),
	CONFISERIE_HAUTE_EQUITABLE(Categorie.CONFISERIE, Gamme.HAUTE, false, true ),
	CONFISERIE_MOYENNE_EQUITABLE(Categorie.CONFISERIE, Gamme.MOYENNE, false, true ),
	CONFISERIE_MOYENNE(Categorie.CONFISERIE, Gamme.MOYENNE, false, false ),
	CONFISERIE_BASSE(Categorie.CONFISERIE, Gamme.BASSE, false, false ),

	POUDRE_HAUTE_BIO_EQUITABLE(Categorie.POUDRE, Gamme.HAUTE, true, true ),
	POUDRE_HAUTE_EQUITABLE(Categorie.POUDRE, Gamme.HAUTE, false, true ),
	POUDRE_MOYENNE_EQUITABLE(Categorie.POUDRE, Gamme.MOYENNE, false, true ),
	POUDRE_MOYENNE(Categorie.POUDRE, Gamme.MOYENNE, false, false ),
	POUDRE_BASSE(Categorie.POUDRE, Gamme.BASSE, false, false );
	
	private Categorie categorie;
	private Gamme gamme;
	private boolean equitable;
	private boolean bio;
	
	Chocolat(Categorie categorie, Gamme gamme, Boolean bio, Boolean equitable) {
		this.categorie = categorie;
		this.gamme = gamme;
		this.equitable = equitable;
		this.bio = bio;
	}
	public Categorie getCategorie() {
		return this.categorie;
	}
	public Gamme getGamme() {
		return this.gamme;
	}
	public boolean isEquitable() {
		return this.equitable;
	}
	public boolean isBio() {
		return this.bio;
	}
	public double qualite() {
		double qualite;
		switch (getGamme()) {
		case BASSE : qualite=1.0;
		case MOYENNE : qualite=2.0;
		default : qualite=3.0; //HAUTE
		}
		if (isEquitable()) {
			qualite=qualite+0.5;
		}
		if (isBio()) {
			qualite=qualite+0.5;
		}
		return qualite;
	}
	public static void main(String[] args) {
		for (Chocolat c : Chocolat.values()) {
			System.out.println(c);
		}
	}
}
