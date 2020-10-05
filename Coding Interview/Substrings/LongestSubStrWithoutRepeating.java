//https://leetcode.com/problems/longest-substring-without-repeating-characters/
/*
Given a string s, find the length of the longest substring without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

 * */

/*
Solution:
	We solve it using sliding window technique.
	We keep two pointers: start and end.
	We use a set.
	We traverse each char in the given string.
		When we see a char not there in set, we update the substring size.
		When we see a char which is already there, then we must delete chars from start till we go to a position where we find that char.
		'start' needs to be now after that char. 
 * */
package leetcode;

import java.util.HashSet;

public class LongestSubStrWithoutRepeating {
	public static int lengthOfLongestSubstring(String str) {
		if(str == null || str.length() == 0)return 0;
		//bestWindow is the size of substring
		int start=0, end=-1, bestStart=0, bestWindow=0, currentWindow=0;
		HashSet<Character> set= new HashSet<>();
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i);
			//if char does not exist in set, simply add it and update window size
			if(!set.contains(ch)) {
				set.add(ch);
				currentWindow = i-start+1;
				if(bestWindow < currentWindow) {
					bestWindow = currentWindow;
					bestStart = start;
				}
			}else {	//else remove till 'ch' is there in set. Remove from index 'start'. Also add 'ch' since i is now pointing to ch
				while(set.contains(ch)) {
					set.remove(str.charAt(start));
					start++;
				}
				set.add(ch);
				
			}
		}
		return bestWindow;
	}
	public static void main(String[] args) {
		//String str = "pwwkew";
		//String str = "aab";
		//String str = "tmmzuxt";
		String str = "dvdf";
		System.out.println(lengthOfLongestSubstring(str));

	}
	

}

/*
Explanation:
	str = "tzmxtmuxt";
	Suppose start is at t and end is at m.
	Then we need to delete t, z, and start should be now at x. Since end is at m, we need to add it too, but since
	we do not delete it, it is there already!!!
 * */