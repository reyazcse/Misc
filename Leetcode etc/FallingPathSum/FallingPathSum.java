//src: https://leetcode.com/problems/minimum-falling-path-sum/submissions/
//easy
package misc;

public class FallingPathSum {
	 public int minFallingPathSum(int[][] A) {
		 	int M = A.length;
		 	int N = A[0].length;
		 	int [][] table = new int [M][N];
		 	for (int i=0; i<N; i++) {
		 		table[M-1][i] = A[M-1][i];
		 	}
		 	for(int i=M-2; i>=0; i--) {
		 		for (int j=0; j<N; j++) {
		 			if (j > 0 && j < N-1) {
		 				//min of below three values plus current cell value
		 				table[i][j] =  A[i][j] + minOfThree(table[i+1][j-1], table[i+1][j], table[i+1][j+1]);
		 			}else if(j == 0) {	//first col 
		 				table[i][j] = A[i][j] + Math.min(table[i+1][j], table[i+1][j+1]);
		 			}else {				//last col
		 				table[i][j] = A[i][j] + Math.min(table[i+1][j-1], table[i+1][j]);
		 			}
		 		}
		 	}
		 	int min = Integer.MAX_VALUE;
		 	//get min value from first row
		 	for(int i=0; i<N; i++) 
		 		min = Math.min(min, table[0][i]);
		 	
		 	return min;
	    }

	 private int minOfThree(int a, int b, int c) {
		 return Math.min(a, Math.min(b, c));
	 }
		 
	 public static void main(String [] args) {
		 int [][] A = {{1,2,3},{4,5,6},{7,8,9}};
		 System.out.println(new FallingPathSum().minFallingPathSum(A));
		 
	 }
	 
}
