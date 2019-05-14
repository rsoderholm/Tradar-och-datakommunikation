package p1;

/**
 * Adds an iconproducer object to the object inside IconProducerManager
 * 
 * @author robinsoderholm
 *
 */

public class IconProducerManager {
	Buffer<IconProducer> producerBuffer;

	public IconProducerManager(Buffer<IconProducer> producerBuffer) {
		this.producerBuffer = producerBuffer;
	}

	/**
	 * Objektet får tillgång till en instans av en klass vilken implementerar
	 * iconProducer Detta objekt placeras i en buffert av typen Buffer.
	 * 
	 * @param producer
	 */
	public void addIconProducer(IconProducer producer) {
		producerBuffer.put(producer);
	}

}
