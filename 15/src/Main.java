import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main implements Runnable{
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
	}
	
	class Node{
		long value;
		int x;
		int y;
		long pathValue;
		int visited;
	}
	
	class C implements Comparator<Node>{ 
        public int compare(Node n1, Node n2) { 
            if (n1.pathValue > n2.pathValue) 
                return 1; 
            else if (n1.pathValue < n2.pathValue) 
                return -1; 
            return 0; 
        } 
    } 
	
	static int n,k,m,x1,y1,x2,y2;static Node[][]arr;
	Queue<Node>queue=new PriorityQueue<>(1000000,new C());

	@Override
	public void run() {
		try {
			StreamTokenizer s=new StreamTokenizer(new BufferedReader(new FileReader("in.txt")));
			n=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)n=(int)s.nval;
			m=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)m=(int)s.nval;
			arr=new Node[n][m];
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					arr[i][j]=new Node();
					if(s.nextToken()==StreamTokenizer.TT_NUMBER)arr[i][j].value=(long)s.nval;
					arr[i][j].visited=1;
					arr[i][j].pathValue=Long.MAX_VALUE;
					arr[i][j].x=i;
					arr[i][j].y=j;
				}
			}
			k=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)k=(int)s.nval;
			x1=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)x1=(int)s.nval-1;
			y1=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)y1=(int)s.nval-1;
			x2=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)x2=(int)s.nval-1;
			y2=0;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER)y2=(int)s.nval-1;
			} catch (IOException e1) {}
			
			arr[x1][y1].pathValue=0l;
			queue.add(arr[x1][y1]);
			while(!queue.isEmpty()) {
				Node node=queue.poll();
				if(node.y>0 && arr[node.x][node.y-1].visited!=-1) {
					arr[node.x][node.y-1].pathValue=Math.min(Math.abs(arr[node.x][node.y].value-arr[node.x][node.y-1].value) + k + arr[node.x][node.y].pathValue, arr[node.x][node.y-1].pathValue);
					queue.add(arr[node.x][node.y-1]);
				}
				if(node.y<m-1 && arr[node.x][node.y+1].visited!=-1) {
					arr[node.x][node.y+1].pathValue=Math.min(Math.abs(arr[node.x][node.y].value-arr[node.x][node.y+1].value) + k + arr[node.x][node.y].pathValue, arr[node.x][node.y+1].pathValue);
					queue.add(arr[node.x][node.y+1]);
				}
				if(node.x>0 && arr[node.x-1][node.y].visited!=-1) {
					arr[node.x-1][node.y].pathValue=Math.min(Math.abs(arr[node.x][node.y].value-arr[node.x-1][node.y].value) + k + arr[node.x][node.y].pathValue, arr[node.x-1][node.y].pathValue);
					queue.add(arr[node.x-1][node.y]);
				}
				if(node.x<n-1 && arr[node.x+1][node.y].visited!=-1) {
					arr[node.x+1][node.y].pathValue=Math.min(Math.abs(arr[node.x][node.y].value-arr[node.x+1][node.y].value) + k + arr[node.x][node.y].pathValue, arr[node.x+1][node.y].pathValue);
					queue.add(arr[node.x+1][node.y]);
				}
				arr[node.x][node.y].visited=-1;
			}
		try {
			FileWriter fileWriter=new FileWriter(new File("out.txt"));
			fileWriter.write(Long.toString(arr[x2][y2].pathValue));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
