package by.bsu.fpmi.Z.CustomShort;

public class CustomShort implements Comparable<CustomShort>{
	private short value=0;
	public CustomShort(short inst) {
		value=inst;
	}
	@Override
	public int compareTo(CustomShort arg0) {
		return value-arg0.value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
}
