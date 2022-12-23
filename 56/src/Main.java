import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Main implements Runnable{
	public static long n;
	public static int[]a;
	
	public static int log2(long l) {
		if(l==0l) {
			return 0;
		}
		return 63 - Long.numberOfLeadingZeros(l);
	}
	
	public static long pow2(int deg) {
		long base=1l;
		for(int i=0;i<deg;i++) {
			base*=2l;
		}
		return base;
	}
	
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			FileWriter fileWriter = new FileWriter(new File("output.txt"));
			n=scanner.nextLong();
			int k=log2(n);
			System.out.println("k="+k);
			a=new int[k+1];
			long max=pow2(k);
			System.out.println("max="+max);
			System.out.println();
			for(int i=k;i>=0;i--) {
				if(n-max>=0) {
					n-=max;
					a[i]=1;
				}
				else {
					a[i]=0;
				}
				max/=2l;
				System.out.println("max="+max);
			}
			System.out.println(Arrays.toString(a));
			for(int i=0;i<k+1;i++) {
				if(a[i]==1)
					fileWriter.write(Integer.toString(i)+"\r\n");
			}
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}