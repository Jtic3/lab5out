//Joseph Ticer
//Lab5out 
//Dr. Smith
//Software Engineering TR 2:40
package lab5out;

import java.io.Serializable;

public class CreateAccData implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Private data fields for the username, password and second password entry.
	  private String username;
	  private String password;
	  private String verifyPass;
	  
	  // Getters for the username and password.
	  public String getUsername()
	  {
	    return username;
	  }
	  public String getPassword()
	  {
	    return password;
	  }
	  public String getVerifyPass()
	  {
	    return verifyPass;
	  }
	  
	  // Setters for the username and password.
	  public void setUsername(String username)
	  {
	    this.username = username;
	  }
	  public void setPassword(String password)
	  {
	    this.password = password;
	  }
	  public void setVerifyPassword(String verifyPass)
	  {
	    this.verifyPass = verifyPass;
	  }
	  
	  // Constructor that initializes the username and password.
	  public CreateAccData(String username, String password, String verifyPass)
	  {
	    setUsername(username);
	    setPassword(password);
	    setVerifyPassword(verifyPass);
	  }
}
