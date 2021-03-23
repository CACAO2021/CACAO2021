package abstraction.eq8Romu.fevesAO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstraction.eq8Romu.produits.Feve;
import abstraction.fourni.Filiere;
import abstraction.fourni.IActeur;
import abstraction.fourni.Journal;
import abstraction.fourni.Variable;

public class ExempleAbsVendeurFevesAO implements IActeur {
	private static int NB_INSTANCES = 0; // Afin d'attribuer un nom different a toutes les instances
	protected int numero;
	protected Variable stockFeves;
	protected Integer cryptogramme;
	protected Feve feve;
	protected Variable prodMin, prodMax, prixMin, prixMax;
	protected Journal journal;

	public ExempleAbsVendeurFevesAO(Feve feve, double prodMin, double prodMax, double prixMin, double prixMax) {
		if (prodMin<0 || prodMax<0 || prodMax<prodMin) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurFevesAO avec prodMin="+prodMin+" et prodMax="+prodMax);
		}
		if (prixMin<0) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurFevesAO avec prixMin="+prixMin);
		}		
		if (prixMax<0 || prixMax<prixMin) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurFevesAO avec prixMin="+prixMin);
		}		
		if (feve==null) {
			throw new IllegalArgumentException("creation d'une instance de ExempleAbsVendeurFevesAO avec feve==null");
		}		
		NB_INSTANCES++;
		this.numero=NB_INSTANCES;
		this.feve = feve;
		this.prodMin=new Variable(getNom()+" prod min", this, 0, 1000000, prodMin);
		this.prodMax=new Variable(getNom()+" prod max", this, 0, 1000000, prodMax);
		this.prixMin=new Variable(getNom()+" prix min", this, 0.1, 5, prixMin);
		this.prixMax=new Variable(getNom()+" prix max", this, 0.1, 5, prixMax);
		this.stockFeves=new Variable(getNom()+" stock feves", this, 0, 100000, 10000);
		this.journal = new Journal(this.getNom()+" activites", this);
	}
	
	public String getNom() {
		return "V.Feves A.O."+this.numero+""+feve.name();
	}

	public String getDescription() {
		return "Vendeur de feves par A.O. "+this.numero+" "+this.feve.name();
	}

	public Color getColor() {
		return new Color(128+((numero)*(127/NB_INSTANCES)), 64+((numero)*(191/NB_INSTANCES)), 0);
	}

	public void initialiser() {
	}

	public void next() {
		double production =  (int) (this.prodMin.getValeur()+ (Math.random()*(this.prodMax.getValeur()-prodMin.getValeur())));
		this.stockFeves.ajouter(this, production);
		this.journal.ajouter("Production de "+production);
	}

	public List<String> getNomsFilieresProposees() {
		return new ArrayList<String>();
	}

	public Filiere getFiliere(String nom) {
		return null;
	}

	public List<Variable> getIndicateurs() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.stockFeves);
		return res;
	}

	public List<Variable> getParametres() {
		List<Variable> res=new ArrayList<Variable>();
		res.add(this.prodMin);
		res.add(this.prodMax);
		res.add(this.prixMin);
		res.add(this.prixMax);
		return res;
	}

	public List<Journal> getJournaux() {
		List<Journal> j= new ArrayList<Journal>();
		j.add(this.journal);
		return j;
	}

	public void setCryptogramme(Integer crypto) {
		this.cryptogramme = crypto;
	}
	public void notificationFaillite(IActeur acteur) {
	}
	public void notificationOperationBancaire(double montant) {
	}
	
}
