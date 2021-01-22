/*
Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,1,2]
Output:
[
	[1, 1, 2],
	[1, 2, 1],
	[2, 1, 1]
]

 * */

/*
Solution: 
	The logic is similar to what we used when there were distinct items.
	The only difference is once we use a number at a position, then we should not use other similar numbers at this position.
	So we have to skip the similar numbers once we have used it once at current position.
	So sort the array before calling dfs 

 * */
package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsDuplicates {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums == null || nums.length == 0) {
			return result;
		}
		boolean[] used = new boolean[nums.length];
		ArrayList<Integer> path = new ArrayList<>();
			
		Arrays.sort(nums);
		dfs(nums, path, used, result);
		return result;
	}
	
	private void dfs(int[] nums, ArrayList<Integer> path, boolean[] used, List<List<Integer>> result) {
		if(path.size() == nums.length) {
			result.add(new ArrayList<Integer>(path));  	//clone the list and add to the result
			return;
		}
		int i=0;													
		while(i < nums.length) {

			if(!used[i]) {
				path.add(nums[i]);
				used[i] = true;
				dfs(nums, path, used, result);
				path.remove(path.size()-1);
				used[i] = false;
				while(i+1 < nums.length && nums[i] == nums[i+1]) {			//skip the next numbers which are similar nums[i]
					i++;
				}
				
				
			}
			i++;
		
		}
		
	}
	
	

	public static void main(String[] args) {
		int[] nums = {1,1,2};
		PermutationsDuplicates ob = new PermutationsDuplicates();
		List<List<Integer>> result = ob.permute(nums);
		for(List<Integer> perm : result ) {
			System.out.println(perm);
		}
 
	}

}
