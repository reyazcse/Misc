
/*
This solution is based on the BFS. Approach is similar to the one in the book
See WordTransformer5.java for another implementation of same approach.
 * */
package ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class WordTransformer5 {
	public LinkedList<String> transform(String start, String stop, String[] words) {
			Map<String, List<String>> wildcardToWordsMap = getWildCardToWordsMap(words);
			
			BFSData source = new BFSData(start);
			BFSData destination = new BFSData(stop);
			
			while(!source.isFinished() && !destination.isFinished()) {
				String connection = processQueue(source, destination, wildcardToWordsMap);
				if(connection != null) {
					return mergePaths(source, destination, connection);
				}
				
				connection = processQueue(destination, source, wildcardToWordsMap);
				if(connection != null) {
					return mergePaths(source, destination, connection);
				}
			}
			return null;
			
	}
	private LinkedList<String> mergePaths(BFSData primary, BFSData secondary, String connection) {
		LinkedList<String> result = new LinkedList<>();
		PathNode start = primary.visited.get(connection);
		while(start != null) {
			result.addFirst(start.word);
			start = start.prevNode;
		}
		
		start = secondary.visited.get(connection);
		start = start.prevNode;
		while(start != null) {
			result.addLast(start.word);
			start = start.prevNode;
		}
		return result;
		
	}
	
	//process nodes at each level in the queue. while putting nodes in queue, we mark them as visited and put 
	//them in the map
	private String processQueue(BFSData primary, BFSData secondary, Map<String, List<String>> wildcardToWordsMap) {
		int size =  primary.queue.size();
		
		for(int i=0; i<size; i++) {
			PathNode poppedNode = primary.queue.poll();
			
			if(secondary.visited.containsKey(poppedNode.word)) {			//if this string has been seen in the other queue
				return poppedNode.word;
			}
			List<String> linkedWords = getValidLinkedWords(poppedNode.word, wildcardToWordsMap);	//get all one edit words except input word 
			for(String linkedWord : linkedWords) {
				if(!primary.visited.containsKey(linkedWord)) {
					PathNode next = new PathNode(linkedWord, poppedNode);
					primary.visited.put(linkedWord, next);					//mark it visited
					primary.queue.add(next);								//put into queue
				}
			}
		}
		return null;
	}
	
	
	/*
		_at -> cat, rat
		c_t -> cat, cot
		ca_ -> cat, car, cam
	*/
	Map<String, List<String>> getWildCardToWordsMap(String []words) {
		Map<String, List<String>> wildcardToWordsMap = new HashMap<>();
		for(String word : words) {		//cat, rat, dag
			List<String> wildCardRootWords = getWildcardRootWords(word);			//_at, c_t, ca_
			putIntoMap(wildcardToWordsMap, wildCardRootWords, word);
		}
		return wildcardToWordsMap;
	}
	
	//for cat, it will return a list of [_at, c_t, ca_]
	List<String> getWildcardRootWords(String word) {
		List<String> result = new ArrayList<>();
		for(int i=0; i<word.length(); i++) {
			String newWord = word.substring(0, i) + "_" + word.substring(i+1);
			result.add(newWord);
		}
		return result;
	}
	
	private void putIntoMap(Map<String, List<String>> wildCardToWordsMap, List<String> wildCardRootWords, String word) {
		for(String wildCard : wildCardRootWords) {
			List<String> words = null;
			if(wildCardToWordsMap.containsKey(wildCard)) {
				words = wildCardToWordsMap.get(wildCard);
			}else {
				words = new ArrayList<String>();
			}
			words.add(word);
			wildCardToWordsMap.put(wildCard, words);
		}
	}
	
	//for a given word, it gives all the words that are one edit away, except the input word
	List<String> getValidLinkedWords(String word, Map<String, List<String>> wildCardToWordsMap) {
		List<String> linkedWords = new ArrayList<>();
		List<String> wildCards = getWildcardRootWords(word);
		for(String wildCard : wildCards) {
			List<String> validWords = wildCardToWordsMap.get(wildCard);
			for(String validWord : validWords) {
				if(!validWord.equals(word)) {
					linkedWords.add(validWord);
				}
				
			}
		}
		return linkedWords;
		 
	}
	//queue data for BFS
	private static class BFSData {
		Queue<PathNode> queue = new LinkedList<>();
		Map<String, PathNode> visited = new HashMap<>();
		
		
		public BFSData(String word) {
			PathNode pathNode = new PathNode(word);
			visited.put(word, pathNode);
			queue.add(pathNode);
		}
		
		public boolean isFinished () {
			return queue.isEmpty();
		}
		
	}
	
	//queue node
	private static class PathNode {
		String word;
		PathNode prevNode;							//link to prev node in the queue
		public PathNode(String word) {
			this.word = word;
			prevNode = null;
		}
		
		public PathNode(String word, PathNode pathNode){
			this.word = word;
			this.prevNode = pathNode;
		}
	}
	
	public static void main(String[] args) {
		String start = "cat";
		String stop = "dog";
		String[] words = {"cat","aat","cag","cbt","dag", "dog"};
		WordTransformer5 obj = new WordTransformer5();
//		List<String> result = obj.getOneEditWords("cat");
		LinkedList<String> path = obj.transform("cat", "dog", words);
		System.out.println(path);


	}
}
