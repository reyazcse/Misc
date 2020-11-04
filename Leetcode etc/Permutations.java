//https://leetcode.com/problems/permutations/
/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

 * */

//Solution: The idea is: at each position we can fill with all the numbers. 
//We also need to backtrack. We have to use a number only if it is not used.
package misc;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums == null || nums.length == 0) {
			return result;
		}
		boolean[] used = new boolean[nums.length];
		ArrayList<Integer> path = new ArrayList<>();
		dfs(result, used, path, nums);
		return result;
	}

	private void dfs(List<List<Integer>> result, boolean[] used, List<Integer> path, int[] nums) {
		if(path.size() == nums.length) {
			result.add(new ArrayList<Integer>(path));  	//clone the list and add to the result
			return;
		}
		int n = nums.length;
		for(int i=0; i<n; i++) {				//we can try all the unused numbers at this position in the 'path' list
			if(!used[i]) {						//use this only if it is not used before
				used[i] = true;
				path.add(nums[i]);
				dfs(result, used, path, nums);
				path.remove(path.size()-1);		//backtrack
				used[i] = false;				//backtrack
			}
		}
	}
}
