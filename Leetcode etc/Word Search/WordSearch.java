//Question: leetcode word search
/*

	Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	The same letter cell may not be used more than once.
	
	Example:
	board =
	[
  		['A','B','C','E'],
  		['S','F','C','S'],
  		['A','D','E','E']
	]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.

 * */
package leetcode;

public class WordSearch {
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return false;
		}
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		//search at each position in the grid
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(utl(board,word, visited, 0, i, j))
					return true;						//if found at any position
			}
		}
		return false;
    }
	
	public boolean utl(char [][] board, String word, boolean[][]visited, int pos, int i, int j) {
		if( pos == word.length())
			return true;
		if(i<0 || i>= board.length || j<0 || j>= board[0].length || visited[i][j] || board[i][j] != word.charAt(pos))
			return false;
		//char at this pos matches
		visited[i][j] = true;
		int[][]moves = {{1,0},{-1,0},{0,1},{0,-1}};
		for(int x=0; x<4; x++) {
			boolean found = utl(board, word, visited, pos+1, i+moves[x][0], j+moves[x][1]);
			if(found)
				return true;
		}
		visited[i][j] = false;		//not found hence backtrack
		return false;
		
	}
	public static void main(String[] args) {
		char[][]board = {{'a','b','i','c','r','m','n','z'},
						 {'e','a','t','i','g','e','x','y'},
						 {'m','d','i','g','p','q','r','s'},
						 {'n','o','r','e','m','n','o','p'},
						 {'x','x','x','x','x','x','x','x'}};
		
		String word = "tiger";
		//String word = "cat";
		WordSearch obj = new WordSearch();
		System.out.println(obj.exist(board, word));

	}
	
	//Note: instead of visited matrix, we can use some special character for backtracking. We just modify that cell and then undo it on backtracking

}
