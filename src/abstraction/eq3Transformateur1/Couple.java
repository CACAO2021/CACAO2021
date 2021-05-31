package abstraction.eq3Transformateur1;

public class Couple  {
	
	private double q;
	private int t;
	
	public Couple(double q, int t) { 
		this.q=q;
		this.t=t;
	}
	public double get1() {
		return this.q; 
	}
	public int get2() {
		return this.t;
	}
	
	public void set1(double q) {
		this.q = q;
	}
	
	public void set2(int t) {
		this.t = t;
	}
	
	public boolean isNot(Couple couple) {
		if (this.get1() != couple.get1() && this.get2() != couple.get2()) {
			return true;
		}
		return false;
	}
	
}