/**
 * Since this class is to be used in
 * the return value for the interface method, it must be declared to implement the
 * Serializable interface (contained in package java.io ).
 * @author mikieJ
 *
 */

public class Player implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Instance variables…
	private int id;
	private String name;
	private double score;
	
	//Constructor…
	public Player(int id, String name, double score)
	{
		this.id = id;
		this.name = name;
		this.score = score;
	}
	
	
	//Methods…
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getScore()
	{
		return score;
	}
	
}