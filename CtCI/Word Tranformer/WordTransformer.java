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
Solution1 : DFS but brute force
	The idea is we get all the words which are at one edit distance from current word.
	For each letter of the current word, we replace that letter with letters from 'a' till 'z'
	to get one distance away words
 
 * */
package ctci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordTransformer {
	public LinkedList<String> findWords(String start, String stop, String[]words) {
		HashSet<String> dict = setUpDict(words);
		HashSet<String> visited = new HashSet<>();
		return transform(start, stop, dict, visited);
	}
	
	//puts all words in a set
	private HashSet<String> setUpDict(String[] words) {
		HashSet<String> dict = new HashSet<>();
		for(String word: words) {
			dict.add(word.toLowerCase());
		}
		return dict;
	}
	
	private LinkedList<String> transform(String start, String stop, HashSet<String> dict, HashSet<String> visited) {
		//if we got the target string
		if(start.equalsIgnoreCase(stop)) {
			LinkedList<String> path = new LinkedList<>();
			path.add(start);
			return path;
		}
		
		if(visited.contains(start) || !dict.contains(start)) {
			return null;
		}
		//visit current word
		visited.add(start);
		List<String> oneEditWords = getOneEditWords(start);
		LinkedList<String> path = null;
		
		//check for each one edit distance word
		for(String word: oneEditWords) {
			path = transform(word, stop, dict, visited);
			if(path != null) {
				path.addFirst(start);
				return path;
			}
			
		}
		return null;
	}
	
	//returns list of words which are one distance away from current word
	private List<String> getOneEditWords(String word) {
		List<String> result = new ArrayList<>();
		for(int i=0; i<word.length(); i++) {
			for(char c = 'a'; c<= 'z'; c++) {
				String editWord = word.substring(0,i) + c + word.substring(i+1);
				if(!editWord.equalsIgnoreCase(word))
					result.add(editWord);
			}
		}
		return result;
	}
	public static void main(String[] args) {
		String start = "cat";
		String stop = "dog";
		String[] words = {"cat","aat","cag","cbt","dag"};
		WordTransformer obj = new WordTransformer();
//		List<String> result = obj.getOneEditWords("cat");
		LinkedList<String> path = obj.findWords("cat", "dog", words);
		System.out.println(path);
		
		
	}

}
