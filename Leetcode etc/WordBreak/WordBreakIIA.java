//https://leetcode.com/problems/word-break-ii/
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

	The same word in the dictionary may be reused multiple times in the segmentation.
	You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
 * */

/*
Solution:
	Recursive solution as well as top down approach
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordBreakIIA {

	//Recursive: TLE
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> result = wordBreakUtl(s, 0, wordDict);
		return result;
	}


	private List<String> wordBreakUtl(String s, int start, List<String> wordDict) {
		List<String> result = new ArrayList<>();
		if(start == s.length()) {
			result.add("");								//return non-empty list			
			return result;
		}
		
		for(int i=start; i<s.length(); i++) {
			String left = s.substring(start, i+1);
			if(wordDict.contains(left)) {
				for(String sentence : wordBreakUtl(s, i+1, wordDict)) {		//if we get get empty list, then this loop wont continue
					String newSentence = left + " " + sentence;
					result.add(newSentence.trim());							 	
				}
			}
		}
		return result;
	}

	
	
	//Top down dp : Accepted
	public List<String> wordBreak_TopDown(String s, List<String> wordDict) {
		Map<Integer, List<String>> map = new HashMap<>();						//dp table
		List<String> result = wordBreakUtl_TopDown(s, 0, wordDict, map);
		return result;
	}
	
	private List<String> wordBreakUtl_TopDown(String s, int start, List<String> wordDict, Map<Integer, List<String>> map) {
		List<String> result = new ArrayList<>();
		if(start == s.length()) {
			result.add("");																	//return non-empty list
			return result;
		}
		
		if(map.containsKey(start)) {
			return map.get(start);
		}
		
		for(int i=start; i<s.length(); i++) {
			String left = s.substring(start, i+1);
			if(wordDict.contains(left)) {
				for(String sentence : wordBreakUtl_TopDown(s, i+1, wordDict, map)) {		//if we get get empty list, then this loop wont continue
					String newSentence = left + " " + sentence;
					result.add(newSentence.trim());							 	
				}
			}
		}
		map.put(start, result);
		return result;
	}
	
	
	
	
	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		wordDict.add("co");
		WordBreakIIA ob = new WordBreakIIA();
		List<String> result = ob.wordBreak(s, wordDict);
		System.out.println(result);

	}

}
