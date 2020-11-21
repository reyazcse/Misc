//https://leetcode.com/problems/max-consecutive-ones-iii/
/*
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
 * */


package leetcode;

public class MaxConsecutiveOnesIII {
	public int longestOnes(int[] nums, int K) {
		int countZeroes = 0;
		int maxLen = 0;
		int start = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 0) {
				countZeroes++;
			}
			//move start to the next element of the first 0 in the window
			while(countZeroes > K) {
				if(nums[start] == 0) {
					countZeroes--;
				}
				start++;
			}
			maxLen = Math.max(maxLen, i-start+1);
		}
		return maxLen;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
