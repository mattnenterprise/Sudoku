package sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NumActionListener implements ActionListener {

	private SudokuPanel sPanel;
	
	public NumActionListener(SudokuPanel sPanel) {
		this.sPanel = sPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sPanel.messageFromNumActionListener(Integer.parseInt((((JButton) e.getSource()).getText())));
	}
}
