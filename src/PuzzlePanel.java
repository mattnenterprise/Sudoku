import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class PuzzlePanel extends JPanel implements Runnable {
	
	Sudoku sudoku;
	int selectedCol;
	int selectedRow;
	Thread animator;
	
	public static final int DELAY = 50;
	
	public PuzzlePanel(Sudoku s) {
		addMouseListener(new MouseAdapter());
		
		sudoku = s;
		
		selectedCol = -1;
		selectedRow = -1;

		setDoubleBuffered(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		 Graphics2D g2d = (Graphics2D) g;
		 
		 g2d.setColor(new Color(1.0f,1.0f,1.0f));
		 g2d.fillRect(0, 0,this.getWidth(),this.getHeight());
		 
		 g2d.setColor(new Color(0.0f,0.0f,0.0f));
		 
		 g2d.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		 
		 //render all of the numbers
		 for(int row = 0;row < 9;row++) {
			 for(int col = 0;col < 9;col++) {
				 if(sudoku.getBoardElement(row, col) != 0) {
					 g2d.drawString(Integer.toString(sudoku.getBoardElement(row, col)), ((col) * 60) + (50 / 2), ((row + 1) * 50) - (45 / 2));
				 }
			 }
		 }
		 
		 g2d.setColor(new Color(0.0f,0.0f,1.0f,0.3f));
		 
		 if(selectedRow != -1 && selectedCol != -1) {
			 g2d.fillRect(selectedCol * 60,selectedRow * 50,60,50);
		 }
		 
		 g2d.setColor(new Color(0.0f,0.0f,0.0f,1.0f));
		 
		 g2d.drawLine(0, 449, this.getWidth(), 449);
		 
		 for(int x = 0,i = 0;x <= 540;x+=60,i++) {
			 if(i%3 == 0) {
				 g2d.setStroke(new BasicStroke(2));
				 g2d.drawLine(x, 0, x, this.getHeight());
			 }
			 else {
				 g2d.setStroke(new BasicStroke(1));
				 g2d.drawLine(x, 0, x, this.getHeight());
			 }
		 }
		 
		 for(int y = 0,j = 0;y <= 450;y+=50,j++) {
			 if(j%3 == 0) {
				 g2d.setStroke(new BasicStroke(2));
				 g2d.drawLine(0, y, this.getWidth(), y);
			 }
			 else {
				 g2d.setStroke(new BasicStroke(1));
				 g2d.drawLine(0, y, this.getWidth(), y);
			 }
		 }
	}
	
	/*public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }*/
	
	public void start() {
		animator = new Thread(this);
		animator.start();
	}
	
	class MouseAdapter extends MouseInputAdapter {
		
		public void mouseClicked(MouseEvent e) {
			
			if(e.getButton() == 1) {
				selectedRow = e.getY() / 50;
				selectedCol = e.getX() / 60;
				sudoku.setSelectedCol(selectedCol);
				sudoku.setSelectedRow(selectedRow);
			}
		}
	}

	public void run() {
		long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
	}
	
	public int getSelectedRow() {
		return selectedRow;
	}
	
	public int getSelectedCol() {
		return selectedCol;
	}
	
	/*public void setBoardElement(int row,int col,int value) {
		board[row][col] = value;
	}
	
	public int getBoardElement(int row,int col) {
		return board[row][col];
	}

	public void actionPerformed(ActionEvent e) {
		
		if(selectedRow != -1 && selectedCol != -1) {
			
			if(((JButton)e.getSource()).getText().equals("1")) {
				board[selectedRow][selectedCol] = 1;
			} else if(((JButton)e.getSource()).getText().equals("2")) {
					board[selectedRow][selectedCol] = 2;
			} else if(((JButton)e.getSource()).getText().equals("3")) {
				board[selectedRow][selectedCol] = 3;
			} 
		}
		
	}*/
}
