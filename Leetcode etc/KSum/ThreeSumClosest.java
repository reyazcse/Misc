//https://leetcode.com/problems/3sum-closest/
/*

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

 * */
package leetcode;

import java.util.Arrays;
/*
Solution:
	This is similar to 3Sum problem. When we get the sum, we check how far it is from the target
	If the distance is less, then we update our result.
	
	Note that in this problem, we can skip indices for i, start and end if we encounter duplicates.
	This will do some optimization but still the complexity is O(N^2).

 * */
public class ThreeSumClosest {
	 public int threeSumClosest(int[] nums, int target) {
	        //sort the array
	        Arrays.sort(nums);
	        int absSum = Integer.MAX_VALUE;
	        int resultSum = 0;
	        for(int i=0; i<nums.length-2; i++) {
	        	int start = i+1;
	        	int end = nums.length-1;
	        	while(start < end) {
	        		int currSum = nums[i] + nums[start] + nums[end];
	        		if(Math.abs(currSum - target) < absSum) {
	        			resultSum = currSum;
	        			absSum = Math.abs(currSum - target);
	        		}
	        		if(currSum == target) {
	        			return resultSum;
	        		}
	        		if(currSum < target) {
	        			start++;
	        		}else {
	        			end--;
	        		}
	        	}
	        	
	        }
	        return resultSum;
	        
	 }
	public static void main(String[] args) {
		int[]nums = {-1,0,1,1,55};
		int target = 3;
		ThreeSumClosest obj = new ThreeSumClosest();
		System.out.println(obj.threeSumClosest(nums, target));

	}

}
