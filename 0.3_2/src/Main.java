import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Scanner;

public class Main {
	static int[]ancestor;
	static int[]size;
	static int groups;
	static int find_anc(int x) {
		if(ancestor[x] == x) return x;
		ancestor[x]=find_anc(ancestor[x]);
		return ancestor[x];
	}

	static int n;
	static int q;
	public static void main(String[] args) throws IOException{
		StreamTokenizer s=new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		if(s.nextToken()==StreamTokenizer.TT_NUMBER) n=(int)s.nval;
		if(s.nextToken()==StreamTokenizer.TT_NUMBER) q=(int)s.nval;
		ancestor = new int[n + 1];
		size=new int[n+1];
		groups = n;
		for (int i = 1; i <= n; i++) {
			ancestor[i] = i;
			size[i] = 1;
		}
		String l=System.lineSeparator();
		FileWriter writer=new FileWriter(new File("output.txt"));
		int u=0, v=0;
		for (int i = 0; i < q; i++) {
			if(s.nextToken()==StreamTokenizer.TT_NUMBER) u=(int)s.nval;
			if(s.nextToken()==StreamTokenizer.TT_NUMBER) v=(int)s.nval;
			u = find_anc(u);
			v = find_anc(v);
			if (u != v) {
				if (size[u] > size[v]) {
					ancestor[v] = u;
					size[u] += size[v];
				}
				else {
					ancestor[u] = v;
					size[v] += size[u];
				}
				groups--;
			}
			writer.write(Integer.toString(groups));writer.write(l);
		}
		writer.close();
	}

}
