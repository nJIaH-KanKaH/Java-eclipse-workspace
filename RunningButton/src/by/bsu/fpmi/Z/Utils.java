package by.bsu.fpmi.Z;

import java.awt.Point;
import java.util.zip.Inflater;

public class Utils {
	public static final double MAX=32768d;
	public static int rand() {
		return (int)(Math.random()*MAX);
	}
	
	public static Point randPoint(int width,int height) {
		return new Point(rand()%width,rand()%height);
	} 
	
	public static Point runnin(int width,int height,Point oldPoint) {
		int n=rand()%4;
		Point newPoint=new Point(oldPoint);
		switch(n) {
		case 0:newPoint.setLocation(oldPoint.x-DragNDropFrame.BUTTON_BOUNDS.width-1, oldPoint.y-DragNDropFrame.BUTTON_BOUNDS.height-1);break;
		case 1:newPoint.setLocation(oldPoint.x-DragNDropFrame.BUTTON_BOUNDS.width-1, oldPoint.y+DragNDropFrame.BUTTON_BOUNDS.height+1);break;
		case 2:newPoint.setLocation(oldPoint.x+DragNDropFrame.BUTTON_BOUNDS.width+1, oldPoint.y-DragNDropFrame.BUTTON_BOUNDS.height-1);break;
		case 3:newPoint.setLocation(oldPoint.x+DragNDropFrame.BUTTON_BOUNDS.width+1, oldPoint.y+DragNDropFrame.BUTTON_BOUNDS.height+1);break;
		default:break;
		}
		if(newPoint.getX()<=0)
			newPoint.setLocation(width, newPoint.getY());
		if(newPoint.getX()>=width)
			newPoint.setLocation(0, newPoint.getY());
		if(newPoint.getY()<=0)
			newPoint.setLocation(newPoint.getX(), height);
		if(newPoint.getY()>=height)
			newPoint.setLocation(newPoint.getX(),0);
		return newPoint;
	}
}
