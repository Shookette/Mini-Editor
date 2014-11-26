package controller.memento;

import model.CoreInterface;
import controller.MementoCommand;

public class MementoCopy implements MementoCommand {

	private CoreInterface c;
	
	public MementoCopy(CoreInterface c) {
		this.c = c;
	}

	@Override
	public void execute() {
		c.copy();
	}

}
