package by.bsu.fpmi.Z.Tree;

public class Tree<T extends Comparable<T>> {
	private class Node<T> {
		private T data=null;
		private Node<T> leftNode=null;
		private Node<T>rightNode=null; 
		
		private Node(T dataT){
			this.data=dataT;
		}        
	}
	
	private Node<T> root=null;
	public Tree() {
		root=null;
	}
	public void insert(T dataT) {
		root=insert(root,dataT);
	}
	private Node<T> insert(Node<T>dataT,T data) {
		if(dataT==null) {
			dataT=new Node<T>(data);
		}else if(dataT.data.compareTo(data)>0) {
			dataT.leftNode=insert(dataT.leftNode,data);
		}else if(dataT.data.compareTo(data)<0) {
			dataT.rightNode=insert(dataT.rightNode,data);
		}
		return dataT;
	}
	
	public boolean search(T value) {
		return search(root, value, true) != null;
	}
	
	private Node search(Node<T> node, T value, Boolean print) {
		if (value.compareTo(node.data) < 0)
			if (node.leftNode != null)
				if (!print && value.compareTo(node.leftNode.data) == 0)
					return node;
				else
					return search(node.leftNode, value, print);
			else {
				System.out.println("There is no " + value + " in tree");
				return null;
			}
		else if (value.compareTo(node.data) > 0)
			if (node.rightNode != null)
				if (!print && value.compareTo(node.rightNode.data) == 0)
					return node;
				else
					return search(node.rightNode, value, print);
			else {
				System.out.println("There is no " + value + " in tree");
				return null;
			}
		else {
			if (print) {
				System.out.println(value + " was found");
			}
			return node;
		}

	}

    public void Preorder() {
        if (root == null) {
            return;
        }
        Preorder(root);
        System.out.println();
    }
    
    private void Preorder(Node<T>node) {
    	System.out.print((String.valueOf(node.data))+" ");
    	if(node.leftNode!=null) {
    		Preorder(node.leftNode);
    	}
    	if(node.rightNode!=null) {
    		Preorder(node.rightNode);
    	}
    }

    public void Inorder() {
        if (root == null) {
            return;
        }
        Inorder(root);
        System.out.println();
    }

    private void Inorder(Tree<T>.Node<T> node) {
    	if(node.leftNode!=null) {
    		Inorder(node.leftNode);
    	}
    	System.out.print((String.valueOf(node.data))+" ");
    	if(node.rightNode!=null) {
    		Inorder(node.rightNode);
    	}
	}
    
	public void Postorder() {
        if (root == null) {
            return;
        }
        Postorder(root);
        System.out.println();
    }
	
	private void Postorder(Tree<T>.Node<T> node) {
		if(node.leftNode!=null) {
    		Postorder(node.leftNode);
    	}
		if(node.rightNode!=null) {
    		Postorder(node.rightNode);
    	}
		System.out.print((String.valueOf(node.data))+" ");
	}
}

