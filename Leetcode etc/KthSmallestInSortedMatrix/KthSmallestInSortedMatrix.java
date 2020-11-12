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
You may assume k is always valid, 1 ≤ k ≤ n2.
 * */

/*
We create a max heap of size k
Then for each element of the matrix, insert it in the heap if it is less than element at the top of heap
Before inserting, we need to remove top element so that size of heap remains k.

Time Complexity:  mn * log k, where m is no of rows and n is no of cols in the matrix	
Space Complexity: O(k) for heap of size k

 * */
package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[0].length; j++) {
				if(maxHeap.size() < k) {
					maxHeap.add(matrix[i][j]);
				}else {
					if(matrix[i][j] < maxHeap.peek()) {		//size of heap is now k
						maxHeap.poll();
						maxHeap.add(matrix[i][j]);
					}
				}
			}
		}
		return maxHeap.poll();		//return top element of heap
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
