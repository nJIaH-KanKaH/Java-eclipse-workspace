import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Runnable {
	public static void main(String[] args){
	    new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}
	static Domino[][]arr=new Domino[7][7];
	static boolean[][]visited=new boolean[7][7];
	static List<Domino>list=new ArrayList<>();
	static int k;
	static int counter=0;
	static Scanner scanner;
	static FileWriter fileWriter;
	static PrintWriter out;
	static String lineSeparator=System.lineSeparator();
	

	static class Domino{
		int first;
		int second;
		int index;
		public Domino(int f,int s) {
			first=f;
			second=s;
		}
	}
	
	static void dfs(Domino domino) throws IOException {
		visited[domino.first][domino.second]=true;
		visited[domino.second][domino.first]=true;
		list.add(domino);
		
		if(list.size()==k) {
			if(k==1) {
				if(list.get(0).first==list.get(list.size()-1).second) {
					for(Domino d:list) {
						fileWriter.write(Integer.toString(d.first)+"."+Integer.toString(d.second)+" ");
					}
					fileWriter.write(lineSeparator);
					counter++;
					return;
				}
			} else if(list.get(0).first==list.get(list.size()-1).second &&(list.get(1).second<list.get(list.size()-1).first || list.get(0).first!=list.get(0).second)) {
				for(Domino d:list) {
					fileWriter.write(Integer.toString(d.first)+"."+Integer.toString(d.second)+" ");
				}
				fileWriter.write(lineSeparator);
				counter++;
			}
			return;
		}
		
		for(int i=0;i<7;i++) {
			if(!visited[domino.second][i]) {
				dfs(arr[domino.second][i]);
				list.remove(list.size()-1);
				visited[domino.second][i]=false;
				visited[i][domino.second]=false;
			}
		}		
	}
	
	@Override
	public void run(){
		for(int i=0;i<7;i++) {
			arr[i][i]=new Domino(i, i);
			for(int j=i+1;j<7;j++) {
				arr[i][j]=new Domino(i, j);
				arr[j][i]=new Domino(j, i);
				visited[i][j]=false;
				visited[j][i]=false;
			}
		}
		try {
			scanner=new Scanner(new File("input.txt"));
		} catch (FileNotFoundException e1) {}
		k=scanner.nextInt();
		scanner.close();
		try {
			fileWriter=new FileWriter("output.txt");
			if(k>0 && k<=28 && k!=2) {
				for(int i=0;i<7;i++) {
					for(int j=0;j<7;j++) {
						list.clear();
						dfs(arr[i][j]);
					}
				}
				fileWriter.write(Integer.toString(counter));
			} else {
				fileWriter.write("wrong count"+lineSeparator);
			}
			fileWriter.close();
		} catch (Exception e) {}	
	}
}
