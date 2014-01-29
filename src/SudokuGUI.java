import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SudokuGUI
{
	static Sudoku sudoku;
	
	public static void GUI()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Sudoku");
		frame.setSize(800,600);
		frame.setVisible(true);
		frame.setMinimumSize(new Dimension(800,600));
		
		sudoku = new Sudoku(frame);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("Game");
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new NewGameActionListener());
		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new OptionsActionListener());
		
		file.add(newGame);
		file.add(options);
		menuBar.add(file);
		
		frame.setJMenuBar(menuBar);
		
		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new FlowLayout());
		windowPanel.setPreferredSize(new Dimension(800,600));
		
		JPanel puzzlePanel = new JPanel();
		
		puzzlePanel.setPreferredSize((new Dimension(600,500)));
		//puzzlePanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		PuzzlePanel pp = new PuzzlePanel(sudoku);
		pp.setPreferredSize(new Dimension(540,450));
		puzzlePanel.add(pp);
		pp.start();
		
		JPanel buttonSelectionPanel = new JPanel();
		
		buttonSelectionPanel.setPreferredSize(new Dimension(75,500));
		
		JButton one = new JButton("1");
		one.addActionListener(sudoku);
		one.setPreferredSize(new Dimension(40,40));
		JButton two = new JButton("2");
		two.addActionListener(sudoku);
		two.setPreferredSize(new Dimension(40,40));
		JButton three = new JButton("3");
		three.addActionListener(sudoku);
		three.setPreferredSize(new Dimension(40,40));
		JButton four = new JButton("4");
		four.addActionListener(sudoku);
		four.setPreferredSize(new Dimension(40,40));
		JButton five = new JButton("5");
		five.addActionListener(sudoku);
		five.setPreferredSize(new Dimension(40,40));
		JButton six = new JButton("6");
		six.addActionListener(sudoku);
		six.setPreferredSize(new Dimension(40,40));
		JButton seven = new JButton("7");
		seven.addActionListener(sudoku);
		seven.setPreferredSize(new Dimension(40,40));
		JButton eight = new JButton("8");
		eight.addActionListener(sudoku);
		eight.setPreferredSize(new Dimension(40,40));
		JButton nine = new JButton("9");
		nine.addActionListener(sudoku);
		nine.setPreferredSize(new Dimension(40,40));
		
		buttonSelectionPanel.add(one);
		buttonSelectionPanel.add(two);
		buttonSelectionPanel.add(three);
		buttonSelectionPanel.add(four);
		buttonSelectionPanel.add(five);
		buttonSelectionPanel.add(six);
		buttonSelectionPanel.add(seven);
		buttonSelectionPanel.add(eight);
		buttonSelectionPanel.add(nine);
		//buttonSelectionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		windowPanel.add(puzzlePanel);
		windowPanel.add(buttonSelectionPanel);
		frame.add(windowPanel);
		
	}
	public static void main(String [] args)
	{	
		//make the look and feel of the GUI look and feel like the operating system 
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
		catch(IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch(InstantiationException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		//create thread to run the Program
		Runnable Sudoku = new Runnable () 
		{
			public void run()
			{
				GUI();
			}
		};
		SwingUtilities.invokeLater(Sudoku);
	}
}
