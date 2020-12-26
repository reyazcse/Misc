//https://leetcode.com/problems/minimum-path-sum/
/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 * */

//Solution: O(m*n) time and O(1) space since modifying the given matrix
package leetcode;

public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if(grid == null || grid.length == 0) {
			return 0;
		}
		int r = grid.length;
		int c = grid[0].length;


		//fill last column from bottom till top
		for(int i=r-2; i>=0; i--) {
			grid[i][c-1] += grid[i+1][c-1];
		}




		//fill last row from right till left
		for(int i=c-2; i>=0; i--) {
			grid[r-1][i] += grid[r-1][i+1];
		}



		for(int i=r-2; i>=0; i--) {
			for(int j=c-2; j>=0; j--) {
				grid[i][j] = grid[i][j] + Math.min(grid[i+1][j], grid[i][j+1]);
			}
		}


		return grid[0][0];
	}

}
