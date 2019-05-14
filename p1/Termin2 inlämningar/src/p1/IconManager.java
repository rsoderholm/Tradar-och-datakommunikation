package p1;

import java.util.Observable;

import javax.swing.Icon;

/**
 * IconManager-objektet hämtar Icon-implementeringar ur bufferten och skickar
 * dessa till viewerobjekten. Använder mig av observer/observable för att
 * åstadkomma detta.
 * 
 * @author robinsoderholm
 *
 */

public class IconManager extends Observable {
	private Buffer<Icon> iconBuffer;
	private Icon icon;
	private Activity thread;

	public IconManager(Buffer<Icon> iconBuffer) {
		this.iconBuffer = iconBuffer;

	}

	/**
	 * Om tråden är tom så exekvera.
	 */
	public void start() {
		if (thread == null) {
			thread = new Activity();
			thread.start();
		}
	}

	/**
	 * Returnerar icon.
	 * 
	 * @return
	 */
	public Icon getIcon() {
		return icon;
	}

	/**
	 * Notifierar observers om att tillståndet har förändrats sålänge som
	 * villkoret är sant.
	 * 
	 * @author robinsoderholm
	 *
	 */
	private class Activity extends Thread {
		public void run() {
			while (true) {
				try {
					icon = iconBuffer.get();
					setChanged();
					notifyObservers();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}