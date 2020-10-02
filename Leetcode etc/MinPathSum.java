//https://leetcode.com/problems/minimum-path-sum/
/*

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
 * */
package leetcode;

public class MinPathSum {
	public int minPathSum(int[][] grid) {
		
		if(grid == null || grid.length == 0)return 0;
		int r = grid.length;
		int c = grid[0].length;
		int[][]dp = new int[grid.length][grid[0].length];
		dp[r-1][c-1] = grid[r-1][c-1];
		//fill last row
		for(int j=c-2; j>=0; j--) {
			dp[r-1][j] = grid[r-1][j] + dp[r-1][j+1];
		}
		
		//fill last col
		for(int i=r-2; i>= 0; i--) {
			dp[i][c-1] = grid[i][c-1] + dp[i+1][c-1];
		}
		
		//fill remaining cells
		for(int i=r-2; i>=0; i--) {
			for(int j=c-2; j>=0; j--) {
				dp[i][j] = grid[i][j] + Math.min(dp[i][j+1], dp[i+1][j]);
			}
		}
		//printTable(dp);
		return dp[0][0];
		
    }
	private void printTable(int[][]grid) {
		for(int i=0; i<grid.length; i++) {
			System.out.println();
			for(int j=0; j<grid[0].length; j++) {
				System.out.print(grid[i][j] + "  ");
			}
		}
	}
	public static void main(String[] args) {
		int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
		MinPathSum obj = new MinPathSum();
		System.out.println(obj.minPathSum(grid));
	}

}
