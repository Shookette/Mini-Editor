package model;

import controller.Recordable;

public interface RecorderInterface {
	public void record(Recordable c);
	public void replay();
	public void start();
	public void stop();
}
