//https://leetcode.com/problems/reverse-pairs/
/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
 * */

/*
Solution:
	The ideas similar to counting inversions using merge sort.
	O(n log n) time and O(n) space
 * */
package leetcode;

public class ReversePairs {
	public int reversePairs(int[] nums) {
		return mergeSort(nums, 0, nums.length-1);
	}
	
	private int mergeSort(int[]nums, int lo, int hi) {
		if(lo >= hi) {
			return 0;
		}
		int count = 0;
		int mid = lo + (hi-lo)/2;
		count += mergeSort(nums, lo, mid);
		count += mergeSort(nums, mid+1, hi);
		count += merge(nums, lo, mid, hi);
		return count;
	}
	
	private int merge(int[]nums, int lo, int mid, int hi) {
		int [] tmp = new int[hi-lo+1];
		int i=lo, j=mid+1;
		int count = 0;
		//count inversions
		while(i <= mid && j <= hi) {
			if(nums[i] > 2 * nums[j]) {
				count += mid-i+1;			//all elements after i will also be greater than twice of nums[j]
				j++;
			}else {
				i++;
			}
		}
		
		//merge operation of merge sort
		i = lo;
		j = mid+1;
		int k = 0;
		while(i <= mid && j <= hi) {
			if(nums[i] <= nums[j]) {
				tmp[k++] = nums[i++]; 
			}else {
				tmp[k++] = nums[j++];
			}
		}
		while(i <= mid) {
			tmp[k++] = nums[i++];
		}
		while(j <= hi) {
			tmp[k++] = nums[j++];
		}
		//copying sorted array into original array
		for(int elt : tmp) {
			nums[lo++] = elt;
		}
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/*
Notes:
	1. We use long variable to take care of overflow as test case will fail if we have this data:
	   [inf, inf, inf]
*/
