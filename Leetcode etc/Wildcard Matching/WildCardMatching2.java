
//https://leetcode.com/problems/wildcard-matching/
/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
 
Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
 * */package leetcode;

public class WildCardMatching2 {
	//DP solution
	////O(mn) time and O(mn) space, where m = length of text and n = length of pattern
	public boolean isMatch(String s, String p) {
		int m = s.length();
		int n = p.length();
		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;			//empty text and empty pattern, hence true
		for(int i=1; i<=m; i++) {
			dp[i][0] = false;		//text is not empty but pattern is empty, hence false
		}
		for(int j=1; j<=n; j++) {
			if(p.charAt(j-1) == '*') {		// to handle case like pattern is "*****", but text is ""
				dp[0][j] = dp[0][j-1]; 
			}
		}
		
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(p.charAt(j-1) == '*') {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];	//either one OR zero character matched
				}else if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?') {
					dp[i][j] = dp[i-1][j-1];
				}
			}
		}
		print(dp);
		return dp[m][n];
	}
	
	private void print(boolean [][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				if(dp[i][j]) {
					System.out.print("1  ");
				}else System.out.print("0  ");
				
			}
			System.out.println();
		}
	}
	//OPtimizations: We can just use two rows and also we can change the string pattern by removing multiple *'s from "a***b***"  to "a*b*"
	//We will solve using two rows below. Other optimization is trivial
	//Below solution has O(n) space. The above had O(mn) space.
	
	//For a cell, we just need three previous values from the table: dp[i-1][j-1] or (dp[i][j-1] and dp[i-1][j])
	//So we can do with just two rows. After we are done updating the second row, we need to shift this row to first row
	//and again populate the second row
	
	////O(mn) time and O(n) space, where m = length of text and n = length of pattern
	public boolean isMatchSpaceOptimized(String s, String p) {
		int m = s.length();
		int n = p.length();
		boolean[][] dp = new boolean[2][n+1];
		dp[0][0] = true;		
		
		dp[1][0] = false;		//not empty text but empty pattern	
		
		for(int j=1; j<=n; j++) {
			if(p.charAt(j-1) == '*') {		// to handle case like pattern is "*****", but text is ""
				dp[0][j] = dp[0][j-1]; 
			}
		}
		
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(p.charAt(j-1) == '*') {
					dp[1][j] = dp[0][j] || dp[1][j-1];
				}else if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?') {
					dp[1][j] = dp[0][j-1];
				}
			}
			//shift second row to top
			shiftRow(dp);
		}
		return dp[0][n];			
	}
	
	//shifts second row to top and also initializes second row to false
	//initializing second row is NEEDED since when we are in second row, we may not update a cell if the two conditions do not
	//hold
	private void shiftRow(boolean[][]dp) {
		
		for(int j=0; j<dp[0].length; j++) {
			dp[0][j] = dp[1][j];
			dp[1][j] = false;                   //VERY IMPORTANT
		}
	}
	
	public static void main(String[] args) {
//		String s = "aa";
//		String p = "a";
		String s = "acdcb";
		String p = "a*c?b";
		WildCardMatching2 obj = new WildCardMatching2();
		//System.out.println(obj.isMatch(s, p));
		System.out.println(obj.isMatchSpaceOptimized(s, p));
		
	}

}
