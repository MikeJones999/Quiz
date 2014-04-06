import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class PlayerClient extends ClientConnection implements java.io.Serializable
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
	
	/**
	 * Launch method - ServerManager is obtained and new PlayGame object created
	 * Loads the Welcome menu in PlayGame Class
	 * @throws NotBoundException
	 * @throws IOException
	 */
	public void launch() throws NotBoundException, IOException
	{
		//call method to connect to server		
		ServerManager serverConnect = initialConnect();	
		
		PlayGame newPlayer = new PlayGame(serverConnect);
		newPlayer.welcomeMenu();		
	}
		
	
}
