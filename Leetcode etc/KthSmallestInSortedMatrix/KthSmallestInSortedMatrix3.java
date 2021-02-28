//https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n^2.
 * */


/*
[The idea is quick select, but the difference is we actually count number of elements <= target each time]
If we sort all the elements in asc order, then we return the kth element. This gives us the following idea:
For an element we find count of elements less than equal to it. IF we find such an element 
whose count is k, return that element.

The algorithm is as follows:
	We solve using binary search.
	The idea is take the min and max element from the matrix. 
	min element is at top left corner and max element is at bottom right corner
	Then we take mid of these two values.
	We find how many elements in the matrix are less than or equal to mid.
	If this 'count' is less than k,then it means we need to go right to increment value of min
	So we do min = mid+1
	If we find count equal to k, then it means we need to go left. We SHOULD not return the value immediately.
	So we do max = mid.
	We return either min when min  = max.
	
	Suppose mid = 15 and and k = 8.
	Suppose we get count for mid to be 8. Now it can happen that 15 is not there in the matrix, but 14 is there and count of 14 is 
	also k.
	So we should not return value when we get count as k. We have to do max = mid instead and go left.
	
	
Complexity:
	Time:   n*log(max - min), where n is time to get count.
	Space: O(1)


 * */
package leetcode;

public class KthSmallestInSortedMatrix3 {
	public int kthSmallest(int[][] matrix, int k) {
		int m = matrix.length;
		int n = matrix[0].length;
		int min = matrix[0][0];
		int max = matrix[m-1][n-1];
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
	
	//returns count of elements less than equal to 'elt'
	private int getCount(int[][] matrix, int elt) {
		int m = matrix.length, n = matrix[0].length;
		int row = 0, col = n-1;
		int count = 0;
		while(row < m && col >=0) {
			if(matrix[row][col] <= elt) {
				count += col+1;
				row++;					//go to next row to add more count
			}else {			
				col--;					//check prev element
			}
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
