package lab5out;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

public class CreateAccControl implements ActionListener {

	// Private data field for storing the container.
	private final int PASS_LENGTH = 6;
	private JPanel container;
	private ChatClient client;
	// Constructor for the initial controller.
	public CreateAccControl(JPanel container, ChatClient client)
	{
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();
		// The Cancel button takes the user to the login page.
		if (command.equals("Cancel"))
		{
			//Handle Login Here
			LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
			loginPanel.setError("");
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "2");
		}
		// The Submit button takes the user to the login panel.
		else if (command.equals("Submit"))
		{
			// Get the username and password the user entered.
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			CreateAccData data = new CreateAccData(
					createPanel.getUsername(), 
					createPanel.getPassword(),
					createPanel.getVerifyPass());

			// Check the validity of the information locally first.
			System.out.println(data.getUsername()+" "+data.getPassword()+" "+ data.getVerifyPass());
			if (data.getUsername().equals("") || data.getPassword().equals("")|| data.getVerifyPass().equals(""))
			{
				displayError("CREATE ERROR 3: NO INFORMATION ENTERED");
				return;
			}
			else if(data.getPassword().length()<PASS_LENGTH) {
				displayError("CREATE ERROR 4: PASSWORD LENGTH REQ NOT MET");
				return;
			}
			// Submit the login information to the server.
			try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}
	// After the login is successful, set the User object and display the contacts screen. - this method would be invoked by 
	//the ChatClient
	public void createSuccess(String msg)
	{

		if(msg.equals("CREATE SUCCESS")) { 
			CardLayout cardLayout = (CardLayout)container.getLayout();
			LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
			loginPanel.setUserNameField("");
			loginPanel.setPassWordField("");
			loginPanel.setError("");
			cardLayout.show(container, "2");			
		}

	}
	// Method that displays a message in the error - could be invoked by ChatClient or by this class (see above)
	public void displayError(String error)
	{
		if(error.equals("CREATE ERROR 1: USERNAME SELECTED")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			createPanel.setError("Username is already selected.");
			createPanel.setUserNameField("");
			createPanel.setPassWordField("");
			createPanel.setVerifyPassWordField("");
			cardLayout.show(container, "3");
			System.out.println(error);
		}
		else if(error.equals("CREATE ERROR 2: PASSWORDS DON'T MATCH")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			createPanel.setError("Passwords do not match.");
			createPanel.setUserNameField("");
			createPanel.setPassWordField("");
			createPanel.setVerifyPassWordField("");
			cardLayout.show(container, "3");
			System.out.println(error);
		}
		else if(error.equals("CREATE ERROR 3: NO INFORMATION ENTERED")) {
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			createPanel.setError("You must enter a username and two entities of your password.");
			createPanel.setUserNameField("");
			createPanel.setPassWordField("");
			createPanel.setVerifyPassWordField("");
		}
		else if(error.equals("CREATE ERROR 4: PASSWORD LENGTH REQ NOT MET")) {
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			createPanel.setError("You must enter a password thats atleast 6 characters long.");
			createPanel.setUserNameField("");
			createPanel.setPassWordField("");
			createPanel.setVerifyPassWordField("");
		}
		else {
			
		}
	}
}
