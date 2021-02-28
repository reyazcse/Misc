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
	Recursive solution. NOT accepted
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreakII {
	

	
	//RECURSIVE SOLUTION: Gives TLE
	public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        Map<String, Boolean> wordsMap = getWordsMap(wordDict);
        wordBreakUtl(s, 0, current, result, wordsMap);
        return result;
    }
    
    private void wordBreakUtl(String s, int start, StringBuilder current, List<String> result, Map<String, Boolean> wordsMap) {
        
        if(start == s.length()) {
            result.add(new String(current.toString().trim()));		//trim to remove space from the end
            return;
        }
        
        for(int i=start; i<s.length(); i++) {
            String left = s.substring(start, i+1);
            if(wordsMap.containsKey(left)) {
                current.append(left + " ");							
                wordBreakUtl(s, i+1, current, result, wordsMap);
                
                //backtrack: delete the substring just appended
                int begin = current.length() - (left.length() + 1);
                int end = begin + left.length()+2;
                current.delete(begin, end);
            }
            
        }
    }
    
    private Map<String, Boolean> getWordsMap(List<String> wordDict) {
        Map<String, Boolean> wordsMap = new HashMap<>();
        for(String word : wordDict) {
            wordsMap.put(word, true);
        }
        return wordsMap;
    }

	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		WordBreakII ob = new WordBreakII();
		List<String> result = ob.wordBreak(s, wordDict);
		System.out.println(result);

	}

}
