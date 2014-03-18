import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class QuizImpl extends UnicastRemoteObject implements Quiz
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String quizName;
	private int questionTotal;
	private int quizId;
	private List<Question> quizQuestions; 
	
	
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

	
	
	
}
