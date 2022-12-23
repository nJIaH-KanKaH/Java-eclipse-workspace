package by.bsu.fpmi.Z;

public class Student extends Number{
	private String name;
	private int ball;
	
	public Student(String name,int ball) {
		this.name=name;
		this.ball=ball;
	}

	@Override
	public double doubleValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public int intValue() {
		return ball;
	}

	@Override
	public long longValue() {
		return 0;
	}
}
