package sudoku;

public enum SudokuPuzzleType {
	SIXBYSIX(6,6,3,2,new String[] {"1","2","3","4","5","6"});
	
	private final int rows;
	private final int columns;
	private final int boxWidth;
	private final int boxHeight;
	private final String [] validValues;
	
	private SudokuPuzzleType(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		this.rows = rows;
		this.columns = columns;
		this.boxWidth = boxWidth;
		this.boxHeight = boxHeight;
		this.validValues = validValues;
	}
}
