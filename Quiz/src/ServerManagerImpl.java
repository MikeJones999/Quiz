import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class ServerManagerImpl extends UnicastRemoteObject implements ServerManager 
{
	private static int count = 0; 
	
	private HashMap<Integer, Quiz> quizMap = new HashMap<Integer, Quiz>(); 
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>(); 
	
	private static int playerId = 0;
	private static int quizId = 0;		
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServerManagerImpl() throws RemoteException 
	{
		super();		
	}

	@Override
	public int addNewQuiz(String name, int questionNum) throws RemoteException 
	{
		QuizImpl quizTemp = new QuizImpl(name, questionNum);
		int id = createQuizId(quizTemp);
		quizMap.put(id, quizTemp);
		count = count + 1;
		
		return id;
	}
	
		

	@Override
	public int createQuizId(Quiz quizTemp) throws RemoteException 
	{
		quizTemp.createQuizId(count);		
		return count;
	}

	
	@Override
	public Quiz getQuizFromID(int Id) throws RemoteException 
	{	
		Quiz quizFound = null;
		if (quizMap.containsKey(Id))
		{	
			quizFound = quizMap.get(Id);		
			System.out.println("****DEBUG**** Quiz: " + quizFound.getQuizName() + " found");
		}	
		else
		{	
				throw new IllegalArgumentException ("Quiz " + Id + " Does not exist");
		}			
		return quizFound;
	}	
	
	
	@Override
	public void addQuestionToQuiz(Quiz quiz, Question q) throws RemoteException 
	{
		quiz.addQuestionToQuiz(q);
		
	}

	@Override
	public void addQuestionToQuiz(int Id, Question q) throws RemoteException 
	{
		Quiz temp = this.getQuizFromID(Id);
		temp.addQuestionToQuiz(q);
		
	}

	@Override
	public HashMap<Integer, Quiz> returnAllQuizzes() throws RemoteException 
	{		
		return quizMap;
	}

	@Override
	public int addNewPlayer(String name) throws RemoteException 
	{
		//create ID - may need to synchronise this
		int Id = quizId;
		Player newPlayer = new Player(Id, name);
		quizId = quizId + 1;
		players.put(Id, newPlayer);
		return Id;
	}

	@Override
	public void returningPlayer(String name, int Id) throws RemoteException 
	{
		// TODO Auto-generated method stub
		
		//players.put(Id, newPlayer);
	}
	
	
	//loadPlayerFromFile
	

	@Override
	public Player getPlayerFromId(int Id) throws RemoteException 
	{
		Player tempPlayer = null;
		for(Map.Entry<Integer, Player> entry: players.entrySet())
		{
			if (entry.getKey() == Id)
			{
				tempPlayer = entry.getValue();
				System.out.println("***DEBUG*** Player ID: " + entry.getKey() + ". " + "Player Name: " + entry.getValue().getName());
			}
		}
		return tempPlayer;
	
	}
	
	
	public int createPlayerID() throws RemoteException 
	{
		return 0;	
		
	}
	
	


}
