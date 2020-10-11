/*
Question: (this qs also called Word Ladder)
	Given two words of equal length that are in a dictionary, write a method to transform
	one word into another word by changing only one letter at a time. The new word at each step
	must be in the dictionary.
	Example
	Input: DAMP, LIKE
	Output: DAMP->LAMP->LIMP->LIME->LIKE
 * */

/* 
Solution1 : DFS with optimization
	The idea is we get all the words which are at one edit distance from current word.
	The one distance away words are in the dictionary. We map all the dictionary words to
	certain common keys. See below for e.g.
	For example, for 'cat', we take all the words from below three lists:
		_at -> list of words which contain _at
		c_t -> list of words which contain c_t
 		ca_ -> list of words which contain ca_
 * */
package ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordTransformer3 {
	public LinkedList<String> transform(String start, String stop, String[] words) {
		Map<String, List<String>> map = setUpMap(words);
		Set<String> visited = new HashSet<>();
		return transform(start, stop, map, visited);
	}
	
	private LinkedList<String> transform(String start, String stop, Map<String, List<String>> map, Set<String> visited) {
		//target is found
		if(start.equalsIgnoreCase(stop)) {
			LinkedList<String> path = new LinkedList<>();
			path.add(start);
			return path;
		}
		//visit current word
		visited.add(start);
		List<String> wildCardWords = getWildcardStrings(start);
		for(String wildCardWord : wildCardWords) {
			if(map.containsKey(wildCardWord)) {
				List<String> wordslist = map.get(wildCardWord);
				for(String wordInList : wordslist) {
					if(!visited.contains(wordInList)) {
						LinkedList<String> path = transform(wordInList, stop, map, visited);
						if(path != null) {
							path.addFirst(start);
							return path;
						}
					}
				}
			}
		}
		return null;
	}
	
	//returns a map with key as a wildcard and value as a list of given strings
	//for example one entry: '_at' -> 'cat', 'rat', 'mat'
	private Map<String, List<String>> setUpMap(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		for(String word : words) {
			List<String> wildCardWords = getWildcardStrings(word);
			populateMap(map, wildCardWords, word);
		}
		return map;
	}
	
	//add the word in the map. The key in map is a wildcard string like '_at' and the value at
	//this key will words which have 'at' as the 2nd and 3rd char...
	//for example one entry: '_at' -> 'cat', 'rat', 'mat'
	private void populateMap(Map<String, List<String>> map, List<String> wildCardWords, String word) {
		List<String> existing = null;
		for(String wildCard : wildCardWords) {
			if(map.containsKey(wildCard)) {
				existing = map.get(wildCard);
			} else {
				existing = new ArrayList<>();
			}
			existing.add(word);
			map.put(wildCard, existing);
		}
	}
	
	//for 'cat', it returns ['_at', 'c_t', 'ca_']
	private List<String> getWildcardStrings(String word) {
		List<String> oneEdits = new ArrayList<>();
		for(int i=0; i<word.length(); i++) {
			String oneEdit = word.substring(0, i) + "_" + word.substring(i+1);
			oneEdits.add(oneEdit);
		}
		return oneEdits;
	}
	public static void main(String[] args) {
		String start = "cat";
		String stop = "dog";
		String[] words = {"cat","aat","cag","cbt","dag", "dog"};
		WordTransformer3 obj = new WordTransformer3();
//		List<String> result = obj.getOneEditWords("cat");
		LinkedList<String> path = obj.transform("cat", "dog", words);
		System.out.println(path);

	}

}
