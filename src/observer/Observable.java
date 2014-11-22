/**
 * @author Anne-Sophie Balestra
 * @author J�r�my Viallatte
 * 
 * @version 1.0
 */

package observer;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(String str, String textAreaName);
}