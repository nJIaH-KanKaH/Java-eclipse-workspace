import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main implements Runnable {
	public static void main(String[] args) {
	    new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}

	@Override
	public void run() {
		String lineSeparator = System.getProperty("line.separator");
		Scanner scanner;
		try {
			scanner=new Scanner(new File("input.txt"));
			FileWriter fileWriter=new FileWriter(new File("output.txt"));
			int n=scanner.nextInt();
			int []arr=new int[n];
			int [][]_arr=new int[n][n];
			boolean flaq=true;
			Queue<Integer>q=new PriorityQueue<Integer>(100,new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return 0;
				}
			});
			for(int i=0;i<n;i++) {
				arr[i]=0;
				for(int j=0;j<n;j++) {
					_arr[i][j]=scanner.nextInt();
					if(flaq && _arr[i][j]==1) {
						q.add(i);
						flaq=false;
					}
				}
			}
			int counter=0;
			while(!q.isEmpty()) {
				int i=q.poll();
				if(arr[i]==0) {
					counter++;
					arr[i]=counter;
				}
				for(int j=0;j<n;j++) {
					
					if(_arr[i][j]==1 && arr[j]==0 && !q.contains(j)) {
						q.add(j);
					}
					if(_arr[j][i]==1 && arr[j]==0 && !q.contains(j)) {
						q.add(j);
					}
				}
			}
			for(int i=0;i<n;i++) {
				fileWriter.write(Integer.toString(arr[i]) + ((i!=n-1) ? " " : ""));
			}
			fileWriter.close();
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
