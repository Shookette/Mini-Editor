/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller.recordable;

import controller.MementoCommand;
import controller.Recordable;
import controller.memento.MementoCut;
import view.HMIInterface;
import model.CoreInterface;
import model.RecorderInterface;

/**
 * Implementation of Recordable Interface to call the cut function in the model(core)
 */
public class Cut implements Recordable{

	private CoreInterface c;
	@SuppressWarnings("unused")
	private HMIInterface h;
	private RecorderInterface r;
	private MementoCommand m;
	
	public Cut(CoreInterface c, HMIInterface h , RecorderInterface r) {
		this.c = c;
		this.h = h;
		this.r = r;
	}
	
	@Override
	public void execute() {
		this.setMemento();
		r.record(this);
		c.cut();
	}

	@Override
	public MementoCommand getMemento() {
		return m;
	}

	@Override
	public void setMemento() {
		this.m = new MementoCut(c);
	}

}
