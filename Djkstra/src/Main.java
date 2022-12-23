import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.sun.media.jfxmedia.events.NewFrameEvent;

public class Main implements Runnable {
	public static void main(String[] args) {
	    new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	
	class Pair{
		Node u;
		Node v;
		public Pair(Node u,Node v) {
			this.v=v;
			this.u=u;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((u == null) ? 0 : u.hashCode());
			result = prime * result + ((v == null) ? 0 : v.hashCode());
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
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (u == null) {
				if (other.u != null)
					return false;
			} else if (!u.equals(other.u))
				return false;
			if (v == null) {
				if (other.v != null)
					return false;
			} else if (!v.equals(other.v))
				return false;
			return true;
		}
		private Main getEnclosingInstance() {
			return Main.this;
		}
	}
	
	class Node{
		int val;
		int visited;
		long pathValue;
		
		public String toString() {
			return Integer.toString(val)+" with pathV="+pathValue;
		}
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

	@Override
	public void run() {
		String lineSeparator = System.getProperty("line.separator");
		Scanner scanner;
		try {
			scanner=new Scanner(new File("input.txt"));
			FileWriter fileWriter=new FileWriter(new File("output.txt"));
			int n=scanner.nextInt();
			int m=scanner.nextInt();
			Queue<Node>q=new PriorityQueue<>(100,new C());
			Map<Pair, Integer>mp=new HashMap<>();
			List<List<Node>>list=new ArrayList<List<Node>>();
			List<Node>nodes=new ArrayList<>();
			for(int i=0;i<n;i++) {
				Node e=new Node();
				e.pathValue=Long.MAX_VALUE;
				e.val=i;
				e.visited=1;
				nodes.add(e);
				list.add(new ArrayList<Node>());
			}
			for(int i=0;i<m;i++) {
				int u=scanner.nextInt();
				int v=scanner.nextInt();
				Pair pair=new Pair(nodes.get(Math.min(u, v)-1), nodes.get(Math.max(u, v)-1));
				int w=scanner.nextInt();
				if(mp.containsKey(pair)) {
					int old=mp.get(pair);
					mp.remove(pair);
					mp.put(pair, Math.min(w, old));
				}
				else mp.put(pair, w);
				list.get(pair.u.val).add(pair.v);
				list.get(pair.v.val).add(pair.u);
			}
			nodes.get(0).pathValue=0;
			q.add(nodes.get(0));
			while(!q.isEmpty()) {
				Node node=q.poll();
				node.visited=-1;
				List<Node>l=list.get(node.val);
				for(Node i:l) {
					Pair pair=new Pair(nodes.get(Math.min(node.val, i.val)), nodes.get(Math.max(node.val, i.val)));
					if(i.visited!=-1) {
						i.pathValue=Math.min(node.pathValue+mp.get(pair).longValue(), i.pathValue);
						q.add(i);
					}
				}
			}
			fileWriter.write(Long.toString(nodes.get(n-1).pathValue));
			
			fileWriter.close();
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}