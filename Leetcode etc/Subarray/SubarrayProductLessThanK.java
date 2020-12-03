//https://leetcode.com/problems/subarray-product-less-than-k/

/*
Your are given an array of positive integers nums.
Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * */

//Solution : Using sliding window technique
package leetcode;

public class SubarrayProductLessThanK {
	
	/*
	 * WRONG solution:
	 * 		Even though we try to take care of overflow, but still overflow can occur and gives us wrong product < k
	 * 		Note that 'nums' has only +ve integers
	 * */
	public int numSubarrayProductLessThanK_WRONG(int[] nums, int k) {
		int count = 0;
		for(int i=0; i<nums.length; i++) {
			int product = 1;
			for(int j=i; j<nums.length; j++) {
				product *= nums[j];
				if(product < 0) {					//-ve means overflow since nums contains all +ves
					continue;
				}
				if(product < k) {
					count++;
				}
			}
		}
		return count;
	}
	
	/*
	 * Correct solution: using sliding window technique
	 * 	On(n) time and O(1) space
	 * */
	
	public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;										//since we have positive integers only
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while(prod >= k) {
            	prod = prod/nums[left];
            	left++;
            }
            ans += right-left+1;
            
        }
        return ans;
    }
	
	/*
	 * Correct solution 2 : This is similar to above solution. Just a bit simple
	 * 
	 * */
	
	public int numSubarrayProductLessThanK_2(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while(left < right && prod >= k) {
            	prod = prod/nums[left];
            	left++;
            }
            if(prod < k) {
            	ans += right-left+1;
            }
            
            
        }
        return ans;
    }
	
	
	public static void main(String[] args) {
		int [] nums = {10, 15, 1, 1, 2};
		int k = 3;
		SubarrayProductLessThanK ob = new SubarrayProductLessThanK();
		System.out.println(ob.numSubarrayProductLessThanK(nums, k));
	
			

	}

}
