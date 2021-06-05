package abstraction.eq3Transformateur1;



//Chloe 
public class Triple  {
	
	private double q;
	private int t;
	private boolean b;
	
	public Triple(double q, int t, boolean b) { 
		this.q=q;
		this.t=t;
		this.b = b;
	}
	public double get1() {
		return this.q; 
	}
	public int get2() {
		return this.t;
	}
	
	public boolean get3() {
		return this.b;
	}
	
	public void set1(double q) {
		this.q = q;
	}
	
	public void set2(int t) {
		this.t = t;
	}
	
	public void set3(boolean b) {
		this.b = b; 
	}
	
	public boolean isNot(Triple triple) {
		if (this.get1() != triple.get1() && this.get2() != triple.get2()) {
			return true;
		}
		return false;
	}
	
}