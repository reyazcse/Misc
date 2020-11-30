//https://leetcode.com/problems/palindrome-partitioning-ii/
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
* */

/*
Solution:
	In method 1, we solve it using DP.
	Here is the idea:
	s = "abac"
	If we partition like : a | bac then if we already calculatd min cut for  "bac", then ans = 1 + minCut("bac")
	Then we partition like ab | ac . Here ab is not palindrome so we cannot partition like this.
	So again we partition : aba | c. Here min cut = 1 + minCut("c")
	
	Similar we start from b and process like this:
	..b| ac  , ..bac | . Note we cannot partition like ..ba | ac since ba is not a palindrome
	
	We use a table for this and start from end of the string.
	Also note that if we find that whole substring is palindrome, then no need to partition

 * */
package leetcode;

public class PalindromePartitioningII {
	
	//Method1 : Dynamic programming
	public int minCutDp(String s) {
		int size = s.length();
		int [] dp = new int[size];
		for(int i=0; i<dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[size-1] = 0;
		for(int i=size-2; i>=0; i--) {
			for(int j=i; j<size; j++) {
				if(isPalindromeNaive(s, i, j)) {
					if(j == size-1) {
						dp[i] = 0;						//no cut required as while sub string s[i..size-1] is a palindrome
					}else {
						dp[i] = Math.min(dp[i], 1+dp[j+1]);
					}
				}
			}
		}
		return dp[0];
	}
	
	private boolean isPalindromeNaive(String s, int start, int end) {
		while(start < end) {
			if(s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
            end--;
		}
		return true;
	}
	
	
	//Method 2 : DFS and brute force. Generate all partitions and count number of partitions for each call
	//below gives TLE. it is based on Palindrome Partitioning I
	public int minCut(String s) {
		Minimum minimum = new Minimum();								//keeps track of overall minimum
		boolean[][] dp = new boolean[s.length()][s.length()];
		dfs(0, 0, s, minimum, dp);
		return minimum.minValue-1;
	}
	
	private void dfs(int start, int count, String s, Minimum minimum, boolean[][] dp) {
		if(start == s.length()) {											//end of string: all partitions are done for one call
			minimum.minValue = Math.min(minimum.minValue, count);			//calculate min so far
			return;
		}
		for(int i=start; i<s.length(); i++) {
			if(isPalindrome(s, start, i, dp)) {
				dfs(i+1, count+1, s, minimum, dp);				//count+1 in next call as 1 partition is done here
			}
		}
	}
	
	//checks if sub string from start till end is palindrome using memorization
	private boolean isPalindrome(String s, int start, int end, boolean [][] dp) {
		if(s.charAt(start) == s.charAt(end) && (end-start <= 2 || dp[start+1][end-1])) {
			dp[start][end] = true;
			return true;
		}
		return false;
		
	}
	public static void main(String[] args) {
		String s = "abbab";
		PalindromePartitioningII ob = new PalindromePartitioningII();
		System.out.println(ob.minCut(s));;

	}
	
	
	private class Minimum {
		public int minValue;
		public Minimum() {
			minValue = Integer.MAX_VALUE;
		}
	}

}
