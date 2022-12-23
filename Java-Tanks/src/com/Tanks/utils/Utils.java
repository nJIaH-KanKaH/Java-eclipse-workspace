package com.Tanks.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utils {
	public static BufferedImage resize(BufferedImage image,int width,int height) {
		BufferedImage newImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		newImage.getGraphics().drawImage(image, 0, 0, width, height,null);
		return newImage;
	}
	
	public static int[][] levelParser(String filePath) throws FileNotFoundException{
		Scanner scanner=new Scanner(new File(filePath));
		int N=scanner.nextInt();
		int M=scanner.nextInt();
		int[][] tmp=new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0; j<M;j++) {
				tmp[i][j]=scanner.nextInt();
			}
		}
		scanner.close();
		return tmp;
	}
}
