import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main implements Runnable{
	public static long[]arr;
	public static int[]a;
	public static int[]aNext;
	public static int n;
	public static StringBuilder sb;
	
	static void f(int border,int max) {
		if(max==-1) {
			return;
		}
		for(int i=border;i>=0;i--) {
			if(a[i]==max) {
				f(aNext[i], max-1);
				sb.append(Integer.toString(i+1));
				sb.append(" ");
				return;
			}
		}
	}
	
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		scanner = new Scanner(System.in);
		n=scanner.nextInt();
		arr=new long[n];
		a=new int[n];
		aNext=new int[n];
		sb=new StringBuilder();
		for(int i=0;i<n;i++) {
			arr[i]=scanner.nextLong();
			a[i]=0;
			aNext[i]=-1;
		}
		int maxAtArr=0;
		for(int i=0;i<n;i++) {
			int max=0;
			for(int j=0;j<i;j++) {
				if(arr[j]!=0)
					if(Math.abs(arr[i])%Math.abs(arr[j])==0 && a[j]>max) {
						max=a[j];
						aNext[i]=j;
					}
			}
			a[i]=max+1;
			if(a[i]>maxAtArr) {
				maxAtArr=a[i];
			}
		}
		System.out.println(maxAtArr);
		f(n-1, maxAtArr);
		System.out.println(sb);
		
		scanner.close();
	}
}
