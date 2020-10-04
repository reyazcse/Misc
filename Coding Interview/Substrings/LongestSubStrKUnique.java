package leetcode;

import java.util.HashMap;

public class LongestSubStrKUnique {
	public static String findLongestSubString(String str, int k) {
		HashMap<Character, Integer> countMap = new HashMap<>();
		int start =0, end=-1, windowSize=1;
		int bestStart = 0;
		for(int i=0; i<str.length(); i++) {
			//put the char in map
			char c = str.charAt(i);
			if(countMap.containsKey(c)) {
				countMap.put(c, countMap.get(c)+1);
			}else {
				countMap.put(c, 1);
			}
			end++;						//update window end on insert of char
			
			//after putting the char in map, if size of map is more than k, then remove char from 'start' 
			//till size of map is k
			while(countMap.size() > k) {
				char ch = str.charAt(start);
				if(countMap.get(ch) == 1) {
					countMap.remove(ch);
				}else {
					countMap.put(ch, countMap.get(ch)-1);
				}
				start++;		//update window start on removal of char
			}
			
			//update windowSize and bestStart
			if(windowSize < (end-start+1)) {
				windowSize = end-start+1;
				bestStart = start;
			}
			
		}
		//return the best substring
		return str.substring(bestStart, bestStart + windowSize);
	}
	public static void main(String[] args) {
		String str = "karappa";
		System.out.println(findLongestSubString(str, 3));

	}

}
