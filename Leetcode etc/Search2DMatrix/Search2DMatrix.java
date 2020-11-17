//https://leetcode.com/problems/search-a-2d-matrix/
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

	- Integers in each row are sorted from left to right.
	- The first integer of each row is greater than the last integer of the previous row.
 * */

/*
Solution:
	m = total no of rows, n = total no of columns
	Brute force: 
		Simple iterate over all elements to search.
		O(mn) time and O(1) space
		
	Better solution:
		1. For each row, do binary search starting from the first row.
		   O(m * log n)
		
		2. We can improve little bit: Do binary search for the row whose last value is >= target value
		   O(m + log n)
	
	
	Another good solution:
		a. Compare last value of each row starting from first row.
		b. If matrix value is more, then do col--
		c. Else go to next row
		
		O(m + n)
		See search 2D matrix II problem for the solution
		
	Another solution:
		Use binary search since for the given problem, value in next row is greater than last value of previous row
		So all the numbers are sorted.
		For a given index, we can find the row and column in the matrix for this index:
			row = index / n , where n = total number of columns in the matrix
			col = index % n
		
		Example:
		For a 3 by 4 matrix, indices are:
		0  1  2  3
		4  5  6  7
		8  9  10 11
		For index = 9, row = 9/4 = 2 and col = 9%4 = 1
		
		
		Complexity: O(log (m * n))
		
		 
		
 * */
package leetcode;
//O(log (m * n)) complexity solution
public class Search2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int l = 0, r = m*n -1;
		while(l <= r) {
			int mid = l + (r-l)/2;
			int row = mid/n;
			int col = mid%n;
			if(matrix[row][col] == target) {
				return true;
			}else if (matrix[row][col] < target) {
				l = mid+1;
			}else {
				r = mid-1;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
