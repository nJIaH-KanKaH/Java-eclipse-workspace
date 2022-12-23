import java.awt.Point;

public class CircleWithSpace extends Circle{
	public static final double EPSILON=0.001d;

	public CircleWithSpace(Point centre, double radius) {
		super(centre, radius);
	}
	
	public boolean isIn(Point p) {
		if(Utils.distance(this.centre, p)<= this.radius+EPSILON )
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CircleWithSpace: "+super.toString();
	}
	
	
}
