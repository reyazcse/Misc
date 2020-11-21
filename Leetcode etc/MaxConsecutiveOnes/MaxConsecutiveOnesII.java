/*
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:

Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    		 After flipping, the maximum number of consecutive 1s is 4.
 * */

/*
Solution: 
	Sliding window technique:
		When we get a zero then if it is first 0, it is part of window
		But if this 0's count is more than 1, then we have to increment 'start' till we are past the first 0 in our window
		If we get 1, then it is part of window
		
		Complexity: O(2n) 
		
		Note: We can reduce complexity to O(n) if we use store index of last 0 in the window
		The idea is when we find a 0, then we check if prev 0 is part of window or not.
		If it is part of window, then move start to the next element after the prev 0
		Else do not move start, just calculate the maxLength since this 0 will be included.
		Also update that current 0 will be now part of window.
		Complexity: O(n)
 * */
package leetcode;

public class MaxConsecutiveOnesII {
	// O(2n) solution
	public int findMaxConsecutiveOnes(int[] nums) {
		int countZeroes = 0;
		int maxLen = 0;
		int start = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 0) {
				countZeroes++;
			}
			//move start to the next element of the prev 0
			while(countZeroes > 1) {
				if(nums[start] == 0) {
					countZeroes--;
				}
				start++;
			}
			maxLen = Math.max(maxLen, i-start+1);
		}
		return maxLen;
	}
	
	//O(n) solution
	public int findMaxConsecutiveOnesOptimized(int[] nums) {
		int prevIdx = -1;
		int start = 0, maxLen = 0;
		for(int i=0; i<nums.length; i++) {
			if(nums[i] == 0) {
				//if already one 0 is there, then move start to the next index of that 0
				if(prevIdx >= start) {
					start = prevIdx + 1;
				}else {
					maxLen = Math.max(maxLen, i-start+1);
				}
				//include current 0 in the window
				prevIdx = i;
			}else {		//if nums[i] == 1
				maxLen = Math.max(maxLen, i-start+1);
			}
		}
		return maxLen;
	}
	public static void main(String[] args) {
		//int [] nums = {1,0,1,1,0};
		int [] nums = {1,0,1,1,0,0,1,1,1,0,1};
		MaxConsecutiveOnesII ob = new MaxConsecutiveOnesII();
		System.out.println(ob.findMaxConsecutiveOnes(nums));
		System.out.println(ob.findMaxConsecutiveOnesOptimized(nums));

	}

}
