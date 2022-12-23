package by.bsu.fpmi.Z.hierarchy;

public class FirstInheritor extends Ancestor {
	private boolean uniqProperty;
	public FirstInheritor(int fProperty, String sProperty, double tProperty,
			AnotherProperty lProperty,boolean uProperty) {
		super(fProperty, sProperty, tProperty, lProperty);
		this.uniqProperty=uProperty;
	}

	public void action() {
		System.out.println("First inheritor doin action");
	}

	@Override
	public void abstractAction() {
		System.out.println("First inheritor doin abstract action");
	}
	
	public String toString() {
		return super.toString()+" uniqProperty1="+String.valueOf(uniqProperty);
	}

	public boolean isUniqProperty() {
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
