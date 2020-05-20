//Question: leetcode word search II
/*


 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
	
	public List<String> findWords(char[][] board, String[] words) {
		//base cases
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
			return new ArrayList<>();
		}
		int m = board.length;
		int n = board[0].length;
		List<String> result = new ArrayList<>();
		
		//search for each word
		for (String word : words) {						
			//search at each position in the grid
			for(int i=0; i<m; i++) {
				for(int j=0; j<n; j++) {
					boolean[][] visited = new boolean[m][n];
					if(utl(board,word, visited, 0, i, j))
						result.add(word);						//if found at any position, add to result
				}
			}
		}
		return result;
    }
	
	//finds if word starting from pos is there or not starting from i,j positions
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
		
		String[] words = {"tiger", "tige", "nop", "reyaz"};
		//String word = "cat";
		WordSearchII obj = new WordSearchII();
		
		List<String> list = obj.findWords(board, words);
		System.out.println(list);

	}

}
