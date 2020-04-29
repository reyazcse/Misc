//src: https://leetcode.com/problems/palindromic-substrings/
/*
	Input: "aaa"
	Output: 6
	Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * */
package misc;

public class PalinSubstrings {
	public int countSubstrings(String s) {
		int ans = 0;
		int len = s.length();
		int[][] dp = new int [len][len];
		//initialize with -1
		for(int i=0;i<len; i++)
			for(int j=0; j<len; j++)
				dp[i][j] = -1;
		//check all substrings
		for(int i=0; i<len; i++) {
			for (int j=i;j<len ;j++) {
				ans += countSubstringsUtl(s,i,j,dp);
			}
		}
		return ans;
		
    }
	public int countSubstringsUtl(String s, int i, int j, int [][] dp) {
		if (i == j || i>j) return 1;				//substring found
		if (s.charAt(i) != s.charAt(j)) return 0;
		if (dp[i][j] != -1) return dp[i][j];
		dp[i][j] = countSubstringsUtl(s, i+1, j-1, dp);
		return dp[i][j];
	}
	public static void main (String[] args) {
		String s = "aaa";
		PalinSubstrings obj = new PalinSubstrings();
		System.out.println(obj.countSubstrings(s));
	}

}
