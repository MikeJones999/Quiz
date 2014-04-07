/**
 * PlayerScores class - these are individual scores for individual Quizzes.
 * They are linked to the Quiz object once it has been attempted by player.
 * @author mikieJ
 *
 */

public class PlayerScores implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int playerID;
	private String pName;
	private int quizId;
	private int score; 
	
	public PlayerScores(int quizId, int playerID, String pName, int score)
	{
		this.playerID = playerID;
		this.pName = pName;
		this.quizId = quizId;
		this.score = score;
	}
	
	/**
	 * Returns Quiz Id to which the scores are linked
	 * @return quizId
	 */
	public int getQuizID()
	{
		return quizId;
	}
	
	/**
	 * Returns the score for a particular attempt of a quiz
	 * @return score
	 */
	public int getScore()  
	{
		return score;
	}
	
	/**
	 * Returns the player ID for a player attempt of a quiz
	 * @return playerID
	 */
	public int getPlayerId()
	{
		return playerID;
	}
	
	/**
	 * Returns the player name for a player attempt of a quiz
	 * @return pName
	 */
	public String getPlayerName()
	{
		return pName;
	}
	
	
}
