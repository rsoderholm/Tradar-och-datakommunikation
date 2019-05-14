package p2;

import java.util.Observable;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * @author Robin Söderholm
 */
public class IconManager extends Observable {

	private Buffer<Icon> iconBuffer;
	private Thread updateIcon;

	/**
	 * Updates the Viewers with icons.
	 * 
	 * @param iconBuffer
	 */
	public IconManager(Buffer<Icon> iconBuffer) {
		this.iconBuffer = iconBuffer;
	}

	/**
	 * Starts the thread.
	 */
	public void start() {
		if (updateIcon == null) {
			updateIcon = new UpdateIcon();
			updateIcon.start();
		}
	}

	/**
	 * Stops the thread.
	 */
	public void stop() {
		if (updateIcon != null) {
			updateIcon.interrupt();
			updateIcon = null;
		}
	}

	private class UpdateIcon extends Thread {

		@Override
		public void run() {
			Icon icon = new ImageIcon();
			try {
				icon = iconBuffer.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (icon != null) {
				setChanged();
				notifyObservers(icon);
				try {
					icon = iconBuffer.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}