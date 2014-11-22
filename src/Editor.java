/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

import controller.*;
import controller.recordable.Paste;
import controller.recordable.Copy;
import controller.recordable.Cut;
import controller.recordable.Insert;
import controller.recordable.Select;
import view.HMI;
import view.HMIInterface;
import model.Core;
import model.CoreInterface;

public class Editor {

	/**
	 * Launches the editor
	 * @param args
	 */
	public static void main(String[] args) {
		//Declare and initialize model
		CoreInterface core = new Core();
		
		//Create view with controller
		HMIInterface hmi = new HMI();
		
		//Create controller with the model
		Command insert = new Insert(core,hmi);
		Command select = new Select(core,hmi);
		Command copy = new Copy(core,hmi);
		Command cut = new Cut(core,hmi);
		Command paste = new Paste(core,hmi);
		
		//Bind command in hmi
		hmi.setCommand(0, insert, "Insert");
		hmi.setCommand(1, select, "Select");
		hmi.setCommand(2, copy, "Copy");
		hmi.setCommand(3, cut, "Cut");
		hmi.setCommand(4, paste, "Paste");
		
		//Add view as observer of model
		core.addObserver(hmi);		
	}
}