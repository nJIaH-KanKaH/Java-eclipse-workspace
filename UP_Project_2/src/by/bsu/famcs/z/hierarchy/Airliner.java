package by.bsu.famcs.z.hierarchy;

public class Airliner extends Plane {
	private short maxBaggage;

	public Airliner(short maxHeight, int places, int rangeOfFlight, float fuel,short maxBaggage) {
		super(maxHeight, places, -1, rangeOfFlight, fuel);
		this.maxBaggage=maxBaggage;
	}

	@Override
	public String toString() {
		return "Airliner [maxHeight=" + maxHeight + ", places=" + places + ", maxBaggage=" + maxBaggage + ", rangeOfFlight="
				+ rangeOfFlight + ", fuel=" + fuel + "]";
	}

	@Override
	public String toXML() {
		return String.format("<Airliner maxHeight='%s' places='%s' maxBaggage='%s' rangeOfFlight='%s' fuel='%s'/>", maxHeight, places, maxBaggage, rangeOfFlight, fuel);
	}

	public short getMaxBaggage() {
		return maxBaggage;
	}

	public void setMaxBaggage(short maxBaggage) {
		this.maxBaggage = maxBaggage;
	}

}
