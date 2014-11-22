/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller;

import view.HMIInterface;
import model.CoreInterface;

public class Select implements Command{

	private CoreInterface c;
	private HMIInterface h;
	
	public Select(CoreInterface c, HMIInterface h) {
		this.c = c;
		this.h = h;
	}
	
	@Override
	public void execute() {
		//get Position of screenOut
		int[] pos = h.getPosition();
		h.highlightSelection(pos);
		c.select(pos);
	}

}
