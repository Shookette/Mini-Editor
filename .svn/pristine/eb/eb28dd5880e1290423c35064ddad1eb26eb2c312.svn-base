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
 * Implementation of Command Interface to call the paste function in the model(core)
 */
public class Paste implements Command{

	private CoreInterface c;
	@SuppressWarnings("unused")
	private HMIInterface h;
	
	public Paste(CoreInterface c, HMIInterface h) {
		this.c = c;
		this.h = h;
	}
	
	@Override
	public void execute() {
		c.paste();
	}

}
