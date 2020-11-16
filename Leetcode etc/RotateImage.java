//https://leetcode.com/problems/rotate-image/
/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
 * */

/*
Solution 1 : O(n^2) time and O(1) space
	transpose the matrix
	reverse each row of the matrix
	
Solution 2 : We can also rotate each element of the matrix. [refer leetcode solution below commented]
 * */
package leetcode;

public class RotateImage {
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		//transpose of matrix
		transpose(matrix);
		
		//reverse each row
		for(int i=0; i<n; i++) {
			for(int j=0; j<n/2; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n-1-j];
				matrix[i][n-1-j] = tmp;
			}
		}
	}
	private void transpose(int[][] matrix) {
		int n = matrix.length;
		for(int i=0; i<n; i++) {
			for(int j = i; j<n; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
		}
	}
	public static void main(String[] args) {
		int [][] matrix = {{1,2,3},
							{4,5,6},
							{7,8,9}};
		RotateImage ob = new RotateImage();
		ob.rotate(matrix);
		System.out.println(matrix);

	}

}

/*
 * Leetcode solution for rotation method to solve:

class Solution {
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < (n + 1) / 2; i ++) {
      for (int j = 0; j < n / 2; j++) {
        int temp = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
        matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 -i];
        matrix[j][n - 1 - i] = matrix[i][j];
        matrix[i][j] = temp;
      }
    }
  }
}

 * */
