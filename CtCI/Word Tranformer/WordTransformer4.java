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
Solution1 : Bidirectional BFS
	The idea is to do BFS from start and end word simultaneously.
	We take the queue node such each node contains current word and also the previous word.
	We keep track of prev word so as to print the solution
 * */
package ctci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class WordTransformer4 {
	public LinkedList<String> transform(String start, String stop, String [] words) {
		//get a map with key,value as :
		//'_at' -> ['cat', 'rat', 'mat']
		Map<String, List<String>> dict = setUpMap(words);
		//two queues
		Queue<PathNode> q1 = new LinkedList<>();	//for start word
		Queue<PathNode> q2 = new LinkedList<>();	//for end word
		Map<String, PathNode> visited1 = new HashMap<>(); 
		Map<String, PathNode> visited2 = new HashMap<>(); 
		
		PathNode pStart = new PathNode(start);
		PathNode pStop = new PathNode(stop);
		q1.add(pStart);
		q2.add(pStop);
		
		while (!q1.isEmpty() && !q2.isEmpty()) {
			//process q1
			PathNode p1 = q1.poll();
			//connection is the collision word
			String connection = processChildrenAndCheckCollision(q1, p1, visited1, visited2, dict); 
			if(connection != null) {
				return mergeLists(visited1, visited2, connection);
			} 
			
			//process q2
			PathNode p2 = q2.poll();
			connection = processChildrenAndCheckCollision(q2, p2, visited2, visited1, dict); 
			if(connection != null) {
				return mergeLists(visited2, visited1, connection);
			}
		}
		return null;
	}
	
	
	private String processChildrenAndCheckCollision (Queue<PathNode> currenQ, PathNode current, Map<String, PathNode> visitedCurrent, Map<String, PathNode> visitedOther, Map<String, List<String>> dict) {
		//if the popped word has already been visited for current queue, then no need to process its children or do anything
		if(visitedCurrent.containsKey(current.word)) {
			return null;
		}
		visitedCurrent.put(current.word, current);
		
		//check if this word was visited in the other queue
		if(visitedOther.containsKey(current.word)) {
			return current.word;
		}
		List<String> wildCards = getWildCardsList(current.word);
		for(String wildCard : wildCards) {
			List<String> linkedWords = dict.get(wildCard);
			for(String linkedWord : linkedWords) {
				//check while putting the word in the queue if this word was visited in the other queue. 
				//This we do for optimization.
				if(visitedOther.containsKey(linkedWord)) {
					visitedCurrent.put(linkedWord, new PathNode(linkedWord, current)); //store connection word so as to retrieve while finding word
					return linkedWord;
				}
					
				if(!linkedWord.equals(current.word)) {
					PathNode pNode = new PathNode(linkedWord, current);
					currenQ.add(pNode);
				}
				
			}
		}
		return null;
	}
	
	//it creates the solution list.
	
	private LinkedList<String> mergeLists(Map<String, PathNode> visitedCurrent, Map<String, PathNode> visitedOther, String connection) {
		PathNode currPathNode = visitedCurrent.get(connection);
		String word = currPathNode.word;
		LinkedList<String> result = new LinkedList<>();
		//get words from the first queue's pathnode
		while(currPathNode != null) {
			result.addFirst(currPathNode.word);
			currPathNode = currPathNode.prevNode;
		}
		PathNode pNode = visitedOther.get(word);
		pNode = pNode.prevNode;
		//get words from the second queue's pathnode
		while(pNode != null) {
			result.addLast(pNode.word);
			pNode = pNode.prevNode;
		}
		return result;
	}
	
	//same as before
	private Map<String, List<String>> setUpMap(String [] words) {
		Map<String, List<String>> dict = new HashMap<>();
		for(String word : words) {
			List<String> wildCards = getWildCardsList(word);
			for(String wildCard : wildCards) {
				populateMap(dict, wildCard, word);
			}
		}
		return dict;
	}
	
	//same as before
	private List<String> getWildCardsList (String word) {
		List<String> result = new ArrayList<>();
		for(int i=0; i<word.length(); i++) {
			String wildCard = word.substring(0, i) + "_" + word.substring(i+1);
			result.add(wildCard);
		}
		return result;
	}
	
	//same as before
	private void populateMap(Map<String, List<String>> map, String key, String value) {
		List<String> valueList = new ArrayList<>();
		if(map.containsKey(key)) {
			valueList = map.get(key);
		}
		valueList.add(value);
		map.put(key, valueList);
	}
	public static void main(String[] args) {
		String start = "cat";
		String stop = "dog";
		String[] words = {"cat","aat","cag","cbt","dag", "dog"};
		WordTransformer4 obj = new WordTransformer4();
//		List<String> result = obj.getOneEditWords("cat");
		LinkedList<String> path = obj.transform("cat", "dog", words);
		System.out.println(path);


	}
	
	private class PathNode {
		public String word;
		public PathNode prevNode;
		public PathNode(String word, PathNode node) {
			this.word = word;
			this.prevNode  = node;
		}
		
		public PathNode(String word) {
			this.word = word;
			this.prevNode = null;
		}
	}
	

}
