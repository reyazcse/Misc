//https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/
/*

Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another character.
Return the minimum number of steps to make t an anagram of s.
An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

Example 1:

Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

 * */
package leetcode;

public class MinimumNumberStepsAnagram {
	public int minSteps(String s, String t) {
        int[] count = new int[256]; //considering 8-bit to store chars
        for(int i=0; i<s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        int steps = 0;
        for(int i=0; i<count.length; i++) {
        	if(count[i] > 0) {
        		steps += count[i];
        	}
        }
        return steps;
    }
}
