package Population;

import java.util.Random;

public class Probability {

	int precision;
	
	public Probability() 
	{
		precision = 100;
	}
	
	/**
	 * Constructs a Probability class with a set precision that aid probability
	 * @param givenPrecision
	 */
	public Probability(int givenPrecision) 
	{
		precision = (int) Math.pow(10, givenPrecision);
	}
	
	/**
	 * Sets precision with a given value
	 * @param givenPre
	 */
	public void setPre(int givenPre)
	{
		precision = (int) Math.pow(10, givenPre);
	}
	
	/**
	 * Produces a random number between 0-99
	 * @return
	 */
	public int randGen()
	{
		Random rand = new Random();
		return Math.abs(rand.nextInt()) % precision;
	}
	
	/**
	 * Returns true if 
	 * @param prob
	 * @return
	 */
	public boolean Chance(double prob)
	{
		int r = randGen();
		
		if(r >= prob * precision)
		{
			return false;
		}
		
		return true;
	}
}
