//Reference leetcode
/*
 
There is a new alien language which uses the latin alphabet. 
However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
Example 1:
Given the following words in dictionary,
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".
Example 2:
Given the following words in dictionary,
[
  "z",
  "x"
]
The correct order is: "zx".
Example 3:
Given the following words in dictionary,
[
  "z",
  "x",
  "z"
]
The order is invalid, so return "".
Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

 */

/*
Solution:
	The idea is to solve using topological sorting.
	For each pair of words, find the chars where the two words differ.
	Then put those two chars in a directed graph.
	Do topological sort. 
	Print the chars storec in the stack.
	If there is a cycle returned in the topological sort, then this is invalid case.
 * */
package leetcode;

import java.util.List;
import java.util.Stack;

import mylibrary.Graph;

public class AlienDictionary {
	public String alienDictionary(String[] words) {
		if(words == null || words.length == 0) {
			return "";
		}
		
		//since alien alphabet is same as Latin alphabet, we take 256 nodes:
		//'a' is 0, 'b' is 1 and so on 
		//note that we create adjacency list for each of the 256 items in the adjacency graph.
		//ideally we should have set adjList[i] = null for i which does not exist(this can be the case
		//when a node has not any edge)
		//but since we are interested only in the nodes stored in the stack of the topological sorting, it does not matter even
		//if we do not set to null.
		Graph g = new Graph(256);			
		populateEdges(g, words);
		
		boolean[] visited = new boolean[256];
		boolean[] recStack = new boolean[256];
		Stack<Integer> stack = new Stack<>();
		topologicalSort(g, visited, recStack, stack);
		StringBuilder result = new StringBuilder();
		while(!stack.isEmpty()) {
			char ch = (char)(stack.pop()+'a');
			result.append(ch);
		}
		return result.toString();
	}
	
	/*
	 * Creates a graph with 256 nodes. Though we do not have all 256 chars, still we create
	 * adjacency list for each node. For node which do not have edges, this list is empty.
	 * 
	 * For each pair of words, we find the first missing char. Then we add an edge. We do this because we are certain
	 * about the order of the two mismatched chars. We are not sure about the remaining chars in the words however
	 * */
	private void populateEdges(Graph g, String[] words) {
		int n = words.length;
		for(int i=0; i<n-1; i++) {
			int minWordLength = Math.min(words[i].length(), words[i+1].length());
			for(int j=0; j<minWordLength; j++) {
				if(words[i].charAt(j) != words[i+1].charAt(j)) {
					//adding edge in the graph at the mismatched chars
					int fromVertex = words[i].charAt(j)-'a';
					int toVertex = words[i+1].charAt(j)-'a';
					g.addEdgeDirected(fromVertex, toVertex); 	//add directed edge
					break;
				}
			}
		}
	}
	
	/*
	 * Do topological sorting using DFS for each of the node for which we have an edge in the
	 * adjacency list.
	 * */
	private void topologicalSort(Graph g, boolean[] visited, boolean[] recStack, Stack<Integer> stack) {
		List<Integer> [] adjList = g.adjList;
		for(int i=0; i<adjList.length; i++) {
			//do only if this list has at least one element and this node i is not visited before
			if(adjList[i].size() > 0 && !visited[i]) {
				boolean isCycle = topologicalSortUtl(g, i, visited, recStack, stack);	
				//if cycle is found, then invalid state
				if(isCycle) {
					stack.clear();
					return;
				}
			}
		}
	}
	
	/*
	 * Do topological sorting and return true if there is a cycle and topological sort is not 
	 * possible
	 * 
	 * */
	private boolean topologicalSortUtl(Graph g, int curr, boolean[] visited, boolean[] recStack, Stack<Integer> stack) {
		visited[curr] = true;
		recStack[curr] = true;
		List<Integer> neighbours = g.adjList[curr];
		for(int i=0; i<neighbours.size(); i++) {
			int neighbour = neighbours.get(i);
			if(!visited[neighbour] && topologicalSortUtl(g, neighbour, visited, recStack, stack)) {
				return true;
			}else if(recStack[neighbour]) {
				return true;
			}
		}
		recStack[curr] = false;
		stack.add(curr);
		return false;
	}
	
	public static void main(String[] args) {
		//String[] words = {"wrt","wrf","er","ett","rftt"};
		String[] words = {"x","z"};
		AlienDictionary obj = new AlienDictionary();
		String result = obj.alienDictionary(words);
		System.out.println(result);

	}

}




