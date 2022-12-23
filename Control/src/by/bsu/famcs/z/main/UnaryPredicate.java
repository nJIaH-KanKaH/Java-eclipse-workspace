package by.bsu.famcs.z.main;

import by.bsu.famcs.z.hierarchy.Automobile;

public interface UnaryPredicate <T>{
	public boolean test(T object);
	
	public static <T>int countIf(T[]array,UnaryPredicate<T>predicate){
		int counter=0;
		for(T i:array) {
			if(predicate.test(i))
				counter++;
		}
		return counter;
	}

	public static <T extends Automobile>int countIf(SpecialContainer<T> specialContainer, UnaryPredicate<T> unaryPredicate) {
		int counter=0;
		for(T i:specialContainer) {
			if(unaryPredicate.test(i))
				counter++;
		}
		return counter;
	}	
}
