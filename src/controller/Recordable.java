package controller;



public interface Recordable extends Command{
	public MementoCommand getMemento();
	public void setMemento();
}
