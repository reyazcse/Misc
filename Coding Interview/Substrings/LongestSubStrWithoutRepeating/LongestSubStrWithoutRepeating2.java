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
	We have already solved it in O(2n) time.
	Here we solve it in O(n) time.
	The idea is to use a map to store the char as key and its position as value:
		IF we find a char which is not in map or it is outside of current substring
		then we either put it in map or update its new position if it is there in the map
		and we also update maxLength 
		
		If we find that the current char is already part of the substring from start till before end
		In this case, we have to start from the next index of the char found in map.
		So we update start and also update the new position of the char in map. Actually we discard the prev char and 
		include the new position of the char.
		
		For example : If we find a again, then we need to set start to b and also update new position of the a which 
		will be last a where we are currently:
		...abca
		
Complexity: 
		O(2n) time since each char is visited twice: once by end pointer and other while removing from set 
		O(n) space
		
	See another code where we do in O(n) by putting the char and its index in a map instead of using set
 * */
package leetcode;

import java.util.HashMap;

public class LongestSubStrWithoutRepeating2 {
	
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() == 0)return 0;
		int start =0, end = 0, maxLen = 0;
		HashMap<Character, Integer> mp = new HashMap<>();
		while(end < s.length()) {
			char ch = s.charAt(end);
			//ch is not part of the substring
			if(!mp.containsKey(ch) || mp.get(ch) < start) {
				mp.put(ch, end);
				maxLen = Math.max(maxLen, end-start+1);
			}else {								// ch is part of substring
				int idx = mp.get(ch);
				start = idx + 1;				//remove previous ch from substring
				mp.put(ch, end);				//update new position of current ch which is part of substring
			}
			end++;
		}
		return maxLen;
	}
	
	/*******************ANOTHER IMPLEMENTATION OF THIS LOGIC*************************************************************/
	
	
	public int lengthOfLongestSubstringAlternate(String s) {
		if(s == null || s.length() == 0)return 0;
		int start =0, end = 0, maxLen = 0;
		HashMap<Character, Integer> mp = new HashMap<>();
		while(end < s.length()) {
			char ch = s.charAt(end);
			//ch is not part of the substring
			if(mp.containsKey(ch)) {
				start = Math.max(mp.get(ch) + 1, start);
			}
			mp.put(ch, end);
			maxLen = Math.max(maxLen, end-start+1);
			end++;
		}
		return maxLen;
	}

}
