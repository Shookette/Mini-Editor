/**
 * @author Anne-Sophie Balestra
 * @author J�r�my Viallatte
 * 
 * @version 1.0
 */

package controller.recordable;

import controller.MementoCommand;
import controller.Recordable;
import controller.memento.MementoSelect;
import view.HMIInterface;
import model.CoreInterface;
import model.RecorderInterface;

/**
 * Implementation of Recordable Interface to call the select function in the model(core)
 */
public class Select implements Recordable{

	private CoreInterface c;
	private HMIInterface h;
	private RecorderInterface r;
	private MementoCommand m;
	private int[] pos;
	
	public Select(CoreInterface c, HMIInterface h, RecorderInterface r) {
		this.c = c;
		this.h = h;
		this.r = r;
	}
	
	@Override
	public void execute() {
		//get Position of screenOut
		this.pos = h.getPosition();
		h.highlightSelection(pos);
		this.setMemento();
		r.record(this);
		c.select(pos);
	}

	@Override
	public MementoCommand getMemento() {
		return m;
	}

	@Override
	public void setMemento() {
		this.m = new MementoSelect(c, pos);
	}

}
