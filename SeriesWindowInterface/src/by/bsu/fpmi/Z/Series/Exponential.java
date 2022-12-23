package by.bsu.fpmi.Z.Series;

public class Exponential extends Series {

	public Exponential(float first, float diff) {
		super(first, diff);
	}

	public float getElement(int j) throws NoSuchElementException {
		if (j<0) {
			throw new NoSuchElementException("Exception: j is below zero");
		}
		if(j==0)
			return firstElement;
		else 
			return diff*this.getElement(j-1);
	}
	
	public float getElementNonRecursive(int j) throws NoSuchElementException {
		if (j<0) {
			throw new NoSuchElementException("Exception: j is below zero");
		}
		float temp=1.0f;
		for(int i=0;i<j;i++)
			temp*=diff;
		return temp*firstElement;
	}

}
