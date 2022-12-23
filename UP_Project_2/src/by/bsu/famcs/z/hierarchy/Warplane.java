package by.bsu.famcs.z.hierarchy;

public abstract class Warplane extends Plane {
	public Warplane(short maxHeight, int places, int rangeOfFlight, float fuel,int maxSpeed) {
		super(maxHeight, places, -1, rangeOfFlight, fuel);
		this.maxSpeed=maxSpeed;
	}

	protected int maxSpeed;
	
	public abstract String toXML();

	@Override
	public abstract String toString();
}
