package hierarchy;

public class LampN extends Lamp {
	public LampN(String org, int p,int time) {
		super(org, p);
		this.time=time;
	}

	public static final double CONST_1=2.0;
	
	private int time;

	@Override
	public String toString() {
		return super.toString()+" time="+time+" price="+getPrice();
	}

	@Override
	public String toXML() {
		return String.format("<LampN org='%s' P='%s' time='%s'/>", super.getOrg(), super.getP(), time);
	}

	@Override
	public double getPrice() {
		int temp=(int) ((double)time*CONST_1*(double)super.getP()*100.0);
		return (double)temp/100d;
	}

	public int getTime() {
		return time;
	}

}
