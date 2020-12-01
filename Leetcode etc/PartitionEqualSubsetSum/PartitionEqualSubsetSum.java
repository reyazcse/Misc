//https://leetcode.com/problems/partition-equal-subset-sum/
/*
Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * */
package leetcode;

public class PartitionEqualSubsetSum {
	
	//Recursive approach : include or exclude 
	//Gives TLE
	
	public boolean canPartition(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 == 0) {
			return utl(nums, nums.length, totalSum/2);
		}
		else {
			return false;
		}

	}
	
	private boolean utl(int[] nums, int i, int sum) {
		if(sum == 0) {
			return true;
		}
		if(sum < 0 || i == 0) {
			return false;
		}
		//include or exclude
		return utl(nums, i-1, sum - nums[i-1]) || utl(nums, i-1, sum);
	}
	
	
	/***************************************TOP DOWN******************************************************************/
	//include or exclude
	
	public boolean canPartitionTopDown(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		Boolean[][]dp = new Boolean [nums.length+1][subSetSum+1];
		return utlTopDown(nums, nums.length, subSetSum, dp);

	}
	
	
	private boolean utlTopDown(int []nums, int i, int sum, Boolean[][] dp) {
		if(sum == 0) {
			return true;
		}
		if(sum < 0 || i == 0) {
			return false;
		}
		
		if(dp[i][sum] != null) {
			return dp[i][sum]; 
		}
		
		//include or exclude
		return dp[i][sum] = utlTopDown(nums, i-1, sum - nums[i-1], dp) || utlTopDown(nums, i-1, sum, dp);
		
	}
	
	
	/************************************************BOTTOM UP******************************************************************/
	//include or exclude
	
	public boolean canPartitionBottomUp(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		Boolean[][]dp = new Boolean [nums.length+1][subSetSum+1];
		
		for(int i=0; i<dp.length; i++) {
			dp[i][0] = true;				//sum is 0
		}
		
		for(int j=1; j<dp[0].length; j++) {
			dp[0][j] = false;					//empty nums but non-zero sum
		}
		
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				boolean include = (j >= nums[i-1]) ? dp[i-1][j - nums[i-1]] : false;
				boolean exclude = dp[i-1][j];
				dp[i][j] = include || exclude;
			}
		}
		
		return dp[nums.length][subSetSum];

	}
	
	/*****************************************DFS with loop****************************************************/
	//TLE error
	
	public boolean canPartitionLoop(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		
		return utlLoop(nums, 0, subSetSum);

	}
	
	
	private boolean utlLoop(int[] nums, int start, int sum) {
		if(sum == 0) {
			return true;
		}
		if(sum < 0) {
			return false;
		}
		for(int i=start; i<nums.length; i++) {
			boolean ans = utlLoop(nums, i+1, sum - nums[i]);
			if (ans) {
				return true;
			}
		}
		return false;
	}
	
	/************************************DFS with loop and top down*****************************************************/
	
	public boolean canPartitionLoopTopDown(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		int n = nums.length;
		Boolean[][] dp = new Boolean[n][subSetSum+1];
		return utlLoopTopDown(nums, 0, subSetSum, dp);

	}
	
	private boolean utlLoopTopDown(int[] nums, int start, int sum, Boolean [][] dp) {
		if(sum == 0) {
			return true;
		}
		if(sum < 0 || start == nums.length) {
			return false;
		}
		if(dp[start][sum] != null) {
			return dp[start][sum];
		}
		
		for(int i=start; i<nums.length; i++) {
			boolean ans = utlLoopTopDown(nums, i+1, sum-nums[i], dp);
			if(ans) {
				return dp[start][sum] = ans;
			}
		}
		return dp[start][sum] = false;
		
	}
	
	/*************************************DFS with loop and bottom up**************************************************/

	//See PartitionEqualSubsetSum2 for details and implementation
	
	public static void main(String[] args) {
		PartitionEqualSubsetSum ob  = new PartitionEqualSubsetSum();
		int [] nums = {1,2,5};
		System.out.println(ob.canPartitionLoop(nums));
		
	}

}
