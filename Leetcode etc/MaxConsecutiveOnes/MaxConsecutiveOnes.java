//https://leetcode.com/problems/max-consecutive-ones/
/*
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3

 * */

package leetcode;

public class MaxConsecutiveOnes {
	public int findMaxConsecutiveOnes(int[] nums) {
		int consecutiveOnes = 0;
		int maxLen = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 1) {
				consecutiveOnes++;
				maxLen = Math.max(maxLen, consecutiveOnes);
			}else {
				consecutiveOnes = 0;		//reset
			}
		}
		return maxLen;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
