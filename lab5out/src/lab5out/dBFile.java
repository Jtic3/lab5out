//Joseph Ticer
//Lab5out 
//Dr. Smith
//Software Engineering TR 2:40
package lab5out;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class dBFile {
	private final String FILE_NAME = "UserData.txt";
	private Map<String, List<String>> metaData = new HashMap<String, List<String>>();
	private String id, username, password;
	private FileWriter fw = null;

	//Constructor for dBFile that sets the metaData using read data from text file
	public dBFile() throws IOException  {				
		setMetaData();
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Map<String, List<String>> getMetaData() {
		return metaData;
	}
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	//Reads a file and sets the data to a hash map thats organized by username id
	public void setMetaData() throws IOException {
		try {
			File file = new File(FILE_NAME);
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
				List<String> userData = new ArrayList<>();
				// Creating an object of BufferedReader class
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					String token[] = line.split(",");
					for(int i = 0; i < token.length; i++) {
						userData.add(token[i]);				
					}
					metaData.put(token[0], userData);
					// Print the string
				}
				br.close();
			} else {
				List<String> userData = new ArrayList<>();
				System.out.println("File already exists.");
				// Creating an object of BufferedReader class
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while ((line = br.readLine()) != null) {
					String token[] = line.split(",");
					for(int i = 0; i < token.length; i++) {
						userData.add(token[i]);				
					}
					metaData.put(token[0], userData);
					// Print the string
				}
				br.close();
			}


		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}	
	}
	
	//Validates create account data by checking if username already exists
	public String validate() throws IOException {

		String CreateErrorCode = "";		

		for(String key : metaData.keySet()) {
			List<String> valueSet = metaData.get(key);			
			if(valueSet.contains(this.username)) {
				CreateErrorCode = "CREATE ERROR 1: USERNAME SELECTED";
				return CreateErrorCode;
			}			
			// Print the string
		}
		CreateErrorCode = "CREATE SUCCESS";
		return CreateErrorCode;
	}
	
	//Writes newly created user data to txt file
	public void writeMetaData() throws IOException {
		try { 
			fw = new FileWriter(FILE_NAME, true); 
			fw.write(this.id+","+this.username+","+this.password+"\n"); 
			System.out.println("Data Successfully appended into file"); 			
		} finally 
		{ try {  
			fw.close(); 
		} catch (IOException io) {
			System.out.println("File doesn't exists");
		}
		}
	}	
	//Looks up login information in file and determines if it exists
	public String lookUp() throws IOException {	

		String loginErrorCode = "";

		for(String key : metaData.keySet()) {
			List<String> valueSet = metaData.get(key);
			if(valueSet.contains(this.username) && valueSet.contains(this.password)) {
				loginErrorCode = "LOGIN SUCCESS";
				return loginErrorCode;
			}
		}
		loginErrorCode = "LOGIN ERROR 1: USERNAME/PASSWORD";
		return loginErrorCode;		
	}
}
