


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
	
	public int getQuizID()
	{
		return quizId;
	}
	
	public int getScore()  
	{
		return score;
	}
	
	
	public int getPlayerId()
	{
		return playerID;
	}
	
	public String getPlayerName()
	{
		return pName;
	}
	
	
}
