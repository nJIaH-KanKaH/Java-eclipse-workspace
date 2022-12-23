package by.bsu.famcs.z.hierarchy;

public class Bomber extends Warplane{
	public Bomber(short maxHeight, int places, int rangeOfFlight, float fuel, int maxSpeed,int bombs) {
		super(maxHeight, places, rangeOfFlight, fuel, maxSpeed);
		this.bombs=bombs;
	}

	private int bombs;

	@Override
	public String toXML() {
		return String.format("<Bomber bombs='%s' maxSpeed='%s' maxHeight='%s' places='%s' rangeOfFlight='%s' fuel='%s'/>", 
				bombs, maxSpeed, maxHeight, places, rangeOfFlight, fuel);
	}

	@Override
	public String toString() {
		return "Bomber [bombs=" + bombs + ", maxSpeed=" + maxSpeed + ", maxHeight=" + maxHeight + ", places="
				+ places + ", rangeOfFlight=" + rangeOfFlight + ", fuel=" + fuel + "]";
	}
}
