//Question: https://leetcode.com/problems/word-search-ii/ 

/*
	Given a 2D board and a list of words from the dictionary, find all words in the board.

	Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	The same letter cell may not be used more than once in a word.
	Example:
	Input: 
	board = [
  		['o','a','a','n'],
  		['e','t','a','e'],
  		['i','h','k','r'],
  		['i','f','l','v']
	]
	words = ["oath","pea","eat","rain"]

	Output: ["eat","oath"]
	
	
Solution:
	We implement it using trie.
	Insert all words in the trie.
	Then for each cell in the board, find all the words which are present in the trie.
	We have a method in trie which search if a prefix is there or not in trie. This method actually speeds up the search as we dont need to go further if we
	find that a certain prefix is not there in the trie.
	
Note: It is slow
 * */
package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchIITrie {
	public List<String> findWords(char[][] board, String[] words) {
		if(board == null)
			return new ArrayList<>();
		
		Trie trie = new Trie();
		for(String word : words) {
			trie.insert(word);
		}
		Set<String> result = new HashSet<>();
		//from each position, searh for all the words that present  in the dictionary (or the trie)
		boolean [][] visited = new boolean[board.length][board[0].length];
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				boolean [][] visited = new boolean[board.length][board[0].length];
				dfs(board,trie,"", i, j, result, visited);
			}
		}
		return new ArrayList<String>(result);
		
	}
	
	public void dfs (char[][]board, WordSearchIITrie.Trie trie, String prefix, int i, int j, Set<String>result, boolean[][] visited) {
		if (i <0 || i >= board.length || j <0  || j >= board[0].length || visited[i][j])
			return;
		char c = board[i][j];
		String newPrefix = prefix + c;
		if (!trie.startsWith(newPrefix))  //return as prefix is not in trie
			return;
		
		if(trie.search(newPrefix)) {	//word found?	
			result.add(newPrefix);
		}
		
		visited[i][j] = true;
		int []x = {-1,1,0,0};
		int []y = {0,0, -1,1};
		for(int k=0; k<4; k++) {
			dfs(board, trie, newPrefix, i+x[k],j+y[k], result, visited);
		}
		visited[i][j] = false;		//backtrack
	}
	public static void main(String[] args) {
		WordSearchIITrie obj = new WordSearchIITrie();
		char[][]board = {{'a','b','i','c','r','m','n','z'},
				 {'e','a','t','i','g','e','x','y'},
				 {'m','d','i','g','p','q','r','s'},
				 {'n','o','r','e','m','n','o','p'},
				 {'x','x','x','x','x','x','x','x'}};

		String[] words = {"tiger", "tige", "nop", "reyaz"};
		List<String> list = obj.findWords(board, words);
		System.out.println(list);
	}
	//Trie implementation
	private static class TrieNode {
		public boolean isWord;
		public TrieNode [] children = new TrieNode[26];
		
	}
	private static class Trie {
		TrieNode root = new TrieNode();
		
		public void insert (String word ) {
			
			TrieNode node = root;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				TrieNode child = node.children[c - 'a'];
				if (child == null)
					node.children[c - 'a'] = new TrieNode();
				node = node.children[c - 'a'];
			}
			node.isWord = true;
		}
		
		public boolean search(String word) {
			TrieNode node = root;
			if (root == null) return false;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				TrieNode child = node.children[c - 'a'];
				if (child == null)
					return false;
				node = node.children[c - 'a'];
			}
			return node.isWord;
		}
		
		public boolean startsWith(String prefix) {
			TrieNode node = root;
			for(int i=0; i<prefix.length(); i++) {
				char c = prefix.charAt(i);
				TrieNode child = node.children[c - 'a'];
				if (child == null)
					return false;
				node = child;
			}
			return true;
		}
	}
}
