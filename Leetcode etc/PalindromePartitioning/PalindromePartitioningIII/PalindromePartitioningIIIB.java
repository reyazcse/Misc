/*
Solution: Using 2D table

[ I ] We start breaking the string from from first index to string.length()-k

Example:

aabbc,k=3
break at i=0, a recursion(abbc ,k=2)
break at i=1, aa recursion(bbc ,k=2)
break at i=2 , aab recursion(bc,k=2)
if we break at i=3, aabb recursion(c ,k=2) 2 substrings cant be acheived from "c"

so we have to break from index=0 to string.length()-number of substrings

[ 2 ]Problem is divided into smaller subproblems and result from those subproblems is mainted in result variable .
[ 3 ]After breaking at particular index,
we have to find how many characters need to be changed string.substring(0,index+1)+ result from recursion of subprobelm [ recursion(string.substring(index+1),k-1) ]

For example -

aabbc
break at index 2- aa (bbc, k=2)
Here , as "aa" is plaindrome no need to change any character, so result=0+recursion(bbc,2)

In result we have to store the minimum of all the subproblems

[ 4 ]Base case for recursion should be

if(k=1), no further breaking of string is possible ,so we have to return number of characters need to be changed
if( string.length()==k), means we need to divide whole string into substrings of single length.As a string of length=1 is always a palindrome we return 0 changes.

[ 5 ]To prevent repeated calculations ,we do memorization 

References: https://leetcode.com/problems/palindrome-partitioning-iii/discuss/864822/Java-Top-down-DP-short-and-crisp-code-with-explanation
 * */
package leetcode;

public class PalindromePartitioningIIIB {
	public int palindromePartition(String s, int k) {
		int[][]dp = new int[s.length()][k+1];
		
		//initialize
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		return helper(s, 0, k, dp);
	}
	
	private int helper(String s, int start, int k, int[][] dp) {
		if(dp[start][k] != -1) {
			return dp[start][k];
		}
		
		int n = s.length();
		int len = n-start;
		
		if(len == k) {
			return dp[start][k] = 0;					// each substring of length 1 is a palindrome
		}
		if(len < k) {								
			return dp[start][k] = Integer.MAX_VALUE;	//can't partition the substring into k substrings
		}
		
		if(k == 1) {									//consider string from start till the end
			return dp[start][k] = countMakePalindrome(s, start, s.length()-1);
		}
		
		int current_ans = Integer.MAX_VALUE;
		for(int i=start; i<=n-k; i++) {						//we can break till n-k th index
			int left = countMakePalindrome(s, start, i);
			int right = helper(s, i+1, k-1, dp);
			if(right != Integer.MAX_VALUE) {
				current_ans = Math.min(current_ans, left + right);
			}
		}
		
		return dp[start][k] = current_ans;
		
	}
	
	//returns the minimum number of changes to make substring a palindrome
	//Note: we can further optimize this using memorization. See PalindromePartitioningIIIC
	private int countMakePalindrome(String s, int start, int end) {
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
		PalindromePartitioningIIIB ob = new PalindromePartitioningIIIB();
		String s = "abc";
		int k=2;
		System.out.println(ob.palindromePartition(s, k));
 

	}

}
