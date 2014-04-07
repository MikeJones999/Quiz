import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Quiz extends Remote
{	
	/**
	 * Adds a question to the current quiz
	 * @param quest
	 * @throws RemoteException
	 */
	void addQuestionToQuiz(Question quest) throws RemoteException;
	
	/**
	 * Returns the name of the current Quiz
	 * @return
	 * @throws RemoteException
	 */
	String getQuizName() throws RemoteException;
	
	/**
	 * Returns a list of questions for current Quiz
	 * @return
	 * @throws RemoteException
	 */
	List<Question> getQuestions() throws RemoteException;
	
	/**
	 * Returns ID of currently selected Quiz
	 * @return
	 * @throws RemoteException
	 */
	int getQuizId() throws RemoteException;
	
	/**
	 * Creates an ID for the newly created quiz
	 * @param id
	 * @throws RemoteException
	 */
	void createQuizId(int id) throws RemoteException;
	
	
	/**
	 * Adds a players score (PlayerScores) to the quiz
	 * If player has the highest score then they achieve this accolade 
	 * @param pScore
	 * @return
	 * @throws RemoteException
	 */
	String addToPlayerScore(PlayerScores pScore) throws RemoteException;
	
	
	/**
	 * Returns List of PlayerScores for this particular Quiz
	 * @return
	 * @throws RemoteException
	 */
	List<PlayerScores> getScores() throws RemoteException;
	
	/**
	 * Returns the total amount of questions for this quiz
	 * @return
	 * @throws RemoteException
	 */
	int getQuestionTotal() throws RemoteException;

	/**
	 * Sets the total amount of questions for this quiz
	 * @param questionTotal
	 * @throws RemoteException
	 */
	void setQuestionTotal(int questionTotal) throws RemoteException;
	
	/**
	 * Set the top score for this quiz
	 * @param p
	 * @throws RemoteException
	 */
	void setTopScore(PlayerScores p) throws RemoteException;
	
	/**
	 * Returns the top score for this Quiz
	 * @return
	 * @throws RemoteException
	 */
	PlayerScores getTopScore() throws RemoteException;

	
}
