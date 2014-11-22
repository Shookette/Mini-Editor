/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;

import observer.Observer;

public class Core implements CoreInterface{

	private Buffer buffer = new Buffer();
	private Selection selection = new Selection();
	private ClipBoard clipBoard = new ClipBoard();
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	/**
	 * Basic constructor for editor
	 */
	public Core() {}
	
	/**
	 * Configurable constructor for unit testing
	 * @param b String for string buffer content
	 * @param s array of int for selection position
	 * @param c String for clipboard content
	 */
	public Core(String b, int[] s, String c) {
		this.buffer = new Buffer(b);
		this.selection = new Selection(s);
		this.clipBoard = new ClipBoard(c);
	}
	
	
	/**
	 * Add an observer to the listObserver
	 */
	@Override
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
		
	}

	/**
	 * Clear the listObserver/remove observers from the list
	 */
	@Override
	public void removeObserver() {
		this.listObserver.clear();		
	}

	/**
	 * Notify All the observer with String text
	 * @param str String text to pass to the observer (HMI)
	 * @param textAreaName String name of the textarea to put str in
	 */
	@Override
	public void notifyObserver(String str,String textAreaName) {
		//Create an iterator on the list of observers
		Iterator<Observer> it = listObserver.iterator();

		//update each with the text we want
		while (it.hasNext()) {
			it.next().update(str,textAreaName);
		}
	}
	
	/**
	 * Insert Function
	 * Insert text in the buffer then modify screenOut with notify
	 * @param txt String text to insert into the buffer
	 */
	@Override
	public void insert(String txt) {
		//Verify that txt isn't null
		if (!txt.equals("")) {
			System.out.println("insert");
			int[] position = selection.getSelection();
			//Add text to buffer
			buffer.insert(txt, position);
			//Notify all observers that buffer has changed
			notifyObserver(buffer.getText(),"screenOut");
			//change selection to last character in buffer
			position[0] += txt.length();
			position[1] = position[0];
			select(position);
		}
	}
	
	/**
	 * Select Function.
	 * Get the position of the selection
	 * @param pos int[] contains start and end position for the selection
	 */
	@Override
	public void select(int[] pos) {
		System.out.println("select");
		//set the selection to the positions we got in the parameters
		int sizeBuffer = this.buffer.getText().length();
		
		//if start is negative, we bring it back to 0
		if (pos[0] <= 0 ) {
			pos[0] = 0;
		}
		
		//if start is longer than the buffer content size, we change it to the buffer content size
		if (pos[0] >= sizeBuffer) {
			pos[0] = sizeBuffer;
		}
		
		//if end position is smaller than start position, we bring it back to the value of the start position
		// example start = 0 and end = 4 -> start = 6 and end = 6
		if (pos[1] <= pos[0]) {
			pos[1] = pos[0];
		}
		
		//if end is longer than the buffer content size, we change it to the buffer content size
		if (pos[1] >= sizeBuffer) {
			pos[1] = sizeBuffer;
		}
		
		selection.setSelection(pos[0], pos[1]);
		//display the result in the selection textarea
		notifyObserver(selection.toString(),"selectArea");
	}
	
	/**
	 * Copy Function.
	 * Get the text selected in the buffer then copy it in the clipboard
	 */
	@Override
	public void copy() {
		// If start < end then copy buffer selection in clipboard
		int[] select = selection.getSelection();
		if (select[0] < select[1]) {
			System.out.println("copy");
			//fetch the text inside the selection
			String copy = buffer.getSelection(select[0], select[1]);
			System.out.println("copy content : "+copy);
			//copy this text in the clipboard
			clipBoard.setContent(copy);
			notifyObserver(copy, "clipBoardArea");
		}
	}

	/**
	 * Cut Function.
	 * Get the text selected in the buffer then copy it in the clipboard
	 * and delete the selected text from the buffer
	 */
	@Override
	public void cut() {
		int[] select = selection.getSelection();
		//check that start < end position
		if (select[0] < select[1]) {
			System.out.println("cut");
			// get the text selected and remove it from the buffer
			String cut = buffer.cutBuffer(select[0], select[1]);
			//copy it in the clipboard
			clipBoard.setContent(cut);
			notifyObserver(cut, "clipBoardArea");
			//Notify all observers that buffer has changed
			notifyObserver(buffer.getText(),"screenOut");
			//change selection to last character in buffer
			//change selection to last character in buffer
			select[1] = select[0];
			select(select);
		}
	}

	/**
	 * Paste Function.
	 * Paste the clipboard at the position selected
	 */
	@Override
	public void paste() {
		//get the text in the clipboard
		String cp = clipBoard.getContent();
		//only if there is text in the clipboard
		if(!cp.equals("")) {
			System.out.println("paste");
			//get the positions of the selection
			int[] select = selection.getSelection();
			//replace the selection with the text in the clipboard
			buffer.pasteSelection(select,cp);
			notifyObserver(buffer.getText(),"screenOut");
			//Change the selection after the text is pasted
			select[0] += cp.length();
			select[1] = select[0];
			select(select);
		}
	}
	
	/**
	 * Get the buffer for the test cases
	 * @return buffer Buffer
	 */
	public Buffer getBuffer() {
		return this.buffer;
	}
	
	/**
	 * Get the clipboard for the test cases
	 * @return clipboard ClipBoard
	 */
	public ClipBoard getClipBoard() {
		return this.clipBoard;
	}
	
	/**
	 * Get the selection for the test cases
	 * @return selection Selection
	 */
	public Selection getSelection() {
		return this.selection;
	}
}