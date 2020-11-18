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
	/**********************************BEST SOLUTION using COMBINATION*******************************************/
	
	/*
	 * Let's say we have 2 row and 3 columns. Then we have to move in 2 right directions and 1 down direction.
	 * So 2 R and 1 D ==> (n-1) R and (m-1) D
	 * Hence total directions  = n-1 + m-1 = n+m-2 = N
	 * The total number of movement combinations will contain 2 R and 1 D
	 * So total number of combinations = (N) C (m-1) OR (N) C (n-1)
	 * 
	 * Complexity: O(m-1) or O(n-1) depending upon how we choose to solve the combination formula
	 * 
	 * References: https://www.youtube.com/watch?v=t_f0nwwdg5o&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2 | take u forward
	 * 
	 * */
	
	public int uniquePaths(int m, int n) {
		int N = m+n-2;
		int r = m>n ? n-1:m-1;				//we can use m-1 or n-1. So we choose the smaller one
		//solve the combination
		double ans = 1;
		for(int i=1; i<=r; i++) {
			ans = ans * (N + 1-  i)/i; 		//8C2 = (8*7)/(2*1) 
		}
		return (int)ans;
	}
	
	//Note: These will not give correct answers:
	//ans *= (N+1-i)/i
	//ans = ans * ((N+1-i)/2)
	//We need to FIRST multiply ans with (N+1-i) then divide
	/**************************TOP DOWN************************************************************************/
	// O(mn time and space)
	public int uniquePathsTopDown(int m, int n) {
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
	// O(mn time and space)
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
		UniquePaths ob = new UniquePaths();
		System.out.println(ob.uniquePaths(9, 8));

	}

}
