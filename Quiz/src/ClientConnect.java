

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class ClientConnect
{
	public static void main (String[] args) throws MalformedURLException, RemoteException, NotBoundException
	{
		try {
				new ClientConnect().launch();
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
	
		//get Setup menu from server
		SetUPMenu newSetup = new SetUPMenu(serverConnect);
		newSetup.welcomeMenu();
		//System.out.println("quiz 1 = " + serverConnect.getQuizFromID(1).getQuizName());
		

	}

	public ServerManager initialConnect() throws MalformedURLException, RemoteException, NotBoundException
	{
		
		System.out.print("Insert word to Send: ");
		String str = System.console().readLine();
		
		System.out.print("Insert IP: ");
		String ip = System.console().readLine();
		
		System.out.print("Insert Port: ");
		String port = System.console().readLine();		
	
		System.out.println("Looking up " + "//"+ip+":"+port +"/echo");
		
		ServerManager service = (ServerManager)Naming.lookup("//"+ip+":"+port +"/echo");
		System.out.println("Looked up " + "//"+ip+":"+port +"/echo");
			
		return service;
	}
	
	

//to run client// java -Djava.security.policy=client.policy EchoClient
}