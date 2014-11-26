package controller.memento;

import model.CoreInterface;
import controller.MementoCommand;

public class MementoCut implements MementoCommand {

	private CoreInterface c;
	
	public MementoCut(CoreInterface c) {
		this.c = c;
	}

	@Override
	public void execute() {
		c.cut();
	}

}
