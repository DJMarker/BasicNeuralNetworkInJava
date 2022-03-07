package Creature;

import Population.Probability;

public class Blob {
	
	int age = 1;
	Probability prob = new Probability();
	Blob[][] grid;
	int[] position = new int[2];
	int x = position[0];
	int y = position[1];
	boolean moved = false;
	int ID;
	
	double[] actives = new double[4];
	
	double[] weights = new double[4];
	double[] biases = new double[4];
	
	public Blob(){}
	
	public Blob(Blob[][] grid, int x, int y, int ID)
	{
		this.grid = grid;
		this.x = x;
		this.y = y;
		this.ID = ID;
		
		for(int i = 0; i < weights.length; i++)
		{
			double r = prob.randGen();
			
			weights[i] = r / 100;
		}
		
		for(int i = 0; i < weights.length; i++)
		{
			double r = prob.randGen() / 100;
			
			biases[i] = -r / 100;
		}
	}
	
	/**
	 * Based on inputs, weights, and biases, determines what action(s) to make
	 * @param inputs
	 */
	public void core(double[] inputs)
	{
		for(int i = 0; i < actives.length; i++)
		{	
			actives[i] = (inputs[i] * weights[i]) + biases[i];
		}
		
		int strong = 0;
		
		for(int i = 0; i < actives.length; i++)
		{
			if(actives[strong] <= actives[i])
			{
				strong = i;
			}
		}
		
		if(strong == 0) 
		{
			moveUp();
		}
		else if(strong == 1)
		{
			moveDown();
		}
		else if(strong == 2)
		{
			moveLeft();
		}
		else if(strong == 3)
		{
			moveRight();
		}
			
	}
	
	/**
	 * Returns the ID of this Blob
	 * @return
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * Returns the position of the Blob
	 * @return
	 */
	public int[] getPos()
	{
		position[0] = x;
		position[1] = y;
		
		return position;
	}
	
	/**
	 * Returns the age of this Blob
	 * @return
	 */
	public int getAge()
	{
		return age;
	}
	
	/**
	 * Increases the age of this Blob by 1
	 */
	public void older()
	{
		if(age == 0)
		{
			System.out.println("Dead");
		}
		else
		{
			age++;
		}
	}

	/**
	 * Chance of death
	 */
	public boolean deathChance(double deathProb, int pre)
	{
		prob.setPre(pre);
		
		return prob.Chance(deathProb);
	}
	
	/**
	 * Chance of replicating
	 */
	public boolean repChance(double repProb, int pre)
	{
		prob.setPre(pre);
		
		return prob.Chance(repProb);
	}

	/**
	 * Moves Blob to the right 1 unit per cycle
	 */
	public void moveRight()
	{
		if((x + 1 < grid.length) && (grid[y][x + 1] == null) && !moved)
		{
			grid[y][x] = null;
			x++;
			grid[y][x] = this;
			moved = true;
		}
	}
	
	/**
	 * Moves Blob to the left 1 unit per cycle
	 */
	public void moveLeft()
	{
		if((x - 1 >= 0) && (grid[y][x - 1] == null) && !moved)
		{
			grid[y][x] = null;
			x--;
			grid[y][x] = this;
			moved = true;
		}
	}
	
	/**
	 * Moves Blob up 1 unit per cycle
	 */
	public void moveUp()
	{
		if((y - 1 >= 0) && (grid[y - 1][x] == null) && !moved)
		{
			grid[y][x] = null;
			y--;
			grid[y][x] = this;
			moved = true;
		}
	}
	
	/**
	 * Moves Blob down 1 unit per cycle
	 */
	public void moveDown()
	{
		if((y + 1 < grid.length) && (grid[y + 1][x] == null) && !moved)
		{
			grid[y][x] = null;
			y++;
			grid[y][x] = this;
			moved = true;
		}
	}

	/**
	 * Prints out the grid of the Blobs
	 */
	public void PrintGrid()
	{
		for(int m = 0; m < grid.length; m++)
		{
			for(int n = 0; n < grid[0].length; n++)
			{
				if(grid[m][n] != null)
				{
					if(grid[m][n].getID() > 9)
					{
						System.out.print("B" + grid[m][n].getID() + " ");
					}
					else
					{
						System.out.print("B" + grid[m][n].getID() + "  ");
					}
				}
				else
				{	
					System.out.print(".   ");
				}
			}
			
			System.out.println();
		}
	}
	
	/**
	 * Resets the boolean flag Moved to false for the next cycle
	 */
	public void resetMoved()
	{
		moved = false;
	}
}


