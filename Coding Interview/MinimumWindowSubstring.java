//https://leetcode.com/problems/minimum-window-substring/
/*
	Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

	Example:
	Input: S = "ADOBECODEBANC", T = "ABC"
	Output: "BANC"
 * */

/*
 * Idea is to keep a sliding window with l and r. 
 * We keep moving till we get a window which contains substring. Such window is called desirable
 * When we get a desirable window, we update the bestStart. 
 * Then we move l, and see if we still get a desirable window. If we get, then we update bestStart and move l further
 * Else we move r till we get a desirable window. 
 * 
 * When formed == required, it means the window contains the substring
 * See solution on leetcode.
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumWindowSubstring {
	public static String minWindow(String s, String t) {
		//bestStart of window and best size of this window
		int bestStart=-1,bestWindow=-1;

		//left and right boundaries of window
		int l=0, r=0;
		HashMap<Character, Integer> dictT = new HashMap<>();
		for(char ch : t.toCharArray()) {
			dictT.put(ch, dictT.getOrDefault(ch, 0)+1);
		}
		int required = dictT.size();
		int formed = 0;
		HashMap<Character, Integer> windowCounts = new HashMap<>();
		while(r < s.length()) {
			//adding current char in the window
			char ch = s.charAt(r);
			windowCounts.put(ch, windowCounts.getOrDefault(ch, 0)+1);

			//check if after putting the char in window, we get same count in both the maps
			if(dictT.containsKey(ch) && dictT.get(ch).intValue() == windowCounts.get(ch).intValue()) {
				formed++;
			}
			//if formed != requied, we keep increasing r else we do below:
			while(l <= r && formed == required) {

				if(bestWindow == -1 || (r - l) < bestWindow) {
					bestWindow = r-l;
					bestStart = l;
				}
				//removing current char from the window
				char c = s.charAt(l);
				windowCounts.put(c, windowCounts.get(c)-1);
				if(dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
					formed--;
				}

				l++;
			}
			r++;
		}
		if(bestWindow == -1) return "";
		return s.substring(bestStart, bestStart+bestWindow+1);
	}

	////////////////////////////////////////////////////////////////////////////
	//MORE OPTIMIZED: Here we remove chars form s which are not in t. And we 
	//keep the indices of a char in s.
	//For example, if s = "ADABC" and t = "AB", then filtered_s = [(0,A), (2,A), (3,B)]

	public static String minWindowOptimized(String s, String t) {
		//bestStart of window and best size of this window
		int bestStart=-1,bestWindow=-1;

		//left and right boundaries of window
		int l=0, r=0;
		HashMap<Character, Integer> dictT = new HashMap<>();
		for(char ch : t.toCharArray()) {
			dictT.put(ch, dictT.getOrDefault(ch, 0)+1);
		}
		int required = dictT.size();
		int formed = 0;
		HashMap<Character, Integer> windowCounts = new HashMap<>();
		List<Pair> filtered_s = new ArrayList<>();
		//create a list which contains only chars found in string t
		for(int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if(dictT.containsKey(c)) {
				filtered_s.add(new Pair(i,c));
			}
		}
		
		while(r < filtered_s.size()) {
			char ch = filtered_s.get(r).value;
			//put current char in window
			windowCounts.put(ch, windowCounts.getOrDefault(ch, 0)+1);
			if(windowCounts.get(ch).intValue() == dictT.get(ch).intValue()) {
				formed++;
			}
			
			while(l <= r && formed == required) {
				//start and end of window
				int startIndex = filtered_s.get(l).index;
				int endIndex = filtered_s.get(r).index;
				if(bestWindow == -1 || endIndex-startIndex < bestWindow) {
					bestWindow = endIndex-startIndex;
					bestStart = startIndex;
				}
				//removing from window
				char c = filtered_s.get(l).value;
				windowCounts.put(c, windowCounts.get(c)-1);
				if(windowCounts.get(c).intValue() < dictT.get(c).intValue() ) {
					formed--;
				}
				l++;
			}
			r++;
		}	
		if(bestWindow == -1) return "";
		return s.substring(bestStart, bestStart+bestWindow+1);
	}
	
	
	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
		//		String s = "ACCBC";
		//		String t = "AB";
		System.out.println(minWindow(s, t));
		System.out.println(minWindowOptimized(s,t));

	}
	
	private static class Pair{
		public int index;
		public char value;
		Pair(int i, char c) {
			index = i;
			value = c;
					
		}
	}

}
