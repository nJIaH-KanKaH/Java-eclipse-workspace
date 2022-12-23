package by.bsu.fpmi.Z.hierarchy;

import by.bsu.fpmi.Z.UnaryPredicate;

public abstract class Ancestor {
	private
	int firstProperty; private
	String secondProperty; private
	double thirdProperty; private
	AnotherProperty lastProperty;
	
	public Ancestor(int fProperty, String sProperty,double tProperty,AnotherProperty lProperty) {
		this.firstProperty=fProperty;
		this.secondProperty=sProperty;
		this.thirdProperty=tProperty;
		this.lastProperty=lProperty; 
	}
	
	public Ancestor() {
		this.firstProperty=0;
		this.secondProperty="";
		this.thirdProperty=0.0;
		this.lastProperty=AnotherProperty.EMPTY;
	}
	
	public String toString() {
		return "firstProperty="+String.valueOf(this.firstProperty)+
				" secondProperty="+this.secondProperty+
				" thirdProperty="+String.valueOf(thirdProperty)+
				" lastProperty="+lastProperty.name();
	}
	
	public abstract void abstractAction();
	
	public void action() {
		System.out.println("ancestor doin action");
	}

	public int getFirstProperty() {
		return firstProperty;
	}

	public String getSecondProperty() {
		return secondProperty;
	}

	public double getThirdProperty() {
		return thirdProperty;
	}

	public AnotherProperty getLastProperty() {
		return lastProperty;
	}

	public double compareTo(Ancestor i) {
		return this.firstProperty-i.firstProperty!=0 ? this.firstProperty-i.firstProperty :
			this.secondProperty.compareTo(i.secondProperty)!=0 ? this.secondProperty.compareTo(i.secondProperty) :
				this.thirdProperty-i.thirdProperty!=0 ? this.thirdProperty-i.thirdProperty : 0;
	};
	
	public boolean equals(Ancestor i) {
		return this.firstProperty-i.firstProperty==0 
				&& this.secondProperty.equals(i.secondProperty) 
				&& this.thirdProperty-i.thirdProperty==0 
				&& this.lastProperty.equals(i.lastProperty);
	}
}
