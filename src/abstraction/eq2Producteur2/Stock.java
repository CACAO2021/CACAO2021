package abstraction.eq2Producteur2;

public class Stock {
	private double qtt;
	private int step;
	/**
	 * @param qtt
	 * @param step
	 */
	public Stock(double qtt, int step) {
		this.qtt = qtt;
		this.step = step;
	}
	/**
	 * @return the qtt
	 */
	public double getQtt() {
		return qtt;
	}
	/**
	 * @param d the qtt to set
	 */
	public void setQtt(double d) {
		this.qtt = d;
	}
	/**
	 * @return the step
	 */
	public int getStep() {
		return step;
	}

}
