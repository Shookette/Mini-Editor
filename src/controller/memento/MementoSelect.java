package controller.memento;

import model.CoreInterface;
import controller.MementoCommand;

public class MementoSelect implements MementoCommand{

	private CoreInterface c;
	private int[] pos;
	
	public MementoSelect(CoreInterface c, int[] pos) {
		this.c = c;
		this.pos = pos;
	}
	
	@Override
	public void execute() {
		c.select(this.pos);
	}

}
