package by.bsu.famcs.z.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import by.bsu.famcs.z.hierarchy.Automobile;


public class SpecialContainer<T extends Automobile> extends ArrayList<T> {
	public List<T> min() {
		List<T>list1=new ArrayList<>(this);
		Collections.sort(list1,new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				
				return arg0.getYear()-arg1.getYear()==0 ? (int)(arg0.getFuelPerHundred()-arg1.getFuelPerHundred()) : arg1.getYear()-arg0.getYear();//needs Double.compare(arg0, arg1)
			}
		});
		List<T>list=new ArrayList<>();
		for(int i=0;i<3;i++) {
			if(!list.contains(list1.get(i)))
				list.add(list1.get(i));
		}
		return list;
	}
	
	public void sortByYearAndFuel() {
		Collections.sort(this,new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				return (int) arg0.compareTo(arg1);//neeeds Double.compare(arg.0,arg1)
			}
		});
	}
	
	public String toString() {
		StringBuilder builder=new StringBuilder("[");
		for(T i:this) {
			builder.append(i.toString());
			builder.append(i.equals(this.get(this.size()-1))?"": ", \n ");
		}
		builder.append("]");
		return builder.toString();
	}
	
	public int countIf(T obj) {
		return UnaryPredicate.countIf(this, new UnaryPredicate<T>() {

			@Override
			public boolean test(T object) {
				if(object.equals(obj))
					return true; 
				return false;
			}
		});
	}
	
	public void printWithLambdaAndStreamAPI(String name) {
		System.out.println("number of auto named "+name+":"+this.stream().filter((p)->p.getName().equals(name)).count());
	}
}
