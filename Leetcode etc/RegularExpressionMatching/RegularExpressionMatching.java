//https://leetcode.com/problems/regular-expression-matching/
/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where: 
'.' Matches any single character.​​​​
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * */

/*
Solution:
	Given that the * is always preceded by . or any character, we take two characters from the pattern(p) at a time.
	If second char is *, then we have to match  with 0 or more characters in the text. So we recurse for two cases.
	Match with 0 character in the text is given by this recursion : go(s, p, i, j+2)
	Match with 0 or more character in the text is given by this recursion : go(s, p, i+1, j)
	If second char is not *, then we have to see if first character matches with the character in the text and recurse.
	
	We need to handle base case too.
	
	1. When we are at end of text, we have to return true if we find that we have still this type of string left in the 
	pattern to process:  "a*a*" | ".*a*" | ".*" | ".*.*.*.*"
	We also return true if we find that we are at the end of the pattern also
	We return false if the second character is not *. For example, p = "aa" | "a." | "ab"
	
	2. When we are end of the pattern, we return true if we are at the end of the text too, else false.
	
	Below we have implemented recursion as well as DP version of the above logic
	
 * */
package leetcode;

public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		return go(s, p, 0, 0);
	}

	private boolean go(String s, String p, int i, int j) {
		if(i == s.length()) {	//end of the text
			if(j == p.length()) {
				return true;		//if we are at the end of the pattern and text
			}
			
			//we need to check for remaining string in the pattern
			while(j < p.length()) {
				char first = p.charAt(j);
				char second = j+1 < p.length() ? p.charAt(j+1) : '\0';
				if(second != '*') {		//If second char is not *, it means there is one char in the pattern, but text is empty at
					return false;		//this point, hence return false
				}
				j+=2;					//jump by 2 since we have taken second character
			}
			return true;
		}

		if(j == p.length()) {			//end of pattern
			return i == s.length();		//returns true if text is also processed
		}

		char first = p.charAt(j);
		char second = j+1 < p.length() ? p.charAt(j+1) : '\0';
		boolean ans = false;
		if(second == '*') {
			if(first == s.charAt(i) || first == '.') {	//if i'th char matches : either equal or a '.'
				ans = go(s, p, i, j+2) || go(s, p, i+1, j); //match with 0 char in text OR with more char in text respectively 

			}

			else{			//if i'th char does not match then we match pattern char with 0 char in text and recurse
				ans = go(s, p, i, j+2);
			}

		}else {			//second char is a letter or '.'
			if(first == s.charAt(i) || first == '.') {	//recurse only if first char matches with the ith char in text else do not recurse
				ans = go(s, p, i+1, j+1);
			}
		}
		return ans;


	}

	/************************************************************************************************************************/
	//DP version of above recusion
	public boolean isMatchDp(String s, String p) {
		int[][]dp = new int[s.length()][p.length()];				//table for dp
		initialize(dp);
		return goDp(s, p, 0, 0, dp) == 1? true : false;
	}

	private int goDp(String s, String p, int i, int j, int[][]dp) {
		if(i == s.length()) {
			if(j == p.length()) {
				return 1;
			}

			while(j < p.length()) {
				char first = p.charAt(j);
				char second = j+1 < p.length() ? p.charAt(j+1) : '\0';
				if(second != '*') {   // || first == '.'
					return 0;
				}
				j+=2;
			}
			return 1;
		}

		if(j == p.length()) {
			return i == s.length()? 1 : 0;
		}

		//if value is already computed
		if(dp[i][j] != -1) {
			return dp[i][j];
		}

		char first = p.charAt(j);
		char second = j+1 < p.length() ? p.charAt(j+1) : '\0';
		int ans = 0;
		if(second == '*') {
			if(first == s.charAt(i) || first == '.') {
				if(goDp(s, p, i, j+2, dp) == 1 || goDp(s, p, i+1, j, dp) == 1) {
					ans = 1; 
				}

			}

			else{
				ans = goDp(s, p, i, j+2, dp);
			}

		}else {
			if(first == s.charAt(i) || first == '.') {
				ans = goDp(s, p, i+1, j+1, dp);
			}
		}
		return ans;


	}

	private void initialize(int [][] dp) {
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
	}
	public static void main(String[] args) {

		RegularExpressionMatching obj = new RegularExpressionMatching();
		//		String s = "pi";
		//		String p = "p*.";
//		String s = "ab";
//		String p = ".*..";
		String s = "";
		String p = ".*.*";
		
		System.out.println(obj.isMatch(s, p));
	}

}
