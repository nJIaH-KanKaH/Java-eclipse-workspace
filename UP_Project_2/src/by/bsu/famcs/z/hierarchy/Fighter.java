package by.bsu.famcs.z.hierarchy;

public class Fighter extends Warplane {
	public Fighter(short maxHeight, int places, int rangeOfFlight, float fuel, int maxSpeed,int rockets) {
		super(maxHeight, places, rangeOfFlight, fuel, maxSpeed);
		this.rockets=rockets;
	}

	private int rockets;

	@Override
	public String toXML() {
		return String.format("<Fighter rockets='%s' maxSpeed='%s' maxHeight='%s' places='%s' rangeOfFlight='%s' fuel='%s'/>", 
					rockets, maxSpeed, maxHeight, places, rangeOfFlight, fuel);
	}

	@Override
	public String toString() {
		return "Fighter [rockets=" + rockets + ", maxSpeed=" + maxSpeed + ", maxHeight=" + maxHeight + ", places="
				+ places + ", rangeOfFlight=" + rangeOfFlight + ", fuel=" + fuel + "]";
	}
}
