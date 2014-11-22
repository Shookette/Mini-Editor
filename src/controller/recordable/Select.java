/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller.recordable;

import controller.MementoCommand;
import view.HMIInterface;
import model.CoreInterface;

/**
 * Implementation of Recordable Interface to call the select function in the model(core)
 */
public class Select implements Recordable{

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

	@Override
	public MementoCommand getMemento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMemento() {
		// TODO Auto-generated method stub
		
	}

}
