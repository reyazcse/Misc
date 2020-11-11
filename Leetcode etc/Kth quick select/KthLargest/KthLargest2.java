//https://leetcode.com/problems/kth-largest-element-in-an-array/
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
 * */

//SOLUTION:
//Let there be n elelments in the array. Then kth largest is same as n-k+1 th smallest
//So we find in terms of kth smallest algorithm
package leetcode;

public class KthLargest2 {
	public int findKthLargest(int[] nums, int k) {
		int n = nums.length;
		int res = findKthSmallest(nums, 0, nums.length-1, n-k+1);
		return res;
	}
	
	public int findKthSmallest(int[] nums, int l, int r, int k) {
//		if(l > r) {
//			return Integer.MAX_VALUE;
//		}
		int pivot = randomPartition(nums, l, r) ;
		if(pivot-l+1 == k) {
			return nums[pivot];
		}else if(pivot-l+1 > k) {
			return findKthSmallest(nums, l, pivot-1, k);
		}else {
			return findKthSmallest(nums, pivot+1, r, k-(pivot-l+1));
		}
	}
	
	private int randomPartition(int[]nums ,int l, int r) {
		int pivot = l + (int)(Math.random()*(r-l+1));
		int x = nums[pivot];
		swap(nums, pivot, r);
		int curr = l-1;
		for(int i=l; i<=r-1; i++) {
			if(nums[i] <= x) {
				curr++;
				swap(nums, curr, i);
			}
		}

		swap(nums, curr+1, r);
		return curr+1;
	}
	
	private void swap(int []nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	
	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5};
		int k=3;
		KthLargest2 ob = new KthLargest2();
		System.out.println(ob.findKthLargest(nums, k));

	}

}
