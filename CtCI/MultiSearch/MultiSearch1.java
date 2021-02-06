//17.17: MultiSearch
/*
	Here we build trie with the suffixes of the big word and then we search for the small strings
	Complexity: O(b^2 + kt)
				b^2 to build the trie and kt to search all the small strings
				Here k is length of the longest word in []smalls, t is number of strings in []smalls
	 	 		and b is the length of the bigger word

 * */
package ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//node of the trie
class TrieNode1 {
	HashMap<Character, TrieNode1> children;
	boolean terminates;
	char ch;
	List<Integer> indexes;
	public TrieNode1() {
		children = new HashMap<>();
		indexes = new ArrayList<>();
	}
	public void insert(String word, int position) {
		if (word == null)
			return;
		indexes.add(position);
		if(word.length() == 0) {
			terminates = true;
			return;
		}
		
		char value = word.charAt(0);
		String remainder = word.substring(1);
		TrieNode1 child = null; 
		if(children.containsKey(value)) {
			child = children.get(value);
		} else {
			child = new TrieNode1();
			children.put(value, child);
		}
		child.insert(remainder, position+1);
	}
	
	public List<Integer> search(String word) {
		
		if (word==null || word.length() == 0)
			return indexes;
		
		char value = word.charAt(0);
		TrieNode1 child = children.get(value);
		if(child == null)
			return null;
		String remainder = word.substring(1);
		return child.search(remainder);
	}
}

//trie
class Trie1 {
	TrieNode1 root = new TrieNode1();
	
	public void insert(String word, int position) {
		root.insert(word, position);
	}
	
	public List<Integer> search(String word) {
		return root.search(word);
	}
}

public class MultiSearch1 {
	public Map<String, List<Integer>> search(String big, String [] smalls) {
		   Trie1 trie = new Trie1();
		   Map<String, List<Integer>> lookup = new HashMap<>();
		   
		   buildTrie(trie, big);
		   for(String small : smalls) {
			   List<Integer> locations = trie.search(small);
			   adjustLocations(locations, small);
			   lookup.put(small, locations);
		   }
		   
		   return lookup;
	}
	
	private void buildTrie(Trie1 trie, String str) {
		for(int i=0; i<str.length(); i++) {
			String subStr = str.substring(i);
			trie.insert(subStr, i);
		}
	}
	
	
	
	private void adjustLocations (List<Integer> locations, String str) {
		for(int i=0; i<locations.size(); i++) {
			locations.set(i, locations.get(i) - str.length());
		}
	}
	public static void main(String[] args) {
		MultiSearch1 obj = new MultiSearch1();
		String big = "bibsbi";
		String []smalls = {"bib", "s", "bi","ibs"};
		
		Map<String, List<Integer>> lookup = obj.search(big, smalls);
		System.out.println(lookup);
	}

}

/*
	Trie code:
	
		Trie1 trie = new Trie1();
		trie.insert("bibs");
		trie.insert("ibs");
		trie.insert("big");
		List<Integer> result = trie.search("bi");
		System.out.println(result);
		result = trie.search("bib");
		System.out.println(result);
		result = trie.search("b");
		System.out.println(result);
		result = trie.search("");
		System.out.println(result);
		
		Output:
		[2, 2]
		[3]
		[1, 1]
		[0, 0, 0]
 * */
