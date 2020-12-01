//https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

* */

/*
Solution:
	Backtracking method. This is a strongly NP complete. If k = 3 or more then no pseudo polynomial time could exist
	Approach:
		If k is 0 or total sum of elements of nums array is less than k, return false;
		If total sum is not divisible by k, return false;
		For each subset, start from the beginning of the nums array and add number to the sum_so_far for current subset.
		While choosing a number from nums array, check that it is not used that adding it does not increase sum past target.
		If sum_so_far for current subset k is equal to target sum, then call the method for the k-1 th subset.
		
		We have a boolean array 'used' to keep track of elements already used.
		
		Also we return true when k=1. Reason is suppose we have total sum=20 and k=4. Then per subset, we need 5
		If we manage to have 3 subsets with each having sum of 5, then only 5 will remain which we can put in the last subset.
		
		 
		 
	Complexity:
		Space : O(n) space for the recursive call stack and O(n) for the array to keep track of used element = O(n)
		Time: 
		
References : https://www.youtube.com/watch?v=qpgqhp_9d1s
Another method: https://www.youtube.com/watch?v=DB-9JlnbBpM

 * */
package leetcode;

public class PartitiontoKEqualSumSubsets {
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int total_sum = 0;
		for(int num : nums) {
			total_sum += num;
		}
		if(k == 0 || total_sum < k || total_sum%k != 0) {
			return false;
		}
		int target = total_sum/k;
		boolean[] used = new boolean[nums.length];
		return utl(nums, k, 0, target, 0, used);
	}
	
	/*
	 * k = subset number. k, k-1, k-2, ...1
	 * start is start of index in the 'nums' array
	 * target = target sum to achieve for each subset
	 * sum_so_far = current sum accumulated in the kth subset
	 * used = boolean array to keep track of used elements of the nums array
	 * */
	private boolean utl(int[]nums, int k, int start, int target, int sum_so_far, boolean[] used) {
		if(k == 1) {					
			return true;				//no need to check for the last subset
		}
		if(sum_so_far == target) {		//current subset success
			return utl(nums, k-1, 0, target, 0, used);
		}
		for(int i=start; i<nums.length; i++) {
			
			if(!used[i] && (sum_so_far + nums[i] <= target)) {					//minor optimization: if nums is sorted, then we can break quickly if we find sum_so_far + nums[i] > target 
				used[i] = true;													//choose
				if(utl(nums, k, i+1, target, sum_so_far + nums[i], used)) {
					return true;
				}
				used[i] = false;												//un-choose i.e. backtrack
			}
		}
		return false;
	}
	public static void main(String[] args) {
		PartitiontoKEqualSumSubsets ob  = new PartitiontoKEqualSumSubsets();
		int [] nums = {4,3,2,3,5,2,1};
		int k=4;
		System.out.println(ob.canPartitionKSubsets(nums, k));

	}

}
//Note if we sort nums array, then maybe we can break from loop when we find sum_so_far + nums[i] > target, rather than
//checking for the next element.
