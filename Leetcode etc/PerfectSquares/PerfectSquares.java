//https://leetcode.com/problems/perfect-squares/
/*
Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 * */

/*
Solution:
	Take square root of n, say i
	Now we have to subtract i, i-1, ... 1 from n.
	At each step we have two options: include this number in subtraction or do not include this number in subtraction
	
	
	Below are three solutions:
	
 * */
package leetcode;

public class PerfectSquares {
	//Recursive: TLE
	public int numSquares(int n) {
		int i = (int)Math.sqrt(n);
		return utl(n, i);
	}
	
	private int utl(int n, int i) {
		if(n < 0) {
			return 9999;
		}
		if(i == 1) {
			return n;
		}
		if (n == 0) {
			return 0;
		}
		int include = 1 + utl(n-i*i, i);
		int exclude = utl(n, i-1);
		
		return Math.min(include, exclude);
	}
	
	//TOP DOWN
	//O (n * sqrt(n)) time and space each
	public int numSquares_TopDown(int n) {
		int i = (int)Math.sqrt(n);
		Integer [][] dp = new Integer[n+1][i+1];
		return utl_TopDown(n, i, dp);
	}
	
	private int utl_TopDown(int n, int i, Integer[][] dp) {
		if(n < 0) {
			return 9999;
		}
		if(i == 1) {
			return n;
		}
		if (n == 0) {
			return 0;
		}
		if(dp[n][i] != null) {
			return dp[n][i];
		}
		int include = 1 + utl_TopDown(n-i*i, i, dp);
		int exclude = utl_TopDown(n, i-1, dp);
		
		dp[n][i] = Math.min(include, exclude);
		return dp[n][i];
	}
	
	//BOTTOM UP
	//O (n * sqrt(n)) time and space each
	public int numSquares_BottomUp(int n) {
		int i = (int)Math.sqrt(n);
		int [][] dp = new int[n+1][i+1];
		
		for(int r=1; r<=n; r++) {
			for(int c=1; c<=i; c++) {
				if(c == 1) {
					dp[r][c] = r;
				}else {
					int include = 1 + ((r >= c*c) ? dp[r - c*c][c]:9999);			//9999(big value) and not 0 since if r-c*c is negative, then no answer possible if we include i
					int exclude = dp[r][c-1];
					dp[r][c] = Math.min(include, exclude);
				}
			}
		}
        return dp[n][i];
	}
	
	public static void main(String[] args) {
		PerfectSquares ob = new PerfectSquares();
		System.out.println(ob.numSquares(12));
		System.out.println(ob.numSquares_BottomUp(12));

	}

}
