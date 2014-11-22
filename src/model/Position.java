/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package model;

public class Position {

	private int pos;
	
	/**
	 * Position Constructor
	 * Init position with an absolute int param.
	 * 
	 * @param pos int
	 */
	public Position(int pos){
		this.pos = Math.abs(pos);
	}
	
	/**
	 * Set position
	 * @param pos int
	 */
	public void setPosition(int pos) {
		this.pos = Math.abs(pos);
	}
	
	/**
	 * Get position
	 * @return pos int
	 */
	public int getPosition() {
		return this.pos;
	}
}