package by.bsu.fpmi.Z;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import by.bsu.fpmi.Z.SpecialContainer.OddElementException;
import by.bsu.fpmi.Z.Utils.NException;
import by.bsu.fpmi.Z.Subject.Faculty;
import by.bsu.fpmi.Z.Subject.Stud;
import by.bsu.fpmi.Z.Subject.Subject;
import by.bsu.fpmi.Z.hierarchy.Ancestor;
import by.bsu.fpmi.Z.hierarchy.AnotherProperty;
import by.bsu.fpmi.Z.hierarchy.FirstInheritor;
import by.bsu.fpmi.Z.hierarchy.SecondInheritor;


public class Main {
	public static final String INPUT_1="input1.txt";
	public static final String INPUT_2="input2.txt";
	public static final String INPUT_3="input3.txt";
	public static final String INPUT_4="input4.txt";
	public static final String INPUT_5="input5.txt";
	public static final String INPUT_6="doesnt exist.txt";
	public static final String INPUT_7="input7.txt";
	
	public static void readFromFileFirst(SpecialContainer<Ancestor>sContainer,String fileName) throws FileNotFoundException, NumberFormatException,IllegalArgumentException {
		Scanner scanner=new Scanner(new File(fileName));
		int N=scanner.nextInt();
		for(int i=0;i<N;i++) {
			Ancestor one=new FirstInheritor(scanner.nextInt(), scanner.next(), scanner.nextDouble(),
					AnotherProperty.valueOf(scanner.next()), scanner.nextBoolean());
			sContainer.add(one);
		}
		if(scanner.hasNext()){
			scanner.close();
			throw new NoSuchElementException();
		} else
			scanner.close();
	}
	
	public static void readFromFileSecond(SpecialContainer<Ancestor>sContainer,String fileName) throws FileNotFoundException, NumberFormatException,IllegalArgumentException {
		Scanner scanner=new Scanner(new File(fileName));
		int N=scanner.nextInt();
		for(int i=0;i<N;i++) {
			Ancestor one=new SecondInheritor(scanner.nextInt(), scanner.next(), scanner.nextDouble(),
					AnotherProperty.valueOf(scanner.next()), scanner.nextFloat());
			sContainer.add(one);
		}
		if(scanner.hasNext()){
			scanner.close();
			throw new NoSuchElementException();
		} else
			scanner.close();
	}
	
	public static void printWithLambdaAndStreamAPI(SpecialContainer<Ancestor> sContainer3,AnotherProperty property) {
		//System.out.println("sum="+sContainer3.stream().flatMapToDouble((p)->DoubleStream.of(p.getThirdProperty())).sum());
		System.out.println("sum="+sContainer3.stream().filter((p)->p.getLastProperty().equals(property)).collect(Collectors.toList()).stream().flatMapToDouble((p)->DoubleStream.of(p.getThirdProperty())).sum());
	}
	
	public static void part1() {
		//Student[]students= {};
		Student[]students= {new Student("one", 1),new Student("two", 2),new Student("three", 3)};
		System.out.println(Utils.calcSum(students));
		try {
			System.out.println(Utils.calcAverage(students));
		} catch (NException e) {
			System.out.println(e.getMessage());
		}
		int counter=UnaryPredicate.countIf(students,new UnaryPredicate<Student>() {
			@Override
			public boolean test(Student object) {
				if(object.intValue()>2)
					return true;
				return false;
			}
		});
		System.out.println(counter);
	}
	
