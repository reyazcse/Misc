//https://leetcode.com/problems/palindrome-partitioning/
/*
Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 * */


package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	
	//O (n * 2 ^ n) time and O(n) space for recursive call stack
	public List<List<String>> partition(String s) {
		List<List<String>> result = new ArrayList<>();
		List<String> currentList = new ArrayList<>();
		int n = s.length();
		boolean[][]dp = new boolean[n][n]; 
		partitionUtl(s, 0, result, currentList, dp);
		return result;
	}

	private void partitionUtl(String str, int start, List<List<String>> result, List<String> currentList, boolean[][]dp) {
		if(start >= str.length()) {
			result.add(new ArrayList<>(currentList));
			return;
		}

		for(int end = start; end<str.length(); end++) {
			if(isPalindrome(str, start, end, dp)) {						//one possible solution
				String subStr = str.substring(start, end+1);
				currentList.add(subStr);							
				partitionUtl(str, end+1, result, currentList, dp);
				currentList.remove(currentList.size()-1);		//backtrack
			}
		}


	}

	//checks if palindrome in constant time
	private boolean isPalindrome(String s, int start, int end, boolean[][]dp) {
		if(s.charAt(start) == s.charAt(end) && (end - start <=2 || dp[start+1][end-1])) {
			dp[start][end] = true;														//store that it is palindrome
			return true;
		}
		return false;
	}
	public static void main(String[] args) {
		String s = "aab";
		PalindromePartitioning obj  =  new PalindromePartitioning();
		for(List<String>list : obj.partition(s)) {
			System.out.println(list);
		}

	}

	/*******************************************Using naive palindrome check method*************************************************/
	//O (n * 2 ^ n) time and O(n) space for recursive call stack
	public List<List<String>> partitionRecursive(String s) {
		List<List<String>> result = new ArrayList<>();
		List<String> currentList = new ArrayList<>();
		dfs(0, result, currentList, s);
		return result;
	}
	
	private void dfs(int start, List<List<String>> result, List<String> currentList, String s) {
		if(start == s.length()) {
			result.add(new ArrayList<>(currentList));
			return;
		}
		for(int i = start; i<s.length(); i++) {
			//String subStr = s.substring(start, i+1);
			if(isPalindromeNaive(s, start, i)) {
				currentList.add(s.substring(start, i+1));
				dfs(i+1, result, currentList, s);
				currentList.remove(currentList.size()-1);
			}
		}
	}
	
	//naive palindrome check
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

}
