import java.awt.Dimension;
import java.awt.Point;

public class Main {
	public static void main(String []strings) {
		CircleWithSpace circle=new CircleWithSpace(new Point((int)(Math.random()*100),(int)(Math.random()*100)),Math.random()*60);
		CircleWithSpace circle1=new CircleWithSpace(new Point((int)(Math.random()*100),(int)(Math.random()*100)),Math.random()*50);
		Point point=new Point((int)(Math.random()*100),(int)(Math.random()*100));
		System.out.println(circle);
		System.out.println(circle1);
		System.out.println(point);
		
		Dimension dimension=new Dimension(140, 160);
		System.out.println(dimension);
		int diam=100;
		System.out.println("diam="+diam);
		System.out.println("circle size change:");
		if(circle.setSize(dimension)) {
			System.out.println("Size changed successfully");
		}
		else System.out.println("Wrong dimension");
		
		System.out.println("circle size change:");
		if(circle.setSize(diam)) {	
			System.out.println("Size changed successfully");
		}
		else System.out.println("Wrong diam");
		
		System.out.println(circle);
		System.out.println(circle1);
		
		if(circle.equals(circle1)) {
			System.out.println("circle equals circle 1");
		}
		else System.out.println("circle and circle 1 are different");
		
		if(circle.isIn(point)) {
			System.out.println("point is in circle");
		}
		else System.out.println("point is not in circle");
		if(circle1.isIn(point)) {
			System.out.println("point is in circle1");
		}
		else System.out.println("point is not in circle1");
	}
}
