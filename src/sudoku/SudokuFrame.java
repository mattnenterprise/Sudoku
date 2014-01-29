package sudoku;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SudokuFrame extends JFrame {

	public SudokuFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sudoku");
		this.setMinimumSize(new Dimension(800,600));
		this.setMaximumSize(new Dimension(800,600));
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		JPanel buttonSelectionPanel = new JPanel();
		buttonSelectionPanel.setPreferredSize(new Dimension(75,500));
		
		SudokuPanel sPanel = new SudokuPanel();
		
		String [] numbers = {"1","2","3","4","5","6","7","8","9"};
		for(String num : numbers) {
			JButton b = new JButton(num);
			b.setPreferredSize(new Dimension(40,40));
			b.addActionListener(new NumActionListener(sPanel));
			buttonSelectionPanel.add(b);
		}
		
		windowPanel.add(sPanel);
		windowPanel.add(buttonSelectionPanel);
		this.add(windowPanel);
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
