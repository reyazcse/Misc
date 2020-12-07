//https://leetcode.com/problems/reverse-words-in-a-string/
/*
Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.


 * */

/*
Solution:
	Traverse the string from right side.
	'i' is the end of a word while 'j+1' is the start of the word. Take the substring and append it to result
	Keep moving towards left.
	
	Complexity: O(n) time and O(1) space
	
	Note we can also solve by splitting the string on basis of space and then appending the strings to result.
	
 * */
package leetcode;

public class ReverseWords {
	public String reverseWords(String s) {
		StringBuilder result = new StringBuilder();
		if(s == null || s.length() == 0) {
			return "";
		}
		int i = s.length()-1;
		while(i >= 0) {
			while(i >= 0 && Character.isWhitespace(s.charAt(i))) {
				i--;
			}
			if(i < 0) {
				break;
			}
			int j=i;
			while(j >= 0 && !Character.isWhitespace(s.charAt(j))) {
				j--;
			}
			String str = s.substring(j+1, i+1);					//word is from j+1 till i
			result.append(str).append(" ");
			if(j < 0) {
				break;
			}
			i = j;												//shift i
		}
		result.deleteCharAt(result.length()-1);
		return result.toString();
	}
	public static void main(String[] args) {
		ReverseWords ob = new ReverseWords();
		System.out.println(ob.reverseWords("    reyaz is      "));

	}

}
