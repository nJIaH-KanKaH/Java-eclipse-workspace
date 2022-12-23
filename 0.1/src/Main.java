import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable{
	static class BinarySearchTree{
		class Node{
			Integer value;
			Node left;
			Node right;
			public Node() {
				value=null;
			}
			public Node(int v) {
				value=new Integer(v);
			}
			void insert(int v) {
				if(this.value==null)
					value=new Integer(v);
				if (this.value.intValue()==v)
					return;
				if(this.value.intValue()>v) {
					if(this.left==null)
						this.left=new Node();
					this.left.insert(v);}
				if(this.value.intValue()<v) {
					if(this.right==null)
						this.right=new Node();
					this.right.insert(v);}
			}
			void printInStraightLeft(FileWriter f,String l) throws IOException {
				f.write(value.toString());
				f.write(l);
				if(this.left!=null)
					this.left.printInStraightLeft(f,l);
				if(this.right!=null)
					this.right.printInStraightLeft(f,l);
			}
		}
		Node root;
		BinarySearchTree() {
			root=new Node();
		}
		
		void insert(int value) {
			root.insert(value);
		}
		
		void printInStraightLeft(FileWriter f) throws IOException {
			String l=System.lineSeparator();
			root.printInStraightLeft(f,l);
		}
	}
	
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("input.txt"));
			FileWriter fileWriter=new FileWriter(new File("output.txt"));
			BinarySearchTree tree=new BinarySearchTree();
			while(scanner.hasNext()) {
				tree.insert(scanner.nextInt());
			}
			tree.printInStraightLeft(fileWriter);
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
