import java.awt.Dimension;
import java.awt.Point;

public class Circle {
	protected Point centre;
	protected double radius;
	
	public Circle(Point centre, double radius) {
		super();
		this.centre = centre;
		this.radius = radius;
	}
	
	public boolean setSize(int diam) {
		if(diam<=0) {
			return false;
		}
		else {
			this.radius=diam/2.0;
			return true;
		}
	}
	
	public boolean setSize(Dimension dimension) {
		if(dimension.width!=dimension.height || dimension.width<0)
			return false;
		else {
			radius=(double)dimension.width/2.0;
			return true;
		}
	}
	
	public boolean setSize(Point leftUp,Point rightDown) {
		if(rightDown.x-leftUp.x<0 || rightDown.y-leftUp.y<0 || rightDown.x-leftUp.x!=rightDown.y-leftUp.y || (rightDown.x+leftUp.x)%2!=0 || (rightDown.y+leftUp.y)%2!=0 ) {
			return false;
		}
		else {
			centre=new Point((rightDown.x+leftUp.x)/2,(rightDown.y+leftUp.y)/2);
			radius=(rightDown.x-leftUp.x)/2.0;
			return true;
		}
	}

	@Override
	public String toString() {
		return "Circle [centre=" + centre + ", radius=" + radius + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centre == null) ? 0 : centre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circle other = (Circle) obj;
		if (centre == null) {
			if (other.centre != null)
				return false;
		} else if (!centre.equals(other.centre))
			return false;
		if (Double.doubleToLongBits(radius) != Double.doubleToLongBits(other.radius))
			return false;
		return true;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
