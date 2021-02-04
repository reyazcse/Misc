/*
Given a dictionary of valid words and a sentence whose spaces (and other things) are removed.
Re-insert the spaces to minimize the number of unrecognized characters
 * */

/*
Solution is simplified version of the one given in the book.
Here we just get the count. In book, the string is also calculated
 * */
package ctci;

import java.util.HashMap;
import java.util.HashSet;



public class ReSpace {
	HashSet<String> dict = new HashSet<>();
	
	public void init() {
		dict.add("looked");
		dict.add("just");
		dict.add("like");
		dict.add("her");
		dict.add("brother");
	}
	public int find(String str) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		return utl(str, 0, map);
	}
	
	private int utl(String str, int index, HashMap<Integer, Integer> map) {
		if(index == str.length()) {
			return 0;
		}
		if(map.containsKey(index)) {
			return map.get(index);
		}
		int best = Integer.MAX_VALUE;
		for(int i=index+1; i<=str.length(); i++) {
			String substr = str.substring(index, i);
			int left = dict.contains(substr) ? 0 : substr.length();
			int right = utl(str, i, map);
			int curr = left + right;
			best = Math.min(best, curr);
		}
		map.put(index, best);
		return best;
	}

	public static void main(String[] args) {
		ReSpace ob = new ReSpace();
		ob.init();
		System.out.println(ob.find("jesslookedjustliketimherbrother"));

	}

}
