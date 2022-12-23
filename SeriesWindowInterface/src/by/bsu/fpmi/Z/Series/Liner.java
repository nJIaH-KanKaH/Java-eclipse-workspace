package by.bsu.fpmi.Z.Series;

public class Liner extends Series {

	public Liner(float first, float diff) {
		super(first, diff);
	}

	public float getElement(int j) throws NoSuchElementException {
		if(j<0)
			throw new NoSuchElementException("Exception: j is below zero");
		return firstElement+diff*j;
	}

}
