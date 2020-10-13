//https://leetcode.com/problems/longest-palindromic-substring/
//Given a string s, return the longest palindromic substring in s.

/*
Solution:
	The palindrome can be either odd length or even length.
	Each letter in the given string can be the mid point of palindrom(odd length)
	or two letters can be center points (even length palindrome).
	The idea is to iterate over the string and start to look for similar letters in both the directions for a palindrome

Complexity: O(n^2) time and O(1) space
 * */
package practice;

public class LongestPalindromeSubstr {
	public String longestPalindrome(String str) {
		if(str == null || str.length() == 0) {
			return "";
		}
		int n = str.length();
		int len=1, bestLen = 1, bestStart = 0; //base case when a single letter is given in input
		for(int mid=0; mid<n; mid++) {
			//finding longest odd length palindrome with center at mid
			int low = mid-1;
			int high = mid+1;
			while(low >=0 && high < n && str.charAt(low) == str.charAt(high)) {
				len = high-low+1;
				if(len > bestLen) {
					bestLen = len;
					bestStart = low;
				}
				low--;
				high++;
			}
			
			//finding longest even length palindrome with centers at mid and mid+1
			low = mid;
			high = mid+1;
			while(low >=0 && high < n && str.charAt(low) == str.charAt(high)) {
				len = high-low+1;
				if(len > bestLen) {
					bestLen = len;
					bestStart = low;
				}
				low--;
				high++;
			}
		}
		
		return str.substring(bestStart, bestStart + bestLen);
	}
	public static void main(String[] args) {
		LongestPalindromeSubstr obj = new LongestPalindromeSubstr();
		System.out.println(obj.longestPalindrome("a"));

	}

}
