//https://leetcode.com/problems/longest-common-prefix/
/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

 * */

/*
Solution:
	Using vertical scanning approach.
	
	Time complexity: O(S), where S = m*n. where m =  length of largest string and n = number of strings
	
	
	For one more variant, see the solution on leetcode
 * */
package leetcode;

public class LongestCommonPrefix {
	
	
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		int min_len = Integer.MAX_VALUE;					//length of the shortest string
		for(int i=0; i<strs.length; i++) {
			min_len = Math.min(min_len, strs[i].length());
		}
		boolean all_same = true;
		int i=0;
		for(; i<min_len; i++) {
			for(int j=0; j<strs.length-1; j++) {
				if(strs[j].charAt(i) != strs[j+1].charAt(i)) {		//mismatch
					all_same = false;
					break;
				}
			}
			if(!all_same) {											//mismatch had occurred before 
				break;
			}
		}
		if(i == 0) {					
			return "";
		}
		else {
			return strs[0].substring(0, i);
		}
	}

	//No need to first find the shortest string as we did in the last method. We can do like below also:
	//Complexity: O(m * n); where m =  length of largest string and n = number of strings
	
	public String longestCommonPrefix_Optimized(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		
		for(int i=0; i<strs[0].length(); i++) {
			for(int j=1; j<strs.length; j++) {
				char c = strs[0].charAt(i);
				if(i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0,i);
				}
			}
			
		}
		return strs[0];
		
	}
	public static void main(String[] args) {
		String[] strs = {"flower","f","flight"};
		LongestCommonPrefix ob = new LongestCommonPrefix();
		System.out.println(ob.longestCommonPrefix(strs));

	}

}
