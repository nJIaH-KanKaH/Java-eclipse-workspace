import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.util.Scanner;

public class Main implements Runnable{
	static int[]snacks;
	static int[]res;
	static int n;
	static int func() {
		if(snacks.length==1){
			return snacks[0];
		}
		if(snacks.length==2) {
			return -1;
		}
		res[0]=snacks[0];
		res[1]=Integer.MIN_VALUE;
		res[2]=res[0]+snacks[2];
		for(int index=3;index<res.length;index++) {
			if(index-3>=0) {
				res[index]=Math.max(res[index-2], res[index-3])+snacks[index];
			}
		}
		return res[res.length-1];
	}
	
	public static void main(String[] args) {
		new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
	}

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			n=scanner.nextInt();
			snacks=new int[n];
			res=new int[n];
			for(int i=0;i<n;i++) {
				snacks[i]=scanner.nextInt();
				res[i]=0;
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			//System.out.println(res[n-1]);
			FileWriter fileWriter=new FileWriter(new File("output.txt"));
			fileWriter.write(Integer.toString(func()));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
