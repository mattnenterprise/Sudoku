package sudoku;

public class SudokuPuzzle {

	private int [][] board;
	private final int ROWS = 9;
	private final int COLUMNS = 9;
	
	public SudokuPuzzle() {
		board = new int[ROWS][COLUMNS];
		initializeBoard();
	}
	
	public int getNumRows() {
		return ROWS;
	}
	
	public int getNumColumns() {
		return COLUMNS;
	}
	
	public void makeMove(int row,int col,int num) {
		if(inRange(row,col) && isValidMove(row,col,num)) {
			board[row][col] = num; 
		}
	}
	
	public boolean isValidMove(int row,int col,int num) {
		if(inRange(row,col)) {
			if(!numInCol(col,num) && !numInRow(row,num)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean numInCol(int col,int num) {
		if(col <= COLUMNS) {
			for(int row=0;row < ROWS;row++) {
				if(board[row][col] == num) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean numInRow(int row,int num) {
		if(row <= ROWS) {
			for(int col=0;col < COLUMNS;col++) {
				if(board[row][col] == num) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isSlotEmpty(int row,int col) {
		 return (inRange(row,col) && board[row][col] == 0);
	}
	
	public int getValue(int row,int col) {
		if(inRange(row,col)) {
			return board[row][col];
		}
		return -1;
	}
	
	private boolean inRange(int row,int col) {
		return row <= ROWS && col <= COLUMNS && row >= 0 && col >= 0;
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
				board[row][col] = 0;
			}
		}
	}
}
