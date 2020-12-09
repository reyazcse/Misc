//https://leetcode.com/problems/longest-common-prefix/

/*
Solution: Using binary search.
		  Idea is find the length of the shortest string: minLen.
		  And then we find the prefix in all the strings using this minLen.
		  While finding out if all strings contain the same prefix, we can take any string as 
		  reference. Here we take the first string (strs[0]) as the reference.
		  
References: https://www.geeksforgeeks.org/longest-common-prefix-using-binary-search/
 * */
package leetcode;

public class LongestCommonPrefix3 {
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		int minLen = getMinLen(strs);
		
		int lo=0, hi = minLen-1;
		StringBuilder prefix = new StringBuilder();
		while(lo <= hi) {
			int mid = lo + (hi-lo)/2;
			if(allContainsCommonPrefix(strs, strs[0], lo, mid)) {
				prefix.append(strs[0].substring(lo, mid+1));
				lo = mid + 1;
			}else {
				hi = mid - 1;
			}
		}
		return prefix.toString();
	}
	
	/*
	 * Returns true if all the strings in the strs array have same prefix from 
	 * index start till index end.
	 * The reference string is taken to be strs[0]
	 * */
	private boolean allContainsCommonPrefix(String[] strs, String reference, int start, int end) {
		for(String str : strs) {
			for(int i=start; i<=end; i++) {
				char c = reference.charAt(i);
				if(str.charAt(i) != c) {
					return false;
				}
			}
		}
		return true;
	}
	
	
	private int getMinLen(String[] strs) {
		int len = Integer.MAX_VALUE;
		for(String str : strs) {
			len = Math.min(len, str.length());
		}
		
		return len;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