	public static void part2() {
		SpecialContainer<Ancestor>sContainer1=new SpecialContainer<>();
		SpecialContainer<Ancestor>sContainer2=new SpecialContainer<>();
		SpecialContainer<Ancestor>sContainer3=new SpecialContainer<>();
		SpecialContainer<Ancestor>sContainer4=new SpecialContainer<>();
		SpecialContainer<Ancestor>sContainer5=new SpecialContainer<>();
		SpecialContainer<Ancestor>sContainer6=new SpecialContainer<>();
		try {
				System.out.print("INPUT_1 ...");
				readFromFileFirst(sContainer1, INPUT_1);
				System.out.println(" read.");
				
				System.out.print("INPUT_2 ...");
				readFromFileSecond(sContainer2, INPUT_2);
				System.out.println(" read.");
				
				System.out.print("INPUT_3 ...");
				readFromFileSecond(sContainer3, INPUT_3);
				System.out.println(" read.");
//				
//				System.out.print("INPUT_4 ...");		//##shows NumberFormatException|NoSuchElementException
//				readFromFileSecond(sContainer4, INPUT_4);
//				System.out.println(" read.");
//				
//				System.out.print("INPUT_5 ...");		//##shows IllegalArgumentException
//				readFromFileSecond(sContainer5, INPUT_5);
//				System.out.println(" read.");
//				
//				System.out.print("INPUT_6 ...");		//##shows FileNotFoundException
//				readFromFileSecond(sContainer6, INPUT_6);
//				System.out.println(" read.");
				
//				System.out.print("INPUT_7 ...");
//				readFromFileSecond(sContainer6, INPUT_7);
//				System.out.println(" read.");
		} catch (FileNotFoundException e) {
			System.out.println("Exception: file with such name doesnt exist");
		}catch (NumberFormatException|NoSuchElementException e) {
			System.out.println("Exception: wronge format of data");
		}catch (IllegalArgumentException e) {
			System.out.println("Exception: such element doesnt exist in base");
		}
		@SuppressWarnings("unchecked")
		int counter=SpecialPr.countIf(sContainer1, new SpecialPr() {
			@Override
			public boolean test(Ancestor object) {
				if(object.getFirstProperty()>=3)
					return true;
				return false;
			}
		});
		System.out.println(counter);
		
		
		System.out.println("min in sContainer1:");
		System.out.println(sContainer1.min());
		System.out.println("min in sContainer2:");
		System.out.println(sContainer2.min());
		System.out.println("sContainer1:");
		System.out.println(sContainer1);
		System.out.println("sContainer2:");
		System.out.println(sContainer2);
		System.out.println("sContainer3:");
		System.out.println(sContainer3);
		printWithLambdaAndStreamAPI(sContainer3, AnotherProperty.EMPTY);
		System.out.println(sContainer1.countIf(new FirstInheritor(1, "one", 1.0d, AnotherProperty.PROPERTY_1, true)));
	}

	public static void part3() {
		List<Subject>list=new ArrayList<>();
		list.add(new Subject("one", 71.0));
		list.add(new Subject("two", 72.0));
		list.add(new Subject("three", 73.0));
		System.out.println(list);
		Collections.sort(list, new Comparator<Subject>() {
			@Override
			public int compare(Subject arg0, Subject arg1) {
					
				return arg0.hashCode()-arg1.hashCode();
			}
		});
		System.out.println(list);
		List<Subject>list2=new ArrayList<>();
		Collections.copy(list2, list);
		Collections.sort(list2, new Comparator<Subject>() {
			@Override
			public int compare(Subject arg0, Subject arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		});
		System.out.println(list);
		System.out.println(list2);
		int counter=Collections.frequency(list, new Subject("", 72.0));
		System.out.println(counter);
	}
	
	public static void part4() {
		List<Stud>list=new ArrayList<>();
		Scanner scanner=new Scanner(System.in);
		list.add(new Stud(scanner.next(), null, null, 2000, 2, Faculty.valueOf(scanner.next()), scanner.nextInt(), 1));
		list.add(new Stud("f", "n", "o", 2000, 2, Faculty.MECHANICS, 2, 2));
		list.add(null);
		printIf(list, new Predicate<Stud>() {

			@Override
			public boolean test(Stud obj) {
				if(obj!=null)
					return true;
				return false;
			}
		});
		scanner.close();
	}
	
	public static void printIf(List<Stud>list,Predicate<Stud>predicate) {
		for(Stud i:list) {
			if(predicate.test(i))
				System.out.println(i);
		}
	}
	
	public static void main(String[] args) {
		//part1();
		part2();
		//part3();
		//part4();
	}

}
