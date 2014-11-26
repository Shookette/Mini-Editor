package model;

import java.util.ArrayList;
import java.util.Iterator;
import controller.MementoCommand;
import controller.Recordable;

public class Recorder implements RecorderInterface {

	private boolean recording;
	private ArrayList<MementoCommand> listMemento;
	
	public Recorder() {
		this.listMemento = new ArrayList<MementoCommand>();
	}
	
	public Recorder(ArrayList<MementoCommand> list) {
		this.listMemento = list; 
	}
	
	public void record(Recordable c) {
		if (this.recording) {
			// add result of c.getMemento in Collection
			listMemento.add(c.getMemento());
		}
	}
	
	public void replay() {
		//Play the memento collection
		if (!recording && !listMemento.isEmpty()) {
			Iterator<MementoCommand> it = listMemento.iterator();
			int i = 0;
			while (it.hasNext() && i < listMemento.size()) {
				MementoCommand m = it.next();
				m.execute();
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}	
		}
		
	}
	
	public void start() {
		this.recording = true;
	}
	
	public void stop() {
		this.recording = false;
	}
	
}
