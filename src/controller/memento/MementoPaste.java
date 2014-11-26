package controller.memento;

import model.CoreInterface;
import controller.MementoCommand;

public class MementoPaste implements MementoCommand {

	private CoreInterface c;
	
	public MementoPaste(CoreInterface c) {
		this.c = c;
	}

	@Override
	public void execute() {
		c.paste();
	}

}
