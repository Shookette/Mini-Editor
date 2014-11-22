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
 * Implementation of Recordable Interface to call the cut function in the model(core)
 */
public class Cut implements Recordable{

	private CoreInterface c;
	@SuppressWarnings("unused")
	private HMIInterface h;
	
	public Cut(CoreInterface c, HMIInterface h) {
		this.c = c;
		this.h = h;
	}
	
	@Override
	public void execute() {
		c.cut();
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
