package by.bsu.famcs.z.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import by.bsu.famcs.z.hierarchy.Automobile;
import by.bsu.famcs.z.hierarchy.Body;
import by.bsu.famcs.z.hierarchy.Car;
import by.bsu.famcs.z.hierarchy.Fuel;
import by.bsu.famcs.z.hierarchy.Truck;

public class Main {
	public static final String INPUT_1="input1.txt";
	public static final String INPUT_2="input2.txt";
	
	public static void readFromFileFirst(SpecialContainer<Automobile>sContainer,String fileName) throws FileNotFoundException, NumberFormatException,IllegalArgumentException {
		Scanner scanner=new Scanner(new File(fileName));
		int N=scanner.nextInt();
		for(int i=0;i<N;i++) {
			Automobile one=new Car(scanner.next(),Fuel.valueOf(scanner.next()),scanner.nextDouble(),scanner.nextInt(),Body.valueOf(scanner.next()));
			sContainer.add(one);
		}
		if(scanner.hasNext()){
			scanner.close();
			throw new NoSuchElementException();
		} else
			scanner.close();
	}
	
	public static void readFromFileSecond(SpecialContainer<Automobile>sContainer,String fileName) throws FileNotFoundException, NumberFormatException,IllegalArgumentException {
		Scanner scanner=new Scanner(new File(fileName));
		int N=scanner.nextInt();
		for(int i=0;i<N;i++) {
			Automobile one=new Truck(scanner.next(), Fuel.valueOf(scanner.next()), scanner.nextDouble(),
					scanner.nextInt(), scanner.nextInt(),scanner.nextDouble());
			sContainer.add(one);
		}
		if(scanner.hasNext()){
			scanner.close();
			throw new NoSuchElementException();
		} else
			scanner.close();
	}
	
	public static void main(String[] args) {
		SpecialContainer<Automobile>sContainer1=new SpecialContainer<>();
		SpecialContainer<Automobile>sContainer2=new SpecialContainer<>();
		try {
			System.out.print("INPUT_1 ...");
			readFromFileFirst(sContainer1, INPUT_1);
			System.out.println(" read.");
			
			System.out.print("INPUT_2 ...");
			readFromFileSecond(sContainer2, INPUT_2);
			System.out.println(" read.");
		} catch (FileNotFoundException e) {
			System.out.println("Exception: file with such name doesnt exist");
		}catch (NumberFormatException|NoSuchElementException e) {
			System.out.println("Exception: wronge format of data");
		}catch (IllegalArgumentException e) {
			System.out.println("Exception: such element doesnt exist in base");
		}
		
		System.out.println("min in sContainer1:");
		System.out.println(sContainer1.min());
		System.out.println("min in sContainer2:");
		System.out.println(sContainer2.min());
		sContainer1.sortByYearAndFuel();
		System.out.println("sContainer1:");
		System.out.println(sContainer1);
		sContainer2.sortByYearAndFuel();
		System.out.println("sContainer2:");
		System.out.println(sContainer2);
		sContainer1.printWithLambdaAndStreamAPI("Volvo");
		System.out.println("number of trucks: Renault DIESEL 35d 2014 8 8d\n"+sContainer2.countIf(new Truck("Renault", Fuel.DIESEL, 35d, 2014, 8, 8d)));
	}
}
