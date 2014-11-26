package controller.memento;

import model.CoreInterface;
import controller.MementoCommand;


public class MementoInsert implements MementoCommand {

	//Variables
	private String text;
	private CoreInterface c;

	/**
	 * MementonInsert constructor
	 * Init text with empty string
	 * Init c with coreInterface
	 * @param txt 
	 */
	public MementoInsert(CoreInterface c, String txt) {
		this.text = txt;
		this.c = c;
	}
	
	/**
	 * Execute insert with memento
	 */
	public void execute() {
		c.insert(this.text);
	}
	
}
