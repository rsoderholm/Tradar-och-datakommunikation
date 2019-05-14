package p2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;

/**
 * Server that recieves Icon-objects from IconManager and sends them
 * forward to IconClient
 * @author robinsoderholm
 *
 */
public class IconServer {
	
	public IconServer(IconManager iconManager, int port) {
		new ClientConnection(iconManager, port).start();
	}
	
	private class ClientConnection extends Thread {
		
		private int port;
		private Socket socket;
		private boolean running = true;
		private IconManager iconManager;
		
		public ClientConnection(IconManager iconManager, int port) {
			this.iconManager = iconManager;
			this.port = port;
		}
		
		@Override
		public void run() {
			System.out.println("Server started");
			while (running) {
				try (ServerSocket serverSocket = new ServerSocket(port)) {
					socket = serverSocket.accept();
					ServerThread thread = new ServerThread(socket);
					iconManager.addObserver(thread);
					System.out.println("New Client connected");
				} catch (IOException e) {
					e.printStackTrace();
					try {
						running = false;
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * ServerThread listens after clients. If found, it creates a socket
	 * and starts sending objects.
	 * 
	 * @author robinsoderholm
	 *
	 */
	private class ServerThread implements Observer {

		private Socket socket;
		private ObjectOutputStream outputStream;
		
		public ServerThread(Socket socket) throws IOException {
			this.socket = socket;
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		}

		/**
		 * If obj is an instance of Icon, Icon Objects get sent through an outputstream.
		 */
		@Override
		public void update(Observable o, Object obj) {
			try {
				if (obj instanceof Icon) {
					outputStream.writeObject((Icon) obj);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}