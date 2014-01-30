package sudoku;

public class SudokuGenerator {

	public SudokuPuzzle generateRandomSudoku(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		SudokuPuzzle puzzle = new SudokuPuzzle(rows, columns, boxWidth, boxHeight, validValues);
		
		
		
		return puzzle;
	}
}
