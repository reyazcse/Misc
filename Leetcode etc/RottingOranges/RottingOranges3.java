//https://leetcode.com/problems/rotting-oranges/

//Solution: Naive algorithm. We can do efficiently using BFS. See RottingOranges.java
/*
References: https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
Complexity: O (max (r,c) * r*c) time and O(1) space

 * */
package leetcode;

public class RottingOranges3 {
	public int orangesRotting(int[][] grid) {
		int[] x = {0,-1,0,1};
		int[] y = {-1,0,1,0};
		int mins = 0;
		boolean changed = false;
		int current_val = 2;						//initially value for rotten orange  is 2
		while(true) {
			for(int i=0; i<grid.length; i++) {
				for(int j=0; j<grid[0].length; j++) {
					if(grid[i][j] == current_val) {
						
						//process adjacent 4 cells
						for(int k=0; k<4; k++) {
							int row = i + x[k];
							int col = j + y[k];
							if(isSafe(grid, row, col) && grid[row][col] == 1) {
								grid[row][col] = current_val + 1;
								changed = true;
							}
						}
					}
				}
			}
			if(!changed) {
				break;
			}else {
				changed = false;
				mins++;						//minutes elapsed
				current_val++;				//next value to check rotten oranges
			}
		}
		
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 1) {
					return -1;
				}
			}
		}
		return mins;
		
	}
	
	private boolean isSafe(int[][] grid, int r, int c) {
		return (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length);
	}
	
	public static void main(String[] args) {
		int[][]grid = {{2,1,1},{1,1,0},{0,1,1}};
		System.out.println(new RottingOranges3().orangesRotting(grid));

	}

}
