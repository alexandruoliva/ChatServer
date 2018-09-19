package com.server.main;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.server.gui.ServerGui;
import com.service.server.ServerService;

public class ServerMain {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatServer/download.png");

		ServerGui serverGui = new ServerGui();
		ServerService serverService = new ServerService(serverGui);
		
		
		serverGui.addObserver(serverService);
		serverGui.setSize(700, 700);
		serverGui.setVisible(true);

		JFrame frame = new JFrame();

		serverGui.buildFrame(icon, serverGui, frame, "Server");

		serverService.startRunning();

	}
}
