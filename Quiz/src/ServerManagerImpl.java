import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class ServerManagerImpl extends UnicastRemoteObject implements ServerManager 
{
	private static int count = 0; 
	
	private HashMap<Integer, Quiz> quizMap = new HashMap<Integer, Quiz>(); 
	
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


}
