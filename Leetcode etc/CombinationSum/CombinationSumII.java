//https://leetcode.com/problems/combination-sum-ii/
/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
 * */

//Solution: Here note that we can use a number only once. So we need sorting here
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		Arrays.sort(candidates);							//sorting is necessary
		utl(candidates, 0, target, current, result);
		return result; 
	}
	
	private void utl(int[] candidates, int i, int sum, ArrayList<Integer> current, List<List<Integer>> result) {
		if(sum < 0) {
			return;
		}
		if(sum == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		
		for(int pos = i; pos < candidates.length; pos++) {
			/* We do not want duplicate combinations in our list,
            so we will make sure that we do not start a combination with
            a number that has already been use. It is enough to just check
            for the previous number as the array is sorted. This can also be
            achieved by letting the combination form now and discarding it
            when adding to the list by checking if it already exists. Since
            that will be an expensive operation, it is better to check here
            and eliminate the possibility of forming a duplicate combination.*/
			if(pos > i && candidates[pos] == candidates[pos-1]) continue;
			
			current.add(candidates[pos]);
			utl(candidates, pos+1, sum-candidates[pos], current, result);
			current.remove(current.size()-1);
			
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
