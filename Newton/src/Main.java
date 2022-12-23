
public class Main {
	static double x0;
	static double x;
	static double e;
	public static double df(double x) {
		if(x!=0)
			return (3*x*x+6*x+3+(1/x));
		return Double.MAX_VALUE;
	}
	
	public static double f(double x) {
		if(x!=0)
			return (Math.log(Math.abs(x))+(x+1)*(x+1)*(x+1));
		return Double.MIN_VALUE;
	}
	
	public static boolean cheq() {
		return Math.abs(x-x0)<e;
	}

	public static void main(String[] args) {
		x0=0.000005d;
		e=1.0/1000000.0;
		x=x0 - f(x0)/df(x0);
		int i=1;
		System.out.println("on iteration "+i+" x="+x);
		while(!cheq()) {
			x0=x;
			x=x0-f(x0)/df(x0);
			i++;
			System.out.println("on iteration "+i+" x="+x);
		}
		System.out.println(i);
		System.out.println(x);
	}

}
