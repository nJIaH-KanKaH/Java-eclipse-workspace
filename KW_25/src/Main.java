import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main implements Runnable {
		public static int position=-1;
		public static char open1='(';
		public static char close1=')';
		public static char open2='{';
		public static char close2='}';
		public static char open3='[';
		public static char close3=']';
		public static Stack<Character>stack;

		@Override
		public void run() {
			Scanner scanner;
			try {
				scanner = new Scanner(new File("input.txt"));
				FileWriter fileWriter=new FileWriter(new File("output.txt"));
				String string =scanner.next();
				stack=new Stack<>();
				for(int i=0;i<string.length();i++) {
					if(string.charAt(i)==open1 || string.charAt(i)==open2 || string.charAt(i)==open3) {
						stack.push(string.charAt(i));
					}
					else {
						if(stack.empty()) {
							position=i;
							break;
						}
						else {
							char temp=stack.peek().charValue();
							stack.pop();
							if(temp==open1 && string.charAt(i)==close1) {
								
							} else if(temp==open2 && string.charAt(i)==close2) {
								
							} else if(temp==open3 && string.charAt(i)==close3) {
								
							} else {
								position=i;
								break;
							}
						}
					}
				}
				if(position!=-1) {
					fileWriter.write("NO\r\n");
					fileWriter.write(Integer.toString(position));
				}
				else if(stack.empty()) { 
					fileWriter.write("YES");
				} else {
					fileWriter.write("NO\r\n");
					fileWriter.write(Integer.toString(string.length()));
				}
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
