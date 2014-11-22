/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package model;

public class Selection {

	private Position start;
	private Position end;
	
	/**
	 * Selection Constructor
	 * Init Selection with start and end value to zero
	 */
	public Selection () {
		start = new Position(0);
		end = new Position(0);
	}
	
	/**
	 * Selection Constructor
	 * Init Selection with params
	 * @param s int array with start and end position
	 */
	public Selection (int[] s) {
		start = new Position(s[0]);
		end = new Position(s[1]);
	}
	
	/**
	 * Set selection with start and end value
	 * @param start int start position
	 * @param end int end position
	 */
	public void setSelection(int start, int end) {
		this.start.setPosition(start);
		this.end.setPosition(end);
	}
	
	/**
	 * Get selection (start and end position)
	 * @return table int[] with start and end position
	 */
	public int[] getSelection() {
		int[] result = new int[2];
		result[0] = this.start.getPosition();
		result[1] = this.end.getPosition();
		return result;		
	}
	
	/**
	 * Return a string from the position
	 * @return String with info on the positions of the selection
	 */
	public String toString() {
		return "Start : "+start.getPosition()+"\nEnd : "+end.getPosition();
	}
}