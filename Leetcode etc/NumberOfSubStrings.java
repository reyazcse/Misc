//https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
/*
Given a string s consisting only of characters a, b and c.

Return the number of substrings containing at least one occurrence of all these characters a, b and c.

Example 1:

Input: s = "abcabc"
Output: 10
Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again). 
 * */

/*
Solution:
	We use sliding window technique. There are two ideas to solve it:
	1. When we have a substring i...j , then for all j till end of string, we will get valid substrings
	2. When we have a substring i...j , then rather than increasing j, we increase i and check if it is a valid substring
	
	Valid substring means at least one of each a,b,c is there in the substring.
	We have a count[] array to store count of a,b,c
 * */
package leetcode;


public class NumberOfSubStrings {
	
	public int numberOfSubstrings(String s) {
		int n = s.length();
		int[] count = new int[3];	//stores count of a,b,c at indices 0,1,2 respectively
		int result = 0;
		
		//calculate first window of size=3
		for(int i=0; i<3; i++) {
			char ch = s.charAt(i);
			count[ch - 'a']++;
		}
		int left=0;
		int right=2;
		while(right < n) {
			while(count[0]>0 && count[1]>0 && count[2]>0 && left<=right) {	//while substring is valid
				result += n-right;					//if left...right is valid substring, then we have valid substrings till end of string
				char ch = s.charAt(left);
				count[ch - 'a']--;
				left++;			//increment left since right has to be kept fixed
			}
			right++;			//increment right and add the new character encountered
			if(right == n) {
				break;
			}
			char ch = s.charAt(right);
			count[ch - 'a']++;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		String s = "abca";
		NumberOfSubStrings obj = new NumberOfSubStrings();
		System.out.println(obj.numberOfSubstrings(s));

	}

}
