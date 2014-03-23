import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
/**
 * Interface to setup the Server Manager - that is the main port of contact
 * @author mikieJ
 *
 */


public interface ServerManager extends Remote 
{

	/**
	 * Create a new empty Quiz with name and number of questions
	 * returns the ID number to recall test
	 * @param name
	 * @param questionNum
	 */
	int addNewQuiz(String name, int questionNum) throws RemoteException;
	
	/**
	 * 
	 * @param quizTemp
	 * @return
	 * @throws RemoteException
	 */
	int createQuizId(Quiz quizTemp) throws RemoteException;
	
	
	/**
	 * 
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	Quiz getQuizFromID(int Id) throws RemoteException;
	
	
	/**
	 * Add question to quiz object
	 * @param quiz
	 * @param q
	 * @throws RemoteException
	 */
	void addQuestionToQuiz(Quiz quiz, Question q)  throws RemoteException;
	
	/**
	 * Add questions to a quiz Via its Id
	 * @param ID
	 * @throws RemoteException
	 */
	void addQuestionToQuiz(int Id, Question q)  throws RemoteException;
	
	/**
	 * Return HashMap of all quizzes on server
	 * @return
	 * @throws RemoteException 
	 */
	HashMap<Integer, Quiz> returnAllQuizzes() throws RemoteException;
	
		
	/**
	 * Adds new player to the Hashmap
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	int addNewPlayer(String name) throws RemoteException;
	
	/**
	 * TO DO
	 * @param name
	 * @param Id
	 */
	void returningPlayer(String name, int Id) throws RemoteException;
	
	/**
	 * return a player from specific ID
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	public Player getPlayerFromId(int Id) throws RemoteException;
	
	
	/**
	 * Saves all data to Disk
	 */
	public void flush()  throws RemoteException;
	
}