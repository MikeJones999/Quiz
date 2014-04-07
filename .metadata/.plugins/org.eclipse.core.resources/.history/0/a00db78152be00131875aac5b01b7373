import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Quiz class that create a quiz object and is saved in the ServerManagerImpl class
 * Each Quiz is made up of a number of Question objects
 * This class holds the quiz ID, Number of questions for this quiz, and Top Score
 * @author mikieJ
 *
 */
public class QuizImpl extends UnicastRemoteObject implements Quiz,java.io.Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quizName;
	private int questionTotal;
	private int quizId;
	private PlayerScores topScore = null;
	private List<Question> quizQuestions = new ArrayList<Question>(); 
	private List<PlayerScores> scores  = new ArrayList<PlayerScores>();
	//adds players ID and Score to this Quiz
	//private HashMap<Integer, Integer> playerScore = new HashMap<Integer, Integer>();

	
	public QuizImpl(String quizName, int questionTotal) throws RemoteException 
	{
		this.quizName = quizName;
		this.setQuestionTotal(questionTotal);
	}


	@Override
	public void addQuestionToQuiz(Question quest)throws RemoteException 
	{
		quizQuestions.add(quest);
		
	}


	@Override
	public List<Question> getQuestions() throws RemoteException 
	{	
		return quizQuestions;
	}


	@Override
	public String getQuizName() throws RemoteException 
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

	@Override
	public String addToPlayerScore(PlayerScores pScore) throws RemoteException
	{		
		String update = "";
		//need to have an overwrting element
		if (getTopScore() == null)
		{	
			if(pScore.getScore() > 0)
			{
				setTopScore(pScore);
				update = "**** CONGRATULATIONS NEW TOP SCORE *****";
			}
		}
		else
		{
			if (pScore.getScore() > topScore.getScore())
			{
				setTopScore(pScore);
				update = "**** CONGRATULATIONS NEW TOP SCORE *****";
			}
		}
		scores.add(pScore);
		return update;
	}
	
	
	@Override
	public List<PlayerScores> getScores() throws RemoteException
	{
		return scores;
	}

	@Override
	public int getQuestionTotal() throws RemoteException
	{
		return questionTotal;
	}

	@Override
	public void setQuestionTotal(int questionTotal) throws RemoteException
	{
		this.questionTotal = questionTotal;
	}


	@Override
	public void setTopScore(PlayerScores p) throws RemoteException 
	{
		this.topScore = p;
	}


	@Override
	public PlayerScores getTopScore() throws RemoteException
	{
		return topScore;
	}
	
}
