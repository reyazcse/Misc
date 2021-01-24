//https://leetcode.com/problems/search-a-2d-matrix-ii/
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

	- Integers in each row are sorted in ascending from left to right.
	- Integers in each column are sorted in ascending from top to bottom.

Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

*/

//Solution: 
//Complexity: O(m+n)
package leetcode;

public class Search2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		//start with first row and last col
		int row=0, col = matrix[0].length-1;
		while(row < matrix.length && col >= 0) {
			if(matrix[row][col] == target) {
				return true;
			}else if(matrix[row][col] > target) {
				col--;
			}else {
				row++;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
//Note: We can use binary search here also. See ctci 10.9 approach 2
