import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Runnable{
	
	public static List<Way>wayList=new ArrayList<Way>();
	public static final String LINE_SEPARATOR=System.lineSeparator();
	static class BinarySearchTree{
		class Node{
			Integer value;
			Node left;
			Node right;
			Node ancestor;
			int height=-1;
			int minWayLength=-1;
			long minWayWeight=0l;
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
				if(this.value==null) {
					value=new Integer(v);}
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
			
			void writer() {
				int lH=-1; int rH=-1;
				int lM=Integer.MIN_VALUE; int rM=Integer.MIN_VALUE;
				long lW=0l; long rW=0l;
				if(this.left!=null) {
					this.left.writer();
					lH=this.left.height;
					lM=this.left.minWayLength;
					lW=this.left.minWayWeight;
				}
				if(this.right!=null) {
					this.right.writer();
					rH=this.right.height;
					rM=this.right.minWayLength;
					rW=this.right.minWayWeight;
				}
				this.height=Math.max(lH,rH)+1;
				if(this.left!=null && this.right!=null) {
					if(lM<rM)	{
						this.minWayLength=lM+1;
						this.minWayWeight=lW+this.value.longValue();
					}
					else if(lM==rM){
						if(lW<=rW) {
							this.minWayLength=lM+1;
							this.minWayWeight=lW+this.value.longValue();
						}else{
							this.minWayLength=rM+1;
							this.minWayWeight=rW+this.value.longValue();
						}
					}
					else{
						this.minWayLength=rM+1;
						this.minWayWeight=rW+this.value.longValue();
					}

//					this.minWayLength=Math.min(lM, rM)+1;
//					this.minWayWeight=Math.min(lW, rW)+this.value.longValue();
					
					wayList.add(new Way(this.left.minWayLength+this.right.minWayLength+2, this, this.left.minWayWeight+this.right.minWayWeight+this.value.longValue()));
				}
				else {						
					this.minWayLength=1+ (this.left!=null ? lM :this.right!=null ? rM : 0); 
					this.minWayWeight=this.value.longValue()+ (this.left!=null ? lW : this.right!=null ? rW : 0l) ;
				}
			}
			@Override
			public String toString() {
				return value.toString();
			}
		}
		
		Node root;
		int treeHeight;
		BinarySearchTree() {
			root=new Node();
		}
		
		void insert(int value) {
			root.insert(value);
		}
		
		void delete(int value) {
			root.delete(value);
		}
		
		void writer() {
			root.writer();
		}
		
		void printInStraightLeft(FileWriter f) throws IOException {
			root.printInStraightLeft(f);
		}
	}
	static class Way{
			int length=0;
			BinarySearchTree.Node root=null;
			long weight=0;
			public Way(int length, BinarySearchTree.Node root, long weight) {
				this.length = length;
				this.root = root;
				this.weight = weight;
			}
			@Override
			public String toString() {
				return "Way [length=" + length + ", root=" + root + ", weight=" + weight + "]";
			}
		}
	public static void main(String[] args) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

	@Override
	public void run() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("in.txt"));
			FileWriter fileWriter=new FileWriter(new File("out.txt"));
			BinarySearchTree tree=new BinarySearchTree();
			while(scanner.hasNext()) {
				tree.insert(scanner.nextInt());
			}
			tree.writer();
			if(!wayList.isEmpty()) {
				Way minWay=wayList.get(0);
				for(Way i:wayList) {
					if(i.length<minWay.length)
						minWay=i;
					else if (i.length==minWay.length) {
						if(i.weight<minWay.weight)
							minWay=i;
						else if (i.weight==minWay.weight)
								if(Integer.compare(i.root.value,minWay.root.value)<0)
									minWay=i;
					}
				}
				if(minWay.length%2==0) {
					int diff=minWay.root.minWayLength-minWay.length/2;
					BinarySearchTree.Node nodeToDelete=minWay.root;
					while(diff>0) {
						if(nodeToDelete.left!=null) {
							if(nodeToDelete.left.minWayWeight==(nodeToDelete.minWayWeight-nodeToDelete.value.longValue())) {
								nodeToDelete=nodeToDelete.left;
							}
						}
						else { if(nodeToDelete.right!=null)
							if(nodeToDelete.right.minWayWeight==(nodeToDelete.minWayWeight-nodeToDelete.value.longValue())) {
								nodeToDelete=nodeToDelete.right;
							}
						}
						diff--;
					}
					nodeToDelete.delete(nodeToDelete.value.intValue());
				}
			}
			tree.printInStraightLeft(fileWriter);
			scanner.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
