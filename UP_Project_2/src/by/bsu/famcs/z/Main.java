package by.bsu.famcs.z;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import by.bsu.famcs.z.hierarchy.*;

public class Main {

	public static void main(String[] args) {
		PlaneCompany<Plane>company1=new PlaneCompany<>();

		Plane an2=new Plane(Utils.random((short)10),Utils.random(15),Utils.random(20),Utils.random(500),Utils.random(10f));
		Warplane b52=new Bomber(Utils.random((short)10),Utils.random(15),Utils.random(20000),Utils.random(10f),Utils.random(500),Utils.random(20));
		Warplane f22=new Fighter(Utils.random((short)10),Utils.random(2),Utils.random(20000),Utils.random(20f),Utils.random(1000),Utils.random(30));
		Plane boeing747=new Airliner(Utils.random((short)10),Utils.random(15),Utils.random(20),Utils.random(10f),Utils.random((short)100));
		Fighter f16=new Fighter(Utils.random((short)15),Utils.random(2),Utils.random(25000),Utils.random(20f),Utils.random(1000),Utils.random(30));
		company1.add(f16);
		company1.add(b52);
		company1.add(f22);
		company1.add(an2);
		company1.add(boeing747);
		System.out.println(company1);
		System.out.println("\n Sorted by rangeOfFlight");
		company1.sortByRangeOfFlight();
		System.out.println(company1);
		System.out.println("");
		
		int lower=Utils.random(5);
		int upper=lower+Utils.random(5);
		System.out.println("List of planes with fuel in range: from "+lower+" to "+upper);
		System.out.println(company1.findByFuel(-10, -5));
		System.out.println(company1.resultPlacesAndMaxBaggage());
		
		Utils.saveToXML(company1);
		
		try {
			List<Plane>company2=Utils.readFromXML(new File("output.xml"));
			System.out.println(company2);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}

}
