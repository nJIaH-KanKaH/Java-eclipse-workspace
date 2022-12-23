import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main implements Runnable{
	public static List<String>list=new ArrayList<>();
	public static int n;
	public static int q;
	static StringBuilder sb;
	static StringBuilder sb1;
	static StringBuilder sb2;
	static int i1;
	static int i2;
	static int j1;
	static int j2;
	static char c;
	static int countA=0;static char A='A';
	static int countC=0;static char C='C';
	static int countG=0;static char G='G';
	static int countT=0;static char T='T';
	static String string=null;
	
	
	
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		scanner = new Scanner(System.in);
		n=scanner.nextInt();
		for(int i=0;i<n;i++) {
			list.add(scanner.next());
		}
		q=scanner.nextInt();
		for(int i=0;i<=q;i++) {
			string=scanner.next();
			switch (string) {
			case "X":
				i1=scanner.nextInt()-1;
				i2=scanner.nextInt()-1;
				j1=scanner.nextInt();
				j2=scanner.nextInt();
				sb1=new StringBuilder(list.get(i1));
				sb2=new StringBuilder(list.get(i2));
				sb=new StringBuilder(sb1.substring(0, j1)).append(sb2.substring(j2));
				list.add(sb.toString());
				sb=new StringBuilder(sb2.substring(0, j2)).append(sb1.substring(j1));
				list.add(sb.toString());
				break;	
			case "?":
				i1=scanner.nextInt()-1;
				j1=scanner.nextInt()-1;
				j2=scanner.nextInt();
				for(int j=j1;j<j2;j++) {
					c=list.get(i1).charAt(j);
					if(A==c) countA++; else if (C==c) countC++; else if (G==c) countG++; else if (T==c) countT++;
				}
				System.out.println(countA+" "+countC+" "+countG+" "+countT);
				countA=0;countC=0;countG=0;countT=0;
				break;
			case "=":
				i1=scanner.nextInt()-1;
				j1=scanner.nextInt()-1;
				c=scanner.next().charAt(0);
				sb=new StringBuilder(list.get(i1));
				sb.setCharAt(j1, c);
				list.set(i1, sb.toString());
				break;
			}
		}
		
		scanner.close();
	}
}