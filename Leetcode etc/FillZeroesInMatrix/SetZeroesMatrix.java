//src: https://leetcode.com/problems/set-matrix-zeroes/
/*
	In this soln we use two arrays []rows and []cols which mark the rows and cols having 0.
	 
 * */
package leetcode;

public class SetZeroesMatrix {
	//O(m+n) space and O(mn) time
	 public void setZeroes(int[][] matrix) {
		 int m = matrix.length;
		 int n = matrix[0].length;
		 boolean []rows = new boolean[m];
		 boolean []cols = new boolean[n];
		 //mark rows and cols which has a 0
		 for(int i =0; i<m;i++) {
			 for(int j=0; j<n; j++) {
				 if(matrix[i][j] == 0) {
					 rows[i] = true;
					 cols[j] = true;
					 
				 }
			 }
		 }
		 //if a row or col has been identified to have 0, mark this cell as 0
		 for(int i =0; i<m;i++) {
			 for(int j=0; j<n; j++) {
				 if(rows[i] || cols[j]) {
					 matrix[i][j] = 0;
					 
				 }
			 }
		 }
		 
	 }
	 
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
