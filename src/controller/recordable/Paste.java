/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller.recordable;

import controller.MementoCommand;
import controller.Recordable;
import controller.memento.MementoPaste;
import view.HMIInterface;
import model.CoreInterface;
import model.RecorderInterface;

/**
 * Implementation of Recordable Interface to call the paste function in the model(core)
 */
public class Paste implements Recordable{

	private CoreInterface c;
	@SuppressWarnings("unused")
	private HMIInterface h;
	private RecorderInterface r;
	private MementoCommand m;
	
	public Paste(CoreInterface c, HMIInterface h, RecorderInterface r) {
		this.c = c;
		this.h = h;
		this.r = r;
	}
	
	@Override
	public void execute() {
		this.setMemento();
		r.record(this);
		c.paste();
	}

	@Override
	public MementoCommand getMemento() {
		return m;
	}

	@Override
	public void setMemento() {
		this.m = new MementoPaste(c);
	}

}
