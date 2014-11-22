/**
 * @author Anne-Sophie Balestra
 * @author J�r�my Viallatte
 * 
 * @version 1.0
 */

package model;

public class ClipBoard {
	
	private String content;
	
	/**
	 * clipboard constructor.
	 * Init content to null;
	 */
	public ClipBoard() {
		content = "";
	}
	
	/**
	 * ClipBoard constructor
	 * Init content with params
	 * @param s String with clipboard content
	 */
	public ClipBoard(String s) {
		content = s;
	}
	
	/**
	 * Set clipBoard content
	 * @param text String content
	 */
	public void setContent(String text) {
		content = text;
	}
	
	/**
	 * Get clipBoard content
	 * @return content String
	 */
	public String getContent() {
		return this.content;
	}
}
