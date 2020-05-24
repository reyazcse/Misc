//https://leetcode.com/problems/count-square-submatrices-with-all-ones/

/*
Question:
	Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
	Input: matrix =
	[
	  [0,1,1,1],
	  [1,1,1,1],
	  [0,1,1,1]
	]
	Output: 15
	Explanation: 
	There are 10 squares of side 1.
	There are 4 squares of side 2.
	There is  1 square of side 3.
	Total number of squares = 10 + 4 + 1 = 15.

Solution:
	We solve this using dp.
	We construct dp[][] which is of same size as given matrix.
	If element in matrix is 1, then we find what is the size of square matrix which includes this element.
	If element in matrix is 0, we dont do anything as this element cant be included.
Complexity: O(M *N ) time and O (M * N) space
 * */
package leetcode;

public class SquareSubMatrices {
	public int countSquares(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int [][] dp = new int[m][n];
		int totalCount = 0;
		
		//first row
		for(int i=0; i<n; i++) {
			dp[0][i] = matrix[0][i];
			totalCount += matrix[0][i];
		}
		//first column
		for(int i=0; i<m; i++) {
			dp[i][0] = matrix[i][0];
			totalCount += matrix[i][0];
		}
			
		
		for(int i=1; i<m; i++) {
			for(int j=1; j<n; j++) {
				if (matrix[i][j] == 1) {  //if element is 0, it can't be part of a matrix ending at this element
					
					//max size of matrix including element at current cell
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
					
				}
				totalCount += dp[i][j];
				
			}
		}
		
		
		return totalCount;
		
    }
	public static void main(String[] args) {
		int [][] matrix = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
		SquareSubMatrices obj = new SquareSubMatrices();
		System.out.println(obj.countSquares(matrix));

	}

}
