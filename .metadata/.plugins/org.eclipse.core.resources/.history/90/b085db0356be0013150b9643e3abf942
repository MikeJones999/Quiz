
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
*An implementation of the echo service
*/
public interface TestService extends Remote 
{
	/**
	*Returns the same string passed as parameter
	*@params s a string
	*@retrurn the same string passed as parameter
	*/
	public String echo(String s) throws RemoteException;

	
	public String returnWord() throws RemoteException;
	
	
	public void createNewPlayer(String name) throws RemoteException;
	
	public ArrayList<Player> getPlayers() throws RemoteException;	

}