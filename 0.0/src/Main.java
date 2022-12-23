import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File("input.txt"));
		FileWriter fileWriter=new FileWriter(new File("output.txt"));
		Set<Integer>set=new TreeSet<>();
		while(sc.hasNext()) {
			set.add(sc.nextInt());
		}
		long sum=0l;
		for(Integer i:set) {
			sum+=i.longValue();
		}
		fileWriter.write(String.valueOf(sum));
		sc.close();
		fileWriter.close();
	}

}
