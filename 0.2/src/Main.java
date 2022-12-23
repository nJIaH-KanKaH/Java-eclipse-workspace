import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable{
	public static final String LINE_SEPARATOR=System.lineSeparator();
	static class BinarySearchTree{
		class Node{
			Integer value;
			Node left;
			Node right;
			Node ancestor;
			public Node() {
				value=null;
			}
			public Node(int v) {
				value=new Integer(v);
			}
			
			public void setNodeSaveAncestor(Node n) {
				this.value=n.value;
				this.right=n.right;
				this.left=n.left;
			}
			
			void insert(int v) {
				if(this.value==null)
					value=new Integer(v);
				if (this.value.intValue()==v)
					return;
				if(this.value.intValue()>v) {
					if(this.left==null) {
						this.left=new Node();
						this.left.ancestor=this;
					}
					this.left.insert(v);}
				if(this.value.intValue()<v) {
					if(this.right==null) {
						this.right=new Node();
						this.right.ancestor=this;
					}
					this.right.insert(v);}
			}
			
			void delete(int value) {
				if(this.value==null) {
					return;
				}
				if(this.value.intValue()>value) {
					if(this.left!=null)
						this.left.delete(value);
					return;
				}
				if(this.value.intValue()<value) {
					if(this.right!=null)
						this.right.delete(value);
					return;
				}
				
				if(this.left==null && this.right==null) {
					if(this.ancestor!=null) {
						if(this.ancestor.left==this) {
							this.ancestor.left=null;
							return;
						}
						if(this.ancestor.right==this) {
							this.ancestor.right=null;
							return;
						}}
					else {
						this.value=null;
						return;
					}
				}
				
				if(this.left==null) {
					this.setNodeSaveAncestor(this.right);
					return;
				}
				if(this.right==null) {
					this.setNodeSaveAncestor(this.left);
					return;
				}
				Node min=this.right.findMin();
				this.value=new Integer(min.value.intValue());
				min.delete(min.value.intValue());
			}
			
			Node findMin() {
				if(this.left!=null)
					return this.left.findMin();
				else 
					return this;
			}
			
			void printInStraightLeft(FileWriter f) throws IOException {
				if(this.value!=null)
					f.write(value.toString());
				f.write(LINE_SEPARATOR);
				if(this.left!=null)
					this.left.printInStraightLeft(f);
				if(this.right!=null)
					this.right.printInStraightLeft(f);
			}
		}
		Node root;
		BinarySearchTree() {
			root=new Node();
		}
		
		void insert(int value) {
			root.insert(value);
		}
		
		void delete(int value) {
			root.delete(value);
		}
		
		void printInStraightLeft(FileWriter f) throws IOException {
			root.printInStraightLeft(f);
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
			int value=scanner.nextInt();
			while(scanner.hasNext()) {
				tree.insert(scanner.nextInt());
			}
			tree.delete(value);
			tree.printInStraightLeft(fileWriter);
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
