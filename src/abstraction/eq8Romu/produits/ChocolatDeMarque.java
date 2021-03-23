package abstraction.eq8Romu.produits;

import abstraction.fourni.Filiere;

public class ChocolatDeMarque {
	private Chocolat chocolat;
	private String marque;
	
	public ChocolatDeMarque(Chocolat chocolat, String marque) {
		if (chocolat==null) {
			throw new IllegalArgumentException("Tentative de creer une instance de ChocolatDeMarque avec null pour premier parametre");
		}
		if (marque==null) {
			throw new IllegalArgumentException("Tentative de creer une instance de ChocolatDeMarque avec null pour second parametre");
		}
		if (marque.length()<1) {
			throw new IllegalArgumentException("Tentative de creer une instance de ChocolatDeMarque avec une chaine vide pour second parametre");
		}
		if (Filiere.LA_FILIERE!=null && !Filiere.LA_FILIERE.getMarquesChocolat().contains(marque)) {
			throw new IllegalArgumentException("Tentative de creer une instance de ChocolatDeMarque de marque \""+marque+"\" alors qu'aucun acteur n'a depose cette marque");
		}
		this.chocolat = chocolat;
		this.marque = marque;
	}
	
	public String name() {
		return chocolat.name()+" "+marque;
	}

	public Chocolat getChocolat() {
		return chocolat;
	}
	
	public double qualitePercue() {
		return (2*this.getChocolat().qualite()+Filiere.LA_FILIERE.qualitePercueMarque(this.marque))/3.0; // 2/3 est la qualite intrinseque du chocolat, 1/3 est due a la qualite percue de la marque
	}
	public Gamme getGamme() {
		return this.chocolat.getGamme();
	}
	public Categorie getCategorie() {
		return this.chocolat.getCategorie();
	}
	public boolean isBio() {
		return this.chocolat.isBio();
	}
	public boolean isEquitable() {
		return this.chocolat.isEquitable();
	}

	public String getMarque() {
		return marque;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chocolat == null) ? 0 : chocolat.hashCode());
		result = prime * result + ((marque == null) ? 0 : marque.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (!(obj instanceof ChocolatDeMarque)) {
			return false;
		} else {
			ChocolatDeMarque cdmobj = (ChocolatDeMarque) obj;
			return cdmobj.getChocolat()!=null 
					&& cdmobj.getMarque()!=null 
					&& cdmobj.getChocolat().equals(this.getChocolat()) 
					&& cdmobj.getMarque().contentEquals(this.getMarque());
		}
	}
	
	public String toString() {
		return this.chocolat.toString()+"_"+this.marque;
	}
	
}
