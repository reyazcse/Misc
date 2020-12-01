//https://leetcode.com/problems/subsets-ii/
/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]

 * */

//Solution reference : https://www.youtube.com/watch?v=0ElTC4XiDvc
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
	/*
	 * Method 1: enumerate numbers for a position
	 * Complexity:
	 * 		Time: O(2^n) time 
	 * 		Space: O(n) for path and O(n) for dfs call stack = O(n)
	 * */
	public List<List<Integer>> subsetsWithDup_1(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Arrays.sort(nums);										//put same numbers together
		dfs1(nums, 0, path, result);
		return result;
	}
	
	private void dfs1(int[] nums, int idx, List<Integer> path, List<List<Integer>> result) {
		result.add(new ArrayList<>(path));				//at each level there is a subset
		
		for(int i=idx; i<nums.length; i++) {
			if(i > idx && nums[i] == nums[i-1]) {		//skip duplicates
				continue;
			}
			
			path.add(nums[i]);
			dfs1(nums, i+1, path, result);
			path.remove(path.size()-1);					//restore
		}
	}
	
	/*
	 * Method 2: To choose or not to choose a number
	 * 		     Here while going to  'not choose' branch, check if last element of path is not same as current number
	 * 			 then only go to the 'not choose' branch. Let's say we have {1,2} and we are at element 2. Then we should not
	 * 			 go to 'not choose' branch since last element is similar to element 2. If we go, then we will have {1,2} in 
	 * 			 solution which we have already calculated before!
	 *  		
	 * Complexity:
	 * 		Time: O(2^ (n+1)) time since there are n+1 levels 
	 * 		Space: O(n) for path and O(n) for dfs call stack = O(n)
	 * 
	 * */
	
	public List<List<Integer>> subsetsWithDup_2(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Arrays.sort(nums);										//put same numbers together
		dfs2(nums, 0, path, result);
		return result;
	}
	
	private void dfs2(int[] nums, int idx, List<Integer> path, List<List<Integer>> result) {
		if(idx == nums.length) {						//subset is at the last level
			result.add(new ArrayList<>(path));
			return;
		}
		
		//choose
		path.add(nums[idx]);
		dfs2(nums, idx+1, path, result);
		path.remove(path.size()-1);					//restore
		
		if(path.size() > 0 && path.get(path.size()-1) == nums[idx]) {		//to avoid duplicate subset
			return;
		}
		
		//not choose
		dfs2(nums, idx+1, path, result);
	}
	
	
	/*
	 * Method 3: Letter combinations of a phone number.
	 * 			 Count the occurrences of each number in the given array.
	 * 			 So for {1,2,2}, we have these counts:
	 * 			 1:1; 2:2
	 * 			 We can choose 1 for 0 or 1 time.
	 * 			 We can chose 2 for 0 or 1 or 2 times.
	 * 
	 * Complexity: 
	 * 			O(2^n) time and O(n) space
	 * */
	
	public List<List<Integer>> subsetsWithDup_3(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		Arrays.sort(nums);										//put same numbers together
		dfs3(nums, 0, path, result);
		return result;
	}
	
	private void dfs3(int[] nums, int idx, List<Integer> path, List<List<Integer>> result) {
		if(idx == nums.length) {
			result.add(new ArrayList<Integer>(path));
			return;
		}
		
		int k = 0;
		
		while(idx + k < nums.length && nums[idx] == nums[idx + k]) {
			k++;
		}
		
		for(int j=0; j<=k; j++) {
			dfs3(nums, idx + k, path, result);
			path.add(nums[idx]);
		}
		
		//restore or backtrack
		for(int j = path.size()-1; k >= 0; k--, j--) {
			path.remove(j);
		}
	}
	public static void main(String[] args) {
		int [] nums = {1,2,2};
		SubsetsII ob = new SubsetsII();
		List<List<Integer>> subsets = ob.subsetsWithDup_3(nums);
		for(List<Integer> subset : subsets) {
			System.out.println(subset);
		}
	}

}
