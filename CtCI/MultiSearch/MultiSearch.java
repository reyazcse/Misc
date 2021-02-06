//17.17 : MultiSearch
/*

	Complexity:
		O(kt + bk):
	 	 O(kt) to build trie with small words
	 	 O(bk) to search all the strings in trie
	 	 Here k is length of the longest word in []smalls, t is number of strings in []smalls
	 	 and b is the length of the bigger word

 * */
package ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





public class MultiSearch {
	class TrieNode1 {
		HashMap<Character, TrieNode1> children;
		boolean terminates;
		char ch;
		
		public TrieNode1() {
			children = new HashMap<>();
		}
		public void insert(String word) {
			if(word == null || word.length() == 0) {
				return;
			}
			char value = word.charAt(0);
			String remainder = word.substring(1);
			TrieNode1 child = children.get(value);
			if(child == null) {
				child = new TrieNode1();
				children.put(value, child);
			}
			if (word.length() > 1)
				child.insert(remainder);
			else {
				child.terminates = true;
			}
			
		}
		
		public boolean search(String word) {
			
			if (terminates && (word==null || word.length() == 0))
				return true;
			if(word == null || word.length() == 0)
				return false;
			char value = word.charAt(0);
			TrieNode1 child = children.get(value);
			if(child == null)
				return false;
			String remainder = word.substring(1);
			return child.search(remainder);
			
		}
	}

	class Trie1 {
		TrieNode1 root = new TrieNode1();
		
		public void insertString(String word) {
			root.insert(word);
		}
		
		public boolean search(String word) {
			return root.search(word);
		}
	}
	public Map<String, List<Integer>> search(String big, String [] smalls) {
		   Trie1 trie = new Trie1();
		   Map<String, List<Integer>> lookup = new HashMap<>();
		   
		   //put all the small strings in trie
		   for (String str : smalls) {
			   trie.insertString(str);
		   }
		   
		   //find small words which can be formed starting from an index of the big word
		   for(int i=0; i<big.length(); i++) {
			   findWords(big, i, lookup, trie.root);
			   
		   }
		   return lookup;
	}
	
	private void findWords(String big, int start, Map<String, List<Integer>> lookup, TrieNode1 root) {
		int i=start;
		while (i < big.length()) {
			char value = big.charAt(i);
			TrieNode1 child = root.children.get(value);
			if(child == null)
				return;
			
			//we found a word, so store it and keep moving to the child
			if(child.terminates)
				addWordToMap(lookup,big.substring(start, i+1), i);
			root = child;
			i++;
		}
		
	}
	private void addWordToMap(Map<String, List<Integer>> lookup, String word, int index) {
		List<Integer> positions = lookup.get(word);
		if(positions == null) {
			positions = new ArrayList<>();
		}
		positions.add(index);
		lookup.put(word,positions);
	}
	public static void main(String[] args) {
		MultiSearch obj = new MultiSearch();
		String [] smalls = {"m", "mi","ssi","ppi","ssippi"};
		String big = "mississippi";
		System.out.println("before");
		Map<String, List<Integer>> lookup = obj.search(big, smalls);
		System.out.println("afer");
		System.out.println(lookup);
	}

}
