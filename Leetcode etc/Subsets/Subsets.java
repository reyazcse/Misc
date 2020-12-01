//https://leetcode.com/problems/subsets/
/*
Given an integer array nums, return all possible subsets (the power set).
The solution set must not contain duplicate subsets.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

 * */

//Solution:
//References: https://www.youtube.com/watch?v=MsHFNGltIxw
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
	/*
	 *	Method 1: choose a number and then choose numbers after it
	 *	Complexity: 
	 *		O(2 ^ n) time and 
	 *		O(n space) => path + dfs stack
	 * */

	public List<List<Integer>> subsets_1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs1(nums, 0, path, result);
		return result;

	}
	private void dfs1(int[] nums, int idx, List<Integer>path, List<List<Integer>> result) {
		//at each level there is a subset
		result.add(new ArrayList<>(path));

		for(int i=idx; i<nums.length; i++) {
			path.add(nums[i]);
			dfs1(nums, i+1, path, result);
			path.remove(path.size()-1);				//backtrack
		}
	}


	/*
	 * Method 2 : to choose or not to choose a number
	 * This one has slightly higher complexity than M1 as we have more nodes
	 * Complexity:
	 * 		O(2 ^ (n+1)) time since there are n+1 levels
	 * 		O(n) space : path + dfs call stack = O(n) + (n) = O(n)
	 * 
	 * */

	public List<List<Integer>> subsets_2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs2(nums, 0, path, result);
		return result;
	}

	private void dfs2(int[] nums, int idx, List<Integer>path, List<List<Integer>> result) {
		if(idx == nums.length) {
			result.add(new ArrayList<>(path));
			return;
		}

		//not choose
		dfs2(nums, idx+1, path, result);

		//choose
		path.add(nums[idx]);
		dfs2(nums, idx+1, path, result);
		path.remove(path.size()-1);				//restore
	}

	/*
	 * Method 3 : Using bit manipulation
	 * 		If we have three numbers  in the array:
	 * 		There are 2^3 = 8 subsets as follows
	 * 		000 = {}
	 * 		001 = {1}
	 * 		010 = {2}
	 * 		011 = {1,2}
	 * 		...
	 * 		111 = {1,2,3}
	 * 
	 * 		Complexity:
	 * 			n * 2^n time and O(1) space 
	 * 			
	 * */
	public List<List<Integer>> subsets_3(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		int n = nums.length;
		for(int i=0; i < (1 << n); i++) {			// i < 2^n
			int number = i;							//number will from 0 till 7
			List<Integer> path = new ArrayList<>();
			for(int k=0; k<n; k++) {
				if((number & 1) == 1) {
					path.add(nums[k]);
				}
				number  = number >> 1;
			}
			result.add(new ArrayList<>(path));
		}
		return result;
	}


}
