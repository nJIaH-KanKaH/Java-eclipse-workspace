package by.bsu.fpmi.Z;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import by.bsu.fpmi.Z.Matrix.Matrix;
import by.bsu.fpmi.Z.Matrix.Matrix.OddElementException;
import by.bsu.fpmi.Z.Matrix.Matrix.WrongMatrixSizeException;
import by.bsu.fpmi.Z.PairRowColumn.PairRowColumn;

public class Main {

	public static void main(String[] args) {
		Matrix matrix=new Matrix();
		try {
			matrix.setMainMatrixFromFile("file.txt");
			ArrayList<PairRowColumn>rList=matrix.findPairsRowColumn();
			for(PairRowColumn it:rList) {
				it.show();
			}
			matrix.getMainMatrixToFile("out.txt");
		} 
		catch (WrongMatrixSizeException e) {
			System.out.println(e.getMessage());
		}	
		catch (FileNotFoundException e) {
			System.out.println("Exception: file not found");
		} 
		catch (NumberFormatException e) {
			System.out.println("Exception: wrong format of data");
		}
		catch(OddElementException e) {
			System.out.println("Exception: wrong number of data");
		}
		catch(NoSuchElementException e) {
			System.out.println("Exception: not enough data");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
