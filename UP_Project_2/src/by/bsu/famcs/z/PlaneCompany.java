package by.bsu.famcs.z;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import by.bsu.famcs.z.hierarchy.*;

public class PlaneCompany<T extends Plane> extends ArrayList<T>{
	private String name;
	
	public String resultPlacesAndMaxBaggage() {
		int resPlaces=this.stream().mapToInt(Plane::getPlaces).sum();
		int maxbaggageSum=this.stream().filter(p->p.getClass()==Airliner.class).collect(Collectors.toList()).stream().map(e->(Airliner)e).mapToInt(Airliner::getMaxBaggage).sum();
		return "resPlaces="+resPlaces+", maxbaggageSum="+maxbaggageSum;
	}
	
	public void sortByRangeOfFlight() {
		Collections.sort(this,new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				return arg0.getRangeOfFlight()-arg1.getRangeOfFlight();
			}
		});
	}
	
	public List<T> findByFuel(int lower,int upper){
		return this.stream().filter(new Predicate<T>() {
			@Override
			public boolean test(T t) {
				if(Float.compare(t.getFuel(), lower)>=0 && Float.compare(upper, t.getFuel())>=0)
					return true;
				return false;
			}}).collect(Collectors.toList());
	} 

	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder("[");
		for(T i:this) {
			builder.append(i.toString());
			builder.append(i.equals(this.get(this.size()-1))?"": ", \n ");
		}
		builder.append("]");
		return builder.toString();
	}
	
	public String toXML() {
		StringBuilder builder=new StringBuilder("<?xml version=\"1.0\"?> \n <Planes> \n");
		for(T i:this) {
			builder.append(i.toXML());
			builder.append(i.equals(this.get(this.size()-1))?"": ", \n ");
		}
		builder.append("</Planes>");
		return builder.toString();
	}
}
