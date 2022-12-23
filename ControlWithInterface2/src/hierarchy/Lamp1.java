package hierarchy;

import java.util.Comparator;

public class Lamp1 implements Comparator<Lamp> {
	@Override
	public int compare(Lamp a0, Lamp a1) {
		return Double.compare(a0.getPrice(), a1.getPrice());
	}

}
