import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	static PriorityQueue<Long>q=new PriorityQueue<>(1000000,new Comparator<Long>() {@Override public int compare(Long o1, Long o2) {return Long.compare(o2, o1);}});
	//static PriorityQueue<Car>cars=new PriorityQueue<>(1000000,new Comparator<Car>() {@Override public int compare(Car o1, Car o2) {return Long.compare(o2.w, o1.w);}});
	static PriorityQueue<Car>opened=new PriorityQueue<>(1000000,new Comparator<Car>() {@Override public int compare(Car o1, Car o2) {return Long.compare(o2.s, o1.s);}});
	static Car[]cars;
	static class Car{
		long w;
		long s;
		List<Long>st;
		public Car(long w) {
			this.w=w;this.s=w;st=new LinkedList<Long>();
		}
	}
	static int n;
	static int m;

	public static void main(String[] args) {
		try {
			StreamTokenizer s=new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
			n=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)n=(int)s.nval;
			cars=new Car[n];
			for(int i=0;i<n;i++) {
				long w=0l;
				if(s.nextToken()==StreamTokenizer.TT_NUMBER)w=(long)s.nval;
				cars[i]=new Car(w);
			}
			Arrays.sort(cars, new Comparator<Car>() {@Override public int compare(Car o1, Car o2) {return Long.compare(o2.w, o1.w);}});
			m=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)m=(int)s.nval;
			for(int i=0;i<m;i++) {
				long st=0l;
				if(s.nextToken()==StreamTokenizer.TT_NUMBER)st=(long)s.nval;
				q.add(st);
			}
		} catch (IOException e) {e.printStackTrace();}
		FileWriter fw;
		String l=System.lineSeparator();
		opened.add(cars[0]);
		int counter=0;
		try {
			fw=new FileWriter(new File("output.txt"));
			while(!q.isEmpty()) {
				if(opened.peek().s>=q.peek()) {
					Car car=opened.poll();
					car.st.add(q.peek());
					car.s-=q.poll();
					opened.add(car);
				}
				else {
					if(counter!=n-1) {opened.add(cars[counter++]);}
					else {fw.write("no solution");fw.close();return;}
				}
			}
			fw.write(Long.toString(n)+l);
			for(int i=0;i<n;i++) {
				fw.write(Long.toString(cars[i].w)+l);
				for(int j=0;j<cars[i].st.size();j++) {
					fw.write(Long.toString(cars[i].st.get(j))+" ");
				}
				if(i!=n-1)
					fw.write(l);
			}
			fw.close();
		} catch (IOException e) {e.printStackTrace();}
	}
}
