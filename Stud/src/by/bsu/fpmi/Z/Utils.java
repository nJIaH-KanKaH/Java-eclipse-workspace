package by.bsu.fpmi.Z;

import by.bsu.fpmi.Z.hierarchy.Ancestor;

public class Utils{
	public static <T extends Number> int calcSum(T[]array){
		int sum=0;
		for(T i:array) {
			sum=sum+i.intValue();
		}
		return sum;
	}
	
	public static <T extends Number> double calcAverage(T[]array) throws NException{
		double av=0;
		int sum=0;
		if(array.length!=0)
		{
			sum=calcSum(array);
			av=sum/array.length;
		}
		else throw new NException("null size array");
		return av;
	}
	
	public static <T extends Ancestor> int calcSum(T[]array) {
		int sum=0;
		for(T i:array) {
			sum+=i.getFirstProperty();
		}
		return sum;
	}
	
	public static <T extends Ancestor> double calcAverage(T[]array) throws NException {
		double av=0;
		int sum=0;
		if(array.length!=0)
		{
			sum=calcSum(array);
			av=sum/array.length;
		}
		else throw new NException("null size array");
		return av;
	}
	
	public static class NException extends Exception{
		NException(String message){
			super(message);
		}
	}
}
