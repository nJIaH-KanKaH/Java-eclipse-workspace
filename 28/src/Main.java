import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable {
	public static int[]seq;
	public static long[][]seqSum;
	public static long[][]funcRes;
	public static long min;
	
	public static long c(int n,int k,int l) {
		long val=calc(n-l, k-1)+seqSum[n-l][n];
		return val;
	}
	
	public static long calc(int n,int k) {
		if(funcRes[n][k]==Long.MAX_VALUE) {
			for(int l=0;l<n;l++) {
				funcRes[n][k]=Math.min(funcRes[n][k],c(n, k, l));
			}
		}
		return funcRes[n][k];
	}

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("in.txt"));
			FileWriter fileWriter=new FileWriter(new File("out.txt"));
			int k=scanner.nextInt();
			int n=scanner.nextInt();
			seq=new int[n];
			seqSum=new long[n+1][n+1];
			funcRes=new long[n+1][k+1];
			for(int i=0;i<n;i++) {
				seqSum[i]=scanner.nextInt();
			}
			for(int i=0;i<n+1;i++) {
				for(int j=1;j<n+1-i;j++) {
					seqSum[i][j]=seqSum[i][j-1]+(j+1)*seq[i+j];
				}
				for(int j=0;j<k+1;j++) {
					if(j==0 || i==0) {
						funcRes[i][j]=0;
					} else if(j==1) {
						funcRes[i][j]=funcRes[i-1][j]+(long)(i)*seq[i-1];
					} else if (i==1) {
						funcRes[i][j]=funcRes[i][j-1];	
					} else {
						funcRes[i][j]=Long.MAX_VALUE;
					}
				}
			}
					
			fileWriter.write(Long.toString(calc(n, k)));
			
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Thread(null, new Main(), "", 128 * 1024 * 1024).start();
	}

}