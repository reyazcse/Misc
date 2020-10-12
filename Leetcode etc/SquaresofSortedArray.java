//https://leetcode.com/problems/squares-of-a-sorted-array/
/*
Given an array of integers A sorted in non-decreasing order, 
return an array of the squares of each number, also in sorted non-decreasing order.

Example:
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
 * */

/*
Solution 1:
	We take a result array where we store the squares.
	We take two pointers, left and right. left will start from beginning and right from end
	Also take an index starting from the end of the result array.
	We then compare the absolute value of A[left] and A[right]
	We store the square of the larger value we got in above step. We also increment/decrement left/right pointers

Note:
We can also solve it based on the idea of merging two sorted arrays.
We find the index where the element is increasing.
Let's say we have the array = {-4,-2,0,3,5,7}
We find k=2. Then we have one sorted array from i=k-1 till 0 another sorted array
from k till last 
 
 * */
package leetcode;

public class SquaresofSortedArray {
	public int[] sortedSquares(int[] A) {
        int []result = new int[A.length];
        //base case
        if(A == null || A.length == 0) {
            return result;    
        }
        int left=0, right = A.length-1;
        int pos = A.length-1;
        while(left <= right) {
        	//square of the element at right pointer will be larger
            if(Math.abs(A[left]) <= Math.abs(A[right])) {
            	result[pos] = A[right] * A[right];
            	right--;
            }else { //square of the element at left pointer will be larger
            	result[pos] = A[left] * A[left];
            	left++;
            }
            pos--; //update index for result array
        }
        return result;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
