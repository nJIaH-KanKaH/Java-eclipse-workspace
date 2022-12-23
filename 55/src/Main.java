
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main implements Runnable{
	public static long[]arr;
	public static int n;
	public static boolean flaq=true;
	
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			FileWriter fileWriter = new FileWriter(new File("output.txt"));
			n=scanner.nextInt();
			arr=new long[n+1];
			arr[0]=Long.MIN_VALUE;
			for(int i=1;i<n+1;i++) {
				arr[i]=scanner.nextLong();
			}
			
			for(int i=1;i<n+1;i++) {
				if(2*i<n) {
					if(arr[2*i]<arr[i] || arr[2*i+1]<arr[i]) {
						flaq=false;
						break;
					}
				}
				else if (2*i<=n) {
					if(arr[2*i]<arr[i]) {
						flaq=false;
						break;
					}
				}
			}
			fileWriter.write(flaq? "Yes":"No");
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}