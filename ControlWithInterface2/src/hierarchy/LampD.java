package hierarchy;

public class LampD extends Lamp{
	public LampD(String org, int p,int number) {
		super(org, p);
		this.number=number;
	}

	public static final double CONST_2=1.0;
	
	private int number;

	
	public String toString() {
		return super.toString()+" number="+number+" price="+getPrice();
	}
	
	@Override
	public String toXML() {
		return String.format("<LampD org='%s' P='%s' number='%s'/>", super.getOrg(), super.getP(), number);
	}

	@Override
	public double getPrice() {
		int tmp=(int) ((double)super.getP()*(double)number*100.0/CONST_2);
		return (double)tmp/100d;
	}
	
	public int getNumber() {
		return number;
	}

}
