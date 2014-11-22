/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller;

import view.HMIInterface;
import model.CoreInterface;

/**
 * Implementation of Command Interface to call the insert function in the model(core)
 */
public class Insert implements Command{

	private CoreInterface c;
	private HMIInterface h;
	
	public Insert(CoreInterface c, HMIInterface h) {
		this.c = c;
		this.h = h;
	}
	
	@Override
	public void execute() {
		String txt = h.getScreenInText();
		h.resetScreenIn();
		c.insert(txt);
		
	}

}
