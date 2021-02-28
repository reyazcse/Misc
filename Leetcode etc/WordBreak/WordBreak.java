//https://leetcode.com/problems/word-break/
/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

	The same word in the dictionary may be reused multiple times in the segmentation.
	You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
 * */

//Recursive and top down solution
package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {
	
	//RECURSIVE: TLE
	public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Boolean> wordsMap = getWordsMap(wordDict);
        return wordBreakUtl(s, 0, wordsMap);
    }
    
    private boolean wordBreakUtl(String s, int start, Map<String, Boolean> wordsMap){
        if(start == s.length()){
            return true;
        }
        
        for(int i=start; i<s.length(); i++) {
            String left = s.substring(start, i+1);
            if(wordsMap.containsKey(left)) {
                if(wordBreakUtl(s, i+1, wordsMap)) {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    private Map<String, Boolean> getWordsMap(List<String> wordDict) {
        Map<String, Boolean> wordsMap = new HashMap<>();
        for(String word : wordDict) {
            wordsMap.put(word, true);
        }
        return wordsMap;
    }
    
    
    //Top Down dp
    public boolean wordBreak_TopDown(String s, List<String> wordDict) {
        Map<String, Boolean> wordsMap = getWordsMap(wordDict);
        Boolean[] dp = new Boolean[s.length()];
        return wordBreakUtl_Topdown(s, 0, wordsMap, dp);
    }
    
    private boolean wordBreakUtl_Topdown(String s, int start, Map<String, Boolean> wordsMap, Boolean[] dp){
        if(start == s.length()){
            return true;
        }
        
        if(dp[start] != null) {
            return dp[start];
        }
        for(int i=start; i<s.length(); i++) {
            String left = s.substring(start, i+1);
            if(wordsMap.containsKey(left)) {
                if(wordBreakUtl_Topdown(s, i+1, wordsMap, dp)) {
                    return dp[start] = true;
                }
            }
        }
        return dp[start] = false;
        
    }

}
