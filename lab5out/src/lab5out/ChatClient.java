package lab5out;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JPanel;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient
{
	private JPanel container;

	public ChatClient(JPanel container)
	{
		super("localhost",8300);
		this.container = container;
		try {
			this.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void handleMessageFromServer(Object arg0)
	{
		System.out.println("Server Message sent to Client " + (String)arg0);
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "2");
		// TODO Auto-generated method stub

		String msg = (String)arg0;
		System.out.println(msg);
		if(arg0 instanceof String) {
			if(msg.equals("CREATE ERROR 1: USERNAME SELECTED")) {
				CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
				createPanel.setError("Username has already been selected.");
				cardLayout.show(container, "3");
				System.out.println(msg);
			}
			else if(msg.equals("CREATE ERROR 2: PASSWORDS DON'T MATCH")) {
				CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
				createPanel.setError("Passwords do not match.");
				cardLayout.show(container, "3");
				System.out.println(msg);
			}
			else if(msg.equals("CREATE SUCCESS")) {
				//Handle Login Here
				LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
				loginPanel.setError("");
				cardLayout.show(container, "2");
				System.out.println(msg);	
			}
			else if(msg.equals("LOGIN SUCCESS")) {				
				cardLayout.show(container, "4");
				System.out.println(msg);
			}
			else if(msg.equals("LOGIN ERROR 1: USERNAME/PASSWORD")) {
				LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
				loginPanel.setError("Username/Password Incorrect");
				cardLayout.show(container, "2");
				System.out.println(msg);
			}

		}
		else {

		}
	}

	public void connectionException (Throwable exception) 
	{
		//Add your code here
	}
	public void connectionEstablished()
	{
		//Add your code here
		System.out.println("Connection Established");
	}
	//Dummy Main
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
