import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;
	public ClientHandler(ObjectOutputStream output, ObjectInputStream input, ServerSocket server, Socket connection) {
		this.output = output;
		this.input = input;
		this.server = server;
		this.connection = connection;
	}
}
