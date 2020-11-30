/*
You are given a string s containing lowercase letters and an integer k. You need to :

First, change some characters of s to other lowercase English letters.
Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
Return the minimal number of characters that you need to change to divide the string.

Example 1:

Input: s = "abc", k = 2
Output: 1
Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
* */

//Solution: Using 3D table and top down dp

package leetcode;

public class PalindromePartitioningIII {
	public int palindromePartition(String s, int k) {
		int[][][] dp = new int[s.length()] [s.length()] [k+1];
		
		//initialize the table
		for(int i=0; i<s.length(); i++) {
			for(int j=0; j<s.length(); j++) {
				for(int x=0; x<=k; x++) {
					dp[i][j][x] = -1;
				}
			}
		}
		
		return helper(s, k, 0, s.length()-1, dp);
	}
	
	//returns the minimum number of changes to divide s[start...end] into k substrings
	private int helper(String s, int k, int start, int end, int[][][]dp) {
		if(start > end) {			//empty substring
			if(k <= 0) {
				return 0;
			}
			else {								// k>0
				return Integer.MAX_VALUE;
			}
		}
		
		
		int len = end-start+1;
		if(len == k) {								//if len == k then each substring will be of length = 1 and is a palindrome
			dp[start][end][k] = 0;
			return 0;
		}
		
		if(len < k) {								
			return Integer.MAX_VALUE;				//we can't divide into k substrings since length of sub string is less than k
		}
		
		if(k == 1) {
			dp[start][end][k] = countMakePalindrome(s, start, end);
			return dp[start][end][k];
		}
		
		if(dp[start][end][k] != -1) {				//value precomputed	
			return dp[start][end][k];				
		}
		
		
		int current_ans = Integer.MAX_VALUE;
		for(int i=start; i<=end; i++) {
			int left = helper(s, 1, start, i, dp);
			int right = helper(s, k-1, i+1, end, dp);
			if(left != Integer.MAX_VALUE && right != Integer.MAX_VALUE) {		//check if max value is not returned
				current_ans = Math.min(current_ans, left+right);
			}
			
		}
		dp[start][end][k]=current_ans;
		return current_ans;
	}
	
	//returns the total number of chagne
	private int countMakePalindrome(String s, int start, int end) {
//		if(end < start) {
//			return Integer.MAX_VALUE;
//		}
		int ans = 0;
		while(start < end) {
			if(s.charAt(start) != s.charAt(end)) {
				ans++;
			}
			start++;
			end--;
		}
		return ans;
	}
	public static void main(String[] args) {
		PalindromePartitioningIII ob = new PalindromePartitioningIII();
		String s = "abc";
		int k=2;
		System.out.println(ob.palindromePartition(s, k));

	}

}
