package abstraction.eq2Producteur2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.sun.tools.javac.util.Pair;

import abstraction.eq8Romu.fevesAO.IAcheteurFevesAO;
import abstraction.eq8Romu.fevesAO.IVendeurFevesAO;
import abstraction.eq8Romu.fevesAO.OffreAchatFeves;
import abstraction.eq8Romu.fevesAO.PropositionVenteFevesAO;

public abstract class Producteur2VendeurFeveAO extends Producteur2Transfo implements IVendeurFevesAO {
	
	// ensemble fait par max


	protected LinkedList<PropositionVenteFevesAO> mesContratsAO;
	public LinkedList<PropositionVenteFevesAO> mesContratsAORefusess;

	public Producteur2VendeurFeveAO() {
		super(); 
		this.mesContratsAO = new LinkedList<PropositionVenteFevesAO>();
		this.mesContratsAORefusess = new LinkedList<PropositionVenteFevesAO>();
	}
	
	//Emeline
	 
	//Cette fonction renvoie le prix maximal, l'acheteur et la quantité moyenne. 
	public ArrayList<Object> acheteLePlusCher(LinkedList<PropositionVenteFevesAO> mesContratsAO) {
		//Ce dictionnaire fait correspondre un acheteur avec le prix moyen, et la quantité totale achetée
		HashMap<IAcheteurFevesAO, Pair <Double,Double>> prixMoyenAcheteur = new HashMap<IAcheteurFevesAO, Pair <Double,Double>>(); 
		//Ce dictionnaire fait correspondre un acheteur avec la quantité moyenne qu'il achète
		HashMap<IAcheteurFevesAO,Double> quantiteMoyenne=new HashMap<IAcheteurFevesAO, Double>(); 
		//Ce dictionnaire fait correspondre un acheteur avec le nombre de contrat qu'il a fait
		HashMap<IAcheteurFevesAO,Integer> nombreDeContrat=new HashMap<IAcheteurFevesAO, Integer>(); 
		
		if (!mesContratsAO.isEmpty()) {
			
			//ici on regarde qui nous a acheté le plus cher (le dico ne sert pas à grand chose au final)
			for (PropositionVenteFevesAO contrats : mesContratsAO) {
				double p=contrats.getPrixKG();
				IAcheteurFevesAO acheteur=contrats.getAcheteur();
				if (prixMoyenAcheteur.get(acheteur)!=null) {
					double prixActuel=prixMoyenAcheteur.get(acheteur).fst;
					double quantite=prixMoyenAcheteur.get(acheteur).snd;
					double quantiteTotale=quantite+contrats.getQuantiteKg();
					double prixMoyen= (p*contrats.getQuantiteKg()+prixActuel*quantite)/(quantiteTotale);   //On fait la moyenne des prix
					prixMoyenAcheteur.put(acheteur, new Pair<Double,Double>(prixMoyen,quantiteTotale));
					int nombreContrat=nombreDeContrat.get(acheteur);
					nombreDeContrat.put(acheteur, nombreContrat+1);
					double qttMoyenne=quantiteMoyenne.get(acheteur);
					quantiteMoyenne.put(acheteur, (qttMoyenne*nombreContrat+contrats.getQuantiteKg())/(nombreContrat+1));
				}
				else {
					prixMoyenAcheteur.put(acheteur, new Pair<Double,Double>(p,contrats.getQuantiteKg()));
					nombreDeContrat.put(acheteur,1);
					quantiteMoyenne.put(acheteur, contrats.getQuantiteKg());
				}
				
			}
			//ici on cherche le prix maximum
			double pmax=0;
			IAcheteurFevesAO acheteur= mesContratsAO.get(0).getAcheteur();
			for (Entry<IAcheteurFevesAO, Pair<Double, Double>> mapentry : prixMoyenAcheteur.entrySet()) {
				double possibleprice=mapentry.getValue().fst;
				if (possibleprice<pmax) {
					pmax=possibleprice;
					acheteur=mapentry.getKey();
				}
				
			}
			if (pmax!=0) {
				ArrayList<Object> l =new ArrayList<Object>();
				l.add(pmax);
				l.add(acheteur);
				l.add(quantiteMoyenne.get(acheteur));
				return l;
			}
			else {
				return new ArrayList<Object>();
			}
		}
		else {
			return new ArrayList<Object>();
		}
		
	}

	/**	@author Maxime Boillot

	 */
	public double proposerPrix(OffreAchatFeves oa) {
		double stock = qttTotale(oa.getFeve()).getValeur();
		//if (!(meilleurAcheteur.get(1).equals(oa.getAcheteur())) && meilleurAcheteur.get(0)<oa.get) {
		if (stock >= oa.getQuantiteKG() ) {
			double p = 0;
			double min = Producteur2VeudeurFeveCC.minAcceptee(oa.getFeve()); 
			for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
				if (c.getAcheteur() == oa.getAcheteur() && c.getFeve()==oa.getFeve()) {
					p = c.getPrixKG() - Producteur2VeudeurFeveCC.difAcceptee(oa.getFeve());
				}
			}if (p>0) {
				if (p >= min) {return p;}
				else {return min;}
			}else{
				return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 4;	
			}
		}else {
			return 0.0;
		}
	}
	
	//A changer 
	//Dans le cas où la liste des contrats ao n'est pas vide
	public double proposerPrix2(OffreAchatFeves oa) {
		double stock = qttTotale(oa.getFeve()).getValeur();
		ArrayList meilleurAcheteurTableau=acheteLePlusCher(mesContratsAO);
		IAcheteurFevesAO meilleurAcheteur=(IAcheteurFevesAO)meilleurAcheteurTableau.get(1);
		double meilleurPrix=(double)meilleurAcheteurTableau.get(0);
		double meilleurQuantite=(double)meilleurAcheteurTableau.get(2);
		double p = 0;
		double min = Producteur2VeudeurFeveCC.minAcceptee(oa.getFeve()); 
		for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
			if (c.getAcheteur() == oa.getAcheteur() && c.getFeve()==oa.getFeve()) {
				p = c.getPrixKG() - Producteur2VeudeurFeveCC.difAcceptee(oa.getFeve());
			}
		}if (p>0) {
			if (p >= min) {return p;}
			else {return min;}
		}else{
			return Producteur2VeudeurFeveCC.prixEspere(oa.getFeve()) * 4;	
		}
	}

	/**	@author Maxime Boillot

	 */
	public void notifierPropositionRefusee(PropositionVenteFevesAO proposition) {
		this.mesContratsAORefusess.add(proposition);
	}

	/**	@author Maxime Boillot

	 */
	public void notifierVente(PropositionVenteFevesAO proposition) {
		this.JournalVente.ajouter("nouvelle vente AO avec " + proposition.getAcheteur().getNom() + " qtt = " + Math.floor(proposition.getQuantiteKg()) + proposition.getFeve()
		+ " pour " + proposition.getPrixKG() + "euro au kg, soit " + Math.floor(proposition.getPrixKG()*proposition.getQuantiteKg()) );
		this.mesContratsAO.add(proposition);
		vente(proposition.getQuantiteKg(), proposition.getFeve());
		LinkedList<PropositionVenteFevesAO> rem = new LinkedList<PropositionVenteFevesAO>();
		for (PropositionVenteFevesAO c : this.mesContratsAORefusess) {
			if (c.getAcheteur() == proposition.getAcheteur()) {
				rem.add(c);
			}this.mesContratsAORefusess.removeAll(rem);
		}
	}
}


