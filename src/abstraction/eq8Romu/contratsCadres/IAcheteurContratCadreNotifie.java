package abstraction.eq8Romu.contratsCadres;

public interface IAcheteurContratCadreNotifie extends IAcheteurContratCadre {
	/**
	 * Methode appelee par le SuperviseurVentesContratCadre afin de notifier 
	 * l'acheteur de la reussite des negociations sur le contrat precise en parametre.
	 * Le superviseur veillera a l'application de ce contrat (des appels a livrer(...) 
	 * seront effectues lorsque le vendeur devra livrer afin d'honorer le contrat, et
	 * des transferts d'argent auront lieur lorsque l'acheteur paiera les echeances prevues)..
	 * @param contrat
	 */
	public void notificationNouveauContratCadre(ExemplaireContratCadre contrat);

}
