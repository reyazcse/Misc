//Program to count square matrices of size >= 2*2
package misc;

public class CountMatrices {
	//returns count of square matrices which are of sizes greater than or equal to 2*2
	public static int countMatrices (int [][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int l,m;
		int cnt=0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				l = i+1;
				m = j+1;
				while (l < row && m < col) {
					cnt++;
					l++;m++;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) {
		int [][] matrix = new int[4][4];
		System.out.println(countMatrices(matrix));

	}

}
