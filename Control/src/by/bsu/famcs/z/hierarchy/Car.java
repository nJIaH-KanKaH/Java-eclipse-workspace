package by.bsu.famcs.z.hierarchy;

public class Car extends Automobile {
	private Body body;
	public Car(String iname, Fuel ifuel, double ifuelPerHundred, int iyear,Body ibody) {
		super(iname, ifuel, ifuelPerHundred, iyear);
		this.body=ibody;
	}
	
	public String toString() {
		return super.toString()+" body="+body;
	}
	
	public boolean equals(Car a) {
		if(super.equals(a)&&this.body.equals(a.body))
			return true;
		return false;
	}
}
