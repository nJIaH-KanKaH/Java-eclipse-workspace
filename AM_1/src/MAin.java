
public class MAin {
	
	static double _F(double t) {
		if (Math.abs(1.0-t)>0.001)
			return 1/(1-t*t);
		return Double.POSITIVE_INFINITY;
	}
	
	static double f(double t,double y) {
		return 2.0*t*y*y;
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N=20; double t,y;
		
		double[]v_t=new double[N+1];
		double[]v_y=new double[N+1];
		double[]k=new double[4];
		
		double h=1/(double)N;
		double hh=h*0.5;	
		
		v_t[0]=0;
		v_y[0]=1;
		
		for(int i=1;i<=N;i++) {
			t=v_t[i-1];y=v_y[i-1];
			k[0]=f(t, y);
			k[1]=f(t+hh, y+hh*k[0]);
			k[2]=f(t+hh, y+hh*k[1]);
			k[3]=f(t+h, y+h*k[2]);
			y+=(h*(k[0]+2.0*k[1]+2.0*k[2]+k[3])/6.0);
			t+=h;
			v_t[i]=t;v_y[i]=y;
		}
		
		for(int i=0;i<=N;i++) {
			//System.out.println("x="+v_t[i]+" y="+v_y[i]);
			//System.out.println("точное значение y="+_F(v_t[i])+'\n');
			System.out.println(Math.abs(v_y[i]-_F(v_t[i])));
		}
	}

}
