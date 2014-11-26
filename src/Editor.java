/**
 * @author Anne-Sophie Balestra
 * @author Jérémy Viallatte
 * 
 * @version 1.0
 */

import controller.*;
import controller.command.Replay;
import controller.command.Start;
import controller.command.Stop;
import controller.recordable.Paste;
import controller.recordable.Copy;
import controller.recordable.Cut;
import controller.recordable.Insert;
import controller.recordable.Select;
import view.HMI;
import view.HMIInterface;
import model.Core;
import model.CoreInterface;
import model.Recorder;
import model.RecorderInterface;

public class Editor {

	/**
	 * Launches the editor
	 * @param args
	 */
	public static void main(String[] args) {
		//Declare and initialize model
		CoreInterface core = new Core();
		RecorderInterface recorder = new Recorder();
		
		//Create view with controller
		HMIInterface hmi = new HMI();
		
		//Create controller with the model
		Command insert = new Insert(core, hmi, recorder);
		Command select = new Select(core, hmi, recorder);
		Command copy = new Copy(core, hmi, recorder);
		Command cut = new Cut(core, hmi, recorder);
		Command paste = new Paste(core, hmi, recorder);
		//Recording commands
		Command start = new Start(core, hmi, recorder);
		Command stop = new Stop(core, hmi, recorder);
		Command replay = new Replay(core, hmi, recorder);
		
		//Bind command in hmi
		hmi.setCommand(0, insert, "Insert");
		hmi.setCommand(1, select, "Select");
		hmi.setCommand(2, copy, "Copy");
		hmi.setCommand(3, cut, "Cut");
		hmi.setCommand(4, paste, "Paste");
		hmi.setCommand(5, start, "Start");
		hmi.setCommand(6, stop, "Stop");
		hmi.setCommand(7, replay, "Replay");
		
		//Add view as observer of model
		core.addObserver(hmi);		
	}
}