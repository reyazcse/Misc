//https://leetcode.com/problems/partition-equal-subset-sum/

//Solution: Many approaches for the bottom up dp and why some approaches do not work
package leetcode;

public class PartitionEqualSubsetSum2 {
	//O (m * n) time where m = sum and n = length of given array
	//O (m)  space
	public boolean canPartitionBottomUp1(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		boolean[] dp = new boolean[subSetSum+1];
		dp[0] = true;
		
		for(int i=0; i<nums.length; i++) {
			for(int sum = subSetSum; sum>0; sum--) {		//Traverse from RIGHT only!!!
				if(nums[i] <= sum) {
					dp[sum] = dp[sum] || dp[sum-nums[i]];
				}
			}
		}
		
		return dp[subSetSum];

	}
	
	//This is WRONG!!! See below for explanation
	public boolean canPartitionBottomUp2(int[] nums) {
		int totalSum = 0;
		for(int num : nums) {
			totalSum += num;
		}
		if(totalSum % 2 != 0) {
			
			return false;
		}
		int subSetSum = totalSum/2;
		int n = nums.length;
		boolean[] dp = new boolean[subSetSum+1];
		dp[0] = true;
		

		for(int sum = subSetSum; sum>0; sum--) {
			for(int i=0; i<nums.length; i++) {
				if(nums[i] <= sum) {
					dp[sum] = dp[sum] || dp[sum-nums[i]];
				}
			}
		}
		
		return dp[subSetSum];

	}
	
	/*
	 * 
	 * Case 1: Filling table from left to right and sum in outer loop : WRONG
	 * 	Suppose sum = 3 and nums = {1}
	 * 	outer loop i: sum = 1 to 3
	 *  inner loop j: 1
	 *  table[] = [T, F, F] initially
	 *  Then table[1] = T
	 *  When j=2 and i = 0 then table[2] = table[2] || table[j-nums[0]] = table[2] || table[1] = T || T = T
	 *  But table[2] should be false since we cannot get sum=2 using 1!
	 *  So this is WRONG !!!
	 *  
	 * Case 2 : Filling table from  right to left and sum in outer loop : WRONG
	 * 	Suppose sum = 6 and nums = {2,4}. Then table[6] should be true as we can get sum=6 using [2,4]
	 * 	outer loop i : sum = 6 to 1
	 * 	inner loop j : 2, 4
	 * 	table[6] = table[6] || table[6-2] = table[6] || table[4] = F || F = F
	 * 	only table[4] and table[2] will be true after both loops are done.
	 * 	But this is wrong as table[6] should be true also. Hence WRONG!!!
	 * 
	 * Case 3 : Filling table from left to right and sum in inner loop  : WRONG
	 * 	Suppose sum = 3, and nums = {1};
	 *  outer loop i : 1
	 *  inner loop j : 1 to 3
	 *  Then table[1] = table[1] || table[1-1] = F || T = T
	 *  table[2] = table[2] || table[2-1] = F || T = T
	 *  But table[2] should be False since we cannot get sum = 2 using nums = {1}. 
	 *  This is WRONG!!!
	 *  So we should not traverse from left right
	 *  Instead traverse from right to left as shown in the first method above
	 * 
	 * */

	public static void main(String[] args) {
		int [] nums = {3,3,3,4,5};
		PartitionEqualSubsetSum2 ob = new PartitionEqualSubsetSum2();
		System.out.println(ob.canPartitionBottomUp2(nums));

	}

}
