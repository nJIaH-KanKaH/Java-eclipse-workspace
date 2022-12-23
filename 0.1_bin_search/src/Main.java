import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Runnable {
	static int[]arr;
	static int isIn=0;
	static int lower=0;
	static int upper=0;
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }
	
	public static int lowerBound(int[]array,int i) {
		int lower=0;
		int upper=array.length;
		while(lower<upper) {
			int mid=(lower+upper)/2;
			if(i>array[mid])
				lower=mid+1;
			else upper=mid;
		}
		return lower;
	}
	
	public static int upperBound(int[]array,int i) {
		int lower=0;
		int upper=array.length;
		while(lower<upper) {
			int mid=(lower+upper)/2;
			if(i<array[mid])
				upper=mid;
			else lower=mid+1;
		}
		return lower;
	}
	
	@Override
	public void run() {
		Scanner scanner=new Scanner(System.in);
		List<Integer>listK=new ArrayList<>();
		int n=scanner.nextInt();
		arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=scanner.nextInt();
		}
		int k=scanner.nextInt();
		for(int i=0;i<k;i++) {
			listK.add(scanner.nextInt());
		}
		for(Integer i:listK) {
			int lower=lowerBound(arr, i.intValue());
			int upper=upperBound(arr, i.intValue());
			if(lower==upper) {
				System.out.println("0 "+lower+" "+upper);
			}
			else System.out.println("1 "+lower+" "+upper);
		}
		
	}

}
