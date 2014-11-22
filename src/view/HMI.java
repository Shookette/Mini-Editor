/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLayeredPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import controller.Command;

/**
 * Defines the GUI (Graphical User Interface)
 */
@SuppressWarnings("serial")
public class HMI extends JFrame implements HMIInterface{

	//contains all the components
	private JLayeredPane container = new JLayeredPane();
	
	//size of the buttons
	private Dimension dim = new Dimension(85, 40);
	//panel containing the buttons
	private JPanel panButton = new JPanel();
	//panel with screenOut and screenIn
	private JPanel panScreen = new JPanel();
	//display of the buffer
	private JTextArea screenOut = new JTextArea();
	//where the user can insert text
	private JTextArea screenIn = new JTextArea();
	//panel with the selection textarea and the clipboard textarea
	private JPanel panOther = new JPanel();
	private JTextArea selectArea = new JTextArea();
	private JTextArea clipBoardArea = new JTextArea();
	
	public HMI(){                
		this.setSize(536, 474);
		this.setTitle("Editor");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);           
		this.setContentPane(container);
		container.setLayout(null);
		
		panButton.setBounds(10, 11, 510, 64);
		container.add(panButton);
		
		panScreen.setBounds(10, 86, 337, 349);
		container.add(panScreen);
		panScreen.setLayout(null);
		//the user won't be able to type in the screenOut, only select
		screenOut.setEditable(false);
		screenOut.setBorder(BorderFactory.createLineBorder(Color.black));
		screenOut.setBounds(10, 5, 317, 237);
		screenOut.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				return;
			}
			
			/**
			 * When we have the focus on the screenOut, we get the cursor (to define a selection)
			 */
			@Override
			public void focusGained(FocusEvent arg0) {
				screenOut.getCaret().setVisible(true);
				screenOut.getHighlighter().removeAllHighlights();
			}
		});
		panScreen.add(screenOut);
		
		screenIn.setBounds(10, 253, 317, 80);
		screenIn.setBorder(BorderFactory.createLineBorder(Color.black));
		panScreen.add(screenIn);
		
		panOther.setBounds(357, 86, 163, 349);
		container.add(panOther);
		panOther.setLayout(null);
		selectArea.setText("Start : 0\r\nEnd : 0");
		
		selectArea.setEditable(false);
		selectArea.setBounds(10, 11, 143, 107);
		selectArea.setBorder(BorderFactory.createLineBorder(Color.black));
		panOther.add(selectArea);
		
		clipBoardArea.setEditable(false);
		clipBoardArea.setBounds(10, 129, 143, 107);
		clipBoardArea.setBorder(BorderFactory.createLineBorder(Color.black));
		panOther.add(clipBoardArea);
		this.setVisible(true);
	}

	/**

	 * update a textarea
	 * @param str String text to put in the textarea
	 * @param textAreaName String where to put the text
	 */
	@Override
	//Implements pattern observer
	public void update(String str, String textAreaName) {
		switch (textAreaName) {
		case "screenOut":
			screenOut.setText(str);
			break;
		case "selectArea":
			selectArea.setText(str);
			break;
		case "clipBoardArea":
			clipBoardArea.setText(str);
			break;
		default:break;
		}
	}

	/**
	 * Get screenIn text
	 * @return text String from the screen in
	 */
	public String getScreenInText() {
		return screenIn.getText();
	}

	/**
	 * Create button and bind it with command
	 * @param i int number of the command
	 * @param com Command what commant to bind to this button
 	 * @param name String text on the button
	 */
	public void setCommand(int i, Command com, String name) {
		// New JButton
		JButton button = new JButton();
		button.setText(name);
		button.setPreferredSize(dim);
		button.addActionListener(new CommandListener(com));
		panButton.add(button);
	}
	
	/**
	 * Create a listener to execute command from the button
	 */
	public class CommandListener implements ActionListener {
		private Command com;
		
		public CommandListener(Command com) {
			this.com = com;
		}
		
		public void actionPerformed(ActionEvent e) {
			this.com.execute();
		}   
	}
	
	/**
	 * Get position of the selection in the screen out
	 * @return result int[] with its selection start and end positions
	 */
	public int[] getPosition(){
		System.out.println("from : "+screenOut.getSelectionStart()+" to : "+screenOut.getSelectionEnd());
		int[] result = new int[2];
		result[0] = screenOut.getSelectionStart();
		result[1] = screenOut.getSelectionEnd();
		return result;
		
	}

	/**
	 * Reset text from the screen in
	 */
	public void resetScreenIn() {
		this.screenIn.setText("");
	}
	
	/**
	 * Highlight the selection in the screen out
	 * @param pos int[] contains start and end positions
	 */
	public void highlightSelection(int[] pos) {
		 screenOut.getHighlighter().removeAllHighlights();
		 try {
			 screenOut.getHighlighter().addHighlight(pos[0], pos[1], DefaultHighlighter.DefaultPainter);
		 } catch (BadLocationException e) {
			 e.printStackTrace();
		 }
	 }
}