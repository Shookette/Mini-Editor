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
 * Implementation of Recordable Interface to call the insert function in the model(core)
 */
public class Insert implements Recordable{

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
