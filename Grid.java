package Environment;

import Creature.Blob;
import Population.Probability;

public class Grid {

	int size = 8;
	Blob[][] grid = new Blob[size][size];
	
	public static void main(String[] args)
	{
		int cycles = 10;
		int size = 8;
		Blob[][] grid = new Blob[size][size];
		Blob t = new Blob(grid, -1, -1, -1);
		int ID = 1;
		
		Probability prob = new Probability();
		
		for(int i = 2; i <= 5; i++)
		{
			for(int k = 2; k <= 5; k++)
			{				
				grid[i][k] = new Blob(grid, k, i, ID);
				ID++;
			}
		}
		
		t.PrintGrid();
		
		//
		for(int i = 0; i < cycles; i++)
		{
			double[] inputs = new double[4];
			
			for(int j = 0; j < inputs.length; j++)
			{
				inputs[j] = prob.randGen();
			}
			
			for(int m = 0; m < grid.length; m++)
			{
				for(int n = 0; n < grid[0].length; n++)
				{
					if(grid[m][n] != null)
					{
						grid[m][n].core(inputs);
					}
				}
			}

			for(int m = 0; m < grid.length; m++)
			{
				for(int n = 0; n < grid[0].length; n++)
				{
					if(grid[m][n] != null)
					{
						grid[m][n].resetMoved();;
					}
				}
			}
			
			System.out.println();
			
			t.PrintGrid();
		}
	}
}
