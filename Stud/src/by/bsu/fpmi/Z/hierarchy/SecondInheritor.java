package by.bsu.fpmi.Z.hierarchy;

public class SecondInheritor extends Ancestor{
	private float uniqProperty;
	public SecondInheritor(int fProperty, String sProperty, double tProperty,
			AnotherProperty lProperty,float uProperty) {
		super(fProperty, sProperty, tProperty, lProperty);
		this.uniqProperty=uProperty;
	}

	public void action() {
		System.out.println("Second inheritor doin action");
	}

	@Override
	public void abstractAction() {
		System.out.println("Second inheritor doin abstract action");
	}
	
	public String toString() {
		return super.toString()+" uniqProperty2="+String.valueOf(uniqProperty);
	}

	public float getUniqProperty() {
		return uniqProperty;
	}

	@Override
	public double compareTo(Ancestor i) {
		return super.compareTo(i);
	}

	@Override
	public boolean equals(Ancestor i) {
		return super.equals(i)&&this.getClass()==i.getClass();
	}
}
