package sudoku;

import java.util.Random;

public class SudokuGenerator {

	public SudokuPuzzle generateRandomSudoku(int rows,int columns,int boxWidth,int boxHeight,String [] validValues) {
		SudokuPuzzle puzzle = new SudokuPuzzle(rows, columns, boxWidth, boxHeight, validValues);
		
		Random randomGenerator = new Random();
		
		for(int i = 0;i <= 20;i++) {
			int randomNumber = randomGenerator.nextInt(puzzle.getValidValues().length);
			int randomCol = randomGenerator.nextInt(puzzle.getNumColumns());
			int randomRow = randomGenerator.nextInt(puzzle.getNumRows());
			
			puzzle.makeMove(randomRow, randomCol, puzzle.getValidValues()[randomNumber]);
			
			SudokuPuzzle copy = new SudokuPuzzle(puzzle);
			backtrackSudokuSolver(0, 0, copy);
			if(!copy.boardFull()) {
				puzzle.makeSlotEmpty(randomRow, randomCol);
				System.out.println("Board not filled");
			}
		}
		System.out.println("Puzzle Solved: ");
		System.out.println(puzzle);
		
		return puzzle;
	}
	
	/**
	 * Solves the sudoku puzzle
	 * Pre-cond: r = 0,c = 0
	 * Post-cond: solved puzzle
	 * @param r: the current row
	 * @param c: the current column
	 * @return valid move or not or done
	 * Responses: Erroneous data 
	 */
    private boolean backtrackSudokuSolver(int r,int c,SudokuPuzzle puzzle) {
    	//If the move is not valid return false
		if(!puzzle.inRange(r,c)) {
			return false;
		}
		
		//if the current space is empty
		if(puzzle.isSlotEmpty(r, c)) {
			
			//loop to find the correct value for the space
			for(int i = 0;i < puzzle.getValidValues().length;i++) {
				
				//if the current number works in the space
				if(!puzzle.numInRow(r, puzzle.getValidValues()[i]) && !puzzle.numInCol(c,puzzle.getValidValues()[i]) && !puzzle.numInBox(r,c,puzzle.getValidValues()[i])) {
					
					//make the move
					puzzle.makeMove(r, c, puzzle.getValidValues()[i]);
					
					//if puzzle solved return true
					if(puzzle.boardFull()) {
						return true;
					}
					
					//go to next move
					if(r == puzzle.getNumRows() - 1) {
						if(backtrackSudokuSolver(0,c + 1,puzzle)) return true;
					} else {
						if(backtrackSudokuSolver(r + 1,c,puzzle)) return true;
					}
				}
			}
		}
		
		//if the current space is not empty
		else {
			//got to the next move
			if(r == puzzle.getNumRows() - 1) {
				return backtrackSudokuSolver(0,c + 1,puzzle);
			} else {
				return backtrackSudokuSolver(r + 1,c,puzzle);
			}
		}
		
		//undo move
		puzzle.makeSlotEmpty(r, c);
		
		//backtrack
		return false;
	}
}
