/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package model;

public class Buffer {

	private StringBuffer text;
	
	/**
	 * Buffer constructor
	 * init buffer with a StringBuffer
	 */
	public Buffer() {
		text = new StringBuffer();
	}
	
	/**
	 * Buffer constructor
	 * init buffer with a String params and convert it in StringBuffer
	 * @param s String content of buffer
	 */
	public Buffer(String s) {
		text = new StringBuffer(s);
	}
	
	/**
	 * Function used into insert function
	 * append (insert in the end) screenIn text in buffer
	 * @param text String
	 */
	public void insert(String text,int[] pos) {
		this.text.replace(pos[0], pos[1], text);
	}
	
	/**
	 * Get text in String from the StringBuffer
	 * @return text String in the buffer
	 */
	public String getText() {
		return this.text.toString();
	}
	
	/**
	 * Return string selected from start to end
	 * @param start int start of the selection
	 * @param end int end of the selection
	 * @return String selection
	 */
	public String getSelection(int start, int end) {
		return text.substring(start, end);
	}
	
	/**
	 * Return string selected from start to end and delete the selection afterwards
	 * @param start int
	 * @param end int
	 * @return selection String
	 */
	public String cutBuffer(int start, int end) {
		String result = getSelection(start, end);
		text.delete(start, end);
		return result;
	}
	
	/**
	 * Paste the clipboard in buffer from start position to end position
	 * @param pos int[] contains start and end position of selection
	 * @param clipBoard String contains content of the clipboard
	 */
	public void pasteSelection(int[] pos, String clipBoard) {
		text.replace(pos[0], pos[1], clipBoard);
	}
}
