//https://leetcode.com/problems/edit-distance/
/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 * */

//Solution

package leetcode;
//Assumption : lower letter and length >=0
public class EditDistance {
	
	//RECURSIVE: Gives TLE
	public int minDistance(String word1, String word2) {
		return utl(word1, word2, word1.length(), word2.length());
	}
	
	private int utl(String word1, String word2, int len1, int len2) {
		//base case
		if(len1 == 0 && len2 == 0) {
			return 0;
		}
		if(len1 == 0) {						//if word1 is empty, then we can insert the remaining characters of word2 into word1
			return len2;
		}
		if(len2 == 0) {						//if word2 is empty, then we can delete the remaining characters of word1
			return len1;
		}
		
		char c1 = word1.charAt(len1-1);
		char c2 = word2.charAt(len2-1);
		if(c1 == c2) {										//characters at this position are equal
			return utl(word1, word2, len1-1, len2-1);
		}else {												//characters at this position are not equal, hence can delete or replace or insert
			int delete = utl(word1, word2, len1-1, len2);
			int replace = utl(word1, word2, len1-1, len2-1);
			int insert = utl(word1, word2, len1, len2-1);
			return 1 + Math.min(delete, Math.min(replace, insert));  //return minimum of three operations
		}
		
	}
	
	
	//TOP DOWN
	public int minDistance_topDown(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] dp = new int[len1+1][len2+1];
		return utl_topDown(word1, word2, word1.length(), word2.length(), dp);
	}

	private int utl_topDown(String word1, String word2, int len1, int len2, int [][] dp) {
		//base case
		if(len1 == 0 && len2 == 0) {
			return 0;
		}
		if(len1 == 0) {
			return len2;
		}
		if(len2 == 0) {
			return len1;
		}
		
		if(dp[len1][len2] != 0) {
			return dp[len1][len2];
		}
		
		int ans = 0;
		
		char c1 = word1.charAt(len1-1);
		char c2 = word2.charAt(len2-1);
		if(c1 == c2) {
			ans = utl_topDown(word1, word2, len1-1, len2-1, dp);
		}else {
			int delete = utl_topDown(word1, word2, len1-1, len2, dp);
			int replace = utl_topDown(word1, word2, len1-1, len2-1, dp);
			int insert = utl_topDown(word1, word2, len1, len2-1, dp);
			ans = 1 + Math.min(delete, Math.min(replace, insert));
		}
		
		dp[len1][len2] = ans;
		return ans;
	}

	
	
	//Bottom up
	public int minDistance_BottomUp(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		int[][] dp = new int[len1+1][len2+1];
		
		for(int i=0; i<=len1; i++) {
			for(int j=0; j<=len2; j++) {
				if(i == 0 && j == 0) {
					dp[i][j] = 0;
				}else if(i == 0) {
					dp[i][j] = j;
				}else if(j == 0) {
					dp[i][j] = i;
				}else {
					char c1 = word1.charAt(i-1);
					char c2 = word2.charAt(j-1);
					if(c1  == c2) {
						dp[i][j] = dp[i-1][j-1];
					}else {
						int delete = dp[i-1][j];
						int replace = dp[i-1][j-1];
						int insert = dp[i][j-1];
						dp[i][j] = 1 + Math.min(delete, Math.min(replace, insert));
					}
				}
			}
		}
		return dp[len1][len2];
	}
	
	
	
	
	
	public static void main(String[] args) {
		String word1 = "intention";
		String word2 = "execution";
		
		EditDistance ob = new EditDistance();
		System.out.println(ob.minDistance_BottomUp(word1, word2));

	}

}
