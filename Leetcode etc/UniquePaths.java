//https://leetcode.com/problems/unique-paths/
/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?
Example:
Input: m = 3, n = 2
Output: 3
 * */

package leetcode;

public class UniquePaths {
	
	/**************************TOP DOWN************************************************************************/
	public int uniquePaths(int m, int n) {
		int [][] dp = new int[m][n];
		return utl(0, 0, dp);
	}
	
	private int utl(int r, int c, int [][]dp) {
		if(r < 0 || r >= dp.length || c < 0  || c >= dp[0].length) {
			return 0;
		}
		if(r == dp.length-1 && c == dp[0].length-1) {
			return 1;
		}
		if(dp[r][c] != 0) {
			return dp[r][c];
		}
		int waysRight = utl(r, c+1, dp);
		int waysDown = utl(r+1, c, dp);
		dp[r][c] = waysRight + waysDown;
		return dp[r][c];
	}
	
	/****************************BOTTOM UP*******************************************************************/
	
	public int uniquePathsBottomUp(int m, int n) {
		int [][] dp = new int[m][n];
		dp[m-1][n-1] = 1;
		for(int i=m-1; i>=0; i--) {
			for(int j=n-1; j>=0; j--) {
				if(i == m-1 || j == n-1) {
					dp[i][j] = 1;
				}else {
					dp[i][j] = dp[i][j+1] + dp[i+1][j];
				}
			}
		}
		return dp[0][0];
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
