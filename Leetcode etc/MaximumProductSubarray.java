//https://leetcode.com/problems/maximum-product-subarray/
/*
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
 * */

/*
The idea is :
When we are a number, we have to see if we can include it in the result or not
If the number is negative, then we MAY include this number if we already have a large negative value: nums[i] * (prev neg value)
If the number is positive, then we MAY include this number by multiplying it with prev positive value.

So this means we need to keep track of min and max value (min can be negative or positive).
If nums[i] < 0, then we have to swap min and max since we get new max value by multiplying prev min (which is negative)
and current negative value nums[i].

We have localMax and localMin variables to keep track of least and max value. 
Also note at each element, nums[i] can be the least or max value: for example localMax = Math.max(nums[i], localMax*nums[i]);
 * */
package leetcode;

public class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		int globalMax = nums[0];		//result
		int localMax = nums[0];			//max prev value
		int localMin = nums[0];			//min prev value
		for(int i=1; i<nums.length; i++) {
			if(nums[i] < 0) {					//swap prev min and max value to get new min and max value by multiplying this neg number
				int temp = localMax;
				localMax = localMin;
				localMin = temp;
			}
			localMax = Math.max(nums[i], localMax*nums[i]);
			localMin = Math.min(nums[i], localMin*nums[i]);
			globalMax = Math.max(globalMax, localMax);
		}
		return globalMax;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
