import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class PlayerClient implements java.io.Serializable
{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static void main (String[] args) throws MalformedURLException, RemoteException, NotBoundException
	{
		try {
				new PlayerClient().launch();
			}
	  catch (IOException e) 
			{
				e.printStackTrace();
			}	
	}
	
	public void launch() throws NotBoundException, IOException
	{
		//call method to connect to server		
		ServerManager serverConnect = initialConnect();	
		
		PlayGame newPlayer = new PlayGame(serverConnect);
		newPlayer.welcomeMenu();		
	}
	

	
	/**
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public ServerManager initialConnect() throws MalformedURLException, RemoteException, NotBoundException
	{
		System.out.println("\nJust Setting up the playerClient to Server connection...");
		
		System.out.print("Insert IP: ");
		String ip = System.console().readLine();
		
		System.out.print("Insert Port: ");
		String port = System.console().readLine();		
	
		System.out.println("Looking up " + "//"+ip+":"+port +"/echo");
		
		ServerManager service = (ServerManager)Naming.lookup("//"+ip+":"+port +"/echo");
		System.out.println("Looked up " + "//"+ip+":"+port +"/echo");
			
		return service;
	}
	
	
	/**
	 * Reads and returns a line of strings 
	 * @param instructions
	 * @return
	 * @throws IOException
	 */
	public String readLineViaBuffer(String instructions) throws IOException  
	{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print(instructions);
	    String stringRead = br1.readLine();				
		return stringRead;
	}
	
	
}
