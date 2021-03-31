package abstraction.eq3Transformateur1;

public interface IActeur {
	public void setCrypto(Integer crypto);
	public List<Variable> getIndicateurs();
	public List<Variable> getParametres();
	public List<Journal> getJourneaux();
	public void initialiser();
	public String getNom();
	public String getDescription();
	public Color getColor();
}
