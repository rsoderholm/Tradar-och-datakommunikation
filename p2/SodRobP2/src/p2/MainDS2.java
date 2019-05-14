package p2;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainDS2 {
	
    private static Icon[] getIconArray() {
		Icon[] res = {new ImageIcon("files/new1.jpg"),
				new ImageIcon("files/new2.jpg"),
				new ImageIcon("files/new3.jpg"),
				new ImageIcon("files/new4.jpg"),
				new ImageIcon("files/new5.jpg"),
				new ImageIcon("files/new6.jpg"),
				new ImageIcon("files/new7.jpg"),
				new ImageIcon("files/new8.jpg"),
				new ImageIcon("files/new9.jpg"),
				new ImageIcon("files/new10.jpg")};
        return res;
    }
    
	public static void main(String[] args) {
		Buffer<IconProducer> producerBuffer	= new Buffer<IconProducer>();
		Buffer<Icon> iconBuffer	= new Buffer<Icon>();
				
		IconProducerManager ipManager = new IconProducerManager(producerBuffer);		
        ipManager.addIconProducer(new ArrayProducer(getIconArray(),1000,2));
		
		Producer producer = new Producer(producerBuffer,iconBuffer);
		producer.start();
		
		TestDS2 testDs2 = new TestDS2(iconBuffer);
		testDs2.start();
	}
}
