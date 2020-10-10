//https://leetcode.com/problems/surrounded-regions/

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
 * */

/*
Solution:
	The idea is if we have a 'O' cell and it is connected directly/indirectly to a border 'O' cell,
	then we do not capture it.
	So first we process the 'O' cells on the 4 borders. We can do DFS or BFS and change
	corresponding connected 'O' cells to '#'
	Then we iterate over all the cells and change '#' cells to 'O' and 'O' cells to 'X'.
	
	O(mn) time and O(1) space
 * */
package leetcode;

public class SurroundedRegions {
	public void solve(char[][] board) {
		if(board == null || board.length == 0) return;
		int r = board.length;
		int c = board[0].length;
		
		//fix left and right borders
		for(int i=0; i<r; i++) {
			if(board[i][0] == 'O') {
				merge(board, i, 0);
			}
			if(board[i][c-1] == 'O') {
				merge(board, i, c-1);
			}
		}
		
		//fix top and bottom borders
		for(int j=0; j<c; j++) {
			if(board[0][j] == 'O') {
				merge(board, 0, j);
			}
			if(board[r-1][j] == 'O') {
				merge(board, r-1, j);
			}
		}
		
		//process board
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(board[i][j] == '#') {
					board[i][j] = 'O';
				}else if(board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}
	
	//changes current 'O' cell and all its connected 'O' cells to '#'.
	private void merge(char[][] board, int row, int col) {
		if(isSafe(board, row, col) && board[row][col] == 'O') {
			//process this cell
			board[row][col] = '#';		
			
			//do dfs over other adjacent cells
			int[] idx = {-1,0,1,0};
			int[] idy = {0,1,0,-1};
			for(int k=0; k<4; k++) {
				merge(board, row+idx[k], col+idy[k]);
			}
		}
	}
	
	//checks if board boundary is not crossed
	private boolean isSafe(char[][] board, int row, int col) {
		return (row >=0 && row < board.length && col >=0 && col < board[0].length);
	}
	
	public void printBoard(char[][] board) {
		for(int i=0; i<board.length; i++) {
			System.out.println();
			for(int j=0; j<board[0].length; j++) {
				System.out.print(board[i][j] + "   ");
			}
		}
	}
	public static void main(String[] args) {
		char[][] board = {{'X','O','X','X'},
						 {'X','O','O','X'},
						 {'X','X','X','X'},
						 {'X','O','X','X'},
						 {'X','X','X','X'}};
		
		SurroundedRegions obj = new SurroundedRegions();
		obj.printBoard(board);
		obj.solve(board);
		obj.printBoard(board);
						 

	}

}
