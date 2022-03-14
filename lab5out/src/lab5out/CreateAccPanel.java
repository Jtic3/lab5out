//Joseph Ticer
//Lab5out 
//Dr. Smith
//Software Engineering TR 2:40
package lab5out;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constructor for the initial panel.
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField verifyPassField;
	private JLabel errorLabel;
	// Getter for the text in the username field.
	public String getUsername()
	{
		return usernameField.getText();
	}

	// Getter for the text in the password field.
	public String getPassword()
	{
		return new String(passwordField.getPassword());
	}
	// Getter for the text in the password field.
	public String getVerifyPass()
	{   

		return new String(verifyPassField.getPassword());
	}
	// Setter for the error text.
	public void setError(String error)
	{
		errorLabel.setText(error);
	}
	// Setter for the error text.
	public void setUserNameField(String error)
	{
		usernameField.setText(error);
	}
	//Setter for the error text.
	public void setPassWordField(String error)
	{
		passwordField.setText(error);
	}
	//Setter for the error text.
	public void setVerifyPassWordField(String error)
	{
		verifyPassField.setText(error);
	}
	
	public CreateAccPanel(CreateAccControl cac){
		// Create the controller.
		//InitialControl controller = new InitialControl(container);

		// Create a panel for the labels at the top of the GUI.
		JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		JLabel instructionLabel = new JLabel("Enter your username and password to log in.", JLabel.CENTER);
		labelPanel.add(errorLabel);
		labelPanel.add(instructionLabel);

		// Create a panel for the login information form.
		JPanel createPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		usernameField = new JTextField(10);
		JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		passwordField = new JPasswordField(10);
		JLabel verifyPassLabel = new JLabel("Verify Password:", JLabel.RIGHT);
		verifyPassField = new JPasswordField(10);
		createPanel.add(usernameLabel);
		createPanel.add(usernameField);
		createPanel.add(passwordLabel);
		createPanel.add(passwordField);
		createPanel.add(verifyPassLabel);
		createPanel.add(verifyPassField);

		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(cac);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cac);    
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(labelPanel);
		grid.add(createPanel);
		grid.add(buttonPanel);
		this.add(grid);

	}
}
