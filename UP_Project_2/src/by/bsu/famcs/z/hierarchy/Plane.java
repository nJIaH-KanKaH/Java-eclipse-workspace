package by.bsu.famcs.z.hierarchy;

public class Plane {
	protected short maxHeight;
	protected int places;
	private int weight;
	protected int rangeOfFlight;
	protected float fuel;
	public Plane(short maxHeight, int places, int weight, int rangeOfFlight,float fuel) {
		this.maxHeight = maxHeight;
		this.places = places;
		this.weight = weight;
		this.rangeOfFlight = rangeOfFlight;
		this.fuel=fuel;
	}
	@Override
	public String toString() {
		return "Plane [maxHeight=" + maxHeight + ", places=" + places + ", weight=" + weight + ", rangeOfFlight="
				+ rangeOfFlight + ", fuel=" + fuel + "]";
	}
	
	public String toXML() {
		return String.format("<Plane maxHeight='%s' places='%s' weight='%s' rangeOfFlight='%s' fuel='%s'/>", maxHeight, places, weight, rangeOfFlight, fuel);
	}
	public short getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(short maxHeight) {
		this.maxHeight = maxHeight;
	}
	public int getPlaces() {
		return places;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getRangeOfFlight() {
		return rangeOfFlight;
	}
	public void setRangeOfFlight(int rangeOfFlight) {
		this.rangeOfFlight = rangeOfFlight;
	}
	public float getFuel() {
		return fuel;
	}
	public void setFuel(float fuel) {
		this.fuel = fuel;
	}
}
