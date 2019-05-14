package p2;

import javax.swing.Icon;

/**
 * @author Robin Söderholm
 */
public class Producer {
	
	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;
	private Thread bufferTransfer;
	
	/**
	 * Transfers Icon objects from the IconProducers in the given IconProducer buffer to
	 * the given Icon buffer.
	 * @param prodBuffer
	 * @param iconBuffer
	 */
	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer) {
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;
	}
	
	/**
	 * Starts the thread.
	 */
	public void start() {
		if (bufferTransfer == null) {
			bufferTransfer = new BufferTransfer();
			bufferTransfer.start();
		}
	}
	
	/**
	 * Stops the thread.
	 */
	public void stop() {
		if (bufferTransfer != null)
			bufferTransfer.interrupt();
	}
	
	private class BufferTransfer extends Thread {
		
		@Override
		public void run() {
			try {
				IconProducer iconProducer = prodBuffer.get();
				while (true) {
					for (int i = 0; i < iconProducer.times(); i++) {
						for (int j = 0; j < iconProducer.size(); j++) {
							iconBuffer.put(iconProducer.nextIcon());
							Thread.sleep(iconProducer.delay());
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}