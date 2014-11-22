package controller.recordable;

import controller.Command;
import controller.MementoCommand;


public interface Recordable extends Command{
	public MementoCommand getMemento();
	public void setMemento();
}
