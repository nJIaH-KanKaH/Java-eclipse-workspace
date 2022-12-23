package HelloWorld;
import java.util.Scanner;
import java.lang.Math;
import java.util.StringTokenizer;
import java.util.Vector;

import MyException.ArgumentsException;
import MyException.ErrorTooSmallException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*(x^2k)/(k!*2^k)*/

public class HelloWorld {

	public static void main(String[] args) throws IOException {
		  double x=0.0d;
		  double error=0.0d; 
		  System.out.println("Enter parameter & error");
		  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		  String string = reader.readLine(); 
		  try { 
			  String[] strMas= string.split(" ");
			  if(strMas.length!=2) { 
				  throw new ArgumentsException("Exception: wrong number of arguments"); 
				  } else { 
					  double[] numbers = new double[strMas.length];
					  for (int i = 0; i < strMas.length; i++)
						  numbers[i] = Double.parseDouble(strMas[i]); 
					  x=numbers[0]; 
					  try {
						  if(numbers[1]<0) 
							  throw new ErrorTooSmallException("Exception: error is below zero"); 
						  else
							  error=numbers[1]; 
						  } catch (ErrorTooSmallException exception){
							  System.out.println(exception.getMessage()); 
							  return; 
							  } 
					  } 
			  } catch (ArgumentsException exception) {
				  System.out.println(exception.getMessage());
				  return; 
				  } 
		  double Sum=seriesSum(x, error);
	  
	  System.out.print("Sum: "); 
	  System.out.println(Sum); 
	  }
	
	public static double seriesSum(double x,double error) {
		double Sum=0;
		int counter=1;
		double element=x*x/((double)counter*2.0d);
		while(Math.abs(element)>error) {
			counter++;
			//System.out.println(element);
			Sum+=element;
			element=element*x*x/((double)counter*2.0d);
		}
		return Sum;
	}
}