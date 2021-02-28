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
Algorithm:
	Create a min heap of size equal to no of rows.
	Insert each element of the first column initially.
	After this we pop k elements from the heap.
	So we start from i=0 till i < k
	Each time we pop, we push next element from the same row if there is still element left in that row
	If there is no more element to be added, we simply skip the iteration after popping.
	
	
Complexity:
	Time: It can be that k = m*n, then in worst case we need to add all the elements to the heap. So
		  complexity will be 
		  m*n log m, where m = no of rows and n = no of cols

 	Space: Size of heap  = no of rows in the matrix. Hence O(m)
	

 * */
package leetcode;

import java.util.PriorityQueue;

public class KthSmallestInSortedMatrix2 {
	private class Item {
		public int value;
		public int row;
		public int col;
		public Item(int val, int row, int col) {
			this.value = val;
			this.row = row;
			this.col = col;
		}
	}
	
	public int kthSmallest(int[][] matrix, int k) {
		PriorityQueue<Item> minHeap = new PriorityQueue<>((itm1, itm2) -> itm1.value - itm2.value);
		for(int i=0; i<matrix.length; i++) {
			Item itm = new Item(matrix[i][0], i, 0);
			minHeap.add(itm);
		}
		Item top = null;
		for(int i=0; i<k; i++) {
			top = minHeap.poll();
			if(top.col < matrix[0].length - 1 ) {
				Item next = new Item(matrix[top.row][top.col+1], top.row, top.col+1);
				minHeap.add(next);
			}
		}
		return top.value;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
