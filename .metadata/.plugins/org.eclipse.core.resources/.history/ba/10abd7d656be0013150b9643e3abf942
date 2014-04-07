/**
 * Creates a Question object which is then assigned to a particular quiz.
 * Question is made up of one question and three multiple choice question 
 * @author mikieJ
 *
 */
public class Question implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String quest;
	private String ansOne;
	private String ansTwo;
	private String ansThree;
	private int answer;
	private String[] allAnswers;
	
	public Question (String quest, String ansOne, String ansTwo, String ansThree, int answer)
	{
		this.quest = quest;
		this.ansOne = ansOne;
		this.ansTwo = ansTwo;
		this.ansThree = ansThree;
		this.answer = answer;		
		this.allAnswers = new String[]{ansOne,ansTwo,ansThree};
	}
	
	/**
	 * Returns the actual string question as entered by client
	 * @return quest 
	 */
	public String getQuestion()
	{
		return quest;
	}	
	
	/**
	 * Gets the three Multiple choice answers to this question
	 * @return
	 */
	public String[] getAnswers()
	{
		return allAnswers;		
	}
	
	/**
	 * Returns the correct answer to this question
	 * @return
	 */
	public int getCorrectAnswer()
	{
		return answer;
	}
	
	
	
}
