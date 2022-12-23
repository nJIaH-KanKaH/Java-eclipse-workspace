package by.bsu.fpmi.Z.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import by.bsu.fpmi.Z.Matrix.Matrix.WrongMatrixSizeException;
import by.bsu.fpmi.Z.PairRowColumn.PairRowColumn;

public class Matrix {
	private float[][]mainMatrix=null;
	private int N;
	private int M;
	
	public float[][] getMainMatrix() {
		return mainMatrix;
	}
	
	public int getM() {
		return M;
	}
	public int getN() {
		return N;
	}
	public void setMainMatrix(float[][] mainMatrix) {
		this.mainMatrix = mainMatrix;
	}
	public void setM(int m) {
		M = m;
	}
	public void setN(int n) {
		N = n;
	}
	public void setMainMatrixFromFile(String fileName) throws FileNotFoundException, OddElementException, NoSuchElementException {
		Scanner scanner=new Scanner(new File(fileName));
		N=scanner.nextInt();
		M=scanner.nextInt();
		mainMatrix=new float[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0; j<M;j++) {
				mainMatrix[i][j]=scanner.nextFloat();
			}
		}
		if(scanner.hasNextFloat()){
			scanner.close();
			throw new OddElementException("Exception: wrong number of data");
		} else
			scanner.close();
	}
	
	public PairRowColumn findColumnWithMinimalElement() {
		PairRowColumn minElement=new PairRowColumn(mainMatrix[0][0],0,0);
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++) {
				if(mainMatrix[i][j]<=minElement.getValue()) {
					minElement.setValue(mainMatrix[i][j]);
					minElement.setColumn(j);
					minElement.setRow(i);
				}
			}
		return minElement;
	}
	
	public PairRowColumn findRowWithMaximalElement() {
		PairRowColumn maxElement=new PairRowColumn(mainMatrix[0][0],0,0);
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
			{
				if(mainMatrix[i][j]>=maxElement.getValue()) {
					maxElement.setValue(mainMatrix[i][j]);
					maxElement.setRow(i);
					maxElement.setColumn(j);
				}
			}
		return maxElement;
	}
	
	public float multiplyRowToColumn(int row, int column) throws WrongMatrixSizeException {
		if(N!=M)
			throw new WrongMatrixSizeException("Exception: wrong matrix size");
		float result=0.0f;
		 for(int i=0;i<N;i++) {
			 result+=mainMatrix[row][i]*mainMatrix[i][column];
		 }
		 return result;
		
	}
	
	public ArrayList<PairRowColumn> findPairsRowColumn() throws WrongMatrixSizeException{
		Vector<Integer>rows=new Vector<Integer>();
		Vector<Integer>columns=new Vector<Integer>();
		ArrayList<PairRowColumn> lPairRowColumns = new ArrayList<>();
		PairRowColumn minPairRowColumn= findColumnWithMinimalElement();
		PairRowColumn maxPairRowColumn=findRowWithMaximalElement();
		rows.add(maxPairRowColumn.getRow());
		columns.add(minPairRowColumn.getColumn());
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(mainMatrix[i][j]==maxPairRowColumn.getValue() && !rows.lastElement().equals(i)) {
					rows.add(i);
				}
				if(mainMatrix[i][j]==minPairRowColumn.getValue() && !columns.lastElement().equals(j)){
					columns.add(j);
				}
			}
		}
		for(Integer i:rows)
			for(Integer j:columns) {
				PairRowColumn temp=new PairRowColumn();
				temp.setColumn(j);
				temp.setRow(i);
				temp.setValue(multiplyRowToColumn(i, j));
				lPairRowColumns.add(temp);
			}
		return lPairRowColumns;
	}
	
	private float multiplyRowToColumn1(Matrix matrix,int row,int column) throws WrongMatrixSizeException {
		if(M!=matrix.N)
			throw new WrongMatrixSizeException("Exception: wrong matrix size");
		float result=0.0f;
		 for(int i=0;i<N;i++) {
			 result+=mainMatrix[row][i]*matrix.mainMatrix[i][column];
		 }
		 return result;
	}
	
	public Matrix multiply(Matrix matrix) throws WrongMatrixSizeException {
		if(M!=matrix.N)
			throw new WrongMatrixSizeException("Exception: wrong matrix size");
		Matrix temp=new Matrix();
		temp.N=N;
		temp.M=matrix.M;
		temp.mainMatrix=new float[N][matrix.M];
		for(int i=0;i<temp.N;i++)
			for(int j=0;j<temp.M;j++)
				temp.mainMatrix[i][j]=this.multiplyRowToColumn1(matrix, i, j);
		return temp;
	}
	
	public void getMainMatrixToFile(String fileName) throws IOException {
		File file=new File(fileName);
		FileWriter fileWriter=new FileWriter(file);
		String line=System.lineSeparator();
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++) {
				fileWriter.write(Float.toString(mainMatrix[i][j]));
				fileWriter.write(line);
			}
		fileWriter.close();
	}
	
	public void getMainMatrixToConsole(){
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(mainMatrix[i][j]+" ");
			}
			System.out.println();
		}
	} 
	
	public static class WrongMatrixSizeException extends Exception{
		WrongMatrixSizeException(String message){
			super(message);
		}
	}
	
	public static class OddElementException extends Exception{
		OddElementException(String message) {
			super(message);
		}
	}
	
	public void transponate() {
		float temp;
		for(int i=0;i<N;i++) {
			for(int j=0;j<i;j++) {
				temp=mainMatrix[i][j];
				mainMatrix[i][j]=mainMatrix[j][i];
				mainMatrix[j][i]=temp;
			}
		}
	}
	
	public void Add(Matrix matrix) throws WrongMatrixSizeException {
		if(this.N!=matrix.N || this.M!=matrix.M)
			throw new WrongMatrixSizeException("Exception: wrong matrix size");
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++)
				mainMatrix[i][j]+=matrix.mainMatrix[i][j];
			}
	}
	
	public boolean isSimm() {
		boolean flaq=true;
		for(int i=0;i<N;i++)
			for(int j=0;j<i;j++)
				if(mainMatrix[i][j]!=mainMatrix[j][i]) {
					flaq=false;
					break;
				}	
		return flaq;
	}
}
