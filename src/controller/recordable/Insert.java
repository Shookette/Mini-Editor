/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

package controller.recordable;

import controller.MementoCommand;
import controller.Recordable;
import controller.memento.MementoInsert;
import view.HMIInterface;
import model.CoreInterface;
import model.RecorderInterface;

/**
 * Implementation of Recordable Interface to call the insert function in the model(core)
 */
public class Insert implements Recordable{

	private CoreInterface c;
	private HMIInterface h;
	private RecorderInterface r;
	private String txt;
	private MementoCommand m;
	
	public Insert(CoreInterface c, HMIInterface h, RecorderInterface r) {
		this.c = c;
		this.h = h;
		this.r = r;
		
	}
	
	@Override
	public void execute() {
		this.txt = h.getScreenInText();
		h.resetScreenIn();
		this.setMemento();		
		r.record(this);
		c.insert(txt);
	}

	@Override
	public MementoCommand getMemento() {
		return m;
	}

	@Override
	public void setMemento() {
		this.m = new MementoInsert(c, this.txt);
	}

}
