import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class ServerLauncher
{
		
	public static void main(String[] args)
	{
		ServerLauncher start = new ServerLauncher();
		start.launch();
	}



	private void launch()
	{
		//1. If there is no security manager, start one
		if(System.getSecurityManager() == null)
		{
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{			
				//2. Create the reg if there is not one
				LocateRegistry.createRegistry(1099);
				
				//3. Create the server object
				//Create an implementation object, passing the
				//above ArrayList to the constructor
				//EchoServer server = new EchoServer(playerList);
				
				
				//gets created once when the server starts
				ServerManager server = new ServerManagerImpl();
				
				//4. Register (bind) the server object on the reg.
				// The registry may be on a different machine
				String registryHost = "//localhost/";
				String serviceName = "echo";
				Naming.rebind(registryHost + serviceName, server);
				System.out.println("Server in waiting for connections...");
		} 
		catch (MalformedURLException ex)
		{
			ex.printStackTrace();
		}
		catch(RemoteException ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Main thread ended");
	}

	//java -Djava.security.policy=server.policy ServerLauncher
	

}