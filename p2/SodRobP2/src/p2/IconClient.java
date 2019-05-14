package p2;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

import javax.swing.Icon;

/**
 * IconClient recieves Icon objects form IconServer and
 * sends them forward to the viewer objects.
 * @author robinsoderholm
 *
 */

public class IconClient extends Observable {
	
	/**
	 * Constructor initializes variables
	 * @param ip
	 * @param port
	 */
	public IconClient(String ip, int port) {
		try {
			new ClientThread(ip, port).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ClientThread extends Thread {
		
		private Socket socket;
		private ObjectInputStream inputStream;
		private boolean running = true;
		
		/**
		 * Creates a new socket object with ip and port as parameters.
		 * @param ip
		 * @param port
		 * @throws UnknownHostException
		 * @throws IOException
		 */
		public ClientThread(String ip, int port) throws UnknownHostException, IOException {
			socket = new Socket(ip, port);
			inputStream = new ObjectInputStream(socket.getInputStream());
		}
		
		/**
		 * If obj is an instance of Icon, the client notifies
		 * observers that it's state has changed.
		 */
		@Override
		public void run() {
			while (running) {
				try {
					Object obj = inputStream.readObject();
					if (obj instanceof Icon) {
						setChanged();
						notifyObservers((Icon) obj);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					running = false;
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}