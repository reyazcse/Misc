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
	
	------------------------------------------------------------------------------------
	
	Method 2 is simple to understand. Similar to Method 1, but starts from beginning of the string
	References: https://www.youtube.com/watch?v=WPr1jDh3bUQ

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
	
	
	//Method1 : Dynamic programming
	
	//This is similar to Method 1 but here we find cuts from the beginning of the string
	//We also create a 2 D table where we store if a substring is a palindrome or not. We do not use
	//isPalindromeNaive as above
	
	public int minCut_2(String s) {
		int n = s.length();
		boolean [][] palindrome = new boolean[n][n];
		int[] minCuts = new int[n];
		
		for(int i=0; i<n; i++) {
			minCuts[i] = Integer.MAX_VALUE;
		}
		
		//populate palindrome table
		for(int i=0; i<n; i++) {
			palindrome[i][i] = true;
		}
		
		//if length 2 string is palindrome
		for(int i=0; i<n-1; i++) {
			palindrome[i][i+1] = s.charAt(i) == s.charAt(i+1) ? true : false;
		}
		
		//length greater than or equal to 3
		for(int len=3; len <=n; len++) {
			for(int i=0; i<= n-len; i++) {
				int j = i+len-1;
				if(s.charAt(i) == s.charAt(j) && palindrome[i+1][j-1]) {
					palindrome[i][j] = true;
				} 
			}
		}
		
		//find min cuts starting from the beginning of the string
		
		for(int i=0; i<n ; i++) {
			if(palindrome[0][i]) {		//whole string 0...i is palindrome, hence no need to cut
				minCuts[i] = 0;
			}else {						//try to cut at each position
				int temp = Integer.MAX_VALUE;
				for(int j=0; j<i; j++) {
					if(palindrome[j+1][i] && minCuts[j] + 1 < temp) {
						temp = minCuts[j] + 1;
					}
				}
				minCuts[i] = temp;
			}
		}
		return minCuts[n-1];
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Method 3 : DFS and brute force. Generate all partitions and count number of partitions for each call
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
