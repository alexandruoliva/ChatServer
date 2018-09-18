import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler extends Thread {
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private Socket socket;

	public ClientHandler(ObjectOutputStream output, ObjectInputStream input, Socket socket) throws IOException {
//		this.output = new ObjectOutputStream(socket.getOutputStream());
		this.output=output;
//		this.input = new ObjectInputStream(socket.getInputStream());
		this.input=input;
		this.socket = socket;
	}
	
}
