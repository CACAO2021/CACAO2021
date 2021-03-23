package abstraction.fourni;

import java.util.List;

/**
 * Interface que doit implementer tout acteur detenteur d'une ou plusieurs marques de chocolat.
 * 
 * @author Romuald DEBRUYNE
 *
 */
public interface IMarqueChocolat {

/**
 * Methode appelee en debut de simulation afin de connaitre les marques
 * de chocolat dont l'acteur est le detenteur
 * Remarques : 
 * - un distributeur a au plus une marque.
 * - un producteur/transformateur peut produire des chocolats de l'une de ses marques 
 *   ou de la marque d'un distributeur (partenariat dans lequel le distributeur delegue la 
 *   realisation de ses chocolat a un producteur/transformateur)
 * - une marque n'est detenue que par un acteur (deux acteurs ne peuvent pas detenir la meme marque)
 * @return
 */
	public List<String> getMarquesChocolat() ;
}
