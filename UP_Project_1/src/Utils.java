import java.awt.Point;

public class Utils {
	public static double distance(Point first,Point second) {
		return Math.sqrt( (first.x-second.x)*(first.x-second.x) + (first.y-second.y)*(first.y-second.y) );
	} 
}
