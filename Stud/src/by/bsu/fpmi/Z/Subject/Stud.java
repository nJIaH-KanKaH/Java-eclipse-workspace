package by.bsu.fpmi.Z.Subject;

public class Stud {
	private String fn;
	private String ln;
	private String ftn;
	private int year;
	private int month;
	private Faculty f;
	private int c;
	private int g;
	public Stud(String fn, String ln, String ftn, int year, int month, Faculty f, int c, int g) {
		super();
		this.fn = fn;
		this.ln = ln;
		this.ftn = ftn;
		this.year = year;
		this.month = month;
		this.f = f;
		this.c = c;
		this.g = g;
	}
	@Override
	public String toString() {
		return "Stud [fn=" + fn + ", ln=" + ln + ", ftn=" + ftn + ", year=" + year + ", month=" + month + ", f=" + f
				+ ", c=" + c + ", g=" + g + "]";
	}
	
}
