package by.bsu.fpmi.Z;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import by.bsu.fpmi.Z.hierarchy.Ancestor;
import by.bsu.fpmi.Z.hierarchy.AnotherProperty;
import by.bsu.fpmi.Z.hierarchy.FirstInheritor;
import by.bsu.fpmi.Z.hierarchy.SecondInheritor;

public class SpecialContainer<T extends Ancestor> extends ArrayList<T>{
	
	public T findElementByFirstProperty(int key) {
		for(T i:this) {
			if(i.getFirstProperty()==key)
				return i;
		}
		System.out.println("no such element");
		return null;
	}
	
	public boolean find(T element) {
		for(T i:this) {
			if(i.equals(element))
				return true;
		}
		System.out.println("no such element");
		return false;
	}
	
	public Ancestor min() {
		return Collections.min(this,new Comparator<T>() {

			@Override
			public int compare(T arg0, T arg1) {
				return arg0.getFirstProperty()-arg1.getFirstProperty();
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
	
	public static class OddElementException extends Exception{
		OddElementException(String message) {
			super(message);
		}
	}
}
