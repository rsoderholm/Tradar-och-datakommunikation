package p2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Viewer extends JPanel {

	private JLabel lblIcon = new JLabel();

	public Viewer(int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width, height));
	}
	
	public Viewer(IconClient iconClient, int width, int height) {
		this(width, height);
		iconClient.addObserver(new IconObserver());
	}

	public Viewer(IconManager iconManager, int width, int height) {
		this(width, height);
		iconManager.addObserver(new IconObserver());
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}
	
	private class IconObserver implements Observer {

		@Override
		public void update(Observable obs, Object obj) {
			setIcon((Icon) obj);
		}
	}
}
