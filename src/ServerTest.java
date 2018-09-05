import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ServerTest {
	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("C://Users/aoliva/Desktop/JAVA WORKSPACE/ChatServer/download.png");

		ServerGuiAndMain serverApp = new ServerGuiAndMain();

		serverApp.setSize(700, 700);
		serverApp.setVisible(true);

		JFrame frame = new JFrame();

		serverApp.buildFrame(icon, serverApp, frame, "Server");
		
		serverApp.startRunning();
		
		
	}
}
