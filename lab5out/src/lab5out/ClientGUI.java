//Joseph Ticer
//Lab5out 
//Dr. Smith
//Software Engineering TR 2:40
package lab5out;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientGUI extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// Constructor that creates the client GUI.
  public ClientGUI()
  {
    
    // Set the title and default close operation.
    this.setTitle("Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    ChatClient client = new ChatClient(container);
    
    try {
		client.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    //Create the Controllers next
    //Next, create the Controllers
    InitialControl ic = new InitialControl(container); 
    LoginControl lc = new LoginControl(container, client); 
    CreateAccControl cac = new CreateAccControl(container, client);
    
    
    
    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccPanel(cac);
    JPanel view4 = new ContactPanel();
    
    
    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view4, "4");
   
    
    // Show the initial view in the card layout.
    cardLayout.show(container, "1");
    
    // Add the card layout container to the JFrame.
    this.add(container, BorderLayout.CENTER);

    // Show the JFrame.
    this.setSize(550, 350);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args)
  {
    new ClientGUI();
  }
}
