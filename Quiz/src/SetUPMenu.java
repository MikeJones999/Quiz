import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SetUPMenu implements java.io.Serializable
{
	
	private ServerManager serverConnect;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//constructor - pass the serverConnect file retrieved from Server
	public SetUPMenu(ServerManager servMan)
	{
		this.serverConnect = servMan;
	}
	

	
	public void welcomeMenu() throws IOException
	{
		boolean complete = false;
		while(!complete)
		{
			
			System.out.println("\n************* Welcome to Mike's Quiz Setup *************");
			System.out.println();
			System.out.println("What would you like to do? ");
			System.out.println("1: Make a new Quiz?");
			System.out.println("2: Show all available Quizzes");
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
						
				case 1: System.out.println("*** DEBUG **** Making new quiz");
						makeNewQuiz();
						break;
						
				case 2: System.out.println("*** DEBUG **** Get All Available Quizzes");
						getAllQuizzes();
						break;
						
				//Not yet implemented	
				//case 3: System.out.println("*** DEBUG **** Edit a quiz");
						//break;
						
						
				default: System.out.println("*** DEBUG **** Not an Option, try again");
						break;
			}
		}		
	}
	
	/**
	 * Make a new Quiz from Scratch
	 * @throws IOException
	 */
	public void makeNewQuiz() throws IOException
	{
		
			System.out.println();
			System.out.println("************* Quiz Setup *************");
			System.out.println();
			String qName = readLineViaBuffer("Please Enter a Name for your Quiz: ");	    
			String qAmount = readLineViaBuffer("Please Enter quantity of Questions: ");
			int quantOfQuestions = Integer.parseInt(qAmount);	
			
			int returnedID = serverConnect.addNewQuiz(qName, quantOfQuestions);
			Quiz tempQuiz = serverConnect.getQuizFromID(returnedID);
			adQuestions(quantOfQuestions, tempQuiz);

		
			/*
		//returns list of questions for newly created quiz
		List<Question> temp = tempQuiz.getQuestions();


		for (int j= 0; j< temp.size(); j++)
		{
			System.out.println("Question " + (j+1) + ": ");
			System.out.println(temp.get(j).getQuestion());
		}

		int num = temp.get(0).getCorrectAnswer();
		String[] answers = temp.get(0).getAnswers();
		System.out.println("Corrrect Answer is: " + num);
		System.out.println("Answer is: " + answers[num-1]);
		*/
	}
	
	
	/**
	 * Creates n number of questions in designated quiz
	 * @param num
	 * @param tempQuiz
	 * @throws IOException
	 */
	public void adQuestions(int num, Quiz tempQuiz) throws IOException
	{
		for (int i = 0; i < num; i++)
		{
			System.out.println("Question: " + (i + 1));
			String quest = readLineViaBuffer("Please Enter a Question: ");
			String ansOne = readLineViaBuffer("Please Enter 1st Answer: ");
			String ansTwo = readLineViaBuffer("Please Enter 2nd Answer: ");
			String ansThree = readLineViaBuffer("Please Enter 3rd Answer: ");
			String correctAns = readLineViaBuffer("Please Enter which is the correct Answer: 1,2, or 3: ");
			int answer = Integer.parseInt(correctAns);
		    Question questTemp = new Question(quest, ansOne, ansTwo, ansThree, answer);
		    serverConnect.addQuestionToQuiz(tempQuiz, questTemp);		
		}
		
	}
	
	
	
	/**
	 * Returns from server HashMap of all Quizzes available
	 * @throws IOException
	 */
	public void getAllQuizzes() throws IOException 
	{
		HashMap<Integer, Quiz> tempHlist = serverConnect.returnAllQuizzes();
		System.out.println(); 
		System.out.println("List of Quizzes already on system");
		System.out.println("---------------------------------");
		for(Map.Entry<Integer, Quiz> entry: tempHlist.entrySet())
		{
			System.out.println("QuizID: " + entry.getKey() + ". " + "Quiz Name: " + entry.getValue().getQuizName()); 
		}
		System.out.println(); 
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

	
	
	
	
	
	
	
	
	
	
}//class


/*
String quest = null; 
String ansOne = null; 
String ansTwo = null; 
String ansThree = null; 
String correctAns = null;
int answer = 0;


String qName = readLineViaBuffer("Please Enter a Name for your Quiz: ");	    
String qAmount = readLineViaBuffer("Please Enter quantity of Questions: ");
int quantOfQuestions = Integer.parseInt(qAmount);	    

int returnedID = serverConnect .addNewQuiz(qName, quantOfQuestions);

Quiz tempQuiz = serverConnect.getQuizFromID(returnedID);

//QuizImpl quiz1 = new QuizImpl(qName, quantOfQuestions);



for (int i = 0; i < quantOfQuestions; i++)
{
	System.out.println("Question: " + (i + 1));
	quest = readLineViaBuffer("Please Enter a Question: ");
 	ansOne = readLineViaBuffer("Please Enter 1st Answer: ");
   	ansTwo = readLineViaBuffer("Please Enter 2nd Answer: ");
  	ansThree = readLineViaBuffer("Please Enter 3rd Answer: ");
	correctAns = readLineViaBuffer("Please Enter which is the correct Answer: 1,2, or 3: ");
    answer = Integer.parseInt(correctAns);
    Question questTemp = new Question(quest, ansOne, ansTwo, ansThree, answer);
    serverConnect.addQuestionToQuiz(tempQuiz, questTemp);
}


List<Question> temp = tempQuiz.getQuestions();


for (int j= 0; j< temp.size(); j++)
{
	System.out.println("Question " + (j+1) + ": ");
	System.out.println(temp.get(j).getQuestion());
}

int num = temp.get(0).getCorrectAnswer();
String[] answers = temp.get(0).getAnswers();
System.out.println("Corrrect Answer is: " + num);
System.out.println("Answer is: " + answers[num-1]);
*/