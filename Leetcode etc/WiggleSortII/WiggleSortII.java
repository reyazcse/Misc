//https://leetcode.com/problems/wiggle-sort-ii/
/*
Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?
 * */
/*
Solution:
	Idea:
		We have to arrange the elements in wave form : small, big, small, big.....  Or big, small, big, small
		If the elements are distinct, then we sort the array and just swap the elements in pairs and move pointer by 2.
		
		But if we have duplicates, we sort the array and we select a mid point. 
		The small elements are in left half and big elements are in the right half
		We then put small elements at 0, 2, 4, ....... (or 1, 3, 5 depending upon how we want the wave)		
		And we put the large elements at 1, 3, 4,..... (or 0, 2, 4, ....)
		Time complexity will be O(n log n) as we use sorting and space complexity will be O(n).
		See Method1 implementation.
		
	Algorithm:
		Sort the array.
		Divide the array into two parts: mid is boundary of first part and right is boundary of second part
		Take another array. Given array is 'nums'
		In this array, for even positions put nums[mid] and do mid--
		In this array, for odd positions put nums[right] and do right--
		
		O(nlogn) complexity.
		
		Note: 
		1. How to decide where mid will be?
		For say 8 elements, we have 4 even and 4 odd places. So mid = 3 
		For say 9 elements, we have 5 even and 4 odd places, so mid = 4.
		Hence mid = (size - 1)/2
		
		2. If we have {1,1,1,1,2,2,2,2} then partition will be : 1 1 1 1 || 2 2 2 2 
		If we have {1,1,1,1,1,2,2,2,2} then partition will be : 1 1 1 1 1 || 2 2 2 2
	
 * */
package leetcode;

import java.util.Arrays;

public class WiggleSortII {
	
	//Method 1: O(nlogn) time
	public void wiggleSort(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		int mid = (n-1)/2;
		int right  = n-1;
		
		int[]result = new int[n];
		for(int i=0; i<n; i++) {
			if(i % 2 == 0) {
				result[i] = nums[mid--];
			}else {
				result[i] = nums[right--];
			}
		}
		for(int i=0; i<n; i++) {
			nums[i] = result[i];
		}
	}
	
	public static void main(String[] args) {}

}
