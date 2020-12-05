//https://www.geeksforgeeks.org/find-median-row-wise-sorted-matrix/
/*
We are given a row-wise sorted matrix of size r*c, we need to find the median of the matrix given. It is assumed that r*c is always odd.
Examples: 

Input : 1 3 5
        2 6 9
        3 6 9
Output : Median is 5

Input : 1 3 5
        3 6 9
        2 6 9
        
Output : Median is 5

 * */

/*
Solution:
	Here it is to be noted that r*c is odd and matrix is only rowwise sorted and NOT COLUMNWISE sorted
	
	It can be solved using binary search similar to finding Kth smallest element in a matrix.
	Refer this: KthSmallestInSortedMatrix3.java
	
	Algorithm:
		Find the min and max element in the matrix.
		We have to find kth element. 
			k = r*c/2 + 1 
		Do binary search while min < max
		Get the count of elements less than or equal to mid.
		Once this count is k, we return the min/max
		
		To get the total count for an element, we find the count in each row using binary search.
		
		Complexity:
			r = number of rows
			c = number of columns
			To get count, we do binary search for each row: r * log c
			So total complexity: (r * log c) * log (max - min)
	
 * */
package misc;

public class MatrixMedian {
	int median(int matrix[][], int r, int c) {
		int k = r*c/2 + 1;
		
		int min = getMinMax(matrix)[0];
		int max = getMinMax(matrix)[1];
		
		
		while(min < max) {
			int mid = min + (max-min)/2;
			int count = getCount(matrix, mid);
			if(count < k) {
				min = mid+1;
			}else {
				max = mid;
			}
		}
		return min;
	
	}
	
	//returns min and max element in the matrix
	//since matrix is rowwise sorted, min element is in first column and max element in last column
	private int[] getMinMax(int[][] matrix) {
		int cols = matrix[0].length;
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		for(int i=0; i<matrix.length; i++) {
			min = Math.min(min, matrix[i][0]);				//first column
			max = Math.max(max, matrix[i][cols - 1]);		//last column
		}
		return new int[] {min, max};
	}
	
	//returns count of elements less than equal to  'val'
	private int getCount(int[][] matrix, int val) {
		int count = 0;
		for(int [] row : matrix) {
			int idx = getIndex(row, val);			//get index of element greater than 'val' in current row
			count += idx-0;
		}
		return count;
	}
	
	//returns index of element greater than val in the array.
	//example : int [] row = {10,20,30,40,50,60};
	// val:output = 1:0; 10:1; 50:5; 55:5; 60:6; 70:6
	private int getIndex(int []row, int val) {
		int lo = 0, hi = row.length-1;
		while(lo < hi) {
			int mid = lo + (hi - lo)/2;
			if(row[mid] > val) {
				hi = mid;
			}else {
				lo = mid+1;
			}
		}
		
		if(row[lo] > val) {
			return lo;
		}else {
			return row.length;
		}
	}
	public static void main(String[] args) {
		int [][] matrix = {{2,6,9},
							{1,3,5},
							{3,6,9}};
		int r = matrix.length;
		int c = matrix[0].length;
		MatrixMedian ob = new MatrixMedian();
		int median = ob.median(matrix, r, c);
		System.out.println(median);

	}

}
