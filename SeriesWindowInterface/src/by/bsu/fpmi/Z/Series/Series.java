package by.bsu.fpmi.Z.Series;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public abstract class Series {
	protected float firstElement;
	protected float diff;
	
	public Series(float first,float diff) {
		this.firstElement=first;
		this.diff=diff;
	}
	
	public float getFirst() {
		return firstElement;
	}
	
	public abstract float getElement(int j) throws NoSuchElementException;
	
	public float getSumOf(int n) throws NoSuchElementException {
		float sum=0.0f;
		for(int i=0;i<n;i++) {
			sum+=getElement(i);
		}
		return sum;
	}
	
	public String toString(int numberOfElements) throws NoSuchElementException {
		ArrayList<Float>series=new ArrayList<>();
		for(int i=0;i<numberOfElements;i++)
			series.add(getElement(i));
		return series.toString();
	}
	
	public void getSeriesToFile(String fileName,int numberOfElements) throws IOException, NoSuchElementException {
		FileWriter fileWriter=new FileWriter(new File(fileName==""?"autoGenoutput.txt":fileName+".txt"));
		fileWriter.write("Series: "+this.toString(numberOfElements)+"\n");
		fileWriter.write("Series Sum:"+Float.toString(this.getSumOf(numberOfElements)));
		fileWriter.close();
	}
	
	public static class NoSuchElementException extends Exception{
		NoSuchElementException(String message){
			super(message);
		}
	}
}
