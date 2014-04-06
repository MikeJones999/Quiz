
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;


public class SetUpClient extends ClientConnection  implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main (String[] args) throws MalformedURLException, RemoteException, NotBoundException
	{
		try {
				new SetUpClient().launch();
			}
	  catch (IOException e) 
			{
				e.printStackTrace();
			}	
	}

	/**
	 * Launch method to initiate the connection to the server
	 * @throws NotBoundException
	 * @throws IOException
	 */
	public void launch() throws NotBoundException, IOException
	{
		//call method to connect to server		
		ServerManager serverConnect = initialConnect();	
	
		//get Setup menu from server
		SetUPMenu newSetup = new SetUPMenu(serverConnect);
		newSetup.welcomeMenu();
	}


//to run client// java -Djava.security.policy=client.policy EchoClient
}