package by.bsu.fpmi.Z.PairRowColumn;

public class PairRowColumn {
	private int row;
	private int column;
	private float value;
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	public PairRowColumn(float inst,int r,int c) {
		value=inst;
		row=r;
		column=c;
	}
	
	public PairRowColumn() {
	}
	
	public void show() {
		System.out.println("row "+row+" column "+column+" with element "+value);
	}
}
