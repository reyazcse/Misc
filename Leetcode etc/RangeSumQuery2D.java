//https://leetcode.com/problems/range-sum-query-2d-immutable/
/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.

 * */

/*
Solution:
	It is given that matrix should not change. 
	So we create an auxiliary matrix where we store sum so far till current cell starting from cell 0,0
	
	Complexity:
		O(m*n) time to create the auxiliary matrix : pre-computation
		O(m*n) space for the auxiliary matrix
		Getting sum each time takes O(1) time
 * */
package leetcode;

public class RangeSumQuery2D {
	int[][] aux_matrix = null;
	public RangeSumQuery2D(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
		int r = matrix.length;
		int c = matrix[0].length;
		
		aux_matrix = new int[r][c];
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(i == 0 && j == 0) {
					aux_matrix[i][j] = matrix[0][0];
				}else if (i == 0) {
					aux_matrix[i][j] = matrix[i][j] + aux_matrix[i][j-1];
				}else if (j == 0) {
					aux_matrix[i][j] = matrix[i][j] + aux_matrix[i-1][j];
				}else {
					aux_matrix[i][j] = matrix[i][j] + aux_matrix[i-1][j] + aux_matrix[i][j-1] - aux_matrix[i-1][j-1];
				}
			}
		}
		
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if(aux_matrix == null) {
            return 0;
        }
		int upper = getCumulativeSum(row1 - 1, col2);
		int side = getCumulativeSum(row2,  col1 - 1);
		int whole = aux_matrix[row2][col2];
		int corner = getCumulativeSum(row1 - 1, col1 - 1);
		
		return whole - upper - side + corner;
	}
	
	private int getCumulativeSum(int i, int j) {
		if(i < 0 || i >= aux_matrix.length || j < 0 || j >= aux_matrix[0].length) {				//out of boundary
			return 0;
		}
		return aux_matrix[i][j];
	}

}
