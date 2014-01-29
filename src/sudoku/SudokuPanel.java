package sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel {

	private SudokuPuzzle puzzle;
	private int currentlySelectedCol;
	private int currentlySelectedRow;
	
	public SudokuPanel() {
		this.setPreferredSize(new Dimension(540,450));
		this.addMouseListener(new SudokuPanelMouseAdapter());
		puzzle = new SudokuPuzzle();
		currentlySelectedCol = -1;
		currentlySelectedRow = -1;
	}
	
	public void messageFromNumActionListener(Integer buttonNumber) {
		if(currentlySelectedCol != -1 && currentlySelectedRow != -1) {
			puzzle.makeMove(currentlySelectedRow, currentlySelectedCol, buttonNumber);
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(1.0f,1.0f,1.0f));
		g2d.fillRect(0, 0,this.getWidth(),this.getHeight());
		
		int slotWidth = this.getWidth()/puzzle.getNumColumns();
		int slotHeight = this.getHeight()/puzzle.getNumRows();
		
		g2d.setColor(new Color(0.0f,0.0f,0.0f));
		for(int x = 0;x <= this.getWidth();x+=slotWidth) {
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(x, 0, x, this.getHeight());
		}
		//this will draw the right most line
		g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, this.getHeight());
		for(int y = 0;y <= this.getHeight();y+=slotHeight) {
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(0, y, this.getWidth(), y);
		}
		//this will draw the bottom line
		g2d.drawLine(0, this.getHeight() - 1, this.getWidth(), this.getHeight() - 1);
		
		Font f = new Font("Times New Roman", Font.PLAIN, 26);
		g2d.setFont(f);
		FontRenderContext fContext = g2d.getFontRenderContext();
		for(int row=0;row < puzzle.getNumRows();row++) {
			for(int col=0;col < puzzle.getNumColumns();col++) {
				if(!puzzle.isSlotEmpty(row, col)) {
					int textWidth = (int) f.getStringBounds(Integer.toString(puzzle.getValue(row, col)), fContext).getWidth();
					int textHeight = (int) f.getStringBounds(Integer.toString(puzzle.getValue(row, col)), fContext).getHeight();
					g2d.drawString(Integer.toString(puzzle.getValue(row, col)), (col*slotWidth)+((slotWidth/2)-(textWidth/2)), (row*slotHeight)+((slotHeight/2)+(textHeight/2)));
				}
			}
		}
		if(currentlySelectedCol != -1 && currentlySelectedRow != -1) {
			g2d.setColor(new Color(0.0f,0.0f,1.0f,0.3f));
			g2d.fillRect(currentlySelectedCol * slotWidth,currentlySelectedRow * slotHeight,slotWidth,slotHeight);
		}
		
	}
	
	private class SudokuPanelMouseAdapter extends MouseInputAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				int slotWidth = e.getComponent().getWidth()/puzzle.getNumColumns();
				int slotHeight = e.getComponent().getHeight()/puzzle.getNumRows();
				currentlySelectedRow = e.getY() / slotHeight;
				currentlySelectedCol = e.getX() / slotWidth;
				e.getComponent().repaint();
			}
		}
	}
}
