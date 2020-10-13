//https://leetcode.com/problems/longest-palindromic-substring/
//Given a string s, return the longest palindromic substring in s.

/*
	-We use a boolean table[n][n], where n = length of string
	-table[i][j] = true if str[i...j] is a palindrome
	-To calculate table[i][j], check the value of table[i+1][j-1], 
	 if the value is true and str[i] is same as str[j], then we make table[i][j] true
	 else false
	 
Complexity: O(n^2) time and O(n^2) space

 * */
package practice;

public class LongestPalindromeSubstr2 {
	//bottom up approach
	public static void lpSubString (String str) {
		int n = str.length();
		if (n == 0) return;   //base case
		
		boolean [][]table = new boolean[n][n];
		//string of length 1 is a substring
		for(int i=0; i<n; i++)
			table[i][i] = true;
		int maxLength = 1;
		int startIndex = 0;
		//handling substring of length 2
		for(int i=0; i<n-1; i++) {
			if(str.charAt(i) == str.charAt(i+1)) {
				table[i][i+1] = true;
				maxLength = 2;
				startIndex = i;
			}
		}
		
		//handling substring of length >= 3
		for (int len=3; len<=n; len++) {
			for(int i=0; i<n-len+1; i++) {
				int j = i+len-1;
				if(table[i+1][j-1] && str.charAt(i) == str.charAt(j)) {
					table[i][j] = true;
					if(len > maxLength) {
						maxLength = len;
						startIndex = i;
					}
				}
			}
		}
		
		System.out.println("Longest palindromic substring is  "+ str.substring(startIndex, startIndex + maxLength));
	}
	
	//O(n^2) and constant space
	public static void lpSubStringNonDp(String str) {
		int n = str.length();
		if(n == 0) return;
		int startIndex = 0, maxLength = 1;
		for(int i=0; i<n-1; i++) {
			int left = i, right = i;
			while (left >=0 && right < n && str.charAt(left) == str.charAt(right)) {
				if (right-left+1 > maxLength) {
					maxLength = right-left+1;
					startIndex = left;
				}
				left--;
				right++;
			}
			left = i; right = i+1;
			while (left >=0 && right < n && str.charAt(left) == str.charAt(right)) {
				if (right-left+1 > maxLength) {
					maxLength = right-left+1;
					startIndex = left;
					
				}
				left--;
				right++;
			}
		}
		System.out.println("Longest palindromic substring is  "+ str.substring(startIndex, startIndex + maxLength));
	}
	
	public static void main (String [] args) {
		lpSubString("gega");
		lpSubStringNonDp("geega");
	}

}
