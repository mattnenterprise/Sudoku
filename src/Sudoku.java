import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Sudoku implements ActionListener {

		JFrame frame;
	 	private int boardSize = 9;
	    private int [][] board;
	    private int [][] solvedBoard;
	    private int selectedCol;
	    private int selectedRow;
		
	    public Sudoku(JFrame f) {
	    	frame = f;
	    	board = new int[boardSize][boardSize];
	    	solvedBoard = new int[boardSize][boardSize];
	    	
	    	for(int row = 0;row < boardSize;row++) {
	    		for(int col = 0;col < boardSize;col++) {
	    			board[row][col] = 0;
	    		}
	    	}
	    	
	    	generateRandomPuzzle();
	    	
	    	selectedRow = -1;
	    	selectedCol = -1;
	    }
	    
	    public void newGame() {
	    	board = new int[boardSize][boardSize];
	    	solvedBoard = new int[boardSize][boardSize];
	    	
	    	for(int row = 0;row < boardSize;row++) {
	    		for(int col = 0;col < boardSize;col++) {
	    			board[row][col] = 0;
	    		}
	    	}
	    	
	    	generateRandomPuzzle();
	    	
	    	selectedRow = -1;
	    	selectedCol = -1;
	    }
	    
		/**
		 * Determines if the position is on the board
		 * @param: r the row of the position
		 * @param: c the column of the position
		 * @return: if the position is on the board
		 */
		private boolean onBoard(int r,int c) {
		   return (r >= 0 && r < boardSize && c >= 0 && c < boardSize);
		}
		
		/**
		 * Determines if the position is available
		 * @param: r the row of the position
		 * @param c the column of the position
		 * @return if the position is available or not
		 */
		private boolean available(int r,int c) {
		   return (board[r][c] == 0);
		}
		
		/**
		 * Determines if n is already in the current column
		 * @param: r the row of the position
		 * @param: c the column of the position
		 * @param: n the number to test for
		 * @return: if n is in the column
		 */
		private boolean numInSelectedColumn(int r,int c,int n) {
		   for(int i = 0;i < boardSize;i++) {
		      if(i == r) {
			     continue;
			  }
			  if(board[i][c] == n) {
			     return true;
			  }
		   }
		   return false;
		}
		
		/**
		 * Determines if n is already in the current row
		 * @param: r the row of the position
		 * @param: c the column of the position
		 * @param: n the number to test for
		 * @return: if n is in the row
		 */
		private boolean numInSelectedRow(int r,int c,int n) {
			for(int i = 0;i < boardSize;i++) {
				if(i == c) {
					continue;
				}
				if(board[r][i] == n) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Determines if n is already in the current box
		 * @param: r the row of the position
		 * @param: c the column of the position
		 * @param: n the number to test for
		 * @return: if n is in the box
		 */
		private boolean numInBox(int r,int c,int n) {
		   r = (r / (boardSize/3)) * (boardSize/3);
		   c = (c / 3) * 3;
		      
		   for(int i = 0; i < (boardSize/3); i++) {
		      for(int j = 0; j < 3; j++){
			     if( board[r+i][c+j] == n) {
			        return true ;
			     }
			  }
		   }
		   return false;
		}

		/**
		 * Determines if the board is full
		 * @return if the board is full or not
		 */
		private  boolean boardFull() {
	       for(int i = 0;i < boardSize;i++) {
		      for(int j = 0;j < boardSize;j++) {
			     if(board[i][j] == 0) {
				    return false;
				 }
			  }
		   }
		   return true;
		}
		
		/**
		 * Prints out the current board
		 */
		private void printBoard() {
		   for(int i = 0;i < boardSize;i++) {
		      if(i != 0){ 
			     System.out.println();
			  }
			  for(int j = 0;j < boardSize;j++) {
			     System.out.print(board[i][j]);
			  }
		   }
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
	    private boolean sudokuSolver(int r,int c) {
	    	//If the move is not valid return false
			if(!onBoard(r,c)) {
				return false;
			}
			
			//if the current space is empty
			if(available(r,c)) {
				
				//loop to find the correct value for the space
				for(int i = 1;i < boardSize + 1;i++) {
					
					//if the current number works in the space
					if(!numInSelectedRow(r,c,i) && !numInSelectedColumn(r,c,i) && !numInBox(r,c,i)) {
						
						//make the move
						board[r][c] = i;
						
						//if puzzle solved return true
						if(boardFull()) {
							return true;
						}
						
						//go to next move
						if(r == boardSize - 1) {
							if(sudokuSolver(0,c + 1)) return true;
						} else {
							if(sudokuSolver(r + 1,c)) return true;
						}
					}
				}
			}
			
			//if the current space is not empty
			else {
				//got to the next move
				if(r == boardSize - 1) {
					return sudokuSolver(0,c + 1);
				} else {
					return sudokuSolver(r + 1,c);
				}
			}
			
			//undo move
			board[r][c] = 0;
			
			//backtrack
			return false;
		}
	    
	    public int getBoardElement(int row,int col) {
	    	return board[row][col];
	    }
	    
	    public void setBoardElement(int row,int col,int value) {
	    	board[row][col] = value;
	    }
	    
	    public void setSelectedCol(int sC) {
	    	selectedCol = sC;
	    }
	    
	    public void setSelectedRow(int sR) {
	    	selectedRow = sR;
	    }

		public void actionPerformed(ActionEvent e) {
			
			if(selectedRow != -1 && selectedCol != -1 && (isSolved() == false)) {
				if(((JButton)e.getSource()).getText().equals("1")) {
					board[selectedRow][selectedCol] = 1;
				} else if(((JButton)e.getSource()).getText().equals("2")) {
					board[selectedRow][selectedCol] = 2;
				} else if(((JButton)e.getSource()).getText().equals("3")) {
					board[selectedRow][selectedCol] = 3;
				} else if(((JButton)e.getSource()).getText().equals("4")) {
					board[selectedRow][selectedCol] = 4;
				} else if(((JButton)e.getSource()).getText().equals("5")) {
					board[selectedRow][selectedCol] = 5;
				} else if(((JButton)e.getSource()).getText().equals("6")) {
					board[selectedRow][selectedCol] = 6;
				} else if(((JButton)e.getSource()).getText().equals("7")) {
					board[selectedRow][selectedCol] = 7;
				} else if(((JButton)e.getSource()).getText().equals("8")) {
					board[selectedRow][selectedCol] = 8;
				} else if(((JButton)e.getSource()).getText().equals("9")) {
					board[selectedRow][selectedCol] = 9;
				} 
			}
			
			if(isSolved() == true) {
				//default icon, custom title
				//0 == yes, 1 == no
				Object[] options = {"New Game",
                "Quit"};
				int n = JOptionPane.showOptionDialog(
				    frame,
				    "You have solve the puzzle. Would you like to start a new game or quit?",
				    "End Game",
				    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				System.out.println(n);
				
				if(n == 0) {
					newGame();
				} else {
					//perhaps find something to use besides System.exit() ?????
					System.exit(0);
					//frame.dispose();
				}
			}
		}
		
		public void generateRandomPuzzle() {
			
			Random randomGenerator = new Random();
			
			int randomNumber = randomGenerator.nextInt(9) + 1;
			
			int randomCol = randomGenerator.nextInt(9);
			int randomRow = randomGenerator.nextInt(9);
			
			board[randomRow][randomCol] = randomNumber;
			
			sudokuSolver(0,0);
			
			printBoard();
			
			for(int row = 0;row < boardSize;row++) {
				for(int col = 0;col < boardSize;col++) {
					solvedBoard[row][col] = board[row][col];
				}
			}
			
			for(int row = 0;row < boardSize;row++) {
	    		for(int col = 0;col < boardSize;col++) {
	    			board[row][col] = 0;
	    		}
	    	}
			
			//need to fix this so that no repeats
			
			int randomNumberOfStartingSpotsDone = randomGenerator.nextInt(10) + 30;
			boolean validSpot = false;
			for(int e = 0;e < randomNumberOfStartingSpotsDone;e++) {
				while(!validSpot)
				{
					randomCol = randomGenerator.nextInt(9);
					randomRow = randomGenerator.nextInt(9);
				
					if(board[randomRow][randomCol] == 0)
					{
						board[randomRow][randomCol] = solvedBoard[randomRow][randomCol];
						validSpot = true;
					}
				}
				validSpot = false;
			}
		}
		
		public boolean isSolved() {
			boolean isSolve = true;
			
			for(int row = 0;row < boardSize;row++) {
				for(int col = 0;col < boardSize;col++) {
					if(board[row][col] == solvedBoard[row][col]) {
						continue;
					} else {
						isSolve = false;
						break;
					}		
				}
			}
			return isSolve;
		}
}
