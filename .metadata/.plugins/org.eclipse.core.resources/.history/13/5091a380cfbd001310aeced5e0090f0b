import java.io.IOException;
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
	 * Adds a quiz loaded from .txt file
	 * @param Id
	 * @param name
	 * @param questionNum
	 * @throws RemoteException
	 */
	public void addQuizFromFile(int Id, String name, int questionNum) throws RemoteException; 
	
	
	/**
	 * Creates an Id for each quiz
	 * @param quizTemp
	 * @return
	 * @throws RemoteException
	 */
	int createQuizId(Quiz quizTemp) throws RemoteException;
	
	
	/**
	 * Returns the specified quiz from the given Id
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
	 * Adds players to server when loaded from file
	 * @param name
	 * @param Id
	 */
	public void addPlayersFromFile(String name, int Id) throws RemoteException;
	
	
	/**
	 * Returns hashmap of Players objects and their Ids
	 * @return
	 * @throws RemoteException
	 */
	public HashMap<Integer, Player> returnAllPlayers() throws RemoteException;
	
	
	/**
	 * Return a player from specific ID
	 * @param Id
	 * @return
	 * @throws RemoteException
	 */
	public Player getPlayerFromId(int Id) throws RemoteException;
	
	
	/**
	 * Central Location to call the flush methods
	 * Flushes quizzes and players
	 * @throws RemoteException
	 * @throws IOException
	 */
	public void runflush() throws RemoteException, IOException;
	
	
}
