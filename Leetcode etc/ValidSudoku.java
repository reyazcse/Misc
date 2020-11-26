//https://leetcode.com/problems/valid-sudoku/
/*
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

	Each row must contain the digits 1-9 without repetition.
	Each column must contain the digits 1-9 without repetition.
	Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
	
 * */

/*
Solution:
	Here we have to check if there is no duplicate number (out of given numbers) in a row, column and in a 3 by 3 box.
	
	We can iterate for each row and check for duplicate numbers in a row (using a map for the row)
	Similarly we can iterate for each column and then for each box.
	
	Rather than iterate three times over the board, we can do in one iteration as shown below.
	We have 9 boxes: 0 1 2 3 4 5 6 7 8  (3 in first row, and so on)
	We can have box number = row/3 * 3 + col/3
 * */

package leetcode;

import java.util.HashMap;

public class ValidSudoku {

	public boolean isValidSudoku(char[][] board) {
		HashMap<Integer, Integer>[] rowMaps = new HashMap[9];
		HashMap<Integer, Integer>[] colMaps = new HashMap[9];
		HashMap<Integer, Integer>[] boxMaps = new HashMap[9];
		for(int i=0; i<9; i++) {
			rowMaps[i] = new HashMap<>();
			colMaps[i] = new HashMap<>();
			boxMaps[i] = new HashMap<>();
		}
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j] != '.') {
					int val = (int)board[i][j];
					int boxNumber = i/3 *3 + j/3;
					rowMaps[i].put(val, rowMaps[i].getOrDefault(val,0) + 1);
					colMaps[j].put(val, colMaps[j].getOrDefault(val, 0) + 1);
					boxMaps[boxNumber].put(val, boxMaps[boxNumber].getOrDefault(val, 0) + 1);
					
					if(rowMaps[i].get(val) > 1 || colMaps[j].get(val) > 1 || boxMaps[boxNumber].get(val) > 1) {
						return false;
					}
				}
				
				
			}
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
