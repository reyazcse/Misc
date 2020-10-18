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
	We solve it using binary search.
	The idea is when we are at an element, we have three cases:
	1. the value is equal to the target. In this case the we can either point to the
	leftmost or anywhere. If we are at leftmost and we search for leftmost occurrence, then
	we return that position else we go to the left half.
	2. the value is smaller than the target: we go to left half then
	3. the value is higher than the target: we go to the right half then
 * */
package leetcode;

public class SearchRangeSortedArray {
	public int[] searchRange(int[] nums, int target) {
		int [] result = {-1,-1};
		if(nums == null || nums.length == 0) {
			return result;
		}
		int start = getStart(nums,target);
		if(start == -1) {
			return result;
		}
		int end = getEnd(nums, target);
		result[0] = start;
		result[1] = end;
		return result;
	}
	
	//get starting position
	private int getStart(int []nums, int target) {
		int low=0, high=nums.length-1;
		
		while(low <= high) {
			int mid = low + (high-low)/2;
			if(nums[mid] == target) {
				if(mid == 0 || nums[mid-1] != target) {
					return mid;
				}
				high = mid-1;
			}else if (nums[mid] < target) {
				low = mid+1;
			}else high = mid-1;
		}
		return -1;
	}
	
	//get ending position
	private int getEnd(int []nums, int target) {
		int n = nums.length;
		int low=0, high=n-1;
		
		while(low <= high) {
			int mid = low + (high-low)/2;
			if(nums[mid] == target) {
				if(mid == n-1 || nums[mid+1] != target) {
					return mid;
				}
				low = mid+1;
			}else if (nums[mid] < target) {
				low = mid+1;
			}else high = mid-1;
		}
		return -1;
	}
	public static void main(String[] args) {
		int []nums = {5,7,7,8,8,10};
		int target = 8;
		SearchRangeSortedArray obj = new SearchRangeSortedArray();
		System.out.println(obj.searchRange(nums, target));
		System.out.println(nums[0]);
		System.out.println(nums[1]);

	}

}
