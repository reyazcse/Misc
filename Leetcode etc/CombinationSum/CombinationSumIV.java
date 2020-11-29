//https://leetcode.com/problems/combination-sum-iv/
/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 * */
package leetcode;

public class CombinationSumIV {
	
	/*****************************************DFS with include or exclude********************************************/
	public int combinationSum4(int[] nums, int target) {
		return utl(nums, 0, target);
	}
	
	private int utl(int[] nums, int i, int sum) {
		if(sum == 0) {
			return 1;
		}
		if(sum < 0 || i == nums.length) {		
			return 0;
		}
		
		//include. After including ith element, we go to 0th element since we have to count like 2,1,1 also when we are at 2 (apart from 1,1,2 ...)
		int incl = utl(nums, 0, sum - nums[i]);		
		
		//exclude
		int excl = utl(nums, i+1, sum);
		
		return incl + excl;
	}
	
	
	
	
	
	/****************************************DFS with loop*********************************************************/
	public int combinationSum4A(int[] nums, int target) {
		return utlA(nums, target);
	}
	
	private int utlA(int[] nums, int sum) {
		if(sum == 0) {
			return 1;
		}
		if(sum < 0) {		
			return 0;
		}
		int totalWays = 0;
		for(int index = 0; index < nums.length; index++) {		//index always starts with 0 since we have to take 2,1,1 also when we are at 2
			totalWays += utlA(nums, sum - nums[index]);
		}
		
		return totalWays;
	}
	
	
	
	
	
	//DP based on the DFS loop method()
	public int combinationSum4DP(int[] nums, int target) {
		int [] dp = new int[target+1];
		dp[0] = 1;
		for(int i=1; i<=target; i++) {
			for(int j=0; j<nums.length; j++) {
				if(nums[j] <= i) {
					dp[i] += dp[i - nums[j]];
				}
			}
		}
		return dp[target];
	}
	
	
	
	
	
	
	
	public static void main (String [] args) {
		int [] nums = {1,2,3};
		int target = 4;
		CombinationSumIV ob = new CombinationSumIV();
		System.out.println(ob.combinationSum4(nums, target));
		System.out.println(ob.combinationSum4A(nums, target));
		System.out.println(ob.combinationSum4DP(nums, target));
	}
}
