import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class PlayGame implements java.io.Serializable
{

private ServerManager serverConnect;
private Player player;


//temp measure to me updated 
private int playerScore;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//constructor - pass the serverConnect file retrieved from Server
	public PlayGame(ServerManager servMan)
	{
		this.serverConnect = servMan;
	}
	
	
	public void welcomeMenu() throws IOException
	{
		
		playerSetup();		
		String quizInt = null;
		int quizId = 0;
		boolean complete = false;
		while(!complete)
		{
			System.out.println();
			System.out.println("************* Welcome to Mike's Quiz Server *************");
			System.out.println();
			System.out.println("What would you like to do? ");
			System.out.println("1: Play a quiz?");
			System.out.println("2: Show all available Quizzes");
			System.out.println("3: Show all your scores for a particular Quiz");
			System.out.println("4: Show all the scores for a particular Quiz");
			System.out.println("5: Show all the scores for a all Quizzes");
			System.out.println("0: Exit");
		
			String opt = readLineViaBuffer("Please enter the required option: ");
			//must deal with non int returned here
			int option = Integer.parseInt(opt);	
			
			switch(option) 
			{
				case 0: System.out.println("Exiting: Thank you and good bye");
						complete = true;
						System.exit(0);
						break;
						
				case 1: System.out.println("*** DEBUG **** Play a quiz");
						if(!serverConnect.returnAllQuizzes().isEmpty())
						{	
							playQuiz();
						} else
						{
							System.out.println("No Quizzes to play at present - you need to load ClientConnect.java");
						}
						break;
						
				case 2: System.out.println("*** DEBUG **** Get All Available Quizzes");
						displayAllQuizzes();
						break;
					
						
				case 3: System.out.println("*** DEBUG **** Show all your scores");
						displayAllQuizzes();
						quizInt = readLineViaBuffer("Please enter the ID of the Quiz in which you wish to play: ");
						if(!serverConnect.returnAllQuizzes().isEmpty())
						{	
							//must deal with non int returned here
							quizId = Integer.parseInt(quizInt);	
							playerScoreForQuizID(quizId, player.getId());
						
						} else
						{
							System.out.println("No Quizzes to display at present - you need to load ClientConnect.java");
						}						
						break;
				
				case 4: System.out.println("*** DEBUG **** Show all your scores");
						displayAllQuizzes();
						quizInt = readLineViaBuffer("Please enter the ID of the Quiz in which you wish to play: ");
						if(!serverConnect.returnAllQuizzes().isEmpty())
						{	
							//must deal with non int returned here
							quizId = Integer.parseInt(quizInt);	
							allPlayerScoresForQuizID(quizId);
						} else
						{
							System.out.println("No Quizzes to display at present - you need to load ClientConnect.java");
						}						
						break;		
						
				case 5: System.out.println("*** DEBUG **** Show all scores for All Quizzes");
						if(!serverConnect.returnAllQuizzes().isEmpty())
						{	
								///call to show all scores for all quizzes
							allScoresForAllQuizzes();
						} else
						{
							System.out.println("No Quizzes to play at present - you need to load ClientConnect.java");
						}						
						break;		
						
				default: System.out.println("*** DEBUG **** Not an Option, try again");
						break;
			}
		}		
	}
	
	
	/**
	 * Setup or retrieve player on connecting
	 * @throws IOException
	 */
	public void playerSetup() throws IOException
	{
		String opt = readLineViaBuffer("\nWelcome, are you a returning player with an ID? y/n: ");
		if (opt.toLowerCase().equals("y"))
		{
			String name = readLineViaBuffer("Please enter your name.");
			String ident = readLineViaBuffer("Please enter your Id.");
			int Id = Integer.parseInt(ident);	
			//serverConnect.returningPlayer(name, Id);
			player = serverConnect.getPlayerFromId(Id);
		}
		else
		{
			String name = readLineViaBuffer("Please enter your name.");
			int Id = serverConnect.addNewPlayer(name);	
			System.out.println();
			System.out.println("Your Player ID is: " + Id + ". Please write this down and Keep it safe.");
			System.out.println();
			player = serverConnect.getPlayerFromId(Id);
			//addNewPlayer
		}		
	}
	
	/**
	 * Displays player's score for each quiz
	 * @throws IOException
	 */
	public void allScoresForAllQuizzes() throws IOException
	{
		System.out.println("\nAll Scores for the Available Quizzes");
	
		//iterates through two hashmaps to find the score for the Id provided or each quiz
		 HashMap<Integer, Quiz> tempQHashMap = getAllQuizzes();
			for(Map.Entry<Integer, Quiz> entry: tempQHashMap.entrySet())
			{			
					System.out.println("QuizID: " + entry.getValue().getQuizId() + ", Quiz Name: " + entry.getValue().getQuizName());
					System.out.println("---------------------------------------------------");
					List<PlayerScores> allScores = entry.getValue().getScores();
					int allScoresSize = allScores.size();
					for (int i = 0; i < allScoresSize; i++)
					{
							System.out.println("PlayerId: " + allScores.get(i).getPlayerId() + ", Score = " + allScores.get(i).getScore());
					}
			}	
		System.out.println();
	}
	
	
	
	/**
	 * gets all scores for a certain QuizID
	 * @param Id
	 * @throws RemoteException
	 */
	public void allPlayerScoresForQuizID(int quizId) throws IOException
	{
		System.out.println("\nAll player Scores for the Quiz ID: " + quizId);
		System.out.println();
		Quiz tempQ = serverConnect.getQuizFromID(quizId);
		List<PlayerScores> pScores = tempQ.getScores();
		System.out.println("***DEBUG*** Works to here");
		int pScoresSize = pScores.size();
		for (int i = 0; i < pScoresSize; i++)
		{
			if (pScores.get(i).getQuizID() == quizId)
			{
				System.out.println("PlayerId: " + pScores.get(i).getPlayerId() + ", Score = " + pScores.get(i).getScore());
			}
		}
	}

	
	/**
	 * Gets a players score from their id for a certain quiz
	 * @param Id
	 * @throws RemoteException
	 */
	public void playerScoreForQuizID(int quizId, int pId) throws IOException
	{
		System.out.println("\nAll your Scores for the Quiz ID: " + quizId);
		System.out.println();
		Quiz tempQ = serverConnect.getQuizFromID(quizId);
		List<PlayerScores> pScores = tempQ.getScores();
		int pScoresSize = pScores.size();
		for (int i = 0; i < pScoresSize; i++)
		{
			if (pScores.get(i).getQuizID() == quizId)
			{
				if(pScores.get(i).getPlayerId() == pId)
					{
						System.out.println("PlayerId: " + pScores.get(i).getPlayerId() + ", Score = " + pScores.get(i).getScore());
					}
			}
		}
	}

	
	
	/**
	 * 
	 * @throws IOException
	 */
	public void playQuiz() throws IOException 
	{
		System.out.println(); 
		displayAllQuizzes();
		System.out.println();
		String opt = readLineViaBuffer("Please enter the ID of the Quiz in which you wish to play: ");
		//must deal with non int returned here
		int option = Integer.parseInt(opt);			
		
		Quiz temp = serverConnect.getQuizFromID(option);
		System.out.println();
		//confirms the quiz chosen
		System.out.println("Welcome to " + temp.getQuizName());
		List <Question> quests = temp.getQuestions();
		int questSize = quests.size();
		//create n amount of questions as indicated
		for (int i = 0; i < questSize; i++)
		{
			Question tempQuest = quests.get(i);
			System.out.println("Question: " + (i +1));
			System.out.println(tempQuest.getQuestion());
			String[] tempQuestArray = tempQuest.getAnswers();
			System.out.println((0 + 1) + ":"  + tempQuestArray[0]);
			System.out.println((1 + 1) + ":"  + tempQuestArray[1]);
			System.out.println((2 + 1) + ":"  + tempQuestArray[2]);
			int correctAnswer = tempQuest.getCorrectAnswer();
			String ans = readLineViaBuffer("Please enter Correct Answer: ");
			//must deal with non int returned here
			int answer = Integer.parseInt(ans);	
			
			if (answer == correctAnswer)
			{				
				System.out.println("\nThat answer is Correct");
				System.out.println();
				playerScore = playerScore + 1;
			}
			else
			{
				System.out.println("\nThat answer is wrong");
				System.out.println();
			}
			
		}
		
		//HashMap<Integer, Integer> tempScoreHashMap = temp.getAllPlayerScores();
		PlayerScores pScore = new PlayerScores(option, player.getId(), player.getName(), playerScore);
		temp.addToPlayerScore(pScore);
		//temp.addToPlayerScore(player.getId(), playerScore);
		
		//to show the score has been saved and returned from server
		//must add return function
		System.out.println("End of Quiz. Your Score was " + playerScore);//temp.getPlayersScore(player.getId()));
		//reset score for next go or set of questions.
		playerScore = 0;
	}
	

	/**
	 * Displays all Quizzes available
	 * @throws IOException
	 */
	public void displayAllQuizzes() throws IOException 
	{
		
		HashMap<Integer, Quiz> tempHlist = getAllQuizzes();
		if (tempHlist.isEmpty())
		{
			System.out.println("No Quizzes available");
		}
		else
		{
			System.out.println(); 
			System.out.println("List of Quizzes already on system");
			System.out.println("---------------------------------");
			for(Map.Entry<Integer, Quiz> entry: tempHlist.entrySet())
			{
				System.out.println("QuizID: " + entry.getKey() + ". " + "Quiz Name: " + entry.getValue().getQuizName()); 
			}
			System.out.println(); 
		}
	}
	
	
	/**
	 * returns all quizzes on server
	 * @return
	 * @throws IOException
	 */
	public HashMap<Integer, Quiz> getAllQuizzes() throws IOException 
	{
		HashMap<Integer, Quiz> tempHlist = serverConnect.returnAllQuizzes();
		return tempHlist;
	}
	
	
	/**
	 * Reads and returns a line of strings 
	 * @param instructions
	 * @return
	 * @throws IOException
	 */
	public String readLineViaBuffer(String instructions) throws IOException  
	{
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print(instructions);
	    String stringRead = br1.readLine();				
		return stringRead;
	}
	
}
