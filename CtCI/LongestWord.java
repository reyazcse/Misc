/*
 Question: CtCI 17.15 : Find the longest word which is made of other words in the given list.
 
 Solution:
 	Already in the CtCI, there is a solution in which we first sort the given list. That solution won't 
 	work if the list is not initially sorted. This is because in the map we put false for each word we call
 	the canBuild method with. For example if we call the method with "cat", then cat wont be built and
 	finally we do put("cat",false). Then other words which contains cat are also not formed as cat is put
 	as false now
 	
 	In this solution, I do it without sorting the list.

 * */
package ctci;

import java.util.HashMap;

public class LongestWord {
	public String longestWord(String[] words) {
		HashMap<String, Boolean> map = new HashMap<>();
		for (String str : words) {
			map.put(str, true);
		}
		
		int max  = Integer.MIN_VALUE;
		String longest  = null;
		for(String str : words) {
			if (canBuild(str, map, true)) {  //if we can build str from other words
				if (str.length() > max) {
					max = Math.max(max, str.length());
							longest = str;
				}
				
			}
		}
		return longest;		//return longest word
	}
	
	//original: we use it since a word cannot be formed by itself
	private boolean canBuild(String str, HashMap<String, Boolean> map, Boolean original) {
		//map should have that word as true and this word is not the same we call this method with
		if(map.containsKey(str)  && map.get(str) && !original)
			return true;
		
		//get all substrings and check
		for (int i=1 ;i < str.length(); i++) {
			String left = str.substring(0,i);
			String right = str.substring(i);
			if (map.containsKey(left) && map.get(left) && canBuild(right, map, false)) {
				return true;
			}
		}
		/*
		 * put this word in map if it it not already there
		 * if we dont put the condition, then it can happen that when we will put <"dog",false>
		 * in the map when we call the method with "dog"
		 * 
		 * */
		if (!map.containsKey(str))   
			map.put(str, false);
		return false;
	}

	public static void main(String[] args) {
		String [] words = {"catmew", "cat", "dog", "x","dogmew", "dogx", "catdogcat"};
		LongestWord obj = new LongestWord();
		System.out.println(obj.longestWord(words));
		

	}

}
