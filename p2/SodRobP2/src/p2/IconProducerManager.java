package p2;

/**
 * @author Robin Söderholm
 */
public class IconProducerManager {
	
	private Buffer<IconProducer> buffer;
	
	/**
	 * Manages the IconProducers.
	 * @param buffer
	 */
	public IconProducerManager(Buffer<IconProducer> buffer) {
		this.buffer = buffer;
	}
	
	/**
	 * @param iconProducer - add an IconProducer to the buffer
	 */
	public void addIconProducer(IconProducer iconProducer) {
		buffer.put(iconProducer);
	}
}