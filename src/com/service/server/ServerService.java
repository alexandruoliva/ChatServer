package com.service.server;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.client.gui.ClientGui;
import com.client.observer.Observer;
import com.client.observer.Subject;
import com.client.service.ClientService;
import com.server.clienthandler.ClientHandler;
import com.server.gui.ServerGui;

public class ServerService implements Observer {
	
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket serverSocket;
	private Socket socket;
	private static final int PORT = 6789;
	
	
	static ServerGui serverGui;
	
	public String message ="";
	public ServerService(ServerGui serverGui){
		this.serverGui=serverGui;
	}
	
	public ServerService(){
		
	}
	
	// set up and run server
		public void startRunning() {
			serverSocket =null;
			socket=null;
			
			try {
				// 6789 port number
				while (true) {
					

					try {
						serverSocket =new ServerSocket(PORT,100);
						waitForConnection();

						setupStreams();
						
						whileChatting();
					} catch (EOFException eofException) {
						showMessage("\n Server ended the connection!");
					} finally {
						closeChat();
					}
//					Thread t= new ClientHandler(output, input, socket);
//					t.start();
					
					
//					ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatClient/download.png");
//
//					ClientGui gui = new ClientGui();
//					ClientService t = new ClientService("127.0.01",gui,socket, output, input);
//					gui.addObserver(t);
//					
//					gui.setSize(700, 700);
//					gui.setVisible(true);
//					gui.buildFrame(icon, gui, new JFrame(), "Client Chat");
//					
//					t.startRunning();
				
				
					
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		// wait for connection, the display connection information
		private void waitForConnection() throws IOException {
			int counter =0;
			showMessage("Waiting for someone to connect....\n");

			// listens to the connection and accepts it
			socket = serverSocket.accept();
			
			counter ++;
			showMessage("Client number "+counter +" has connected \n");
			
			showMessage("Now connected to " + socket.getInetAddress().getHostName());
			
		
		}

		// get stream to send and receive data
		private void setupStreams() throws IOException {
			
			
			// creating the pathway that allows us to connect to another computer
			output = new ObjectOutputStream(socket.getOutputStream());
			// flushes the data bytes that are leftover into the stream
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
			showMessage("\n Streams are now setup! \n");
			
		
			

		}

		// during the chat conversation
		private void whileChatting() throws IOException {
			String message = "You are now connected ! ";
//			sendMessage(message);
			ableToType(true);
			do {

				try {
					message = (String) input.readObject();
					showMessage("\n" + message);
				} catch (ClassNotFoundException classNotFoundException) {
					showMessage("\n idk what that user sent ");
				}
			} while (!message.equals("CLIENT - END"));// if the users type END the
		}

		// close streams and sockets after you are done chatting
		private void closeChat() {
			showMessage("\n Closing connection...");
			ableToType(false);
			try {
				output.close();
				input.close();
				socket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}

		}

		// send a message to client
		private void sendMessage(ServerGui serverGui) {
			try {
				message=serverGui.getInputTextTab().getText();
				
				output.writeObject("SERVER - " + message);
				output.flush();
				showMessage("\nSERVER -" + message);
			} catch (IOException ioException) {
				serverGui.getOutputTextTab().append("\n ERROR: CANT'T SEND THAT MESSAGE");
			}
			serverGui.getInputTextTab().setText(null);
			
		}

		// updates chatWindow
		private void showMessage(final String text) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					serverGui.getOutputTextTab().append(text);
				}
			});
		}
		private void showMessage(final String text,int counter) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					serverGui.getOutputTextTab().append(text);
				}
			});
		}

		// let the user type stuff into their box
		private void ableToType(final boolean trueOrFalse) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					serverGui.getInputTextTab().setText("");
					serverGui.getInputTextTab().setEditable(trueOrFalse);
				}
			});
		}

		@Override
		public void update(Subject subject) {
			sendMessage(serverGui);
		}

}
