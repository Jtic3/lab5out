package lab5out;

import java.awt.GridLayout;

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
		JLabel label = new JLabel("Account Information", JLabel.CENTER);

		contacts = new JTextArea(5, 20);
		JScrollPane scrollPane = new JScrollPane(contacts); 
		contacts.setEditable(false);
		JPanel contactBuffer = new JPanel();
		contactBuffer.add(scrollPane);
		
		// Create the login button.
		JButton delete = new JButton("Delete");

		JPanel deleteButtonBuffer = new JPanel();
		deleteButtonBuffer.add(delete);

		// Create the create account button.
		JButton add = new JButton("Add");

		JPanel addButtonBuffer = new JPanel();
		addButtonBuffer.add(add);

		// Create the create account button.
		JButton logOut = new JButton("Log Out");

		JPanel logOutButtonBuffer = new JPanel();
		logOutButtonBuffer.add(logOut);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5));
		grid.add(label);
		grid.add(deleteButtonBuffer);
		grid.add(addButtonBuffer);
		grid.add(logOutButtonBuffer);
		this.add(grid);

	}

}
