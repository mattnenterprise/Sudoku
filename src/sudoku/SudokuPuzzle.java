package sudoku;

public class SudokuPuzzle {

	protected String [][] board;
	private final int ROWS;
	private final int COLUMNS;
	private final int BOXWIDTH;
	private final int BOXHEIGHT;
	private final String [] VALIDVALUES;
	
	public SudokuPuzzle(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		this.ROWS = rows;
		this.COLUMNS = columns;
		this.BOXWIDTH = boxWidth;
		this.BOXHEIGHT = boxHeight;
		this.VALIDVALUES = validValues;
		board = new String [ROWS][COLUMNS];
		initializeBoard();
	}
	
	public SudokuPuzzle(SudokuPuzzle puzzle) {
		this.ROWS = puzzle.ROWS;
		this.COLUMNS = puzzle.COLUMNS;
		this.BOXWIDTH = puzzle.BOXWIDTH;
		this.BOXHEIGHT = puzzle.BOXHEIGHT;
		this.VALIDVALUES = puzzle.VALIDVALUES;
		board = new String [ROWS][COLUMNS];
		for(int r = 0;r < ROWS;r++) {
			for(int c = 0;c < COLUMNS;c++) {
				board[r][c] = puzzle.board[r][c];
			}
		}
	}
	
	public int getNumRows() {
		return ROWS;
	}
	
	public int getNumColumns() {
		return COLUMNS;
	}
	
	public int getBoxWidth() {
		return BOXWIDTH;
	}
	
	public int getBoxHeight() {
		return BOXHEIGHT;
	}
	
	public String [] getValidValues() {
		return VALIDVALUES;
	}
	
	public void makeMove(int row,int col,String value) {
		if(isValidValue(value) && isValidMove(row,col,value)) {
			board[row][col] = value; 
		}
	}
	
	public boolean isValidMove(int row,int col,String value) {
		if(inRange(row,col)) {
			if(!numInCol(col,value) && !numInRow(row,value) && !numInBox(row,col,value)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean numInCol(int col,String value) {
		if(col <= COLUMNS) {
			for(int row=0;row < ROWS;row++) {
				if(board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean numInRow(int row,String value) {
		if(row <= ROWS) {
			for(int col=0;col < COLUMNS;col++) {
				if(board[row][col].equals(value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean numInBox(int row,int col,String value) {
		if(inRange(row, col)) {
			int boxRow = row / BOXHEIGHT;
			int boxCol = col / BOXWIDTH;
			
			int startingRow = (boxRow*BOXHEIGHT);
			int startingCol = (boxCol*BOXWIDTH);
			
			for(int r = startingRow;r <= (startingRow+BOXHEIGHT)-1;r++) {
				for(int c = startingCol;c <= (startingCol+BOXWIDTH)-1;c++) {
					if(board[r][c].equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isSlotEmpty(int row,int col) {
		 return (inRange(row,col) && board[row][col].equals("0"));
	}
	
	public String getValue(int row,int col) {
		if(inRange(row,col)) {
			return board[row][col];
		}
		return "";
	}
	
	public String [][] getBoard() {
		return board;
	}
	
	private boolean isValidValue(String value) {
		for(String str : VALIDVALUES) {
			if(str.equals(value)) return true;
		}
		return false;
	}
	
	public boolean inRange(int row,int col) {
		return row <= ROWS && col <= COLUMNS && row >= 0 && col >= 0;
	}
	
	public boolean boardFull() {
		for(int r = 0;r < ROWS;r++) {
			for(int c = 0;c < COLUMNS;c++) {
				if(board[r][c].equals("0")) return false;
			}
		}
		return true;
	}
	
	public void makeSlotEmpty(int row,int col) {
		board[row][col] = "0";
	}
	
	@Override
	public String toString() {
		String str = "Game Board:\n";
		for(int row=0;row < ROWS;row++) {
			for(int col=0;col < COLUMNS;col++) {
				str += board[row][col] + " ";
			}
			str += "\n";
		}
		return str+"\n";
	}
	
	private void initializeBoard() {
		for(int row = 0;row < ROWS;row++) {
			for(int col = 0;col < COLUMNS;col++) {
				board[row][col] = "0";
			}
		}
	}
}
