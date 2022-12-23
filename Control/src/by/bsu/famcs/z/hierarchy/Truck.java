package by.bsu.famcs.z.hierarchy;

public class Truck extends Automobile {
	private int wheels;
	private double capacity;
	public Truck(String iname, Fuel ifuel, double ifuelPerHundred, int iyear,int iwheels,double icapacity) {
		super(iname, ifuel, ifuelPerHundred, iyear);
		this.wheels=iwheels;
		this.capacity=icapacity;
	}

	public String toString() {
		return super.toString()+" wheels="+String.valueOf(wheels)+" capacity="+String.valueOf(capacity);
	}
	
	public boolean equals(Truck t) {
		if(super.equals(t)&&this.wheels==t.wheels && this.capacity==t.capacity)
			return true;
		return false;
	}
}
