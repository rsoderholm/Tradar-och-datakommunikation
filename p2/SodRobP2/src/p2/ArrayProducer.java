package p2;

import javax.swing.Icon;

/**
 * @author Rolf Axelsson
 */
public class ArrayProducer implements IconProducer {

	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;

	/**
	 * Creates a producer for generating Icons using a pre-defined array of Icons.
	 * Implementations using this class can use the given delay and the given <b>times</b>.
	 * <b>times</b> indicates the number of times to iterate through the Icon array.
	 * @param icons - the Icon array
	 * @param delay - delay between each update
	 * @param times - number of iterations
	 */
	public ArrayProducer(Icon[] icons, int delay, int times) {
		this.delay = delay;
		this.times = times;
		this.icons = icons;
	}

	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return times;
	}

	@Override
	public int size() {
		return (icons == null) ? 0 : icons.length;
	}

	@Override
	public Icon nextIcon() {
		if (icons == null || icons.length == 0)
			return null;
		currentIndex = (currentIndex + 1) % icons.length;
		return icons[currentIndex];
	}

}
