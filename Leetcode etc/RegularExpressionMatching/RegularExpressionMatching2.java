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

//References: Tushar Roy | https://www.youtube.com/watch?v=l3hda49XcDE&t=881s
package leetcode;

public class RegularExpressionMatching2 {
	public boolean isMatch(String s, String p) {
		boolean[][]dp = new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;						//empty text and pattern
		
		//text is not empty but pattern is empty => false
		//no need of below since default values are false
		for(int i=1; i< dp.length; i++) {
			dp[i][0] = false;
		}
		
		//empty text but pattern is not empty
		//handles cases when pattern = "a*b*b*" | "a*.*" | ".a*" | "ab*b*b*b*" etc
		for(int j=1; j< dp[0].length; j++) {
			if(p.charAt(j-1) == '*') {
				dp[0][j] = dp[0][j-2];
			}
		}
		
		for(int i=1; i< dp.length; i++) {
			for(int j=1; j< dp[0].length; j++) {
				if(p.charAt(j-1) == '*') {
					
					dp[i][j] = dp[i][j-2];					//match with 0 character
					if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {	//if equal char OR . then match with 1 char
						dp[i][j] = dp[i][j] || dp[i-1][j];
					}
					
				}else if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.'){	//if char in text and pattern are same
					 
						dp[i][j] = dp[i-1][j-1];
					
				}else {					//for other cases
					dp[i][j] = false;
				}
			}
		}
		return dp[s.length()][p.length()];
	}
	public static void main(String[] args) {
		RegularExpressionMatching2 obj = new RegularExpressionMatching2();
		String s = "ab";
		String p = ".*";
		System.out.println(obj.isMatch(s, p));

	}

}
