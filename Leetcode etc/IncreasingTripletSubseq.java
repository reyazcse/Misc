//https://leetcode.com/problems/increasing-triplet-subsequence/
/*
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. 
If no such indices exists, return false.

Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 * */

//Solution: O(n) time and O(1) space. Brute force would be to consider three loops which is O(n^3)
//Idea is take two numbers a and b such that a < b. Then update a and b if we find a number <= either a or b

package leetcode;

public class IncreasingTripletSubseq {
	public boolean increasingTriplet(int[] nums) {
		int a = Integer.MAX_VALUE;
		int b = Integer.MAX_VALUE;

		for(int i=0; i<nums.length ;i++) {
			if(nums[i] <= a) {
				a = nums[i];
			}else if (nums[i] <= b) {
				b = nums[i];
			}else {
				return true;                //nums[i] is greater than both a and b and (a<b) hence true
			}
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
