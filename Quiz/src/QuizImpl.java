import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class QuizImpl extends UnicastRemoteObject implements Quiz,java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quizName;
	private int questionTotal;
	private int quizId;
	private List<Question> quizQuestions; 
	
	//adds players ID and Score to this Quiz
	private HashMap<Integer, Integer> playerScore = new HashMap<Integer, Integer>();
	
	
	public QuizImpl(String quizName, int questionTotal) throws RemoteException 
	{
		this.quizName = quizName;
		this.questionTotal = questionTotal;
		quizQuestions = new ArrayList<Question>();
	}


	@Override
	public void addQuestionToQuiz(Question quest)
	{
		quizQuestions.add(quest);
		
	}


	@Override
	public List<Question> getQuestions() 
	{	
		return quizQuestions;
	}


	@Override
	public String getQuizName() 
	{		
		return quizName;
	}


	@Override
	public int getQuizId() throws RemoteException
	{		
		return quizId;
	}


	@Override
	public void createQuizId(int id) throws RemoteException 
	{
		this.quizId = id;
		
	}


	
	public  HashMap<Integer, Integer> getAllPlayerScores() throws RemoteException
	{		
		return playerScore;
	}


	@Override
	public void addToPlayerScore(int Id, int score) throws RemoteException
	{		
		//need to have an overwrting element
		playerScore.put(Id, score);
	}
	
	
	@Override
	public int getPlayersScore(int Id) throws RemoteException
	{				
			int pScore = playerScore.get(Id);
			return pScore;
		
	}
	
}
