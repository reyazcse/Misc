//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array
/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
If target is not found in the array, return [-1, -1].
Follow up: Could you write an algorithm with O(log n) runtime complexity?

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
 * */

/*
Solution:
	Here the idea is to use just one method to find both staring and ending position.
	To find the endig pos, we find occurrence of (target+1) and the subtract 1 from the resulting position.
	
	References: errichto youtube https://www.youtube.com/watch?v=dVXy6hmE_0U
	
 * */
package leetcode;

public class SearchRangeSortedArray2 {
	public int[] searchRange(int[] nums, int target) {
		int [] result = {-1,-1};
		if(nums == null || nums.length == 0) {
			return result;
		}
		int start = findElement(nums, target);
		int end = findElement(nums, target+1)-1;
		if(start <= end) {
			result[0] = start;
			result[1] = end;
		}
		return result;
	}
	
	//find the first occurrence of the target in nums array
	private int findElement(int[]nums, int target) {
		int n = nums.length;						
		int pos = n;			//this is needed when we want to find end position and there is just one element in 'nums'.
								//pos = -1 won't work when we have just one element in 'nums' array.
		int low=0, high=n-1;
		while(low <= high) {
			int mid = low + (high-low)/2;
			if(nums[mid] >= target) {
				pos = mid;
				high = mid-1;
			}else {
				low = mid+1;
			}
		}
		return pos;
	}
	public static void main(String[] args) {
		int []nums = {5,7,7,8,10};
		int target = 8;
		SearchRangeSortedArray2 obj = new SearchRangeSortedArray2();
		int[] result = obj.searchRange(nums, target);
		System.out.println(result[0]);
		System.out.println(result[1]);

	}

}
