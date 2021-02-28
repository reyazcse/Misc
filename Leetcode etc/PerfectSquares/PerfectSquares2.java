//https://leetcode.com/problems/perfect-squares/
/*
Solution:
	If we use loop, then we need 1D dp table only.
	In PerfectSquares.java, we use include exclude method and we need 2D table
	
	

Complexity: O(n * sqrt(n)) time and O(n) space
 * */
package leetcode;

public class PerfectSquares2 {

	//Recursive : loop
	public int numSquares_Recursive(int n) {
		if(n == 0) {
			return 0;
		}

		int ans = Integer.MAX_VALUE;
		for(int i=1; i*i<=n; i++) {
			ans = Math.min(ans, numSquares(n - i*i));

		}
		return 1+ans;
	}

	//top down : loop

	public int numSquares_topDown(int n) {

		Integer[] dp = new Integer[n+1];
		dp[0] = 0;
		utl_topDown(n, dp);
		return dp[n];
	}

	private int utl_topDown(int n, Integer[] dp) {
		if(n == 0) {
			return 0;
		}
		if(dp[n] != null) {
			return dp[n];
		}

		int ans = Integer.MAX_VALUE;
		for(int i=1; i*i<=n; i++) {
			ans = Math.min(ans, utl_topDown(n - i*i, dp));

		}
		return dp[n] = 1+ans;
	}

	//Bottom up: loop
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
