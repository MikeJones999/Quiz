import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface Quiz extends Remote
{	
	
	void addQuestionToQuiz(Question quest) throws RemoteException ;
	
	String getQuizName() throws RemoteException ;
	
	List<Question> getQuestions() throws RemoteException ;
	
	int getQuizId() throws RemoteException;
	
	void createQuizId(int id) throws RemoteException;
}
