//https://leetcode.com/problems/longest-palindromic-subsequence/
//Question: Find the length of the longest palindromic subsequence in a string
//for example if the string is "tagcta", output is 3 (since lps is "tat")

package practice;

public class LongestPalindromeSubSeq {
	//recursive brute force
	public static int lps(String str) {
		if (str.length() == 0) return 0;
		return utl(str, 0, str.length()-1);
	}

	public static int utl (String str, int l, int r) {
		//one char
		if (l == r) return 1;
		//two chars
		if (r == (l+1) && (str.charAt(l) == str.charAt(r)))
			return 2;
		
		if (str.charAt(l) == str.charAt(r))
			return 2 + utl(str, l+1, r-1);
		
		return Math.max(utl(str, l, r-1), utl(str, l+1, r));
	}
	
	//using DP 
	public static int dpLps(String str) {
		if (str.length() == 0) return 0;  //empty string
		int n = str.length();
		int [][]table = new int[n][n];
		
		for (int i=0; i<n ;i++)
			table[i][i] = 1;
		//filling the upper portion of table
		for(int len = 2; len<=n; len++) {
			for (int i=0; i< n-len+1; i++) {
				int j = i+len-1;
				if (len == 2 && str.charAt(i) == str.charAt(j)) {
					table[i][j] = 2;
				}
				else if (str.charAt(i) == str.charAt(j)) {
					table[i][j] = 2 + table[i+1][j-1];
				}
				else 
					table[i][j] = Math.max(table[i+1][j], table[i][j-1]);
			}
		}
		printTable(table);
		return table[0][n-1];
	}
	
	public static void printTable(int [][] table) {
		for (int i=0; i< table.length; i++) {
			System.out.println();
			for (int j=0; j<table[0].length; j++) {
				System.out.print(table[i][j] + "  ");
			}
		}
	}
	
	//ANOTHER way is to reverse the copy of the string and then find LCS of two strings
	public static int dpLpsUsingLCS(String str) {
		if(str == null || str.length() == 0)
			return 0;
		int n = str.length();
		StringBuilder input = new StringBuilder();
		input.append(str);
		input = input.reverse();
		String strCp = input.toString();
		int[][]dp = new int[n+1][n+1];
		for(int i=0; i<=n; i++) {
			dp[i][0] = 0;
			dp[0][i] = 0;
		}
		for(int i=1; i<=n ;i++) {
			for(int j=1; j<=n; j++) {
				if(str.charAt(i-1) == strCp.charAt(j-1)) {
					dp[i][j] = 1 + dp[i-1][j-1]; 
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[n][n];
		
	}
	public static void main (String [] args) {
//		System.out.println(lps("tagcta"));
//		System.out.println(dpLps("tagcta"));
		System.out.println(dpLpsUsingLCS("tagcta"));
	}
}
