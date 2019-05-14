package p2;

import java.awt.FlowLayout;
import javax.swing.JFrame;

public class ViewerWindow {
	
	public ViewerWindow(Viewer viewer, int x, int y) {
		JFrame frame = new JFrame("Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(viewer);
		frame.pack();
		frame.setLocation(x, y);
		frame.setVisible(true);
	}
}
