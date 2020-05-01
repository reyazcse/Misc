//src: https://leetcode.com/problems/set-matrix-zeroes/
//if a cell(i,j) contains 0, fill 0's in row i and col j

//Soln:
package leetcode;

public class SetZeroesMatrix2 {
	 //O(n) space and O(mn) time
	 public void setZeroes(int[][] matrix) {
		 int m = matrix.length;
		 int n = matrix[0].length;
		 boolean isRowZero = false;
		 boolean []cols = new boolean[n];
		 //mark cols which has a 0
		 for(int i =0; i<m;i++) {
			 for(int j=0; j<n; j++) {
				 if(matrix[i][j] == 0) {
					 cols[j] = true;
				 }
			 }
		 }
		 
		 for(int row=0; row < m; row++) {
			 isRowZero = false;
			 for(int col=0; col < n; col++) {
				 if(matrix[row][col] == 0) {
					 isRowZero = true;
					 break;
				 }
			 }
			 for(int col=0; col < n; col++) {
				 if(isRowZero || cols[col])
					 matrix[row][col] = 0;
			 }
		 }
		 
	 }
	 //ANOTHER SOLUTION WHICH IS MOST OPTIMAL. Here we use first row and first col of matrix
	 //O(1) space and O(mn) time
	 public void setZeroesOptimal(int[][] matrix) {
		 int m = matrix.length;
		 int n = matrix[0].length;
		 boolean isFirstRowZero = false;
		 boolean isFirstColZero = false;
		 boolean []cols = new boolean[n];
		 //here we use first row to mark cols having 0
		 //and we use first row to mark cols having 0
		 //we also use two bool flags to mark if col 0 or row 0 contain 0
		 
		 for(int i=0; i<n; i++) {
			 if(matrix[0][i] == 0) {
				 isFirstRowZero = true;
				 break;
			 }
		 }
		 for(int i=0; i<m; i++) {
			 if(matrix[i][0] == 0) {
				 isFirstColZero = true;
				 break;
			 }
		 }
		 
		 for(int i = 1; i<m;i++) {
			 for(int j=1; j<n; j++) {
				 if(matrix[i][j] == 0) {
					 matrix[0][j] = 0;  //mark col j to have 0
					 matrix[i][0] = 0;  //mark row i to have 0
					 
				 }
			 }
		 }
		 
		 for(int i = 1; i<m;i++) {
			 for(int j=1; j<n; j++) {
				 if(matrix[i][0] == 0 || matrix[0][j] == 0) {
					 matrix[i][j] = 0;
				 }
			 }
		 }
		 //fill first row with zeroes if it contains 0
		 if(isFirstRowZero) {
			 for (int i=0; i<n; i++)
				 matrix[0][i] = 0;
		 }
		 if(isFirstColZero) {
			 for (int i=0; i<m; i++)
				 matrix[i][0] = 0;
		 }
		 
		 
	 }

	public static void main(String[] args) {
		
		//int [][] matrix = [[1,1,1],[1,0,1],[1,1,1]];
		
		// TODO Auto-generated method stub

	}

}
