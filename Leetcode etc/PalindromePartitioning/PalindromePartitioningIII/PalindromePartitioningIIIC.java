
//This is similar to PalindromePartitioningIIIB. Only we have optimized the method to get the number of changes to make the 
//substring palindrome using memorization
package leetcode;

public class PalindromePartitioningIIIC {
	public int palindromePartition(String s, int k) {
		int[][] cnt = new int[s.length()][s.length()];		//cnt[i][j] is total number of changes needed to make substring i...j a palindrome

		int[][]dp = new int[s.length()][k+1];

		//initialize
		for(int i=0; i<cnt.length; i++) {
			for(int j=0; j<cnt[0].length; j++) {
				cnt[i][j] = -1;
			}
		}
		//initialize
		for(int i=0; i<dp.length; i++) {
			for(int j=0; j<dp[0].length; j++) {
				dp[i][j] = -1;
			}
		}
		return helper(s, 0, k, dp, cnt);
	}
	
	private int helper(String s, int start, int k, int[][] dp, int[][] cnt) {
		if(dp[start][k] != -1) {
			return dp[start][k];
		}

		int n = s.length();
		int len = n-start;

		if(len == k) {
			return dp[start][k] = 0;				// each substring of length is a palindrome
		}
		if(len < k) {
			return dp[start][k] = Integer.MAX_VALUE;
		}

		if(k == 1) {								//consider string from start till the end
			return dp[start][k] = countMakePalindrome(s, start, s.length()-1, cnt);
		}

		int current_ans = Integer.MAX_VALUE;
		for(int i=start; i<=n-k; i++) {						//we can break till n-k th index
			int left = countMakePalindrome(s, start, i, cnt);
			int right = helper(s, i+1, k-1, dp, cnt);
			if(right != Integer.MAX_VALUE) {
				current_ans = Math.min(current_ans, left + right);
			}
		}

		return dp[start][k] = current_ans;

	}

	//returns number of changes needed to make the substring a palindrome using memorization
	private int countMakePalindrome(String s, int start, int end, int[][] cnt) {
		if(start >= end) {
			return 0;
		}
		if(cnt[start][end] != -1) {
			return cnt[start][end];
		}
		

		if(s.charAt(start) != s.charAt(end)) {
			return 1 + countMakePalindrome(s, start+1, end-1, cnt);
		}else {
			return countMakePalindrome(s, start+1, end-1, cnt); 
		}

	}
}
