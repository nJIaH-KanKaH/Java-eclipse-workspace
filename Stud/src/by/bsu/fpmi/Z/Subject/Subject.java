package by.bsu.fpmi.Z.Subject;

public class Subject {
	private
	String name; private
	double volume;
	
	public Subject(String name, double volume) {
		super();
		this.name = name;
		this.volume = volume;
	}

	public boolean equals(Subject arg0) {
		return this.name.equals(arg0.name) && this.volume-arg0.volume<0.00001d;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	public String toString() {
		return "name "+name+" volume "+String.valueOf(volume);
	}
	
	public double compareTo(Subject i) {
		return this.volume-i.volume;
	}

	public String getName() {
		return name;
	}

	public double getVolume() {
		return volume;
	}
	
	
}
