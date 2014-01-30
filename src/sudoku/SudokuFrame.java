package sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {

	private JPanel buttonSelectionPanel;
	private SudokuPanel sPanel;
	
	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800,600));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		JMenu newGame = new JMenu("New Game");
		JMenuItem sixBySixGame = new JMenuItem("6 By 6 Game");
		sixBySixGame.addActionListener(new NewGameListener(6, 6, 3, 2,new String[] {"1","2","3","4","5","6"},30));
		JMenuItem nineByNineGame = new JMenuItem("9 By 9 Game");
		nineByNineGame.addActionListener(new NewGameListener(9, 9, 3, 3,new String[] {"1","2","3","4","5","6","7","8","9"},26));
		JMenuItem twelveByTwelveGame = new JMenuItem("12 By 12 Game");
		twelveByTwelveGame.addActionListener(new NewGameListener(12, 12, 4, 3,new String[] {"1","2","3","4","5","6","7","8","9","A","B","C"},20));
		JMenuItem sixteenBySizteenGame = new JMenuItem("16 By 16 Game");
		sixteenBySizteenGame.addActionListener(new NewGameListener(16, 16, 4, 4,new String[] {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G"},16));
		newGame.add(sixBySixGame);
		newGame.add(nineByNineGame);
		newGame.add(twelveByTwelveGame);
		newGame.add(sixteenBySizteenGame);
		file.add(newGame);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(90,500));
		
		String [] numbers = {"1","2","3","4","5","6","7","8","9"};
		sPanel = new SudokuPanel(new SudokuPuzzle(9,9,3,3,numbers));
		
		for(String num : numbers) {
			JButton b = new JButton(num);
			b.setPreferredSize(new Dimension(40,40));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);
	}
	
	public void rebuildInterface(SudokuPuzzle puzzle,int fontSize) {
		sPanel.newSudokuPuzzle(puzzle);
		sPanel.setFontSize(fontSize);
		buttonSelectionPanel.removeAll();
		for(String value : puzzle.getValidValues()) {
			JButton b = new JButton(value);
			b.setPreferredSize(new Dimension(40,40));
			b.addActionListener(sPanel.new NumActionListener());
			buttonSelectionPanel.add(b);
		}
		sPanel.repaint();
		buttonSelectionPanel.revalidate();
		buttonSelectionPanel.repaint();
	}
	
	private class NewGameListener implements ActionListener {

		private int rows;
		private int col;
		private int boxWidth;
		private int boxHeight;
		private String [] validValues;
		private int fontSize;
		
		public NewGameListener(int rows,int col, int boxWidth,int boxHeight,String [] validValues,int fontSize) {
			this.rows = rows;
			this.col = col;
			this.boxWidth = boxWidth;
			this.boxHeight = boxHeight;
			this.validValues = validValues;
			this.fontSize = fontSize;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rebuildInterface(new SudokuPuzzle(rows, col, boxWidth, boxHeight, validValues),fontSize);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				SudokuFrame frame = new SudokuFrame();
				frame.setVisible(true);
			}
		});
	}
}
