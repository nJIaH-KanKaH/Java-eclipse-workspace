package by.bsu.famcs.z.hierarchy;

public abstract class Automobile {
	private String name;
	private Fuel fuel;
	private double fuelPerHundred;
	private int year;
	
	public Automobile(String iname,Fuel ifuel,double ifuelPerHundred,int iyear) {
		this.name=iname;
		this.fuel=ifuel;
		this.fuelPerHundred=ifuelPerHundred;
		this.year=iyear;
	}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Fuel getFuel() {
		return fuel;
	}
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}
	public double getFuelPerHundred() {
		return fuelPerHundred;
	}
	public void setFuelPerHundred(double fuelPerHundred) {
		this.fuelPerHundred = fuelPerHundred;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public double compareTo(Automobile a) {
		return this.year-a.year==0 ? this.fuelPerHundred-a.fuelPerHundred : a.year-this.year;
	}


	@Override
	public String toString() {
		return "Auto: name=" + name + ", fuel=" + fuel + ", fuelPerHundred=" + fuelPerHundred + ", year=" + year;
	}

	public boolean equals(Automobile arg0) {
		return this.name.equals(arg0.name)
				&&this.year==arg0.year
				&&this.fuel.equals(arg0.fuel)
				&&this.fuelPerHundred==arg0.fuelPerHundred;
	}
}
