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
Solution1 : BFS and brute force
	The idea is we get all the words which are at one edit distance from current word.
	For each letter of the current word, we replace that letter with letters from 'a' till 'z'
	to get one distance away words
 
 * */
package ctci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordTransformer2 {
	public LinkedList<String> transform(String start, String stop, String[]words) {
		HashSet<String> dict = setUpDict(words);
		HashSet<String> visited = new HashSet<>();
		LinkedList<String> result = new LinkedList<>();
		Queue<String> q = new LinkedList<>();
		q.add(start);
		boolean isWordFound = false;
		while(!q.isEmpty()) {
			isWordFound  = false;
			String current = q.poll();
			result.add(current);
			if(current.equalsIgnoreCase(stop))		//if we find the destination string, then no need to go further
				break;
			visited.add(current);
			//put all the neighbour words which are also in dict and not visited into queue
			List<String> oneEditWords = getOneEditWords(current);
			for(String word : oneEditWords) {
				if(!visited.contains(word) && dict.contains(word)) {
					q.add(word);
					isWordFound = true;
				}
			}
			if(!isWordFound) {		//'current' word is not part of the solution
				result.removeLast();//hence update result list
			}
			
		}
		return result;
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
	
	//put the words in a set
	private HashSet<String> setUpDict(String[] words) {
		HashSet<String> set = new HashSet<String>();
		for(String word: words) {
			set.add(word);
		}
		return set;
	}
	public static void main(String[] args) {
		String start = "cat";
		String stop = "dog";
		String[] words = {"cat","aat","cag","cbt","dag", "dog"};
		WordTransformer2 obj = new WordTransformer2();
		LinkedList<String> path = obj.transform("dag", "dog", words);
		System.out.println(path);

	}

}

//Note: Here in the solution example, we get dag -> cag -> dog as cag is one edit away from dag and 
//we encounter cag before encountering dog (dag -> dog)
