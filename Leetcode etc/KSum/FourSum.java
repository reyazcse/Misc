//https://leetcode.com/problems/4sum/
/*
Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
Note:
The solution set must not contain duplicate quadruplets.

 * */

//Solution: O(N^3) time complexity
//We keep four pointers i,j,k and l. Firstly the array is sorted
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	public List<List<Integer>> fourSum (int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		for(int i=0; i<nums.length-3; i++) {
			//skip duplicates
			if(i == 0 || nums[i] > nums[i-1]) {
				for(int j = i+1; j<nums.length-2; j++) {
					if (j == i+1 || nums[j] > nums[j-1]) {
						int k = j+1;
						int l = nums.length-1;
						while(k < l) {
							int currSum = nums[i] + nums[j] + nums[k] + nums[l];
							if(currSum == target) {
								result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
								//skip duplicates after we are done with nums[k]
								while(k < l && nums[k] == nums[k+1]) k++;
								//skip duplicates after we are done with nums[l]
								while(k < l && nums[l] == nums[l-1]) l--;
								//update k and l: VERY IMPORTANT
								k++;
								l--;
							}else if (currSum < target) {
								k++;
							}else {
								l--;
							}
							
						}
					}
					
				}
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		int []nums = {1, 0, -1, 0, -2, 2};
		int target = 0;
		FourSum obj = new FourSum();
		List<List<Integer>> result = obj.fourSum(nums, target);
		System.out.println(result);

	}

}
