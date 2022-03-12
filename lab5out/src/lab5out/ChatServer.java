package lab5out;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class ChatServer extends AbstractServer
{
	private JTextArea log;
	private JLabel status;
	private boolean stopActionFlag;
	private dBFile masterFile;
	public ChatServer(JTextArea log, JLabel status)
	{
		super(12345);
		setTimeout(500);
		this.setLog(log);
		this.setStatus(status);
		try {
			masterFile = new dBFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ChatServer(int port, int timeout)
	{
		super(port);
	}

	public void setLog(JTextArea log)
	{
		this.log = log;
	}

	public JTextArea getLog()
	{
		return log;
	}

	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	public JLabel getStatus()
	{
		return status;
	}
	/*Setting the parameter stopActionFlag from StopHandler class, to 
	 * this ChatServer's stopActionFlag */
	public void setStopFlag(boolean stopActionFlag) {
		this.stopActionFlag = stopActionFlag;
	}



	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{
		// TODO Auto-generated method stub
		String userId = arg0.toString();
		if (arg0 instanceof LoginData)
		{
			String loginErrorCode = "LOGIN SUCCESS";
			LoginData loginData = (LoginData)arg0;	   
			masterFile.setId(userId); 
			masterFile.setUsername(loginData.getUsername()); 
			masterFile.setPassword(loginData.getPassword());
			try {
				loginErrorCode = masterFile.lookUp();
				if(loginErrorCode.equals("LOGIN SUCCESS")) {
					arg1.sendToClient(loginErrorCode);
					User user = new User(masterFile.getId());
					log.append(user.getUserId()+" "+"Has logged into the server!");
				}
				else if(loginErrorCode.equals("LOGIN ERROR 1: USERNAME/PASSWORD")) {
					arg1.sendToClient(loginErrorCode);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (arg0 instanceof CreateAccData)
		{
			String errorMessage = "CREATE SUCCESS";
			
			CreateAccData createData = (CreateAccData)arg0;	   
			if(createData.getPassword().equals(createData.getVerifyPass())){		
					masterFile.setId(userId); 
					masterFile.setUsername(createData.getUsername()); 
					masterFile.setPassword(createData.getPassword());
				try {
					errorMessage = masterFile.validate();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(errorMessage.equals("CREATE SUCCESS")) {
					try {
						log.append("Message from Client" + arg0.toString() + arg1.toString() + "\n");
						arg1.sendToClient(errorMessage);
						masterFile.writeMetaData();
						masterFile.setMetaData();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {											
						arg1.sendToClient("CREATE ERROR 1: USERNAME SELECTED");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				try {
					arg1.sendToClient("CREATE ERROR 2: PASSWORDS DON'T MATCH");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected void listeningException(Throwable exception) 
	{
		//Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());

		/*if (this.isListening())
    {
      log.append("Server not Listening\n");
      status.setText("Not Connected");
      status.setForeground(Color.RED);
    }*/

	}

	protected void serverStarted() 
	{
		System.out.println("Server Started");
		log.append("Server Started\n");
		status.setText("Listening");
		status.setForeground(Color.green);
	}

	protected void serverStopped() 
	{
		System.out.println("Server Stopped");
		//log.append("Server Stopped Accepting New Clients - Press Listen to Start Accepting New Clients\n");
		/*If the close() method was called first, 
		 * the stopActionFlag will be set to false.*/
		if(stopActionFlag==true) {
			log.append("Server Stopped Accepting New Clients - "
					+ "Press Listen to Start Accepting New Clients");
			log.append("\n");
			status.setText("Stopped");
			status.setForeground(Color.red);
		}
	}

	protected void serverClosed() 
	{
		System.out.println("Server and all current clients are closed - Press Listen to Restart");
		//log.append("Server and all current clients are closed - Press Listen to Restart\n");
	}


	protected void clientConnected(ConnectionToClient client) 
	{	
		System.out.println("Client Connected");
		//Display number of Clients:
		System.out.println("Number of Client:" + this.getNumberOfClients());	
		//log.append("Client Connected\n");
	}





}
