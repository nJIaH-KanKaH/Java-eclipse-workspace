package by.bsu.fpmi.Z;

import by.bsu.fpmi.Z.hierarchy.Ancestor;

public interface SpecialPr<T extends Ancestor> {
	public boolean test(Ancestor object);
	
	public static <T extends Ancestor> int countIf(SpecialContainer<T> sContainer,
			SpecialPr<T> p) {
		int counter=0;
		for(T i:sContainer) {
			if(p.test(i))
				counter++;
		}
		return counter;
	}	
}
