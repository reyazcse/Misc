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
 * */
package leetcode;

public class WildCardMatching {
	//RECURSION
	public boolean isMatch(String s, String p) {
		return go(s, p, 0, 0);
	}

	private boolean go(String s, String p, int sPos, int pPos) {
		//if pattern is processed, then return true if text is also processed
		if(pPos == p.length()) {
			return sPos == s.length();
		}
		
		//if text is processed 
		if(sPos == s.length()) {
			for(int i=pPos; i< p.length(); i++) {
				if(p.charAt(i) != '*') {
					return false;			//character other than * found
				}
			}
			return true;			//return true if we have *'s remaining
		}
		
		char sChar = s.charAt(sPos);
		char pChar = p.charAt(pPos);
		if(pChar == '*') {
			return go(s, p, sPos+1, pPos) ||  go(s, p, sPos, pPos+1);	
		}else if (pChar == '?' || sChar == pChar) {
			return go(s, p, sPos+1, pPos+1);
		}
		return false;
	}
	
	/*****************************************************DP solution of the above*******************************************/
	//O(mn) time and O(mn) space, where m = length of text and n = length of pattern
	//ACCEPTED
	public boolean isMatchDp(String s, String p) {
		//0=false; 1=true; -1 means unprocessed
		int[][]dp = new int[s.length()][p.length()];				//table for dp
		initialize(dp);
		return goDp(s, p, 0, 0, dp)==1 ? true:false;
	}
	
	private int goDp(String s, String p, int sPos, int pPos, int[][]dp) {
		//pattern is processed
		if(pPos == p.length()) {
			return sPos == s.length()? 1 : 0;
		}
		
		//text is processed
		if(sPos == s.length()) {
			for(int i=pPos; i< p.length(); i++) {
				if(p.charAt(i) != '*') {
					return 0;
				}
			}
			return 1;
		}
		
		//if value is already computed
		if(dp[sPos][pPos] != -1) {
			return dp[sPos][pPos];
		}
		
		char sChar = s.charAt(sPos);
		char pChar = p.charAt(pPos);
		if(pChar == '*') {
			if(goDp(s, p, sPos+1, pPos, dp)==1 ||  goDp(s, p, sPos, pPos+1, dp) == 1) {
				dp[sPos][pPos] =  1;			//true
			}else {
				dp[sPos][pPos] =  0;			//false
			}
			
		}else if (pChar == '?' || sChar == pChar) {
			dp[sPos][pPos] =  goDp(s, p, sPos+1, pPos+1, dp);
		}
		
		return dp[sPos][pPos];				
	}
	
	private void initialize(int [][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
	}
	public static void main(String[] args) {
		String s = "abc";  //""            
		String p = "a";    //"******"
		WildCardMatching obj = new WildCardMatching();
		System.out.println(obj.isMatch(s, p));
	}

}
