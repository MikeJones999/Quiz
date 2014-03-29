import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ServerManagerImpl extends UnicastRemoteObject implements ServerManager, Serializable  
{
	private static int count = 0; 
	
	private HashMap<Integer, Quiz> quizMap = new HashMap<Integer, Quiz>(); 
	private HashMap<Integer, Player> players = new HashMap<Integer, Player>(); 
	
	private static int playerId = 0;
	private static int quizId = 0;		
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServerManagerImpl() throws RemoteException 
	{
		super();	
		//call quizzes from file
		recallQuizzesFromFile();
	}

	@Override
	//consider synch here
	public int addNewQuiz(String name, int questionNum) throws RemoteException 
	{
		QuizImpl quizTemp = new QuizImpl(name, questionNum);
		int id = createQuizId(quizTemp);
		quizMap.put(id, quizTemp);
		quizId = quizId + 1;
		
		return id;
	}
	
	@Override
	//consider synch here
	public void addQuizFromFile(int Id, String name, int questionNum) throws RemoteException 
	{
		QuizImpl quizTemp = new QuizImpl(name, questionNum);
		quizMap.put(Id, quizTemp);
		quizTemp.createQuizId(Id);
		
		//adjust quizID for newly created quizzes - every time quiz is recalled ensure quizId grows with it.
		increaseQuizId();
		// or if this method is synched - 	quizId = quizId + 1;	
	}	
	
	//this should be synched
	public void increaseQuizId()
	{
		quizId = quizId + 1;	
	}
	

	@Override
	//consider synch here
	public int createQuizId(Quiz quizTemp) throws RemoteException 
	{
		//updates quiz with ID
		quizTemp.createQuizId(quizId);		
		return quizId;
	}

	
	@Override
	public Quiz getQuizFromID(int Id) throws RemoteException 
	{	
		Quiz quizFound = null;
		if (quizMap.containsKey(Id))
		{	
			quizFound = quizMap.get(Id);		
			System.out.println("****DEBUG**** Quiz: " + quizFound.getQuizName() + " found");
		}	
		else
		{	
				throw new IllegalArgumentException ("Quiz " + Id + " Does not exist");
		}			
		return quizFound;
	}	
	
	
	@Override
	public void addQuestionToQuiz(Quiz quiz, Question q) throws RemoteException 
	{
		quiz.addQuestionToQuiz(q);
		
	}

	@Override
	public void addQuestionToQuiz(int Id, Question q) throws RemoteException 
	{
		Quiz temp = this.getQuizFromID(Id);
		temp.addQuestionToQuiz(q);
		
	}

	@Override
	public HashMap<Integer, Quiz> returnAllQuizzes() throws RemoteException 
	{		
		return quizMap;
	}

	@Override
	//consider synch here
	public int addNewPlayer(String name) throws RemoteException 
	{
		//create ID - may need to synchronise this
		int Id = playerId;
		Player newPlayer = new Player(Id, name);
		playerId = playerId + 1;
		players.put(Id, newPlayer);
		return Id;
	}

	@Override
	public void returningPlayer(String name, int Id) throws RemoteException 
	{
		// TODO Auto-generated method stub
		
		//players.put(Id, newPlayer);
	}
	
	
	//loadPlayerFromFile

	@Override
	public HashMap<Integer, Player> returnAllPlayers() throws RemoteException 
	{		
		return players;
	}
		
	

	@Override
	public Player getPlayerFromId(int Id) throws RemoteException 
	{
		Player tempPlayer = null;
		for(Map.Entry<Integer, Player> entry: players.entrySet())
		{
			if (entry.getKey() == Id)
			{
				tempPlayer = entry.getValue();
				System.out.println("***DEBUG*** Player ID: " + entry.getKey() + ". " + "Player Name: " + entry.getValue().getName());
			}
		}
		return tempPlayer;	
	}
	
	//probably remove this
	public int createPlayerID() throws RemoteException 
	{
		return 0;	
		
	}

	@Override
	public void runflush() throws RemoteException, IOException
	{
	 flush();			
	}
	
	@Override
	public void flush() throws IOException, RemoteException
	{
		if (checkFileExists("Quiz.txt"))
		{			
			FileWriter fileWite = new FileWriter("Quiz.txt");
			BufferedWriter bufferWrite = new BufferedWriter(fileWite);				
			FileReader file = new FileReader( "Quiz.txt");
			BufferedReader buffer = new BufferedReader(file);
			String line = null;
			while ( (line = buffer.readLine()) != null)		
			{
				bufferWrite.newLine();
			}
		
			HashMap<Integer, Quiz> tempQuizMap = returnAllQuizzes();
			int hMSize = tempQuizMap.size(); 
			//System.out.println("***DEBUG*** HasMap size is: " + hMSize); 
			bufferWrite.write(hMSize + "," + " Quizzes"); 
			bufferWrite.newLine();
			for(Map.Entry<Integer, Quiz> entry: tempQuizMap.entrySet())
			{	
					//System.out.println("***DEBUG*** Writing Quiz: " + entry.getValue().getQuizName() + " to file");
					bufferWrite.write(entry.getValue().getQuizId() + "," + entry.getValue().getQuizName() + "," + entry.getValue().getQuestionTotal()); 
					bufferWrite.newLine();
					//gets list of questions associated to this quiz
					List <Question> quests = entry.getValue().getQuestions();
					int questSize = quests.size();
					//add each question, answer1,2,3 and correctAnswer to file
					for (int i = 0; i < questSize; i++)
					{
						Question tempQuest = quests.get(i);	
						String[] answers = tempQuest.getAnswers();
						bufferWrite.write(tempQuest.getQuestion() + "," + answers[0] + "," + answers[1] + "," + answers[2] + "," + tempQuest.getCorrectAnswer()); 
						bufferWrite.newLine();
					}					
			}				
		
				bufferWrite.close();
		}	
		else
		{
			System.out.println("File does not exist");
		}
	}
	
	
	public void recallQuizzesFromFile() throws RemoteException
	{
		   String fileName = "Quiz.txt";
		   try {	//possible dont need this as us createNewfile() which checks for you
				   if (!checkFileExists(fileName))
			        {
			        	File file = new File(fileName);
			        	file.createNewFile();
			        } 

		   		Scanner s = null;
	            s = new Scanner(new BufferedReader(new FileReader(fileName)));
	            String line = null; 	
	            //check to see file has contents - then iterate through contacts
	            if(s.hasNext())
	            {	   
	            	line = s.nextLine();
	            	//System.out.println("line = " + line + " Start of Meetings Method");
	            	int QuizzesFound = itemsInFileFound(line);
					for (int i = 0; i < QuizzesFound; i++)
					{
						 line = s.nextLine();	
						 //System.out.println("***DEBUG*** " + line);	
						 //call create contact method below - to use details from each line found
						int[] quest = this.createQuizFromFile(line);
						for (int j = 0; j < quest[1]; j++)
						{
							line = s.nextLine();
							addQuestionsFromFileToQuiz(quest[0],line);
						}
												
					}		            	
              
	            }
	            else
	            {
	            	System.out.println("The file: " + fileName + " is empty");
	            	  s.close();
	            }
	   		}
	        catch(IOException e)
			{
					System.out.println("An error has occurred, Check file is not in use");				
			}		   
	}


	/**
	 *  Checks a line in the CSV/txt file for the first int.
	 *  This is how many records of that type to iterate through 
	 * @param line
	 * @return itemsFound (Int)
	 */
	public int itemsInFileFound(String line)
	{		
		line = line.trim();					
		String[] stringArray = line.split(",");
		int itemsFound = Integer.parseInt(stringArray[0].trim());
		System.out.println("***DEBUG*** Items Found: " + itemsFound);	
		return itemsFound;
	}
	
	public int[] createQuizFromFile(String lineRead) throws RemoteException, FileNotFoundException
	{
		//need to read the first line to establish how many questions per quiz.
		String[] stringArray = lineRead.split(",");
		int id = Integer.parseInt(stringArray[0].trim());
		String quizName = stringArray[1].trim();
		int numberOfQuests = Integer.parseInt(stringArray[2].trim());
		addQuizFromFile(id, quizName, numberOfQuests);
        int[] quizInit = {id, numberOfQuests};  
		return quizInit;
				 
	}
	
	
	public void addQuestionsFromFileToQuiz(int quizId, String lineRead) throws RemoteException
	{
		String[] stringArray = lineRead.split(",");
		String question = stringArray[0].trim();
		String ansOne = stringArray[1].trim();
		String ansTwo = stringArray[2].trim();
		String ansThree = stringArray[3].trim();
		String corAnswer = stringArray[4].trim();
		int answer = Integer.parseInt(corAnswer);
		
		Question q = new Question(question, ansOne, ansTwo, ansThree, answer);
		addQuestionToQuiz(quizId, q);
	}
	
	/**
	 * Checks the provided File (String) actually exists, if not it creates one
	 * @param fileName (String)
	 */
	public boolean checkFileExists(String fileName) throws RemoteException
	{	
		//No option to check the directory as do not know what it is -
		//if user interface then this would be an option
		File file = new File(fileName);
		if(file.exists())
		{
			return true;
		}		
		return false;	
	};

}
