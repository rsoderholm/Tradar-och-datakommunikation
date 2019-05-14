package p2;

import javax.swing.Icon;

public class MainP1 {
	
	public static void main(String[] args) {
		Buffer<Icon> iconBuffer = new Buffer<Icon>();
		Buffer<IconProducer> producerBuffer = new Buffer<IconProducer>();

		IconManager iconManager = new IconManager(iconBuffer);
		new ViewerWindow(new Viewer(iconManager, 320, 320), 100, 100);
		new ViewerWindow(new Viewer(iconManager, 320, 320), 400, 100);
		iconManager.start();

		Producer producer = new Producer(producerBuffer, iconBuffer);
		producer.start();

		IconProducerManager ipManager = new IconProducerManager(producerBuffer);
		ipManager.addIconProducer(new FileProducer("files/new.txt"));
	}
}
