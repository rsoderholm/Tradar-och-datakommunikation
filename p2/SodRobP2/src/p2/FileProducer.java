package p2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class FileProducer implements IconProducer {
	
	private FileReader fileReader; 
	private BufferedReader reader;
	private int delay = 0;
	private int times = 0;
	private int size = 0;
	private int currentIndex = 0;
	private ArrayList<Icon> iconList = new ArrayList<Icon>();
	
	public FileProducer(String filename) {
		try {
			fileReader = new FileReader(filename);
			reader = new BufferedReader(fileReader);
			times = Integer.parseInt(reader.readLine());
			delay = Integer.parseInt(reader.readLine());
			String line = reader.readLine();
			while (line != null) {
				iconList.add(new ImageIcon(line)); 
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace(); 
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
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
		return iconList.size();
	}
	
	@Override
	public Icon nextIcon() {
		if (currentIndex == size()) 
			currentIndex = 0;
		if (size() == 0 || iconList == null)
			return null;
		return iconList.get(currentIndex++);
	}
}