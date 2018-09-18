
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.client.observer.Observer;
import com.client.observer.Subject;



public class ServerGui extends JPanel  implements Subject{
	JPanel chatClient ;
	JTextField inputTextTab ;
	JTextArea outputTextTab ;
	GridBagConstraints gridBagCon ;
	
	List<Observer> observers;
	
	
	
	public JTextField getInputTextTab() {
		return inputTextTab;
	}

	
	public void setInputTextTab(JTextField inputTextTab) {
		this.inputTextTab = inputTextTab;
	}	
	

	public JTextArea getOutputTextTab() {
		return outputTextTab;
	}


	public void setOutputTextTab(JTextArea outputTextTab) {
		this.outputTextTab = outputTextTab;
	}


	public ServerGui() {
		
		observers = new ArrayList<>();
		initGuiElements();
		

		inputTextTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// getActionCommand sends the message;
//				sendMessage(event.getActionCommand());
				outputTextTab.setText("");

			}
		});

	}


	private void initGuiElements() {
		chatClient = new JPanel();
		 inputTextTab = new JTextField("server input message",50);
		 outputTextTab = new JTextArea(5, 5);
		gridBagCon = new GridBagConstraints();
		
		gridBagCon.weightx = 0.5;
		gridBagCon.weighty = 1.0;
		gridBagCon.fill = GridBagConstraints.BOTH;

		setLayout(new GridBagLayout());
		gridBagCon.insets = new Insets(2, 2, 2, 2);

		outputTextTab.setFont(outputTextTab.getFont().deriveFont(20f));
		outputTextTab.setEditable(false);

		gridBagCon.gridx = 0;
		gridBagCon.gridy = 0;
		add(outputTextTab, gridBagCon);
		add(new JScrollPane(outputTextTab),gridBagCon);


		gridBagCon.gridx = 0;
		gridBagCon.gridy = 1;
		// we don't want to let the users to type while are disconnected
		inputTextTab.setEditable(false);
		add(inputTextTab, gridBagCon);
	}

	
	public void buildFrame(ImageIcon icon, ServerGui object, JFrame frame, String titleFrame) {
		frame.setTitle(titleFrame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setIconImage(icon.getImage());
		frame.add(object);
		frame.pack();
		
	}


	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
		
	}


	@Override
	public void deleteObserver(Observer deletedObserver) {
		int indexOfList = observers.indexOf(deletedObserver);
		System.out.println("Observer " + (indexOfList + 1) + "deleted");
		observers.remove(indexOfList);
	}


	@Override
	public void notifyObservers() {
		System.out.println("notifying all services , when somethign changes in the client GUI ");
		for (Observer observer : observers) {
			observer.update(this);
		}
		
	}




}
