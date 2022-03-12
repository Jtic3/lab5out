package lab5out;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContactPanel extends JPanel {
	private JTextArea contacts;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContactPanel(){
		// Create the controller.
		//InitialControl controller = new InitialControl(container);

		// Create the information label.
		// Create a panel for the labels at the top of the GUI.    
		JLabel contactLabel = new JLabel("Contacts", JLabel.CENTER);
		JPanel center = new JPanel();
		BoxLayout layout = new BoxLayout(center, BoxLayout.Y_AXIS);
		center.setLayout(layout);
		
		contacts = new JTextArea(10, 30);
		JScrollPane scrollPane = new JScrollPane(contacts); 
		contacts.setEditable(false);
		JPanel contactPanel = new JPanel();
		contactPanel.add(scrollPane);
		center.add(contactLabel, BorderLayout.CENTER);
		center.add(contactPanel);
		
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel logOutPanel = new JPanel(new FlowLayout());
		// Create the buttons.
		JButton delete = new JButton("Delete");
		JButton add = new JButton("Add");
		JButton logOut = new JButton("Log Out");
		buttonPanel.add(delete);
		buttonPanel.add(add);
		logOutPanel.add(logOut);
		center.add(buttonPanel);
		center.add(logOutPanel);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 0, 0));
		grid.add(center);		
		this.add(grid);

	}

}
