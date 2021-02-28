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
Method 1 implements this idea.

See Method 2 for a straightforward solution
 * */
package leetcode;

public class MaximumProductSubarray {
	//Method 1
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
	
	//Method 2
	public int maxProduct_Simple(int[] nums) {
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		int prev_max_prod = nums[0];
		int prev_min_prod = nums[0];
		int ans = nums[0];
		
		for(int i=1; i<nums.length; i++) {
			int curr_max_prod = maxOf(nums[i], prev_max_prod * nums[i], prev_min_prod * nums[i]);
			int curr_min_prod = minOf(nums[i], prev_max_prod * nums[i], prev_min_prod * nums[i]);
			ans = Math.max(ans, curr_max_prod);
			
			prev_max_prod = curr_max_prod;
			prev_min_prod = curr_min_prod;
		}
		return ans;
	}
	
	private int maxOf(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}
	
	private int minOf(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
	public static void main(String[] args) {
		int [] nums  = {-2, 3, -4};
		MaximumProductSubarray ob = new MaximumProductSubarray();
		System.out.println(ob.maxProduct_Simple(nums));
	}

}
