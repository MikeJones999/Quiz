import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class EchoServer extends UnicastRemoteObject implements TestService
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Declare the ArrayList that will hold Player objects
	private ArrayList<Player> pLayerList;

	public EchoServer(ArrayList<Player> playerList) throws RemoteException
	{
		this.pLayerList = playerList;
	}

	@Override
	public String echo(String s)
	{
		//This printLn is not necessary, but helps verify whether the server
		//has received the call or not on the remote machine
		System.out.println("Replied to some client saying '" + s + "'");
		return s;
	}
	
	
	@Override
	public String returnWord()
	{
		String toReturn = "Welcome to the Server";
		return toReturn;
	}

	@Override
	public ArrayList<Player> getPlayers() throws RemoteException 
	{		
		return pLayerList;
	}

	@Override
	public void createNewPlayer(String name)
	{
		System.out.println(name + " has been Added to the Server");	
		Player newP = new Player(3, name, 0.0);
		pLayerList.add(newP);
	}
	
}