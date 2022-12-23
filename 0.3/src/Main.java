import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static int[]firstParam;
	public static int[]secondParam;
	
	public static int countNumberOfOperations(int n) {
		int[][]matrixOfOperations=new int[n][n];
		for(int i=0;i<n;i++) {
			matrixOfOperations[i][i]=0;
		}
		for(int c=1;c<n;c++) {
			for(int i=0;i<n-c;i++) {
				int j=i+c;
				matrixOfOperations[i][j]=Integer.MAX_VALUE;
				for(int k=i;k<j;k++) {
					matrixOfOperations[i][j]=Math.min(matrixOfOperations[i][j],matrixOfOperations[i][k]+matrixOfOperations[k+1][j]+firstParam[i]*secondParam[k]*secondParam[j]);
				}
			}
		}
		return matrixOfOperations[0][n-1];
	}
	
	public static void main(String[]args) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			FileWriter fileWriter=new FileWriter(new File("output.txt"));
			int n=scanner.nextInt();
			firstParam=new int[n+1];
			secondParam=new int[n];
			for(int i=0;i<n;i++) {
				firstParam[i]=scanner.nextInt();
				secondParam[i]=scanner.nextInt();
			}
			fileWriter.write(String.valueOf(countNumberOfOperations(n)));
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
