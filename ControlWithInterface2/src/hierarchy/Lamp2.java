package hierarchy;

import java.util.Comparator;

public class Lamp2 implements Comparator<Lamp> {

	@Override
	public int compare(Lamp arg0, Lamp arg1) {
		if(arg0.getP()!=0 && arg1.getP()!=0)
			return Double.compare(arg1.getPrice()/(double)arg1.getP(), arg0.getPrice()/(double)arg0.getP());
		else return 0;
	}

}
