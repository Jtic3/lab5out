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
			if(msg.contains("CREATE ERROR")) {
				CreateAccControl cac = new CreateAccControl(container, this);
				cac.displayError(msg);
			}
			else if(msg.contains("LOGIN ERROR")) {
				LoginControl lc = new LoginControl(container, this);
				lc.displayError(msg);
			}
			else if(msg.equals("CREATE SUCCESS")) {
				CreateAccControl cac = new CreateAccControl(container, this);
				cac.createSuccess(msg);
				//Handle Login Here

			}
			else if(msg.equals("LOGIN SUCCESS")) {				
				LoginControl lc = new LoginControl(container, this);
				lc.loginSuccess(msg);
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
