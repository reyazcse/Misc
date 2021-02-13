//https://leetcode.com/problems/3sum/
/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Notice that the solution set must not contain duplicate triplets.

 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThreeSum {
	//WRONG: this solution not accepted as it gives wrong answer. for e.g 1 2 2 2 3 and, then it will give many triplets which is wrong
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
        	map.put(nums[i], i);
        }
        
        for(int i=0; i<nums.length; i++) {
        	for(int j=i+1; j<nums.length; j++) {
        		int sum = nums[i] + nums[j];
        		if(map.containsKey(-sum) && map.get(-sum) != i && map.get(-sum) != j) {
        			triplets.add(Arrays.asList(nums[i], nums[j], nums[map.get(-sum)]));
        		}
        	}
        }
        return triplets;
    }
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	//this solution is appropriate(but was not accepted for one large array :()
	public List<List<Integer>> threeSumNoDups(int[] nums) {
        List<List<Integer>> triplets = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return triplets;   //base case
        //sort the array
        Arrays.sort(nums);
        for(int i=0; i<nums.length-2; i++) {
        	//move i to skip duplicates
        	if(i > 0 && nums[i] == nums[i-1]) {
        		i++;
        		continue;
        	}
        	int start = i+1;
        	int end = nums.length-1;
			
        	while(start < end) {
        		int currSum = nums[i] + nums[start] + nums[end]; 
        		if(currSum == 0) {
        			triplets.add(Arrays.asList(nums[i], nums[start], nums[end]));
        			//move start and end to skip duplicates
        			while(start < end && nums[start] == nums[start+1])start++;
        			while(start <end && nums[end] == nums[end-1])end--;
        			start++;
        			end--;
        		}else if(currSum < 0) {
        			start++;
        		}else {
        			end--;
        		}
        	}
        	
        }
        return triplets;
    }
	public static void main(String[] args) {
		//int[] nums = {-1,0,1,2,-1,-4};
		//int[]nums = {-2,0,0,2,2};
		int[]nums = {-1,0,1,2,-1,-4};
		ThreeSum obj = new ThreeSum();
		//System.out.println(obj.threeSum(nums));
		System.out.println(obj.threeSumNoDups(nums));

	}

}
