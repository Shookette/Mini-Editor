/**
 * @author Anne-Sophie Balestra
 * @author J�r�my Viallatte
 * 
 * @version 1.0
 */

package view;

import controller.Command;
import observer.Observer;

public interface HMIInterface extends Observer{
	void update(String str, String textAreaName);
	void highlightSelection(int[] pos);
	void resetScreenIn();
	void setCommand(int i, Command insert, String string);
	String getScreenInText();
	int[] getPosition();
}