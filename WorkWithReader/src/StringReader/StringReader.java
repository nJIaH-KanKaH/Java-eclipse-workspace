package StringReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class StringReader {

	public static void main(String[] args) throws IOException {
		String string=args[0];
		int result;
		String x="x";
		StringTokenizer st,zn;
	    st = new StringTokenizer(string," +-");
	    zn=new StringTokenizer(string, " 0123456789x");
	    Vector<String> numbers = new Vector<>();
	    Vector<String>signs=new Vector<>();
	    
	    while(st.hasMoreTokens()){
	    	numbers.add(st.nextToken());
	    }
	    while(zn.hasMoreTokens()) {
	    	signs.add(zn.nextToken());
	    }
	    for(String i:numbers) {
	    	if(x.equals(i)) {
	    		i=args[1];
	    	}
	    }
	    result=Integer.parseInt(numbers.firstElement());
	    for(int i=0;i<signs.size();i++) {
	    	if(signs.elementAt(i).charAt(0)=='+') {
	    		result+=Integer.parseInt(numbers.elementAt(i+1));
	    	}
	    	else {
	    		result-=Integer.parseInt(numbers.elementAt(i+1));
	    	}
	    }
	    System.out.println("result: "+result);	
	  }
}
