package p2;

import javax.swing.Icon;

/**
 * @author Rolf Axelsson
 */
public interface IconProducer {
	
	/**
	 * Returns the delay between each Icon.
	 * @return delay
	 */
	public int delay();
	
	/**
	 * Returns the number of times each Icon is shown.
	 * @return times
	 */
	public int times();
	
	/**
	 * Returns the number of Icons in the list.
	 * @return size
	 */
	public int size();
	
	/**
	 * Returns the next Icon. Returns null if next Icon doesn't exist or
	 * the size of the icon list is 0.
	 * @return next icon
	 */
	public Icon nextIcon();
}
