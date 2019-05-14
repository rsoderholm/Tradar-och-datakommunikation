package p1;

import javax.swing.Icon;

/**
 * Hämtar IconProduceimplementaringar från Buffer och lägger in dem i en annan
 * buffert som Iconimplementeringar.
 * 
 * Metoden delay ger pausen, size anger hur många Icon-objekt det finns i
 * sekvensen. NextIcon returnerar Iconimplementeringarna en i taget. När
 * sekvensen är slut så returneras det första elementet igen.
 * 
 * @author robinsoderholm
 *
 */

public class Producer {
	private Activity thread;
	private Buffer<Icon> iconBuffer;
	private Buffer<IconProducer> prodBuffer;

	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer) {
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;

	}

	/**
	 * Startar tråden om den är null.
	 */
	public void start() {
		if (thread == null) {
			thread = new Activity();
			thread.start();
		}
	}

	private class Activity extends Thread {
		private int index = 0;
		private IconProducer ip;
		private int delay;
		private int size;
		private int times;
		private int prodBufferSize;

		public void run() {

			try {

				ip = prodBuffer.get();
				delay = ip.delay();
				size = ip.size();
				times = ip.times();

				for (int i = 0; i < times; i++) {
					for (int j = 0; j < size; j++) {
						iconBuffer.put(ip.nextIcon());
						try {
							Thread.sleep(delay);
						} catch (InterruptedException e) {
						}
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}