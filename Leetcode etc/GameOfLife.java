//https://leetcode.com/explore/interview/card/top-interview-questions-hard/116/array-and-strings/831/
/*
Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
Example:
Input: 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output: 
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
 * */

/*
Solution:
	We solve this in place. When 1 is changed to 0, we do not change it at that time, rather we put 2
	Similarly for 0 -> 1, we put -1 in the cell.
	Then while calculating zeroes and one for ther cells, we also take care of cells with values -1 and 2
	Finaly when we are done with all the cells, we revert 2  to 0 and -1 to 1! 
 * */
package leetcode;

public class GameOfLife {
	public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for(int i=0; i<rows; i++) {
        	for(int j=0; j<cols; j++) {
        		Counts cnt = getCounts(board, i, j);
        		if(cnt.ones <= 1 && board[i][j] == 1) {
        			board[i][j] = 2;
        		}else if(cnt.ones >= 4 && board[i][j] == 1) {
        			board[i][j] = 2;
        		}else if (cnt.ones == 3 && board[i][j] == 0) {
        			board[i][j] = -1;
        		}
        	}
        }
        
        //changing cells with -1 and 2 values
        for(int i=0; i<rows; i++) {
        	for(int j=0; j<cols; j++) {
        		if(board[i][j] == -1)
        			board[i][j] = 1;
        		else if (board[i][j] == 2)
        			board[i][j] = 0;
        	}
        }
    }
	
	//returns counts of zeroes and ones for a cell
	//Note: instead of 8 ifs we can keep an array of -1,0 and 1 and use a loop to iterate the 8 adjacent cells
	private Counts getCounts(int[][]board, int row, int col) {
		int zeroes = 0, ones = 0;
		if(isValid(board, row-1, col-1)) {
			if(board[row-1][col-1] == 1 || board[row-1][col-1] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row-1, col)) {
			if(board[row-1][col] == 1 || board[row-1][col] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row-1, col+1)) {
			if(board[row-1][col+1] == 1 || board[row-1][col+1] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row, col-1)) {
			if(board[row][col-1] == 1 || board[row][col-1] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row, col+1)) {
			if(board[row][col+1] == 1 || board[row][col+1] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row+1, col-1)) {
			if(board[row+1][col-1] == 1 || board[row+1][col-1] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row+1, col)) {
			if(board[row+1][col] == 1 || board[row+1][col] == 2)
				ones++;
			else zeroes++;
		}
		if(isValid(board, row+1, col+1)) {
			if(board[row+1][col+1] == 1 || board[row+1][col+1] == 2)
				ones++;
			else zeroes++;
		}
		return new Counts(zeroes, ones);
	}
	private boolean isValid(int[][] board, int row, int col) {
		if(row < 0 || row >= board.length || col < 0 || col >= board[0].length)
			return false;
		return true;
	}
	public static void main(String[] args) {
		

	}
	
	private class Counts{
		public int zeroes;
		public int ones;
		public Counts(int z, int o) {
			zeroes = z;
			ones = o;
		}
	}

}
