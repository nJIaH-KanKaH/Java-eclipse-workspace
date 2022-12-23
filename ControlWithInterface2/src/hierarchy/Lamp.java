package hierarchy;

public abstract class Lamp {
	public Lamp(String org, int p) {
		this.org = org;
		P = p;
	}

	private String org;
	private int P;

	@Override
	public String toString() {
		return "org=" + org + " P=" + P;
	}
	
	public abstract String toXML();

	public String getOrg() {
		return org;
	}

	public int getP() {
		return P;
	}
	
	public abstract double getPrice();
}
