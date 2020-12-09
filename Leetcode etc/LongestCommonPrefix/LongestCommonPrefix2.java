//https://leetcode.com/problems/longest-common-prefix/


/*
Solution: Using DIVIDE AND CONQUER
	Time complexity: O(S), where S = m*n. where m =  length of largest string and n = number of strings
	Space complexity: O(m * log n) for recursive call stack. 
 * */
package leetcode;

public class LongestCommonPrefix2 {
	
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		
		return longestCommonPrefix(strs, 0, strs.length-1);
	}
	
	private String longestCommonPrefix(String[] strs, int lo, int hi) {
		if(lo == hi) {
			return strs[lo];
		}
		
		int mid = lo + (hi - lo)/2;
		String lcpLeft = longestCommonPrefix(strs, lo, mid);
		String lcpRight = longestCommonPrefix(strs, mid+1, hi);
		
		return commonPrefix(lcpLeft, lcpRight);
	}
	
	//returns common prefix of two strings
	private String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for(int i=0; i<min; i++) {
			if(left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		return left.substring(0, min);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
