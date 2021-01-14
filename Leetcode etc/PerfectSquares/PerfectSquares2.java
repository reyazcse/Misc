//https://leetcode.com/problems/perfect-squares/
/*
Solution:
	Similar to LIS idea

Complexity: O(n * sqrt(n)) time and O(n) space
 * */
package leetcode;

public class PerfectSquares2 {
	public int numSquares(int n) {
		int[]dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i=2; i<=n; i++) {
			int min = Integer.MAX_VALUE;
			for(int j=1; j*j <= i; j++) {
				min = Math.min(min, 1 + dp[i-j*j]);
			}
			dp[i] = min;
		}
		return dp[n];
	}

}
