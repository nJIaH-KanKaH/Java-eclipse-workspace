package by.bsu.fpmi.Z;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import by.bsu.fpmi.Z.CustomShort.CustomShort;
import by.bsu.fpmi.Z.Tree.Tree;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Tree<CustomShort>tree=new Tree<CustomShort>();
		Tree<Integer>tree2=new Tree<Integer>();
		short el;
		Scanner scanner=new Scanner(new File("file.txt"));
		while (scanner.hasNext()) {
			el=scanner.nextShort();
			tree.insert(new CustomShort(el));
			tree2.insert(new Integer(el));
		}
		
		System.out.println("Preorder:");
		tree.Preorder();
		System.out.println("Postorder:");
		tree.Postorder();
		System.out.println("Inorder:");
		tree.Inorder();
		
		tree.search(new CustomShort((short) 2));
		tree.search(new CustomShort((short) 11));
		
		System.out.println();
		
		System.out.println("Preorder:");
		tree2.Preorder();
		System.out.println("Postorder:");
		tree2.Postorder();
		System.out.println("Inorder:");
		tree2.Inorder();
		
		tree2.search(8);
		tree2.search(14);
	}

}
