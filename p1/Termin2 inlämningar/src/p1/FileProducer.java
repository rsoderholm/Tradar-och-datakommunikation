package p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 2.1 Delsystem 1. Ger samma utskrift som IconProducerManager men snabbare.
 * 
 * @author robinsoderholm
 *
 */

public class FileProducer implements IconProducer {
	private ArrayList<Icon> list = new ArrayList<Icon>();
	private int delay = 0;
	private int times = 0;
	private int currentIcon = -1;

	public FileProducer(String filename) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
			times = Integer.parseInt(br.readLine());
			delay = Integer.parseInt(br.readLine());
			String str = br.readLine();
			while (str != null) {
				addIcon(str);
				str = br.readLine();
			}
		} catch (IOException e) {
		}
	}

	/**
	 * Skapar en ImageIcon från den specificerade filen.
	 * 
	 * @param filename
	 */
	private void addIcon(String filename) {
		Icon icon = new ImageIcon(filename);
		if 	(icon != null) {
			list.add(icon);
		}
	}

	/**
	 * Returnerar delay
	 */
	public int delay() {
		return delay;
	}

	/**
	 * Returnerar times.
	 * 
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * Returnerar size
	 */
	@Override
	public int size() {
		return list.size();
	}

	/**
	 * 
	 */
	@Override
	public Icon nextIcon() {
		if (list.size() == 0)
			return null;
		currentIcon = (currentIcon + 1) % list.size();
		return list.get(currentIcon);
	}

}
